package com.evchong.wxpayscore.api.dto;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * 微信支付分 - 公共响应体
 * <p>
 * 如发生错误时的响应体，这些发生错误时才有的字段在正常响应中是非必然出现的
 * 
 * <p>
 * 如：{ "code": "PARAM_ERROR", "message": "参数错误", "detail": { "field":
 * "/amount/currency", "value": "XYZ", "issue": "Currency code is invalid",
 * "location" :"body" } }
 * 
 * @see https://wechatpay-api.gitbook.io/wechatpay-api-v3/wei-xin-zhi-fu-api-v3-jie-kou-gui-fan#http-zhuang-tai-ma
 * @author fanyuwen
 *
 */
@Data
public class WxPayScoreCommonResponse implements WxPayScoreResponse {

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

	/**
	 * 错误详情
	 */
	private WxPayScoreResponseErrorDetail detail;

	public boolean hasError() {
		return !StringUtils.isEmpty(code);
	}
}
