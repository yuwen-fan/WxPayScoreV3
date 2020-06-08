package com.evchong.wxpayscore.api.common.model;

import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.evchong.wxpayscore.api.dto.WxPayScoreExtraParamValidation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 服务位置信息 - 创建支付分订单
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * 
 * @author fanyuwen
 *
 */
@Data
public class CreateServiceOrderLocation implements WxPayScoreExtraParamValidation {

	private static final long serialVersionUID = 1L;

	/**
	 * 服务开始地点
	 * 
	 * <p>
	 * 开始使用服务的地点，不超过50个字符，超出报错处理。
	 * <ol>
	 * 【建议】
	 * <li>用户下单时【未确定】服务结束地点，不填写。
	 * <li>服务在同一地点开始和结束，不填写。
	 * <li>用户下单时【已确定】服务结束地点，填写。
	 * </ol>
	 * <p>
	 * 示例值：嗨客时尚主题展餐厅
	 */
	@Size(max = 50)
	@JsonProperty("start_location")
	@JsonAlias({ "start_location", "startLocation" })
	private String startLocation;

	/**
	 * 预计服务结束位置
	 * <li>结束使用服务的地点，不超过50个字符，超出报错处理 。
	 * <p>
	 * 示例值：嗨客时尚主题展餐厅
	 */
	@Size(max = 50)
	@JsonProperty("end_location")
	@JsonAlias({ "end_location", "endLocation" })
	private String endLocation;

	/**
	 * 填写了服务开始地点，才能填写服务结束地点
	 */
	@Override
	public boolean hasParamError() {
		return StringUtils.isEmpty(this.getStartLocation()) && !StringUtils.isEmpty(this.getEndLocation());
	}

	@Override
	public String paramErrMsg() {
		return "填写了服务开始地点，才能填写服务结束地点";
	}
}
