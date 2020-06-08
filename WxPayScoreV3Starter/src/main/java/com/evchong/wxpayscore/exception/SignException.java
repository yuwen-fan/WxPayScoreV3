package com.evchong.wxpayscore.exception;

/**
 * 签名异常
 * 
 * @author fanyuwen
 *
 */
public class SignException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SignException() {
		super();
	}

	public SignException(String message) {
		super(message);
	}

	public SignException(Throwable t) {
		super(t);
	}

}
