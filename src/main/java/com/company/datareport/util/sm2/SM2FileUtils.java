package com.company.datareport.util.sm2;


/**
 * @Title: SM4FileUtils.java
 * @Package com.zefu.core.sm4
 * @author wangrsh
 * @date 2018年8月3日
 * @version V1.0
 */
public class SM2FileUtils {
	
	public static boolean encrypt(String filePath,String publicKey){
		boolean res = false;
//		try {
//			String pubkS = new String(Base64.encode(Util.hexToByte(publicKey)));
//			byte[] fileByte = Util.getBytes(filePath);
//			byte[] encryptByte = SM2Utils.encrypt(Base64.decode(pubkS.getBytes()), fileByte);
//			res = Util.getFile(encryptByte, filePath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return res;
	}
	
	public static boolean decrypt(String filePath,String privateKey){
		boolean res = false;
//		try {
//			String prikS = new String(Base64.encode(Util.hexToByte(privateKey)));
//			byte[] encryptByte = Util.getBytes(filePath);
//			byte[] decryptByte = SM2Utils.decrypt(Base64.decode(prikS.getBytes()), encryptByte);
//			res = Util.getFile(decryptByte, filePath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return res;
	}

	public static void main(String[] args) {
		
		
//		String pubk="0D4368D201AF57EE4472A93052394D2A5C62DEC6295C2023CB34E1A0D1AFB88A36FA9C6F53865338346C94CBA6CE46CC5D00E73784A146261ED1935065483550";
		String pubk="2591F78F3415C53A4159D954711118F32653D7FAC89F2289DABDC7921AB695D25FB3A05A1B91DC2E4DD6A1E3B01438D5986675B7ED3B208EAD31239D25ED5F9A";
		byte[] encryptByte = SM2Utils.encrypt(pubk, "AA".getBytes());
		System.out.println("hello");
//		encrypt("C:\\Users\\admin\\Desktop\\TestSM2 - 副本.zip","022496285EC5BF2A7A56E48FFA1DEEC5A7C84793D9FF369C6E707C6CDA60D697AB962BE578EA4C527580FF52F8BB06A67A0944C865B429C6B76DEB8FEF915CAB");
//		System.out.println("加密成功");
//		decrypt("C:\\Users\\admin\\Desktop\\TestSM2 - 副本.zip","3D6AA4962B48F6EF13F4B54B956A3C137A8DD8423839309F5AFB2999D69A298F");
//		System.out.println("解密成功");
	}
}
