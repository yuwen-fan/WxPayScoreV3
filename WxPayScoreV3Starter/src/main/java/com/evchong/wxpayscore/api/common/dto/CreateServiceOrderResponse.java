package com.evchong.wxpayscore.api.common.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.evchong.wxpayscore.api.common.model.CreateServiceOrderLocation;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderPostDiscount;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderPostPayment;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderRiskFund;
import com.evchong.wxpayscore.api.common.model.CreateServiceOrderTimeRange;
import com.evchong.wxpayscore.api.dto.WxPayScoreCommonResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * 创建支付分订单API响应体
 * 
 * <p>
 * 如：{ "appid": "wxd678efh567hg6787", "mchid": "1230000109", "out_order_no":
 * "1234323JKHDFE1243252", "service_id": "500001", "service_introduction":
 * "某某酒店", "state": "CREATED", "state_description": "MCH_COMPLETE",
 * "post_payments": [ { "name": "就餐费用服务费", "amount": 4000, "description":
 * "就餐人均100元服务费：100/小时", "count": 1 } ], "post_discounts": [ { "name": "满20减1元",
 * "description": "不与其他优惠叠加" } ], "risk_fund": { "name": " ESTIMATE_ORDER_COST",
 * "amount": 10000, "description": "就餐的预估费用" }, "time_range": { "start_time":
 * "20091225091010", "end_time": "20091225121010" }, "location": {
 * "start_location": "嗨客时尚主题展餐厅", "end_location": "嗨客时尚主题展餐厅" }, "attach":
 * "Easdfowealsdkjfnlaksjdlfkwqoi&wl3l2sald", "notify_url":
 * "https://api.test.com", "order_id": "15646546545165651651", "package": "
 * DJIOSQPYWDxsjdldeuwhdodwxasd_dDiodnwjh9we " }
 * 
 * @see com.evchong.wxpayscore.api.common.dto.CreateServiceOrderRequest
 * @author fanyuwen
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class CreateServiceOrderResponse extends WxPayScoreCommonResponse {
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
	@JsonAlias(value = { "appid", "appId" })
	private String appId;

	/**
	 * 商户号
	 * <p>
	 * 调用接口提交的商户号。
	 * <p>
	 * 示例值：1230000109
	 */
	@NotBlank
	@Size(max = 32)
	private String mchid;

	/**
	 * 商户服务订单号
	 * <p>
	 * 调用接口提交的商户服务订单号
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
	 * 服务ID
	 * <p>
	 * 调用该接口提交的服务ID
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
	 * 服务信息，用于介绍本订单所提供的服务。
	 * <p>
	 * 示例值：某某酒店
	 */
	@NotBlank
	@Size(max = 20)
	@JsonProperty("service_introduction")
	@JsonAlias(value = { "service_introduction", "serviceIntroduction" })
	private String serviceIntroduction;

	/**
	 * 服务订单状态
	 * <p>
	 * 表示当前单据状态。
	 * <ol>
	 * 枚举值：
	 * <li>CREATED：商户已创建服务订单
	 * <li>DOING：服务订单进行中
	 * <li>DONE：服务订单完成
	 * <li>REVOKED：商户取消服务订单
	 * <li>EXPIRED：服务订单已失效，"商户已创建服务订单"状态超过30天未变动，则订单失效
	 * </ol>
	 * 
	 * @see com.evchong.wxpayscore.api.common.enums.OrderStateEnum
	 * 
	 * <p>
	 * 示例值：CREATED
	 */
	@NotBlank
	@Size(max = 32)
	private String state;

	/**
	 * 订单状态说明
	 * <p>
	 * 对服务订单"进行中"状态的附加说明。
	 * <li>1、USER_CONFIRM：用户确认
	 * <li>2、MCH_COMPLETE：商户完结
	 * 
	 * @see com.evchong.wxpayscore.api.common.constant.OrderStateDescriptionConstant
	 * <p>
	 * 示例值：MCH_COMPLETE
	 */
	@Size(max = 32)
	@JsonProperty("state_description")
	@JsonAlias(value = { "state_description", "state_description" })
	private String stateDescription;

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
	 */
	@Size(max = 30)
	@Valid
	@JsonProperty("post_discounts")
	@JsonAlias(value = { "post_discounts", "postDiscounts" })
	private List<CreateServiceOrderPostDiscount> postDiscounts;

	/**
	 * 订单风险金
	 */
	@NotNull
	@Valid
	@JsonProperty("risk_fund")
	@JsonAlias(value = { "risk_fund", "riskFund" })
	private CreateServiceOrderRiskFund riskFund;

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
	 * 微信支付服务订单号
	 * <p>
	 * 微信支付服务订单号，每个微信支付服务订单号与商户号下对应的商户服务订单号一一对应。
	 * <p>
	 * 示例值：15646546545165651651
	 */
	@NotBlank
	@Size(max = 64)
	@JsonProperty("order_id")
	@JsonAlias(value = { "order_id", "orderId" })
	private String orderId;

	/**
	 * 跳转微信侧小程序订单数据
	 * <p>
	 * 用于跳转到微信侧小程序订单数据,跳转到微信侧小程序传入。该数据1小时内有效。
	 * <p>
	 * 示例值：DJIOSQPYWDxsjdldeuwhdodwxasd_dDiodnwjh9we
	 */
	@NotBlank
	@Size(max = 300)
	@JsonProperty("package")
	@JsonAlias(value = { "package", "pkg" })
	private String pkg;

}