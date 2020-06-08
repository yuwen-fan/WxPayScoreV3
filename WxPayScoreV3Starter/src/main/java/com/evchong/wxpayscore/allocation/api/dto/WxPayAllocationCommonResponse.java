package com.evchong.wxpayscore.allocation.api.dto;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * 微信支付分普通直连分账 - 公共响应体
 */
@Data
public class WxPayAllocationCommonResponse implements WxPayAllocationResponse {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 * 
	 * @see <a href=
	 *      "https://wechatpay-api.gitbook.io/wechatpay-api-v3/wei-xin-zhi-fu-api-v3-jie-kou-gui-fan#http-zhuang-tai-ma">公共错误码</a>
	 * @see <a href=
	 *      "https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter1_1.shtml">其他错误见各API</a>
	 */
	private String code;
	/**
	 * 错误描述
	 * <p>
	 * 使用易理解的文字表示错误的原因。
	 */
	private String message;

	public boolean hasError() {
		return !StringUtils.isEmpty(code);
	}
}
