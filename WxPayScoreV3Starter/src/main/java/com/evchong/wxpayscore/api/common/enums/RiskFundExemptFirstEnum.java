package com.evchong.wxpayscore.api.common.enums;

import java.util.stream.Stream;

/**
 * 订单风险金信息 - 先免模式
 */
public enum RiskFundExemptFirstEnum {
	/** 押金 */
	DEPOSIT,
	/** 预付款 */
	ADVANCE,
	/** 保证金 */
	CASH_DEPOSIT;
	public static boolean contains(String name) {
		return Stream.of(RiskFundExemptFirstEnum.values()).anyMatch(e -> e.name().equals(name));
	}
}