package com.emi.roots.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;


/**
 * 对密码进行加密和验证的类
 */
public class PasswordUtil {

	// 十六进制下数字到字符的映射数组
//	private final static String[] hexDigits = { "9", "8", "7", "6", "5", "4", "3", "2", "1", "a", "b", "c",
//			"o", "x", "y", "z" };
	
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9", "A","B","C","D","E","F" };

	/** * 把inputString加密 */
	public static String generatePassword(String inputString) {
		return encodeByMD5(inputString);
	}
	
	
	public static String PASSWORD = "api.JHuan.com)!^";  
	public static byte[] AESKEYS = new byte[]{0x41, 0x72, 0x65, 0x79, 0x6F, 0x75, 0x6D, 0x79, 0x53, 0x6E, 0x6F, 0x77, 0x6D, 0x61, 0x6E, 0x3F };

	/**
	 * 验证输入的密码是否正确
	 * 
	 * @param password
	 *            加密后的密码
	 * @param inputString
	 *            输入的字符串
	 * @return 验证结果，TRUE:正确 FALSE:错误
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/** 对字符串进行MD5加密 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
//				System.out.println("aaa="+new String(results));
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) {


		try {
			String s="qazwsx";
			System.out.println(generatePassword(s));
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//加密  
//		System.out.println("加密前：" + content);  
//		byte[] encryptResult = encrypt(content, password);  
//		String encryptResultStr = parseByte2HexStr(encryptResult);  
//		System.out.println("加密后：" + encryptResultStr);  
//		//解密  
//		byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
//		byte[] decryptResult = decrypt(decryptFrom,password);  
//		System.out.println("解密后：" + new String(decryptResult)); 
	}
	
	


//加密
public static String encrypt(String encData ,String secretKey,String vector) throws Exception {
        
        if(secretKey == null) {
            return null;
        }
        if(secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

//解密	
public static String decrypt(String sSrc,String key,String ivs) throws Exception {
    try {
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    } catch (Exception ex) {
        return null;
    }
}

	
	
	

}
