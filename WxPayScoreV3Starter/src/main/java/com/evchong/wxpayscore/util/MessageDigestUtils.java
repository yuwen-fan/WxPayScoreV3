package com.evchong.wxpayscore.util;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * 消息摘要签名工具类
 * 
 * @author fanyuwen
 *
 */
public final class MessageDigestUtils {
	private MessageDigestUtils() {
		throw new UnsupportedOperationException();
	}

	public static String hmac(HmacAlgorithms algorithms, String key, String valueToDigest) {
		return new HmacUtils(algorithms, key).hmacHex(valueToDigest);
	}
}
