package com.evchong.wxpayscore.api.autoconfirm.constant;

/**
 * 用户授权服务情况
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_8.shtml
 * @author fanyuwen
 *
 */
public class UseServiceStateConstant {

	/** 用户未授权服务 */
	public static final String UNAVAILABLE = "UNAVAILABLE";
	/** 用户已授权服务 */
	public static final String AVAILABLE = "AVAILABLE";

	private UseServiceStateConstant() {
		throw new UnsupportedOperationException();
	}

	public static final String toDescription(String code) {
		if (UNAVAILABLE.equals(code)) {
			return "用户未授权服务";
		}
		if (AVAILABLE.equals(code)) {
			return "用户已授权服务";
		}
		return null;
	}
}