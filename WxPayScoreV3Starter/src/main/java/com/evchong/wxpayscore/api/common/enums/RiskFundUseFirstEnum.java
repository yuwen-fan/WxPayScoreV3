package com.evchong.wxpayscore.api.common.enums;

import java.util.stream.Stream;

/**
 * 订单风险金信息 - 先享模式
 */
public enum RiskFundUseFirstEnum {
	/** 预估订单费用 */
	ESTIMATE_ORDER_COST;
	public static boolean contains(String name) {
		return Stream.of(RiskFundUseFirstEnum.values()).anyMatch(e -> e.name().equals(name));
	}
}
