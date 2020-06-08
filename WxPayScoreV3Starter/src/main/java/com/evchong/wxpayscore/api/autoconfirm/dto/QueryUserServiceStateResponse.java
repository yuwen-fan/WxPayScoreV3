package com.evchong.wxpayscore.api.autoconfirm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.evchong.wxpayscore.api.common.enums.UserServiceStateEnum;
import com.evchong.wxpayscore.api.dto.WxPayScoreCommonResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 查询用户授权状态API响应体
 * 
 * <p>
 * 如：{ "service_id": "500001", "appid": "wxd678efh567hg6787", "openid":
 * "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o", "use_service_state": "AVAILABLE" }
 * 
 * @see com.evchong.wxpayscore.api.autoconfirm.dto.QueryUserServiceStateRequest
 * @author fanyuwen
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class QueryUserServiceStateResponse extends WxPayScoreCommonResponse {

	private static final long serialVersionUID = 1L;

	/**
	 * 公众账号ID
	 * <p>
	 * 调用接口提交的公众账号ID。
	 * <p>
	 * 示例值：wxd678efh567hg6787
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("appid")
	@JsonAlias({ "appid", "appId" })
	private String appId;

	/**
	 * 服务ID
	 * <p>
	 * 调用接口提交的公众账号ID。
	 * <p>
	 * 示例值：500001
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("service_id")
	@JsonAlias({ "service_id", "serviceId" })
	private String serviceId;

	/**
	 * 用户标识
	 * <p>
	 * 微信用户在商户对应appid下的唯一标识
	 * <p>
	 * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 */
	@Size(max = 128)
	@JsonProperty("openid")
	@JsonAlias({ "openid", "openId" })
	private String openId;

	/**
	 * 用户授权服务情况
	 * <ol>
	 * 标识用户授权服务情况。
	 * <li>UNAVAILABLE：用户未授权服务
	 * <li>AVAILABLE：用户已授权服务
	 * </ol>
	 * <p>
	 * 示例值：AVAILABLE
	 */
	@NotBlank
	@Size(max = 16)
	@JsonProperty("use_service_state")
	@JsonAlias({ "use_service_state", "useServiceState" })
	private String useServiceState;

	/**
	 * 是否已授权
	 */
	public boolean hasAuthorized() {
		UserServiceStateEnum state = UserServiceStateEnum.of(this.getUseServiceState());
		return UserServiceStateEnum.AVAILABLE.equals(state);
	}
}
