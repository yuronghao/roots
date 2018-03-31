package com.emi.roots.util;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignUtils {
	
	
	public static final String PUBlICKEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzXV839NFPdR1+WM3wfGm+1hm3EQdcTWZQV3ID"+
			"JNkCYx4CmirPPRGI5gaz3Pqkqou/L8juqTXAb3u47CbvKt+TSq59zeN2ES7bsReDQoBBkHJG4r6G"+
			"pj12SeNDbj0+6SckHpjd3zEh4kWbvUwrW4FypbzAfUIgxMKTRy31FyEq4QIDAQAB";

	public static final String PRIVATEKEY="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALNdXzf00U91HX5YzfB8ab7WGbcR"+
					"B1xNZlBXcgMk2QJjHgKaKs89EYjmBrPc+qSqi78vyO6pNcBve7jsJu8q35NKrn3N43YRLtuxF4NC"+
					"gEGQckbivoamPXZJ40NuPT7pJyQemN3fMSHiRZu9TCtbgXKlvMB9QiDEwpNHLfUXISrhAgMBAAEC"+
					"gYBTN3Dj/zvVR5rlccIqXy6YkbDglI/7rRVZFRkaiE/+oYnc3zYZbFXkVASsEkxfdZjcnQj2YZ9E"+
					"pvSZ+UafFB8h6l/sacS56E3Fi7wlDTrn+L6AKwXySFMVhl6uZmKyOa/z8etr7eU8RgqRbMbhNT07"+
					"WusaR4W6Z8u3ddn6ujk1GQJBANZYZcXRDr46+l7yhs86hi8Wltc/fw5fGmlT5j4x9bM0lsWqqIh0"+
					"oD0CP8QwWmkNZdJsTz0lHx/fSZdockF+eVcCQQDWOLQc4AIDZGGepUqA2EUOoA/yHoP+8bDq6gR9"+
					"I79hz51hBLkbp/YxXx00IEMDEcpyGZ8/rOlISlSirPjbF4KHAkAapUSJkdKGXWiQcw/FJR5XLjS0"+
					"L2WJENOEsnRV2PoVO/1eNzfaNr1QuD3bErO6iXrV7Lbw//Ndj/FjDA3gDGvjAkBXp1wLdJ1eRfSr"+
					"tXaGwfd/xH7B0zqvVrXNaXPswHPO0eTTjdswNcnG6eRJL+o7l0v+4/0FUPsjWUbISX6KJ1SBAkBV"+
					"jMB0HgEAzwlW+s9ShwuJj/hJKoSk//2aPBzA+uS6H5S7Nl26lxGqNbz5kuqGSuj5STTrydZQHe+0"+
					"WknRq6ap";	
	

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	private static String getAlgorithms(boolean rsa2) {
		return rsa2 ? SIGN_SHA256RSA_ALGORITHMS : SIGN_ALGORITHMS;
	}
	
	public static String sign(String content, String privateKey, boolean rsa2) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(getAlgorithms(rsa2));

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();


			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean verify(String content, String sign, String publicKey, boolean rsa2) {
		try {
//			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(publicKey));
			// 构造X509EncodedKeySpec对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PublicKey pubKey = keyf.generatePublic(keySpec);
			java.security.Signature signature = java.security.Signature
					.getInstance(getAlgorithms(rsa2));

			signature.initVerify(pubKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
			boolean isVerify = signature.verify(Base64.decode(sign));
//			return Base64.encode(signed);
			return isVerify;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


	public static void main(String[] str){
		String s=sign("activityid=1&memberid=51",PRIVATEKEY,true);

//		String s=sign("memberid=68&newsid=40",PRIVATEKEY,true);

//		String s=sign("memberid=0",PRIVATEKEY,true);
		try {
			System.out.println(URLEncoder.encode(s,"UTF-8"));
		}catch (Exception e){

		}

	}

}
