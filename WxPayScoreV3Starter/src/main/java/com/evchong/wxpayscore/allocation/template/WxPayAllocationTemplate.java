package com.evchong.wxpayscore.allocation.template;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.allocation.api.dto.ProfitSharingAddReceiverRequest;
import com.evchong.wxpayscore.allocation.api.dto.ProfitSharingAddReceiverResponse;
import com.evchong.wxpayscore.allocation.api.dto.ProfitSharingRequest;
import com.evchong.wxpayscore.allocation.api.dto.ProfitSharingResponse;
import com.evchong.wxpayscore.allocation.api.dto.WxPayAllocationCommonResponse;
import com.evchong.wxpayscore.allocation.api.dto.WxPayAllocationRequest;
import com.evchong.wxpayscore.allocation.api.model.ProfitSharingReceiver;
import com.evchong.wxpayscore.allocation.exception.WxPayAllocationResponseException;
import com.evchong.wxpayscore.allocation.util.WxPayAllocationSignUtil;
import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.exception.SignException;
import com.evchong.wxpayscore.exception.UnsupportedHttpMethodException;
import com.evchong.wxpayscore.util.JacksonJsonUtil;
import com.evchong.wxpayscore.util.NonceStringUtil;

import lombok.Setter;

/**
 * 普通直连分账Template
 * 
 * @throws UnsupportedHttpMethodException
 * @throws WxPayAllocationResponseException
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

	/**
	 * 添加分账接收方
	 * <p>
	 * 需事先开通分账
	 * 
	 * @throws UnsupportedHttpMethodException
	 * @throws WxPayAllocationResponseException
	 * @throws SignException
	 */
	@NotNull
	public ProfitSharingAddReceiverResponse addProfitSharingReceiver(@NotNull ProfitSharingReceiver receiver) {
		ProfitSharingAddReceiverRequest request = new ProfitSharingAddReceiverRequest();
		request.setReceiver(JacksonJsonUtil.beanToJsonNoException(receiver));
		request.setAppId(mchConfig.getAppId());
		request.setMchId(mchConfig.getMchId());
		request.setNonceStr(NonceStringUtil.generateNonceStr(32));
		request.setSignType(request.supportedSignType());
		
		request.setSign(WxPayAllocationSignUtil.hmacSha256(request.mapToSign(), mchConfig.getApiKey()));
		return dispatch(request, ProfitSharingAddReceiverResponse.class);
	}

	/**
	 * 请求单次分账
	 * @throws UnsupportedHttpMethodException
	 * @throws WxPayAllocationResponseException
	 * @throws SignException
	 */
	@NotNull
	public ProfitSharingResponse profitSharing(@NotEmpty List<ProfitSharingReceiver> receivers, @NotBlank String wxTrxId,
			@NotBlank String outOrderNo) {
		ProfitSharingRequest request = new ProfitSharingRequest();
		request.setReceivers(JacksonJsonUtil.beanToJsonNoException(receivers));
		request.setAppId(mchConfig.getAppId());
		request.setMchId(mchConfig.getMchId());
		request.setNonceStr(NonceStringUtil.generateNonceStr(32));
		request.setSignType(request.supportedSignType());
		request.setTransactionId(wxTrxId);
		request.setOutOrderNo(outOrderNo);
		
		request.setSign(WxPayAllocationSignUtil.hmacSha256(request.mapToSign(), mchConfig.getApiKey()));
		return dispatch(request, ProfitSharingResponse.class);
	}
	
	private <T extends WxPayAllocationCommonResponse> T dispatch(WxPayAllocationRequest request, Class<T> target) {
		request.checkParam();
		log.info("request: " + request);

		ResponseEntity<T> entity = restTemplate.postForEntity(request.requestUrl(), request, target);
		log.info("response: " + entity);
		T response = entity.getBody();
		if (response.hasError()) {
			throw new WxPayAllocationResponseException(response);
		}

		return response;
	}

}