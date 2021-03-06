package com.evchong.wxpayscore.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.evchong.wxpayscore.exception.SignException;

/**
 * 
 * 微信支付方数据签名工具类
 * 
 * https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter8_1.shtml
 * 
 * <p>
 * 目前签名方式：HMAC_SHA_256
 * 
 * @author fanyuwen
 *
 */
public final class WxPayScoreSignUtil {
	private static final Log log = LogFactory.getLog(WxPayScoreSignUtil.class);

	private WxPayScoreSignUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 签名
	 * <ol>
	 * 微信支付分可使用该签名算法的地方
	 * <li><a href=
	 * "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter8_1.shtml">用户授权请求</a>
	 * </ol>
	 * @param <K>
	 * @param <V>
	 * 
	 * @throws SignException
	 */
	public static <K, V> String hmacSha256(Map<K, V> map, String apiKey) {
		if (CollectionUtils.isEmpty(map)) {
			throw new SignException("Blank map");
		}
		try {
			String valueToDigest = sortedStr(map, apiKey);
			return MessageDigestUtils.hmac(HmacAlgorithms.HMAC_SHA_256, apiKey, valueToDigest).toUpperCase();
		} catch (Exception e) {
			throw new SignException(e);
		}
	}

	/**
	 * 按字母序排序后拼接成字符串
	 */
	private static <K, V> String sortedStr(Map<K, V> map, String apiKey) {
		Map<K, V> tmap = map;
		if (!(tmap instanceof TreeMap)) {
			tmap = new TreeMap<>();
			tmap.putAll(map);
		}

		StringBuilder sb = new StringBuilder();
		tmap.entrySet().forEach(entry -> sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
		sb.append("key=").append(apiKey);
		return sb.toString();
	}

	public static void main(String[] args) {
		Map<String, String> wechatProvidedSampleData = new HashMap<>();
		wechatProvidedSampleData.put("appid", "wxd930ea5d5a258f4f");
		wechatProvidedSampleData.put("mch_id", "10000100");
		wechatProvidedSampleData.put("device_info", "1000");
		wechatProvidedSampleData.put("body", "test");
		wechatProvidedSampleData.put("nonce_str", "ibuaiVcKdpRxkhJA");
		String sha256 = hmacSha256(wechatProvidedSampleData, "192006250b4c09247ec02edce69f6a2d");
		log.info(sha256);
		log.info("6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6".equals(sha256));
	}
}
