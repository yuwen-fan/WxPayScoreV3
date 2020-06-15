package com.evchong.wxpayscore.allocation.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 子商户与分账方的关系类型
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=27_3&index=4
 * @author fanyuwen
 *
 */
@Getter
@RequiredArgsConstructor
public enum ProfitSharingReceiverRelationTpyeEnum {
	SERVICE_PROVIDER("服务商"), STORE("门店"), STAFF("员工"), STORE_OWNER("店主"), PARTNER("合作伙伴"), HEADQUARTER("总部"),
	BRAND("品牌方"), DISTRIBUTOR("分销商"), USER("用户"), SUPPLIER("供应商"), CUSTOM("自定义");

	private final String decription;
}