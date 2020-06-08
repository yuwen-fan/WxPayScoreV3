package com.evchong.wxpayscore.util;

import java.security.SecureRandom;

/**
 * 随机字符串
 * 
 * @author fanyuwen
 *
 */
public final class NonceStringUtil {

	private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final SecureRandom RANDOM = new SecureRandom();

	private NonceStringUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length 串长
	 * @return
	 */
	public static String generateNonceStr(int length) {
		char[] nonceChars = new char[length];
		for (int index = 0; index < nonceChars.length; ++index) {
			nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
		}
		return new String(nonceChars);
	}
}
