package com.evchong.wxpayscore.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;

/**
 * 数据校验工具类
 * @author yuwen.fan
 *
 */
public final class ValidationUtil {
	private static Validator validator = Validation
			.byProvider(HibernateValidator.class).configure().failFast(true)
			.buildValidatorFactory().getValidator();

	private ValidationUtil() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 通用校验
	 * @throws IllegalArgumentException 校验失败
	 * @return 被校验对象
	 */
	public static <T> T validate(T obj) {
		return doValidate(obj);
	}
	
	private static <T> T doValidate(T obj) {
		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(obj);
		if (!constraintViolations.isEmpty()) {
			throw new IllegalArgumentException(constraintViolations.iterator().next().getMessage());
		}
		
		return obj;
	}
}