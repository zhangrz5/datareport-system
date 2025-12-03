package com.company.datareport.util.sm2;

import org.bouncycastle.asn1.*;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Base64;

import java.io.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class SM2Utils {
    public static byte[] encrypt(String secretkey, byte[] data) {
        String pubkS = new String(Base64.encode(Util.hexToByte(secretkey)));
        byte[] publicKey = Base64.decode(pubkS.getBytes());

        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        byte[] formatedPubKey;
        if (publicKey.length == 64) {
            // 添加一字节标识，用于ECPoint解析
            formatedPubKey = new byte[65];
            formatedPubKey[0] = 0x04;
            System.arraycopy(publicKey, 0, formatedPubKey, 1, publicKey.length);
        } else
            formatedPubKey = publicKey;

        Cipher cipher = new Cipher();
        SM2 sm2 = SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(formatedPubKey).normalize();

        ECPoint c1 = cipher.Init_enc(sm2, userKey).normalize();
        cipher.Encrypt(source);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);

        // 修改：使用 getAffineXCoord() 和 getAffineYCoord() 替代 getX() 和 getY()
        ASN1Integer x = new ASN1Integer(c1.getAffineXCoord().toBigInteger());
        ASN1Integer y = new ASN1Integer(c1.getAffineYCoord().toBigInteger());
        DEROctetString derDig = new DEROctetString(c3);
        DEROctetString derEnc = new DEROctetString(source);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(x);
        v.add(y);
        v.add(derDig);
        v.add(derEnc);
        DERSequence seq = new DERSequence(v);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 修改：使用 ASN1OutputStream 替代 DEROutputStream
        ASN1OutputStream dos = ASN1OutputStream.create(bos);
        try {
            dos.writeObject(seq);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }

        byte[] enc = new byte[encryptedData.length];
        System.arraycopy(encryptedData, 0, enc, 0, encryptedData.length);

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(1, privateKey);

        ByteArrayInputStream bis = new ByteArrayInputStream(enc);
        ASN1InputStream dis = new ASN1InputStream(bis);
        try {
            // 修改：使用 ASN1Primitive 替代 DERObject
            ASN1Primitive derObj = dis.readObject();
            ASN1Sequence asn1 = (ASN1Sequence) derObj;
            // 修改：使用 ASN1Integer 替代 DERInteger
            ASN1Integer x = (ASN1Integer) asn1.getObjectAt(0);
            ASN1Integer y = (ASN1Integer) asn1.getObjectAt(1);
            ECPoint c1 = sm2.ecc_curve.createPoint(x.getValue(), y.getValue());

            Cipher cipher = new Cipher();
            cipher.Init_dec(userD, c1);
            DEROctetString data = (DEROctetString) asn1.getObjectAt(3);
            enc = data.getOctets();
            cipher.Decrypt(enc);
            byte[] c3 = new byte[32];
            cipher.Dofinal(c3);
            return enc;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用默认ID计算
     *
     * @param privateKey
     * @param sourceData
     * @return
     */
    public static byte[] sign(byte[] privateKey, byte[] sourceData) {
        String userId = "1234567812345678";
        return sign(userId.getBytes(), privateKey, sourceData);
    }

    public static byte[] sign(byte[] userId, byte[] privateKey, byte[] sourceData) {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (sourceData == null || sourceData.length == 0) {
            return null;
        }

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(privateKey);
        System.out.println("userD: " + userD.toString(16));
        System.out.println("");

        ECPoint userKey = sm2.ecc_point_g.multiply(userD);
        // 修改：使用 getAffineXCoord() 和 getAffineYCoord() 替代 getX() 和 getY()
        System.out.println("椭圆曲线点X: " + userKey.getAffineXCoord().toBigInteger().toString(16));
        System.out.println("椭圆曲线点Y: " + userKey.getAffineYCoord().toBigInteger().toString(16));
        System.out.println("");

        SM3Digest sm3 = new SM3Digest();
        byte[] z = sm2.sm2GetZ(userId, userKey);
        System.out.println("SM3摘要Z: " + ByteUtil.getHexString(z));
        System.out.println("");

        System.out.println("M: " + ByteUtil.getHexString(sourceData));
        System.out.println("");

        sm3.update(z, 0, z.length);
        sm3.update(sourceData, 0, sourceData.length);
        byte[] md = new byte[32];
        sm3.doFinal(md, 0);

        System.out.println("SM3摘要值: " + ByteUtil.getHexString(md));
        System.out.println("");

        SM2Result sm2Result = new SM2Result();
        sm2.sm2Sign(md, userD, userKey, sm2Result);
        System.out.println("r: " + sm2Result.r.toString(16));
        System.out.println("s: " + sm2Result.s.toString(16));
        System.out.println("");

        // 修改：使用 ASN1Integer 替代 DERInteger，ASN1Primitive 替代 DERObject
        ASN1Integer d_r = new ASN1Integer(sm2Result.r);
        ASN1Integer d_s = new ASN1Integer(sm2Result.s);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(d_r);
        v2.add(d_s);
        DERSequence sign = new DERSequence(v2);
        try {
            return sign.getEncoded();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认id计算
     *
     * @param publicKey
     * @param sourceData
     * @param signData
     * @return
     */
    public static boolean verifySign(byte[] publicKey, byte[] sourceData, byte[] signData) {
        String userId = "1234567812345678";
        return verifySign(userId.getBytes(), publicKey, sourceData, signData);
    }

    @SuppressWarnings("unchecked")
    public static boolean verifySign(byte[] userId, byte[] publicKey, byte[] sourceData, byte[] signData) {
        if (publicKey == null || publicKey.length == 0) {
            return false;
        }

        if (sourceData == null || sourceData.length == 0) {
            return false;
        }

        byte[] formatedPubKey;
        if (publicKey.length == 64) {
            // 添加一字节标识，用于ECPoint解析
            formatedPubKey = new byte[65];
            formatedPubKey[0] = 0x04;
            System.arraycopy(publicKey, 0, formatedPubKey, 1, publicKey.length);
        } else
            formatedPubKey = publicKey;

        SM2 sm2 = SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(formatedPubKey).normalize();

        SM3Digest sm3 = new SM3Digest();
        byte[] z = sm2.sm2GetZ(userId, userKey);
        sm3.update(z, 0, z.length);
        sm3.update(sourceData, 0, sourceData.length);
        byte[] md = new byte[32];
        sm3.doFinal(md, 0);
        System.out.println("SM3摘要值: " + ByteUtil.getHexString(md));
        System.out.println("");

        ByteArrayInputStream bis = new ByteArrayInputStream(signData);
        ASN1InputStream dis = new ASN1InputStream(bis);
        SM2Result sm2Result = null;
        try {
            // 修改：使用 ASN1Primitive 替代 DERObject，ASN1Integer 替代 DERInteger
            ASN1Primitive derObj = dis.readObject();
            Enumeration<ASN1Encodable> e = ((ASN1Sequence) derObj).getObjects();
            BigInteger r = ((ASN1Integer) e.nextElement()).getValue();
            BigInteger s = ((ASN1Integer) e.nextElement()).getValue();
            sm2Result = new SM2Result();
            sm2Result.r = r;
            sm2Result.s = s;
            System.out.println("r: " + sm2Result.r.toString(16));
            System.out.println("s: " + sm2Result.s.toString(16));
            System.out.println("");
            sm2.sm2Verify(md, userKey, sm2Result.r, sm2Result.s, sm2Result);
            return sm2Result.r.equals(sm2Result.R);
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Sm2KeyPair generateKeyPair() {
        SM2 sm2 = SM2.Instance();
        AsymmetricCipherKeyPair keypair = sm2.ecc_key_pair_generator.generateKeyPair();
        ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) keypair.getPrivate();
        ECPublicKeyParameters ecpub = (ECPublicKeyParameters) keypair.getPublic();
        byte[] priKey = new byte[32];
        byte[] pubKey = new byte[64];
        byte[] bigNumArray = ecpriv.getD().toByteArray();
        System.arraycopy(bigNumArray, bigNumArray[0] == 0 ? 1 : 0, priKey, 0, 32);
        // 修改：getEncoded() 方法需要传入 boolean 参数，false 表示非压缩格式
        System.arraycopy(ecpub.getQ().getEncoded(false), 1, pubKey, 0, 64);
        return new Sm2KeyPair(priKey, pubKey);
    }

    public static void Sm2Test() {
        String plainText = "Hello SM !";
        byte[] sourceData = plainText.getBytes();
        Sm2KeyPair keyPair = generateKeyPair();

        System.out.println("私钥: " + ByteUtil.getHexString(keyPair.getPriKey()));
        System.out.println("公钥: " + ByteUtil.getHexString(keyPair.getPubKey()));

        byte[] c = SM2Utils.sign(keyPair.getPriKey(), sourceData);
        System.out.println("sign: " + ByteUtil.getHexString(c));


        boolean vs = SM2Utils.verifySign(keyPair.getPubKey(), sourceData, c);
        System.out.println("验签结果: " + vs);

        System.out.println("加密: ");
        byte[] cipherText = SM2Utils.encrypt(ByteUtil.getHexString(keyPair.getPubKey()), sourceData);
        System.out.println(ByteUtil.getHexString(cipherText));

        System.out.println("解密: ");
        plainText = new String(SM2Utils.decrypt(keyPair.getPriKey(), cipherText));
        System.out.println(plainText);
    }


    public static void main(String[] args) throws IOException {

        String path="D:/upload/DMP/0026/1002/20201021170338_e10f84dcdf934e94b27193b026b7a858";
        String dbName="114100007616819041_0026_1002_20201021170338_e10f84dcdf934e94b27193b026b7a858.db";
        String keyName="digest.key";
        byte[] sourceData = getFileByte(path+"/"+dbName);
        String publicKey="090AC33251E19F578C7C3FE0196F67046F86DFCB74BF84E0550DEB6D91068FF14F6C12BD269E60232ACB4E5ED7CA42E7D37C4759E5D8EEA82C91BAB5E864EA6E";
        String privateKey="52035DE3DA1C4B14E55FECD02A974AB7F7D3810442809D967368459BADD5A5BD";
        byte[] privateKeyByte = ByteUtil.hexStringToBytes(privateKey);
        byte[] publicKeyByte =ByteUtil.hexStringToBytes(publicKey);
        byte[] c = SM2Utils.sign(privateKeyByte, sourceData);
        System.out.println("sign: " + ByteUtil.getHexString(c));

        boolean vs = SM2Utils.verifySign(publicKeyByte, sourceData, c);
        System.out.println("验签结果: " + vs);
    }

    public static byte[] getFileByte(String dbFileAbsolutePath) throws IOException {
        byte[] dbFileByte = readByteFromFile(dbFileAbsolutePath);
        int finalNum = dbFileByte.length > 10 ? 10 : dbFileByte.length;
        byte[] simpleDbFileByte = new byte[finalNum];
        for (int i = 0; i < finalNum; i++) {
            simpleDbFileByte[i] = dbFileByte[i];
        }
        return simpleDbFileByte;
    }
    public static byte[] readByteFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        byte[] content = out.toByteArray();
        return content;
    }
}