/**
 * @Package: com.inspur.dmp.system.api.route.util
 * @author: zhuanghuan
 * @date: 2019年5月22日 上午8:45:23
 */

package com.company.datareport.util.rsa;

import com.company.datareport.util.RsaConstant;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密算法工具类
 *
 * @ClassName: RsaUtil
 * @Description: TODO
 * @author: zhuanghuan
 * @date: 2019年5月22日 上午8:45:23
 */
public class RsaUtil {
    /**
     * 生成秘钥，每次随机成一对公钥和私钥
     *
     * @return void
     * @author zhuanghuan
     * @date 2019年5月22日上午9:01:32
     */
    public static void initKey() {
        // 1、初始化密钥
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 64的整倍数
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            String rsaPublicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
            String rsaPrivateKey = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
            System.out.println("Public Key : " + rsaPublicKey);
            System.out.println("Private Key : " + rsaPrivateKey);
            System.out.println();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用公钥加密
     *
     * @param src 需要加密的字符串
     * @return String
     * @author zhuanghuan
     * @date 2019年5月22日上午9:06:14
     */
    public static String pubEncode(String src) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                    Base64.decodeBase64(RsaConstant.RSA_PUBLIC_KEY));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            return Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 私钥解密，返回解密后的字符串
     *
     * @param src 需要解密的字符串
     * @return String
     * @author zhuanghuan
     * @date 2019年5月22日上午9:06:45
     */
    public static String priDecode(String src) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(RsaConstant.RSA_PRIVATE_KEY));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] result = Base64.decodeBase64(src);
            result = cipher.doFinal(result);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 私钥加密
     *
     * @param src 需要加密的字符串
     * @return String
     * @author zhuanghuan
     * @date 2019年5月22日上午9:07:27
     */
    public static String priEncode(String src) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(RsaConstant.RSA_PRIVATE_KEY));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());

            return Base64.encodeBase64String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return "";

    }

    /**
     * 私钥加密，公钥解密
     *
     * @author jijs
     */
    public static String pubDecode(String src) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                    Base64.decodeBase64(RsaConstant.RSA_PUBLIC_KEY));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] result = Base64.decodeBase64(src);
            result = cipher.doFinal(result);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String passwdStr = "P2bCFibKPLzBzBsO98IQKg/zKvqEyT8CFgUT392Xfmc9uNOdb/qCckHh5UF90hCcH53zUCTg36v44b8+NWytCg==";
        System.out.println(RsaUtil.priDecode(passwdStr));
    }

}
