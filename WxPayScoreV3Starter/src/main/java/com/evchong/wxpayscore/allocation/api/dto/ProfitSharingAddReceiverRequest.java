package com.evchong.wxpayscore.allocation.api.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

/**
 * 添加分账接收方 - 请求
 * 
 * <p>
 * 商户发起添加分账接收方请求，后续可通过发起分账请求将结算后的钱分到该分账接收方。
 * <p>
 * 签名方式：HMAC-SHA256
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_3&index=4
 * @author fanyuwen
 *
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class ProfitSharingAddReceiverRequest implements WxPayAllocationRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * 商户号
	 * <p>
	 * 微信支付分配的商户号
	 * <p>
	 * 示例值：1900000100
	 */
	@Size(max = 32)
	@NotBlank
	@JacksonXmlProperty(localName = "mch_id")
	private String mchId;

	/**
	 * 公众账号ID
	 * <p>
	 * 微信分配的公众账号ID
	 * <p>
	 * 示例值：wx8888888888888888
	 */
	@Size(max = 32)
	@NotBlank
	@JacksonXmlProperty(localName = "appid")
	private String appId;

	/**
	 * 随机字符串
	 * <p>
	 * 随机字符串，不长于32位。推荐<a href=
	 * "https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=4_3">随机数生成算法</a>
	 * <p>
	 * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 */
	@Size(max = 32)
	@NotBlank
	@JacksonXmlProperty(localName = "nonce_str")
	private String nonceStr;

	/**
	 * 签名
	 * <p>
	 * 签名，详见<a href=
	 * "https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=4_3">签名生成算法</a>
	 * <p>
	 * 示例值：ABC6DD4AA85C0EECA82C35595A69EFGH
	 */
	@Size(max = 64)
	@NotBlank
	@JacksonXmlProperty(localName = "sign")
	private String sign;

	/**
	 * 签名类型
	 * <p>
	 * 签名类型，目前只支持HMAC-SHA256
	 * <p>
	 * 示例值：HMAC-SHA256
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "sign_type")
	private String signType;

	/**
	 * 分账接收方
	 * <p>
	 * 分账接收方对象，json格式
	 */
	@Size(max = 2048)
	@NotBlank
	@JacksonXmlProperty(localName = "receiver")
	private String receiver;

	@Override
	public String requestUrl() {
		return "https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver";
	}

	@Override
	public HttpMethod httpMethod() {
		return HttpMethod.POST;
	}

	/**
	 * 使用@JsonProperties时会引发插件Lombok和JacksonJson之间的BUG
	 * <p> https://github.com/FasterXML/jackson-databind/issues/1122
	 */
	@Override
	public Map<String, Object> mapToSign() {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(getMchId())) {
			map.put("mch_id", getMchId());
		}
		if (!StringUtils.isEmpty(getAppId())) {
			map.put("appid", getAppId());
		}
		if (!StringUtils.isEmpty(getNonceStr())) {
			map.put("nonce_str", getNonceStr());
		}
		if (!StringUtils.isEmpty(getReceiver())) {
			map.put("receiver", getReceiver());
		}
		if (!StringUtils.isEmpty(getSignType())) {
			map.put("sign_type", getSignType());
		}
		
		return map;
	}
}
