package com.evchong.wxpayscore.allocation.api.dto;

import java.io.Serializable;

/**
 * 微信支付分普通直连分账 - 额外参数校验
 * <p>
 * 辅助javax.validation完成它无法(或难以)校验的边边角角
 */
public interface WxPayAllocationExtraParamValidation extends Serializable {
	/**
	 * 检查参数是否有错误
	 * 
	 * @throws java.lang.IllegalArgumentException 参数有错
	 */
	default void checkParam() {
		if (hasParamError()) {
			throw new IllegalArgumentException(paramErrMsg());
		}
	}

	/**
	 * 是否有参数错误
	 * <p>
	 * 如条件选填时的参数校验可以在此实现
	 * <p>
	 * 子类选择实现
	 */
	default boolean hasParamError() {
		return false;
	}

	/**
	 * 出错时的错误描述
	 * <p>
	 * 如条件选填时的参数校验可以在此实现
	 * <p>
	 * 子类选择实现
	 */
	default String paramErrMsg() {
		return null;
	}
}
