package com.evchong.wxpayscore.allocation.api.dto;

import org.springframework.http.HttpMethod;

/**
 * 微信支付分 - 普通直连分账请求
 * 
 * @author fanyuwen
 *
 */
public interface WxPayAllocationRequest extends WxPayAllocationExtraParamValidation {
	/**
	 * 请求的地址
	 */
	String requestUrl();

	/**
	 * 请求方式 - GET/POST
	 */
	HttpMethod httpMethod();
}
