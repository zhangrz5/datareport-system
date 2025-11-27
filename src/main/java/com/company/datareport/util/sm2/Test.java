package com.company.datareport.util.sm2;

import org.bouncycastle.util.encoders.Base64;

/**
 * @Title: Test.java
 * @Package com.zefu.test.sm2
 * @author wangrsh
 * @date 2018年8月7日
 * @version V1.0
 */
public class Test {

	public static void main(String[] args) {
		String plainText = "哈哈哈哈哈你好";
		byte[] sourceData = plainText.getBytes();
		String pubKey = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";
		byte[] secretKeyByte = SM2Utils.encrypt(pubKey, sourceData);
		String priKey = "3690655E33D5EA3D9A4AE1A1ADD766FDEA045CDEAA43A9206FB8C430CEFE0D94";
		String prikS = new String(Base64.encode(ByteUtil.hexToByte(priKey)));
		plainText = new String(SM2Utils.decrypt(Base64.decode(prikS.getBytes()), secretKeyByte));
		System.out.println(plainText);
	}
}
