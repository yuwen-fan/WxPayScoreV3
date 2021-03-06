package com.evchong.wxpayscore.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 微信支付分 - 响应的错误详情
 * <p>
 * 如：{ "field": "/amount/currency", "value": "XYZ", "issue": "Currency code is
 * invalid", "location" :"body" }
 * 
 * @author fanyuwen
 *
 */
@Data
public class WxPayScoreResponseErrorDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 指示错误参数的位置。
	 * <li>当错误参数位于请求body的JSON时，填写指向参数的JSON Pointer。
	 * <li>当错误参数位于请求的url或者querystring时，填写参数的变量名。
	 */
	private String field;
	/**
	 * 错误的值
	 */
	private String value;
	/**
	 * 具体错误原因
	 */
	private String issue;
	/**
	 * 如body
	 */
	private String location;
}
