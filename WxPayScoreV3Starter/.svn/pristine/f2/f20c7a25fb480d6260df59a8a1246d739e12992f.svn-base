package com.evchong.wxpayscore.api.common.enums;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户授权状态枚举
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_8.shtml
 * @author fanyuwen
 *
 */
@RequiredArgsConstructor
public enum UserServiceStateEnum {

	 UNAVAILABLE("用户未授权服务"), AVAILABLE("用户已授权服务");

	@Getter
	private final String description;

	public static UserServiceStateEnum of(String state) {
		return Stream.of(UserServiceStateEnum.values()).filter(s -> s.name().equals(state)).findAny().orElse(null);
	}
}
