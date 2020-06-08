package com.evchong.wxpayscore.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JacksonJsonUtil {
	private static final Log log = LogFactory.getLog(JacksonJsonUtil.class);

	private JacksonJsonUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 将java对象转换成JSON字符串
	 * 
	 * @param bean 准备转换的对象
	 * @throws JsonParseException
	 * @return JSON字符串
	 */
	public static String beanToJson(Object bean) throws JsonParseException {
		return beanToJson(bean, false);
	}

	/**
	 * 将java对象转换成JSON字符串
	 * 
	 * @param bean 准备转换的对象
	 * @param ignoreCache 是否不使用已有的ObjectMapper实例
	 * @throws JsonParseException
	 * @return JSON字符串
	 */
	public static String beanToJson(Object bean, Boolean ignoreCache) throws JsonParseException {
		try {
			return getMapperInstance(ignoreCache).writeValueAsString(bean);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonParseException(null, e.getMessage());
		}
	}

	/**
	 * 将java对象转换成JSON字符串,异常不抛出
	 * 
	 * @param bean 准备转换的对象
	 * @return JSON字符串
	 */
	public static String beanToJsonNoException(Object bean) {
		try {
			return beanToJson(bean, false);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 将JSON字符串转换成java对象
	 * 
	 * @param json 准备转换的JSON字符串
	 * @param targetType 准备转换的类
	 * @throws ExceptionJsonParseException
	 */
	public static <T> T jsonToBean(String json, Class<T> targetType) throws JsonParseException {
		return jsonToBean(json, targetType, false);
	}

	/**
	 * 将JSON字符串转换成java对象
	 * 
	 * @param json 准备转换的JSON字符串
	 * @param targetType 准备转换的类
	 * @param ignoreCache 是否不使用已有的ObjectMapper实例
	 * @throws JsonParseException 
	 */
	public static <T> T jsonToBean(String json, Class<T> targetType, Boolean ignoreCache)
			throws JsonParseException {
		try {
			return getMapperInstance(ignoreCache).readValue(json, targetType);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new JsonParseException(null, e.getMessage());
		}
	}
	
	/**
	 * 获取ObjectMapper实例
	 * @param ignoreCache 是否不使用已有的ObjectMapper实例
	 * @return notUseCachedObjectMapper为true时返回新创建的实例，否则返回已有实例
	 */
	private static ObjectMapper getMapperInstance(boolean ignoreCache) {
		return ignoreCache ? new ObjectMapper() : SingletonObjectMapperHolder.INSTANCE;
	}
	
	/**
	 * 静态内部类实现单例
	 * 
	 * @author fanyuwen
	 *
	 */
	private static class SingletonObjectMapperHolder {
		private static final ObjectMapper INSTANCE = new ObjectMapper();
		private SingletonObjectMapperHolder() {
			throw new UnsupportedOperationException();
		}
	}
}
