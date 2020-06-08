package com.evchong.wxpayscore.allocation.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.evchong.wxpayscore.exception.SignException;
import com.evchong.wxpayscore.util.WxPayScoreSignUtil;

/**
 * 
 * 普通直连分账数据签名工具类
 * 
 * https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=4_3
 * 
 * @author fanyuwen
 *
 */
public final class WxPayAllocationSignUtil {
	private static final Log log = LogFactory.getLog(WxPayAllocationSignUtil.class);
	
	private WxPayAllocationSignUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 签名
	 * <p>
	 * 签名方法同{@link com.evchong.wxpayscore.util.WxPayScoreSignUtil#hmacSha256(Map, String)}
	 * 
	 * @throws SignException
	 */
	public static String hmacSha256(Map<String, String> map, String apiKey) {
		return WxPayScoreSignUtil.hmacSha256(map, apiKey);
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
