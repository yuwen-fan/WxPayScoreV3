package com.evchong.wxpayscore.allocation.api.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 请求单次分账 - 响应
 * 
 * <p>
 * 以下字段在return_code为SUCCESS的时候有返回
 * 
 * @see ProfitSharingRequest
 * @author fanyuwen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class ProfitSharingResponse extends WxPayAllocationCommonResponse {
	private static final long serialVersionUID = 1L;

	/**
	 * 商户号
	 * <p>
	 * 该字段在return_code为SUCCESS的时候有返回
	 * <p>
	 * 微信支付分配的商户号
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：1900000100
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "mch_id")
	private String mchId;

	/**
	 * 公众账号ID
	 * <p>
	 * 该字段在return_code为SUCCESS的时候有返回
	 * <p>
	 * 微信分配的公众账号ID
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：wx8888888888888888
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "appid")
	private String appId;

	/**
	 * 随机字符串
	 * <p>
	 * 该字段在return_code为SUCCESS的时候有返回
	 * <p>
	 * 随机字符串，不长于32位。推荐<a href=
	 * "https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=4_3">随机数生成算法</a>
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "nonce_str")
	private String nonceStr;

	/**
	 * 签名
	 * <p>
	 * 签名，详见<a href=
	 * "https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=4_3">签名生成算法</a>
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：ABC6DD4AA85C0EECA82C35595A69EFGH
	 */
	@Size(max = 64)
	@JacksonXmlProperty(localName = "sign")
	private String sign;

	/**
	 * 微信支付订单号
	 * <p>
	 * 该字段在hasError()=true时返回
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：4208450740201411110007820472
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "transaction_id")
	private String transactionId;

	/**
	 * 商户分账单号
	 * <p>
	 * 该字段在hasError()=true时返回
	 * <p>
	 * 必填
	 * <p>
	 * 商户系统内部的分账单号，在商户系统内部唯一（单次分账、多次分账、完结分账应使用不同的商户分账单号），同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
	 * <p>
	 * 示例值： P20150806125346
	 */
	@Size(max = 64)
	@JacksonXmlProperty(localName = "out_order_no")
	private String outOrderNo;

	/**
	 * 微信分账单号
	 * <p>
	 * 该字段在hasError()=true时返回
	 * <p>
	 * 必填
	 * <p>
	 * 微信系统返回的唯一标识
	 * <p>
	 * 示例值： 3008450740201411110007820472
	 */
	@Size(max = 64)
	@JacksonXmlProperty(localName = "order_id")
	private String orderId;
}
