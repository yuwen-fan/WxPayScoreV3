package com.evchong.wxpayscore.allocation.exception;

import com.evchong.wxpayscore.allocation.api.dto.WxPayAllocationResponse;

import lombok.Getter;
import lombok.ToString;

/**
 * 异常 - 微信支付分普通直连分账返回的响应含错
 * <p>
 * 此时业务请求方需检查请求参数等
 * 
 * @author fanyuwen
 *
 */
@ToString(of = "error")
public class WxPayAllocationResponseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Getter
	private final WxPayAllocationResponse error;

	public WxPayAllocationResponseException(WxPayAllocationResponse error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		return "Error: " + error + ", message: " + super.getMessage();
	}
}
