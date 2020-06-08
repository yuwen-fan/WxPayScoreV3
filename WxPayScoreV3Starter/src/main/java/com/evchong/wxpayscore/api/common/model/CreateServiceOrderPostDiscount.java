package com.evchong.wxpayscore.api.common.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.evchong.wxpayscore.api.dto.WxPayScoreExtraParamValidation;

import lombok.Data;

/**
 * 后付费商户优惠 - 创建支付分订单
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * 
 * @author fanyuwen
 *
 */
@Data
public class CreateServiceOrderPostDiscount implements WxPayScoreExtraParamValidation {

	private static final long serialVersionUID = 1L;

	/**
	 * 优惠名称说明
	 * <p>
	 * 示例值：满20减1元
	 */
	@Size(max = 20)
	private String name;

	/**
	 * 金额
	 * <p>
	 * 此付费项目总金额，大于等于0，单位为分，等于0时代表不需要扣费，只能为整数。
	 * <p>
	 * 示例值：40000
	 * 
	 * @see https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=4_2
	 */
	@PositiveOrZero
	private Integer amount;

	/**
	 * 优惠使用条件说明
	 * <p>
	 * 示例值：不与其他优惠叠加
	 */
	@Size(max = 30)
	private String description;

	/**
	 * 优惠数量
	 * <p>
	 * 特殊规则：数量限制100，不填时默认1
	 * <p>
	 * 示例值：2
	 */
	@Min(1)
	@Max(100)
	private Integer count = 1;

	/**
	 * name和description若填写，则必须同时填写
	 */
	@Override
	public boolean hasParamError() {
		if (StringUtils.isEmpty(this.getName())) {
			return !StringUtils.isEmpty(this.getDescription());
		}
		return StringUtils.isEmpty(this.getDescription());
	}

	@Override
	public String paramErrMsg() {
		return "name和description若填写，则必须同时填写，优惠名称不可重复描述";
	}
}
