package com.evchong.wxpayscore.allocation.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.allocation.template.WxPayAllocationTemplate;
import com.evchong.wxpayscore.configuration.MerchantConfiguration;

/**
 * 普通直连分账Bean配置
 * 
 * @author fanyuwen
 *
 */
@Configuration
@EnableConfigurationProperties
public class WxPayAllocationAutoConfiguration {

	@Bean(name = "wxPayAllocationTemplate")
	public WxPayAllocationTemplate wxPayAllocationTemplate(RestTemplate wxPayAllocationRestTemplate,
			MerchantConfiguration mchConfig) {
		WxPayAllocationTemplate template = new WxPayAllocationTemplate();
		template.setRestTemplate(wxPayAllocationRestTemplate);
		template.setMchConfig(mchConfig);
		return template;
	}
}
