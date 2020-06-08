package com.evchong.wxpayscore.api.common.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 订单通知之通知资源数据
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_3.shtml
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceOrderNotifyResource implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 加密算法类型
	 * <p>
	 * 对授权结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM。
	 * <p>
	 * 示例值：AEAD_AES_256_GCM
	 */
	@Size(max = 32)
	@NotBlank
	private String algorithm;

	/**
	 * 数据密文
	 * <p>
	 * Base64编码后的授权/解除授权结果数据密文
	 */
	@Size(max = 1048576)
	@NotBlank
	private String ciphertext;

	/**
	 * 附加数据
	 */
	@Size(max = 16)
	@JsonProperty("associated_data")
	@JsonAlias({ "associated_data", "associatedData" })
	private String associatedData;

	/**
	 * 随机串
	 * <p>
	 * 加密使用的随机串。
	 * <p>
	 * 示例值：fdasflkja484w
	 */
	@Size(max = 16)
	@NotBlank
	private String nonce;
}
