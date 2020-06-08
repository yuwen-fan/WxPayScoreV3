package com.evchong.wxpayscore.configuration.inteceptor;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.interceptor.WxPayScoreCheckSignHandlerInterceptor;
import com.evchong.wxpayscore.interceptor.WxPayScoreMessageDecodingHandlerInterceptor;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;

import lombok.Data;
import lombok.Setter;

@ConfigurationProperties(prefix = "wxpayscore.interceptor.path-patterns")
@Data
public class WxPayScoreWebMvcConfiguration implements WebMvcConfigurer {
	@Setter
	private String[] checkSign;
	@Setter
	private String[] messageDecoding;

	@Autowired
	private MerchantConfiguration mchConfig;
	@Resource(name = "wxPayCertificatesVerifier")
	private Verifier wxPayCertificatesVerifier;

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if (null != checkSign && checkSign.length > 0) {
			WxPayScoreCheckSignHandlerInterceptor checkSignInterceptor = new WxPayScoreCheckSignHandlerInterceptor(
					wxPayCertificatesVerifier);
			registry.addInterceptor(checkSignInterceptor).addPathPatterns(checkSign);
		}
		if (null != messageDecoding && messageDecoding.length > 0) {
			WxPayScoreMessageDecodingHandlerInterceptor messageDecodingInterceptor = new WxPayScoreMessageDecodingHandlerInterceptor(
					mchConfig.getApiV3Key());
			registry.addInterceptor(messageDecodingInterceptor).addPathPatterns(messageDecoding);
		}
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
