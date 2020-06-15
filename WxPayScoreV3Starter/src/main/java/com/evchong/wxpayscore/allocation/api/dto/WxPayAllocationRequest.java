package com.evchong.wxpayscore.allocation.api.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;

import com.evchong.wxpayscore.util.JacksonJsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 微信支付分 - 普通直连分账请求
 * 
 * @author fanyuwen
 *
 */
public interface WxPayAllocationRequest extends WxPayAllocationExtraParamValidation {

	/**
	 * 请求的地址
	 */
	String requestUrl();

	/**
	 * 请求方式 - GET/POST
	 */
	HttpMethod httpMethod();

	/**
	 * 支持的签名算法
	 * <p>
	 * 目前仅支持 HMAC-SHA256
	 */
	default String supportedSignType() {
		return "HMAC-SHA256";
	}

	/**
	 * 转为需要作为签名输入的原始Map信息
	 * <p>
	 * 注意：使用了序列化反序列，性能较差
	 */
	@SuppressWarnings("unchecked")
	@NotEmpty
	default Map<String, Object> mapToSign() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			return JacksonJsonUtil.jsonToBean(JacksonJsonUtil.beanToJsonNoException(mapper, this), Map.class);
		} catch (Exception e) {
			Log plog = LogFactory.getLog(getClass());
			plog.error(e.getMessage(), e);
			return new HashMap<>();
		}
	}
}
