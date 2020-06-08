package com.evchong.wxpayscore.api.autoconfirm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpMethod;

import com.evchong.wxpayscore.api.dto.WxPayScoreRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 查询用户授权状态API请求体
 * <p>
 * 请求方式：GET
 * 
 * <p>
 * 用户申请使用服务时，商户可通过此接口查询用户是否“已授权”本服务。在“已授权”状态下的服务，用户才可以申请使用。
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_8.shtml
 * 
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class QueryUserServiceStateRequest implements WxPayScoreRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 公众账号ID
	 * <p>
	 * 微信公众平台分配的与传入的商户号建立了支付绑定关系的appid，可在公众平台查看绑定关系，此参数需在本系统先进行配置。
	 * <p>
	 * 示例值：wxd678efh567hg6787
	 */
	@NotBlank
	@Size(max = 32)
	@JsonIgnore
	private String appId;

	/**
	 * 服务ID
	 * <p>
	 * 该服务ID有本接口对应产品的权限。
	 * <p>
	 * 示例值：500001
	 */
	@NotBlank
	@Size(max = 32)
	@JsonIgnore
	private String serviceId;

	/**
	 * 用户标识
	 * <p>
	 * 微信用户在商户对应appid下的唯一标识
	 * <p>
	 * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 */
	@NotBlank
	@Size(max = 128)
	@JsonIgnore
	private String openId;

	/**
	 * 如“https://api.mch.weixin.qq.com/payscore/user-service-state?service_id=500001
	 * &appid=wxd678efh567hg6787&openid=oUpF8uMuAJO_M2pxb1Q9zNjWeS6o”
	 */
	@Override
	public String requestUrl() {
		return "https://api.mch.weixin.qq.com/v3/payscore/user-service-state?service_id=" + serviceId + "&appid="
				+ appId + "&openid=" + openId;
	}

	@Override
	public HttpMethod httpMethod() {
		return HttpMethod.GET;
	}

}
