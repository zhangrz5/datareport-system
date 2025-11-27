package com.company.datareport.util.sm4;


import com.company.datareport.util.rsa.RsaUtil;
import com.company.datareport.util.sm2.SM2Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author wangrsh
 * @version V1.0
 * @Title: SM4FileUtils.java
 * @Package com.zefu.core.sm4
 * @date 2018年8月3日
 */
public class SM4FileUtils {
    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static boolean getFile(byte[] bfile, String fileNamePath) {
        boolean res = false;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(fileNamePath);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * 对文件SM4加密
     *
     * @param key      密钥
     * @param filePath
     * @return 并返回加密时的密钥；如果返回为空，说明加密失败
     */
    public static String encrypt(String key, String filePath) {
        String secretKey = "";
        try {
            SM4Utils sm4 = new SM4Utils();
            sm4.setSecretKey(key);
            byte[] fileByte = getBytes(filePath);
            byte[] encryptByte = sm4.encryptData_ECB(fileByte);
            boolean res = getFile(encryptByte, filePath);

            if (res) {
                secretKey = key;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretKey;
    }

    public static boolean decrypt(String filePath, String secretKey) {
        boolean res = false;
        try {
            SM4Utils sm4 = new SM4Utils();
            sm4.setSecretKey(secretKey);
            byte[] encryptByte = getBytes(filePath);
            byte[] decryptByte = sm4.decryptData_ECB(encryptByte);
            res = getFile(decryptByte, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static String decrptTempFile(String sourceFilePath) throws IOException {
        byte[] fileByte = SM4FileUtils.getBytes(sourceFilePath);
        SM4Utils sm4Utils = new SM4Utils();
        String sm4Key = "P2bCFibKPLzBzBsO98IQKg/zKvqEyT8CFgUT392Xfmc9uNOdb/qCckHh5UF90hCcH53zUCTg36v44b8+NWytCg==";
        sm4Utils.setSecretKey(RsaUtil.priDecode(sm4Key));
        byte[] fileByteBySecret = sm4Utils.decryptData_ECB(fileByte);
        saveBinaryFile(sourceFilePath, fileByteBySecret);
        return sourceFilePath;
    }

    public static void saveBinaryFile(String filePath, byte[] fileByte) throws IOException {
        File zipfile = new File(filePath);
        FileOutputStream fileOut = new FileOutputStream(zipfile);
        fileOut.write(fileByte);
        fileOut.close();
    }

    public static List<String> encrptReportZip(String zipFilePath) throws IOException {
        List<String> filePathList = new ArrayList<>();
        //随机SM4秘钥
        String sm4SecretKey = UUID.randomUUID().toString().replace("-", "");
        System.out.println(sm4SecretKey);
        //上报默认SM2加密秘钥
        String sm2SecretKey = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";
        byte[] fileByte = SM4FileUtils.getBytes(zipFilePath);
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setSecretKey(sm4SecretKey);
        byte[] fileByteBySecret = sm4Utils.encryptData_ECB(fileByte);
        SM4FileUtils.saveBinaryFile(zipFilePath, fileByteBySecret);
        String keyFile = zipFilePath.replace(".zip", ".key");
        SM4FileUtils.saveBinaryFile(keyFile, sm4SecretKey.getBytes());
        SM4FileUtils.saveBinaryFile(keyFile, SM2Utils.encrypt(sm2SecretKey, SM4FileUtils.getBytes(keyFile)));
        filePathList.add(zipFilePath);
        filePathList.add(keyFile);
        return filePathList;
    }

}
