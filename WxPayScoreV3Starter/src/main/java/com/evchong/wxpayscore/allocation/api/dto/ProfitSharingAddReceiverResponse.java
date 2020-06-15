package com.evchong.wxpayscore.allocation.api.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 添加分账接收方 - 响应
 * 
 * <p>
 * 以下字段在return_code为SUCCESS的时候有返回
 * 
 * @see ProfitSharingAddReceiverRequest
 * @author fanyuwen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "xml")
public class ProfitSharingAddReceiverResponse extends WxPayAllocationCommonResponse {
	private static final long serialVersionUID = 1L;

	/**
	 * 商户号
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
	 * 分账接收方
	 * <p>
	 * 分账接收方对象（不包含分账接收方全称），json格式
	 * <p>
	 * 如：{"type":"MERCHANT_ID","account":"190001001" }
	 * <p>
	 * 必填
	 */
	@Size(max = 2048)
	@JacksonXmlProperty(localName = "receiver")
	private String receiver;
}
