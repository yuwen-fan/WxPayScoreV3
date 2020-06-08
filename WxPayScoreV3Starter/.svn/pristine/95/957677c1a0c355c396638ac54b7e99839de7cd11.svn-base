package com.evchong.wxpayscore.allocation.template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.configuration.MerchantConfiguration;

import lombok.Setter;

/**
 * 普通直连分账Template
 * 
// * @throws UnsupportedHttpMethodException
// * @throws WxPayScoreResponseException
 * @author fanyuwen
 *
 */
@Validated
public class WxPayAllocationTemplate {
	private final Log log = LogFactory.getLog(getClass());

	@Setter
	private RestTemplate restTemplate;
	@Setter
	private MerchantConfiguration mchConfig;

//
//	private <T extends CommonResponse> T dispatch(WxPayScoreRequest request, Class<T> target) {
//		request.checkParam();
//		log.info("request: " + request);
//
//		ResponseEntity<T> entity;
//		if (isGet(request.httpMethod())) {
//			entity = doGet(request, target);
//		} else {
//			entity = doPost(request, target);
//		}
//
//		log.info("response: " + entity);
//		T response = entity.getBody();
//		if (response.hasError()) {
//			throw new WxPayScoreResponseException(response);
//		}
//
//		return response;
//	}
//
//	private boolean isGet(HttpMethod httpMethod) {
//		if (null == httpMethod) {
//			throw new UnsupportedHttpMethodException();
//		}
//		
//		if (HttpMethod.GET.equals(httpMethod)) {
//			return true;
//		}
//
//		if (!HttpMethod.POST.equals(httpMethod)) {
//			throw new UnsupportedHttpMethodException(httpMethod.name());
//		}
//
//		return false;
//	}
//
//	private <T extends CommonResponse> ResponseEntity<T> doGet(WxPayScoreRequest request, Class<T> target) {
//		return restTemplate.getForEntity(request.requestUrl(), target);
//	}
//
//	private <T extends CommonResponse> ResponseEntity<T> doPost(WxPayScoreRequest request, Class<T> target) {
//		return restTemplate.postForEntity(request.requestUrl(), request, target);
//	}
}