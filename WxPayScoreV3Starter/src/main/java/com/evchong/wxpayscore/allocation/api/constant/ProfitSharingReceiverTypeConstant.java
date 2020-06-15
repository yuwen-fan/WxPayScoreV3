package com.evchong.wxpayscore.allocation.api.constant;

/**
 * 分账接收方类型常量
 * 
 * @see {@link com.evchong.wxpayscore.allocation.api.model.ProfitSharingReceiver#getType()}
 * @author fanyuwen
 *
 */
public final class ProfitSharingReceiverTypeConstant {

	/** 商户ID */
	public static final String MERCHANT = "MERCHANT_ID";
	/** 个人openid */
	public static final String PERSONAL = "PERSONAL_OPENID";

	private ProfitSharingReceiverTypeConstant() {
		throw new UnsupportedOperationException();
	}
}
