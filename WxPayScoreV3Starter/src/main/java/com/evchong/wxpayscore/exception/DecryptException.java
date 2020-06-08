package com.evchong.wxpayscore.exception;

/**
 * 解密异常
 * 
 * @author fanyuwen
 *
 */
public class DecryptException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DecryptException() {
		super();
	}

	public DecryptException(String message) {
		super(message);
	}

	public DecryptException(Throwable t) {
		super(t);
	}

}
