package com.evchong.wxpayscore.api.common.enums;

import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 服务订单状态
 * 
 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_1.shtml
 * @author fanyuwen
 *
 */
@RequiredArgsConstructor
public enum OrderStateEnum {
	CREATED("商户已创建服务订单"), DOING("服务订单进行中"), DONE("服务订单完成"), REVOKED("商户取消服务订单"),
	EXPIRED("服务订单已失效，\"商户已创建服务订单\"状态超过30天未变动，则订单失效");

	@Getter
	private final String description;

	public static OrderStateEnum of(String code) {
		return Stream.of(OrderStateEnum.values()).filter(state -> state.name().equals(code)).findAny().orElse(null);
	}

	public static String toDescription(String code) {
		OrderStateEnum state = of(code);
		return null == state ? null : state.getDescription();
	}
}
