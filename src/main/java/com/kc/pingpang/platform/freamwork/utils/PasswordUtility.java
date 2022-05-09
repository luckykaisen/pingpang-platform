package com.kc.pingpang.platform.freamwork.utils;


import org.apache.commons.lang.StringUtils;

public class PasswordUtility {
	
	public static String createPassword() {
        return RandomGenerator.getSixDigits();
    }
	
	public static String createSalt() {
        return RandomGenerator.getGlobalUniqueId();
    }

	public static String encrypt(String password, String salt) {
        String encryptedPwd = MD5Utility.digest(MD5Utility.digest(password) + salt);

        return encryptedPwd;
    }
	
    public static boolean isEquals(String password, String salt, String encryptedPwd) {
        return StringUtils.equals(encrypt(password, salt), encryptedPwd);
    }

    public static String encodeBase64(String password) throws Exception {
		byte[] passEn = Base64Utility.encode(password.getBytes("utf-8"));
		return new String(passEn, "utf-8");
	}
	
    public static String decodeBase64(String password) throws Exception {
		byte[] passEn = Base64Utility.decode(password.getBytes("utf-8"));
		return new String(passEn, "utf-8");
	}
}
