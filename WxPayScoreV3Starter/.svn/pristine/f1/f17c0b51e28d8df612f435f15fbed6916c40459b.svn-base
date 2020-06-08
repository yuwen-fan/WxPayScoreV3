package com.evchong.wxpayscore.api.common.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 小程序调用支付分授权服务时需要传递给支付分的业务数据
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter8_1.shtml
 */
@ToString
@Getter
@JsonInclude(Include.NON_NULL)
public class WxOpenBusinessViewExtraData {

	/**
	 * 商户号
	 * <p>
	 * 微信支付分配的商户号
	 * <p>
	 * 示例值：1230000109
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("mch_id")
	@JsonAlias(value = { "mch_id", "mchId" })
	@Setter
	private String mchId;

	/**
	 * 服务ID
	 * <p>
	 * 示例值：88888888000011
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("service_id")
	@JsonAlias(value = { "service_id", "serviceId" })
	@Setter
	private String serviceId;

	/**
	 * 商户签约单号
	 * <p>
	 * 调用授权服务接口提交的商户请求唯一标识
	 * <p>
	 * 示例值：234323JKHDFE1243252
	 */
	@NotBlank
	@Size(max = 64)
	@JsonProperty("out_request_no")
	@JsonAlias(value = { "out_request_no", "outRequestNo" })
	@Setter
	private String outRequestNo;

	/**
	 * 时间戳
	 * <p>
	 * 生成签名时间戳，单位秒
	 * <p>
	 * 示例值：1530097563
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("timestamp")
	@JsonAlias(value = { "timestamp" })
	@Setter
	private String timestamp;

	/**
	 * 随机字符串
	 * <p>
	 * 生成签名随机串。由数字、大小写字母组成，长度不超过32位
	 * <p>
	 * 示例值：zyx53Nkey8o4bHpxTQvd8m7e92nG5mG2
	 */
	@NotBlank
	@Size(max = 32)
	@Pattern(regexp = "([0-9]|[a-z]|[A-Z])*")
	@JsonProperty("nonce_str")
	@JsonAlias(value = { "nonce_str", "nonceStr" })
	@Setter
	private String nonceStr;

	/**
	 * 签名方式
	 * <p>
	 * 签名类型，仅支持HMAC-SHA256
	 * <p>
	 * 示例值：HMAC-SHA256
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("sign_type")
	@JsonAlias(value = { "sign_type", "signType" })
	private String signType = "HMAC-SHA256";

	/**
	 * 签名
	 * <p>
	 * 使用字段mch_id、service_id、out_request_no、timestamp、nonce_str、sign_type按照<a href=
	 * "https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=4_3">签名生成算法</a>计算得出的签名值。
	 * <p>
	 * 示例值：029B52F67573D7E3BE74904BF9AEA
	 */
	@NotBlank
	@Size(max = 64)
	@JsonProperty("sign")
	@JsonAlias(value = { "sign" })
	@Setter
	private String sign;

	/**
	 * 返回给前端的map格式
	 */
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<>();
		if (!StringUtils.isEmpty(getMchId())) {
			map.put("mch_id", getMchId());
		}
		if (!StringUtils.isEmpty(getServiceId())) {
			map.put("service_id", getServiceId());
		}
		if (!StringUtils.isEmpty(getOutRequestNo())) {
			map.put("out_request_no", getOutRequestNo());
		}
		if (!StringUtils.isEmpty(getTimestamp())) {
			map.put("timestamp", getTimestamp());
		}
		if (!StringUtils.isEmpty(getNonceStr())) {
			map.put("nonce_str", getNonceStr());
		}
		if (!StringUtils.isEmpty(getSignType())) {
			map.put("sign_type", getSignType());
		}
		if (!StringUtils.isEmpty(getSign())) {
			map.put("sign", getSign());
		}
		return map;
	}

}
