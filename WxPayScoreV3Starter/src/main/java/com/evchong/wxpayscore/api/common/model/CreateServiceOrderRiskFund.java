package com.evchong.wxpayscore.api.common.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.evchong.wxpayscore.api.common.enums.RiskFundExemptFirstEnum;
import com.evchong.wxpayscore.api.common.enums.RiskFundUseFirstEnum;
import com.evchong.wxpayscore.api.dto.WxPayScoreExtraParamValidation;

import lombok.Data;

/**
 * 订单风险金信息 - 创建支付分订单
 * 
 * @author fanyuwen
 *
 */
@Data
public class CreateServiceOrderRiskFund implements WxPayScoreExtraParamValidation {

	private static final long serialVersionUID = 1L;

	/**
	 * 风险金名称
	 * <p>
	 * 枚举值：
	 * <ol>
	 * 【先免模式】（评估不通过可交押金）可填名称为
	 * <li>DEPOSIT：押金
	 * <li>ADVANCE：预付款
	 * <li>CASH_DEPOSIT：保证金
	 * </ol>
	 * <ol>
	 * 【先享模式】（评估不通过不可使用服务）可填名称为
	 * <li>ESTIMATE_ORDER_COST：预估订单费用
	 * </ol>
	 * <p>
	 * 示例值：DEPOSIT
	 */
	@Size(max = 64)
	@NotBlank
	private String name;

	/**
	 * 风险金额
	 * <ol>
	 * <li>数字，必须>0（单位分）。
	 * <li>风险金额≤每个服务ID的风险金额上限。
	 * <li>当商户优惠字段为空时，付费项目总金额≤服务ID的风险金额上限 （未填写金额的付费项目，视为该付费项目金额为0）。
	 * <li>完结金额可大于、小于或等于风险金额。
	 * </ol>
	 * <p>
	 * 示例值：10000
	 */
	@NotNull
	@Positive
	private Integer amount;

	/**
	 * 风险说明
	 * <p>
	 * 文字，不超过30个字。
	 * <p>
	 * 示例值：就餐的预估费用
	 */
	@Size(max = 30)
	private String description;

	@Override
	public boolean hasParamError() {
		return !RiskFundExemptFirstEnum.contains(name) && !RiskFundUseFirstEnum.contains(name);
	}

	@Override
	public String paramErrMsg() {
		return "【先免模式】（评估不通过可交押金）可填名称为\n" + "DEPOSIT：押金\n" + "ADVANCE：预付款\n" + "CASH_DEPOSIT：保证金\n"
				+ "【先享模式】（评估不通过不可使用服务）可填名称为\n" + "ESTIMATE_ORDER_COST：预估订单费用";
	}
}
