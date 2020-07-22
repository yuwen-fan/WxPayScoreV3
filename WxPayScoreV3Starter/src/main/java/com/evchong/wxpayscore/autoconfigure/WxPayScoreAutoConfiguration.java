package com.evchong.wxpayscore.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.template.WxPayScoreTemplate;

/**
 * 微信支付分Bean配置
 * 
 * @author fanyuwen
 *
 */
@EnableConfigurationProperties
public class WxPayScoreAutoConfiguration {

	@Bean(name = "wxPayScoreTemplate")
	public WxPayScoreTemplate wxPayScoreTemplate(RestTemplate wxPayScoreRestTemplate, MerchantConfiguration mchConfig) {
		WxPayScoreTemplate template = new WxPayScoreTemplate();
		template.setRestTemplate(wxPayScoreRestTemplate);
		template.setMchConfig(mchConfig);
		return template;
	}
}
