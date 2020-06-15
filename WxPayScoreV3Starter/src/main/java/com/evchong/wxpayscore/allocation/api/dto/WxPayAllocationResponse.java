package com.evchong.wxpayscore.allocation.api.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.evchong.wxpayscore.util.JacksonJsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 微信支付分 - 普通直连分账响应
 * 
 * @author fanyuwen
 *
 */
public interface WxPayAllocationResponse extends Serializable {

	/**
	 * 转为需要作为校验签名输入的原始Map信息
	 * <p>
	 * 注意：使用了序列化反序列，性能较差
	 */
	@SuppressWarnings("unchecked")
	@NotEmpty
	default Map<String, Object> mapToCheckSign() {
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
