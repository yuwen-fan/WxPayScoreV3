package com.evchong.wxpayscore.exception;

import com.evchong.wxpayscore.api.dto.WxPayScoreResponse;

import lombok.Getter;
import lombok.ToString;

/**
 * 异常 - 微信支付分返回的响应含错
 * <p>
 * 此时业务请求方需检查请求参数等
 * 
 * @author fanyuwen
 *
 */
@ToString(of = "error")
public class WxPayScoreResponseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Getter
	private final WxPayScoreResponse error;

	public WxPayScoreResponseException(WxPayScoreResponse error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		return "Error: " + error + ", message: " + super.getMessage();
	}
}
