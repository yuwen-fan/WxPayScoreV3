package com.evchong.wxpayscore.api.common.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.evchong.wxpayscore.api.dto.WxPayScoreExtraParamValidation;

import lombok.Data;

/**
 * 后付费项目 - 创建支付分订单
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * 
 * @author fanyuwen
 *
 */
@Data
public class CreateServiceOrderPostPayment implements WxPayScoreExtraParamValidation {

	private static final long serialVersionUID = 1L;

	/**
	 * 付费项目名称
	 * <p>
	 * 付费项目名称不能重复，当参数长度超过20个字符时，报错处理。
	 * <p>
	 * 示例值：就餐费用, 服务费
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
	 * 计费说明
	 * <p>
	 * 描述计费规则，不超过30个字符，超出报错处理。
	 * <p>
	 * 示例值：就餐人均100元，服务费：100/小时
	 */
	@Size(max = 30)
	private String description;

	/**
	 * 付费项目的数量
	 * <p>
	 * 特殊规则：数量限制100，不填时默认1。
	 * <p>
	 * 示例值：4
	 */
	@Min(1)
	@Max(100)
	private Integer count = 1;

	/**
	 * 如果填写了“付费项目名称”，则amount或description必须填写其一，或都填。
	 */
	@Override
	public boolean hasParamError() {
		if (StringUtils.isEmpty(this.getName())) {
			return false;
		}
		return null == this.getAmount() && StringUtils.isEmpty(this.getDescription());
	}

	@Override
	public String paramErrMsg() {
		return "如果填写了“付费项目名称”，则amount或description必须填写其一，或都填。";
	}
}
