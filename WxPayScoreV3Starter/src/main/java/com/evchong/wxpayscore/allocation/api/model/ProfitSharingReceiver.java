package com.evchong.wxpayscore.allocation.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.evchong.wxpayscore.allocation.api.dto.WxPayAllocationExtraParamValidation;
import com.evchong.wxpayscore.allocation.api.enums.ProfitSharingReceiverRelationTpyeEnum;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 分账接收方
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_3&index=4
 * @author fanyuwen
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ProfitSharingReceiver implements WxPayAllocationExtraParamValidation {
	private static final long serialVersionUID = 1L;

	/**
	 * 分账接收方类型
	 * <p>
	 * <li>MERCHANT_ID：商户ID
	 * <li>PERSONAL_OPENID：个人openid
	 * <p>
	 * 示例值：MERCHANT_ID
	 */
	@Size(max = 32)
	@NotBlank
	private String type;

	/**
	 * <p>
	 * <li>类型是MERCHANT_ID时，是商户ID
	 * <li>类型是PERSONAL_OPENID时，是个人openid 分账接收方帐号
	 * <p>
	 * 示例值：86693852
	 */
	@Size(max = 64)
	@NotBlank
	private String account;

	/**
	 * 
	 * 分账接收方全称
	 * <p>
	 * <li>分账接收方类型是MERCHANT_ID时，是商户全称（必传）
	 * <li>分账接收方类型是PERSONAL_OPENID时，是个人姓名（选传，传则校验）
	 * <p>
	 * 示例值：示例商户全称
	 */
	@Size(max = 1024)
	private String name;

	/**
	 * 与分账方的关系类型
	 * <ol>
	 * 子商户与接收方的关系。本字段值为枚举：
	 * <li>SERVICE_PROVIDER：服务商
	 * <li>STORE：门店
	 * <li>STAFF：员工
	 * <li>STORE_OWNER：店主
	 * <li>PARTNER：合作伙伴
	 * <li>HEADQUARTER：总部
	 * <li>BRAND：品牌方
	 * <li>DISTRIBUTOR：分销商
	 * <li>USER：用户
	 * <li>SUPPLIER：供应商
	 * <li>CUSTOM：自定义
	 * </ol>
	 * <p>
	 * 示例值：SERVICE_PROVIDER
	 * 
	 * @see com.evchong.wxpayscore.allocation.api.enums.ProfitSharingReceiverRelationTpyeEnum
	 */
	@Size(max = 32)
	@NotBlank
	@JsonProperty("relation_type")
	@JsonAlias({ "relation_type", "relationType" })
	private ProfitSharingReceiverRelationTpyeEnum relationType;

	/**
	 * 自定义的分账关系
	 * <p>
	 * 子商户与接收方具体的关系，本字段最多10个字
	 * <li>当字段relation_type的值为CUSTOM时，本字段必填
	 * <li>当字段relation_type的值不为CUSTOM时，本字段无需填写
	 * <p>
	 * 示例值：代理商
	 */
	@Size(max = 10)
	@JsonProperty("custom_relation")
	@JsonAlias({ "custom_relation", "customRelation" })
	private String customRelation;
	
	/**
	 * 分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
	 * <p> 当为‘请求单次分账’时，必填
	 * @see https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
	 */
	private Integer amount;
	
	/**
	 * 分账的原因描述，分账账单中需要体现
	 * 
	 * <p> 当为‘请求单次分账’时，必填
	 * @see https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_1&index=1
	 */
	@Size(max = 80)
	private String description;

	@Override
	public boolean hasParamError() {
		return ProfitSharingReceiverRelationTpyeEnum.CUSTOM.equals(this.getRelationType())
				&& StringUtils.isEmpty(this.getCustomRelation());
	}

	@Override
	public String paramErrMsg() {
		return "当字段relation_type的值为CUSTOM时，custom_relation必填";
	}
}
