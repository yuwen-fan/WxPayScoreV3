package com.evchong.wxpayscore.api.dto;

import org.springframework.http.HttpMethod;

/**
 * 微信支付分请求
 * 
 * @author fanyuwen
 *
 */
public interface WxPayScoreRequest extends WxPayScoreExtraParamValidation {
	/**
	 * 请求的地址
	 */
	String requestUrl();

	/**
	 * 请求方式 - GET/POST
	 */
	HttpMethod httpMethod();
}
