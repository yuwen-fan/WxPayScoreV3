package com.evchong.wxpayscore.configuration;

import lombok.Data;

/**
 * 商户回调地址
 * 
 * @author fanyuwen
 *
 */
@Data
public class MerchantCallbackUrl {

	/**
	 * 订单回调通知
	 * <li><a href=
	 * "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml">确认订单回调通知</a>
	 * <li><a href=
	 * "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_3.shtml">支付成功回调通知</a>
	 * 
	 */
	private String serviceOrderNotify;
}
