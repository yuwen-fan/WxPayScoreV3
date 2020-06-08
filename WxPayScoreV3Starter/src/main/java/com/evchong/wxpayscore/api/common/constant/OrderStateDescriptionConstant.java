package com.evchong.wxpayscore.api.common.constant;

/**
 * 订单状态常量
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * 
 * @author fanyuwen
 *
 */
public final class OrderStateDescriptionConstant {

	/** 用户确认 */
	public static final String USER_CONFIRM = "USER_CONFIRM";
	/** 商户完结 */
	public static final String MCH_COMPLETE = "MCH_COMPLETE";

	private OrderStateDescriptionConstant() {
		throw new UnsupportedOperationException();
	}

	public static final String toDescription(String code) {
		if (USER_CONFIRM.equals(code)) {
			return "用户确认";
		}
		if (MCH_COMPLETE.equals(code)) {
			return "商户完结";
		}
		return null;
	}
}
