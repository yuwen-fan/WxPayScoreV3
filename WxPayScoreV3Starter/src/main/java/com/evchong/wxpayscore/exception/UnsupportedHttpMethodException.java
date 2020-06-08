package com.evchong.wxpayscore.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
public class UnsupportedHttpMethodException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter
	private final String method;

	public UnsupportedHttpMethodException() {
		this.method = null;
	}

	public UnsupportedHttpMethodException(String method) {
		super(method);
		this.method = method;
	}
}
