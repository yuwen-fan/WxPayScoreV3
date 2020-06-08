package com.evchong.wxpayscore.template;

import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.api.autoconfirm.dto.QueryUserServiceStateRequest;
import com.evchong.wxpayscore.api.autoconfirm.dto.QueryUserServiceStateResponse;
import com.evchong.wxpayscore.api.common.dto.CompleteServiceOrderRequest;
import com.evchong.wxpayscore.api.common.dto.CompleteServiceOrderResponse;
import com.evchong.wxpayscore.api.common.dto.CreateServiceOrderRequest;
import com.evchong.wxpayscore.api.common.dto.CreateServiceOrderResponse;
import com.evchong.wxpayscore.api.common.dto.WxOpenBusinessViewExtraData;
import com.evchong.wxpayscore.api.dto.WxPayScoreCommonResponse;
import com.evchong.wxpayscore.api.dto.WxPayScoreRequest;
import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.exception.SignException;
import com.evchong.wxpayscore.exception.UnsupportedHttpMethodException;
import com.evchong.wxpayscore.exception.WxPayScoreResponseException;
import com.evchong.wxpayscore.util.NonceStringUtil;
import com.evchong.wxpayscore.util.WxPayScoreSignUtil;

import lombok.Setter;

/**
 * 微信支付分Template
 * 
 * @author fanyuwen
 *
 */
@Validated
public class WxPayScoreTemplate {
	private final Log log = LogFactory.getLog(getClass());

	@Setter
	private RestTemplate restTemplate;
	@Setter
	private MerchantConfiguration mchConfig;

	/**
	 * 获取小程序调用支付分授权服务时需要传递给支付分的业务数据
	 * 
	 * @see https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter8_1.shtml
	 * @throws SignException
	 * @return 需要传递给支付分的业务数据
	 */
	@NotEmpty
	public Map<String, String> getWxOpenBusinessViewExtraData() {
		WxOpenBusinessViewExtraData data = new WxOpenBusinessViewExtraData();
		data.setMchId(mchConfig.getMchId());
		data.setServiceId(mchConfig.getServiceId());
		data.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
		data.setNonceStr(NonceStringUtil.generateNonceStr(32));
		data.setOutRequestNo(NonceStringUtil.generateNonceStr(64));

		String sha256 = WxPayScoreSignUtil.hmacSha256(data.toMap(), mchConfig.getApiKey());
		data.setSign(sha256);
		return data.toMap();
	}

	/**
	 * 查询用户授权状态
	 * 
	 * @throws UnsupportedHttpMethodException
	 * @throws WxPayScoreResponseException
	 */
	@NotNull
	public QueryUserServiceStateResponse queryUserServiceState(@NotNull QueryUserServiceStateRequest request) {
		return dispatch(request, QueryUserServiceStateResponse.class);
	}

	/**
	 * 创建支付分订单
	 * 
	 * @throws UnsupportedHttpMethodException
	 * @throws WxPayScoreResponseException
	 */
	@NotNull
	public CreateServiceOrderResponse createServiceOrder(@NotNull CreateServiceOrderRequest request) {
		return dispatch(request, CreateServiceOrderResponse.class);
	}

	/**
	 * 完结支付分订单
	 * 
	 * @throws UnsupportedHttpMethodException
	 * @throws WxPayScoreResponseException
	 */
	@NotNull
	public CompleteServiceOrderResponse completeServiceOrder(@NotNull CompleteServiceOrderRequest request) {
		return dispatch(request, CompleteServiceOrderResponse.class);
	}

	private <T extends WxPayScoreCommonResponse> T dispatch(WxPayScoreRequest request, Class<T> target) {
		request.checkParam();
		log.info("request: " + request);

		ResponseEntity<T> entity;
		if (isGet(request.httpMethod())) {
			entity = doGet(request, target);
		} else {
			entity = doPost(request, target);
		}

		log.info("response: " + entity);
		T response = entity.getBody();
		if (response.hasError()) {
			throw new WxPayScoreResponseException(response);
		}

		return response;
	}

	private boolean isGet(HttpMethod httpMethod) {
		if (null == httpMethod) {
			throw new UnsupportedHttpMethodException();
		}

		if (HttpMethod.GET.equals(httpMethod)) {
			return true;
		}

		if (!HttpMethod.POST.equals(httpMethod)) {
			throw new UnsupportedHttpMethodException(httpMethod.name());
		}

		return false;
	}

	private <T extends WxPayScoreCommonResponse> ResponseEntity<T> doGet(WxPayScoreRequest request, Class<T> target) {
		return restTemplate.getForEntity(request.requestUrl(), target);
	}

	private <T extends WxPayScoreCommonResponse> ResponseEntity<T> doPost(WxPayScoreRequest request, Class<T> target) {
		return restTemplate.postForEntity(request.requestUrl(), request, target);
	}
}