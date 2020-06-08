package com.evchong.wxpayscore.api.common.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;

import com.evchong.wxpayscore.api.common.model.CreateServiceOrderLocation;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderPostDiscount;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderPostPayment;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderRiskFund;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderTimeRange;
import com.evchong.wxpayscore.api.dto.WxPayScoreRequest;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * 创建支付分订单API请求体
 * 
 * <p>
 * 用户申请使用服务时，商户可通过此接口申请创建微信支付分订单。
 * <p>
 * 请求方式：POST
 * 
 * <p>
 * 如：{ "out_order_no": "1234323JKHDFE1243252", "appid": "wxd678efh567hg6787",
 * "service_id": "500001", "service_introduction": "某某酒店", "post_payments": [ {
 * "name": "就餐费用服务费", "amount": 4000, "description": "就餐人均100元服务费：100/小时",
 * "count": 1 } ], "post_discounts": [ { "name": "满20减1元", "description":
 * "不与其他优惠叠加" } ], "time_range": { "start_time": "20091225091010", "end_time":
 * "20091225121010" }, "location": { "start_location": "嗨客时尚主题展餐厅",
 * "end_location": "嗨客时尚主题展餐厅" }, "risk_fund": { "name": "ESTIMATE_ORDER_COST",
 * "amount": 10000, "description": "就餐的预估费用" }, "attach":
 * "Easdfowealsdkjfnlaksjdlfkwqoi&wl3l2sald", "notify_url":
 * "https://api.test.com", "openid": "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o",
 * "need_user_confirm": true, }
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CreateServiceOrderRequest implements WxPayScoreRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * 商户服务订单号
	 * <p>
	 * 商户系统内部服务订单号（不是交易单号），要求此参数只能由数字、大小写字母_-|*组成，且在同一个商户号下唯一。详见[商户订单号]。
	 * <p>
	 * 示例值：1234323JKHDFE1243252
	 */
	@Size(max = 32)
	@NotBlank
	@Pattern(regexp = "([0-9]|[_]|[-]|[|]|[*]|[a-z]|[A-Z])*")
	@JsonProperty("out_order_no")
	@JsonAlias(value = { "out_order_no", "outOrderNo" })
	private String outOrderNo;

	/**
	 * 公众账号ID
	 * <p>
	 * 微信公众平台分配的与传入的商户号建立了支付绑定关系的appid，可在公众平台查看绑定关系，此参数需在本系统先进行配置。
	 * <p>
	 * 示例值：wxd678efh567hg6787
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("appid")
	@JsonAlias(value = { "appid", "appId" })
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
	@JsonProperty("service_id")
	@JsonAlias(value = { "service_id", "serviceId" })
	private String serviceId;

	/**
	 * 服务信息
	 * <p>
	 * 服务信息，用于介绍本订单所提供的服务 ，当参数长度超过20个字符时，报错处理。
	 * <p>
	 * 示例值：某某酒店
	 */
	@NotBlank
	@Size(max = 20)
	@JsonProperty("service_introduction")
	@JsonAlias(value = { "service_introduction", "serviceIntroduction" })
	private String serviceIntroduction;

	/**
	 * 后付费项目
	 * <p>
	 * 后付费项目列表，最多包含100条付费项目。 如果传入，用户侧则显示此参数。
	 */
	@Size(max = 100)
	@Valid
	@JsonProperty("post_payments")
	@JsonAlias(value = { "post_payments", "postPayments" })
	private List<CreateServiceOrderPostPayment> postPayments;

	/**
	 * 后付费商户优惠
	 * <p>
	 * 后付费商户优惠列表，最多包含30条商户优惠。 如果传入，用户侧则显示此参数。
	 * 
	 * <p> 注意：不要传入amount
	 */
	@Size(max = 30)
	@Valid
	@JsonProperty("post_discounts")
	@JsonAlias(value = { "post_discounts", "postDiscounts" })
	private List<CreateServiceOrderPostDiscount> postDiscounts;

	/**
	 * 服务时间段
	 */
	@NotNull
	@Valid
	@JsonProperty("time_range")
	@JsonAlias(value = { "time_range", "timeRange" })
	private CreateServiceOrderTimeRange timeRange;

	/**
	 * 服务位置
	 * <p>
	 * 如果传入，用户侧则显示此参数。
	 */
	@Valid
	private CreateServiceOrderLocation location;

	/**
	 * 订单风险金
	 */
	@NotNull
	@Valid
	@JsonProperty("risk_fund")
	@JsonAlias(value = { "risk_fund", "riskFund" })
	private CreateServiceOrderRiskFund riskFund;

	/**
	 * 商户数据包
	 * <p>
	 * 商户数据包可存放本订单所需信息，需要先urlencode后传入。 当商户数据包总长度超出256字符时，报错处理。
	 * <p>
	 * 示例值：Easdfowealsdkjfnlaksjdlfkwqoi&wl3l2sald
	 */
	@Size(max = 256)
	private String attach;

	/**
	 * 商户回调地址
	 * <p>
	 * 商户接收用户确认订单和付款成功回调通知的地址。
	 * <p>
	 * 示例值：https://api.test.com
	 */
	@NotBlank
	@Size(max = 255)
	@JsonProperty("notify_url")
	@JsonAlias(value = { "notify_url", "notifyUrl" })
	private String notifyUrl;

	/**
	 * 用户标识
	 * <p>
	 * 微信用户在商户对应appid下的唯一标识。
	 * <p>
	 * 条件选填
	 * <li>免确认订单：必填
	 * <li>需确认订单：不填
	 * <p>
	 * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
	 */
	@Size(max = 128)
	@JsonProperty("openid")
	@JsonAlias(value = { "openid", "openId" })
	private String openId;

	/**
	 * 是否需要用户确认
	 * <p>
	 * 枚举值：
	 * <li>false：免确认订单
	 * <li>true：需确认订单
	 * <p>
	 * 示例值：true
	 */
	@NotNull
	@JsonProperty("need_user_confirm")
	@JsonAlias(value = { "need_user_confirm", "needUserConfirm" })
	private Boolean needUserConfirm;

	@Override
	public String requestUrl() {
		return "https://api.mch.weixin.qq.com/v3/payscore/serviceorder";
	}
	
	@Override
	public HttpMethod httpMethod() {
		return HttpMethod.POST;
	}

	@Override
	public void checkParam() {
		if (this.hasParamError()) {
			throw new IllegalArgumentException(paramErrMsg());
		}
		if (!CollectionUtils.isEmpty(postPayments)) {
			postPayments.forEach(CreateServiceOrderPostPayment::checkParam);
		}
		if (!CollectionUtils.isEmpty(postDiscounts)) {
			postDiscounts.forEach(CreateServiceOrderPostDiscount::checkParam);
		}
		if (null != location) {
			location.checkParam();
		}
		timeRange.checkParam();
		riskFund.checkParam();
	}
}
