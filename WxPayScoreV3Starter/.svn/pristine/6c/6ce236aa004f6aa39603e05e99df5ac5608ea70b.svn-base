package com.evchong.wxpayscore.api.common.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 订单通知
 * <p>
 * 如：{ "id":"EV-2018022511223320873", "create_time":"20180225112233",
 * "resource_type":"encrypt-resource", "event_type":"PAYSCORE.USER_CONFIRM",
 * "resource" : { "algorithm":"AEAD_AES_256_GCM", "ciphertext": "...", "nonce":
 * "...", "associated_data": "" } }
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_2.shtml
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter5_3.shtml
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceOrderNotify implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 通知ID
	 * <p>
	 * 通知的唯一ID
	 * <p>
	 * 示例值：EV-2018022511223320873
	 */
	@Size(max = 32)
	@NotBlank
	private String id;

	/**
	 * 通知创建时间
	 * <p>
	 * 格式为 yyyy-MM-ddTHH:mm:ss+08:00(标准iso8601时间格式）。
	 * <p>
	 * 示例值：2020-05-19T20:27:07+08:00
	 */
	@Size(max = 16)
	@NotBlank
	@JsonProperty("create_time")
	@JsonAlias({ "create_time", "createTime" })
	private String createTime;

	/**
	 * 通知的类型
	 * <ol>
	 * <li>授权成功通知的类型为PAYSCORE.USER_OPEN_SERVICE
	 * <li>解除授权成功通知的类型为PAYSCORE.USER_CLOSE_SERVICE
	 * <li>用户确认成功通知的类型为PAYSCORE.USER_CONFIRM
	 * <li>支付成功通知的类型为PAYSCORE.USER_PAID
	 * </ol>
	 * <p>
	 * 示例值：PAYSCORE.USER_OPEN_SERVICE
	 */
	@Size(max = 32)
	@NotBlank
	@JsonProperty("event_type")
	@JsonAlias({ "event_type", "eventType" })
	private String eventType;

	/**
	 * 通知的资源数据类型
	 * <p>
	 * 授权/解除授权成功通知为encryptresource。
	 * <p>
	 * 示例值：encrypt-resource
	 */
	@Size(max = 32)
	@NotBlank
	@JsonProperty("resource_type")
	@JsonAlias({ "resource_type", "resourceType" })
	private String resourceType;

	/**
	 * 通知资源数据
	 * <p>
	 * json格式
	 */
	@Valid
	@NotNull
	private ServiceOrderNotifyResource resource;
}
