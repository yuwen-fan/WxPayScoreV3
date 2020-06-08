package com.evchong.wxpayscore.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.evchong.wxpayscore.exception.DecryptException;

/**
 * AEAD_AES_256_GCM
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
 */
public final class WxPayScoreAeadAes256GcmUtil {
	private static final int TAG_LENGTH_BIT = 128;

	private WxPayScoreAeadAes256GcmUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 解密
	 * 
	 * @param secretkey  必须长为32. 如微信支付分的apiV3Key
	 * @param aad        不得为NULL, 可为EMPTY. 如微信支付分的associated_data
	 * @param gcmIv      不得为EMPTY. 如微信支付分的nonce
	 * @param ciphertext
	 */
	public static String decrypt(byte[] secretkey, byte[] aad, byte[] gcmIv, String ciphertext) {
		try {
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

			SecretKeySpec key = new SecretKeySpec(secretkey, "AES");
			GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, gcmIv);

			cipher.init(Cipher.DECRYPT_MODE, key, spec);
			cipher.updateAAD(aad);

			return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "utf-8");
		} catch (Exception e) {
			throw new DecryptException(e);
		}
	}
}
