package com.evchong.wxpayscore.api.common.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.evchong.wxpayscore.api.dto.WxPayScoreExtraParamValidation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 服务时间段 - 完结支付分订单
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_5.shtml
 * 
 * @author fanyuwen
 *
 */
@Data
public class CompleteServiceOrderTimeRange implements WxPayScoreExtraParamValidation {

	private static final long serialVersionUID = 1L;

	public static final String PATTERN_TIME = "yyyyMMdd";

	/**
	 * 服务开始时间
	 * 
	 * <p>
	 * 用户端展示用途。
	 * <p>
	 * 用户下单时确认的服务开始时间（比如用户今天下单，明天开始接受服务，这里指的是明天的服务开始时间）。
	 * 
	 * <ol>
	 * <li>不能比【商户使用创建订单能力时间】早；
	 * <li>不能比【商户使用完结订单能力时间】晚。
	 * <li>根据传入时间精度进行校验，若传入时间精确到秒，则校验精确到秒；若传入时间精确到日，则校验精确到日。
	 * <li>要求时间格式必须与首次传入格式保持一致，在一致前提下可修改。
	 * </ol>
	 * <ol>
	 * 支持三种格式：yyyyMMddHHmmss、yyyyMMdd和 OnAccept
	 * <li>传入20091225091010表示2009年12月25日9点10分10秒。
	 * <li>传入20091225默认认为时间为2009年12月25日
	 * <li>传入OnAccept表示用户确认订单成功时间为【服务开始时间】。
	 * </ol>
	 * <p>
	 * 【服务开始时间】不能早于调用接口时间。
	 * <ol>
	 * 【建议】
	 * <li>实际服务开始时间与创建订单填写的“服务开始时间”一致时，不填写
	 * <li>若传入时间精准到日，则校验精准到日：【服务开始时间】>=【商户调用创建订单接口时间
	 * </ol>
	 * <p>
	 * 示例值：20091225
	 */
	@Size(max = 14)
	@Pattern(regexp = "[1-9]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])")
	@JsonProperty("start_time")
	@JsonAlias(value = { "start_time", "startTime" })
	private String startTime;

	/**
	 * 
	 * 服务开始时间备注
	 * <p>
	 * 服务开始时间备注说明，服务开始时间有填时，可填写服务开始时间备注，不超过20个字符，超出报错处理。
	 * <p>
	 * 示例值：出账日
	 */
	@Size(max = 20)
	@JsonProperty("start_time_remark")
	@JsonAlias(value = { "start_time_remark", "startTimeRemark" })
	private String startTimeRemark;

	/**
	 * 服务结束时间
	 * <p>
	 * 创建订单未填写服务结束时间，则完结的时候，服务结束时间必填
	 * <ol>
	 * <li>【实际服务结束时间】＞【服务开始时间】。
	 * <li>不能比【商户使用完结订单能力时间】晚。
	 * <li>要求时间格式必须与首次传入格式保持一致，在一致前提下可修改。
	 * <li>若创建时，服务开始时间为格式3=OnAccept，则完结时间默认精确到秒级。
	 * </ol>
	 * <ol>
	 * 支持两种格式：yyyyMMddHHmmss和yyyyMMdd
	 * <li>传入20091225091010表示2009年12月25日9点10分10秒。
	 * <li>传入20091225默认认为时间为2009年12月25日
	 * </ol>
	 * <p>
	 * 【建议】
	 * <li>实际服务结束实际和预计服务结束时间一致时，不填写
	 * <p>
	 * 示例值：20091225
	 * 
	 */
	@Size(max = 14)
	@Pattern(regexp = "[1-9]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])")
	@JsonProperty("end_time")
	@JsonAlias(value = { "end_time", "endTime" })
	private String endTime;

	/**
	 * 预计服务结束时间备注
	 * <p>
	 * 预计服务结束时间有填时，可填写预计服务结束时间备注，不超过20个字符，超出报错处理。
	 * <p>
	 * 若与【服务结束时间备注】不一致，则以【实际服务结束时间备注】为准
	 * <p>
	 * 示例值：结束租借时间
	 */
	@Size(max = 20)
	@JsonProperty("end_time_remark")
	@JsonAlias(value = { "end_time_remark", "endTimeRemark" })
	private String endTimeRemark;

}
