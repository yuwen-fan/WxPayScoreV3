package com.evchong.wxpayscore.api.common.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.http.HttpMethod;

import com.evchong.wxpayscore.api.common.model.CompleteServiceOrderLocation;
import com.evchong.wxpayscore.api.common.model.CompleteServiceOrderPostDiscount;
import com.evchong.wxpayscore.api.common.model.CompleteServiceOrderPostPayment;
import com.evchong.wxpayscore.api.common.model.CompleteServiceOrderTimeRange;
import com.evchong.wxpayscore.api.dto.WxPayScoreRequest;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 完结支付分订单API请求体
 * 
 * <p>
 * 完结微信支付分订单。用户使用服务完成后，商户可通过此接口完结订单。
 * <p>
 * 请求方式：POST
 * 
 * <p>
 * 如：{ "appid": "wxd678efh567hg6787", "service_id": "500001", "post_payments": [
 * { "name": "就餐费用服务费", "amount": 4000, "description": "就餐人均100元服务费：100/小时",
 * "count": 1 } ], "post_discounts":[ { "name": "满20减1元", "description":
 * "不与其他优惠叠加", "amount": 4000 } ], "total_amount": 3900, "time_range": {
 * "start_time": "20091225091010", "end_time": "20091225121010" }, "location": {
 * "end_location": "嗨客时尚主题展餐厅" }, "profit_sharing": false }
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CompleteServiceOrderRequest implements WxPayScoreRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * 商户服务订单号
	 * <p>
	 * 商户系统内部服务订单号（不是交易单号），与创建订单时一致
	 * <p>
	 * 示例值：1234323JKHDFE1243252
	 */
	@Size(max = 32)
	@NotBlank
	@Pattern(regexp = "([0-9]|[_]|[-]|[|]|[*]|[a-z]|[A-Z])*")
	@JsonIgnore
	private String outOrderNoPathParam;

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
	 * 服务订单的主键，唯一定义此资源的标识
	 * <p>
	 * 示例值：500001
	 */
	@NotBlank
	@Size(max = 32)
	@JsonProperty("service_id")
	@JsonAlias(value = { "service_id", "serviceId" })
	private String serviceId;

	/**
	 * 后付费项目
	 * <p>
	 * 后付费项目列表，最多包含100条付费项目
	 */
	@Size(max = 100)
	@NotNull
	@Valid
	@JsonProperty("post_payments")
	@JsonAlias(value = { "post_payments", "postPayments" })
	private List<CompleteServiceOrderPostPayment> postPayments;

	/**
	 * 后付费商户优惠
	 * <p>
	 * 后付费商户优惠列表，最多包含30条商户优惠。 如果传入，用户侧则显示此参数。
	 */
	@Size(max = 30)
	@Valid
	@JsonProperty("post_discounts")
	@JsonAlias(value = { "post_discounts", "postDiscounts" })
	private List<CompleteServiceOrderPostDiscount> postDiscounts;

	/**
	 * 总金额
	 * 
	 * <ol>
	 * <li>金额：数字，必须≥0（单位：分）
	 * <li>总金额 =（完结付费项目1…+完结付费项目n）-（完结商户优惠项目1…+完结商户优惠项目n）
	 * <li>总金额上限 - 【评估不通过：交押金】模式：总金额≤创单时填写的“订单风险金额”
	 * <li>总金额上限 - 【评估不通过：拒绝】模式：总金额≤“每个服务ID的风险金额上限”
	 * </ol>
	 * <p>
	 * 示例值：50000
	 * 
	 * @see https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=4_2
	 */
	@NotNull
	@PositiveOrZero
	@JsonProperty("total_amount")
	@JsonAlias(value = { "total_amount", "totalAmount" })
	private Integer totalAmount;

	/**
	 * 服务时间段
	 * <p> 服务时间范围，创建订单未填写服务结束时间，则完结的时候，服务结束时间必填
	 */
	@Valid
	@JsonProperty("time_range")
	@JsonAlias(value = { "time_range", "timeRange" })
	private CompleteServiceOrderTimeRange timeRange;
	
	/**
	 * 服务位置
	 * <p>
	 * 如果传入，用户侧则显示此参数。
	 * <p> 注意：不要传入startLocation
	 */
	@Valid
	private CompleteServiceOrderLocation location;

	/**
	 * 微信支付服务分账标记
	 * <p>
	 * 完结订单分账接口标记，false-不分账，true-分账。默认：false
	 */
	@JsonProperty("profit_sharing")
	@JsonAlias(value = { "profit_sharing", "profitSharing" })
	private Boolean profitSharing = Boolean.FALSE;

	/**
	 * 订单优惠标记
	 * <p>
	 * 代金券或立减金优惠的参数，说明详见代金券或立减金优惠
	 * <p>
	 * 示例值：goods_tag
	 */
	@Size(max = 32)
	@JsonProperty("goods_tag")
	@JsonAlias(value = { "goods_tag", "goodsTag" })
	private String goodsTag;

	@Override
	public String requestUrl() {
		return "https://api.mch.weixin.qq.com/v3/payscore/serviceorder/" + outOrderNoPathParam + "/complete";
	}
	
	@Override
	public HttpMethod httpMethod() {
		return HttpMethod.POST;
	}

}
