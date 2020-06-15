package com.evchong.wxpayscore.allocation.autoconfigure;

import java.io.IOException;

import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.configuration.httppool.HttpPoolProperties;
import com.evchong.wxpayscore.configuration.httppool.RetryOneTimeRestTemplate;

/**
 * 微信支付普通直连分账 RestTemplate 定制化配置
 * 
 * @author fanyuwen
 *
 */
@Configuration
@EnableConfigurationProperties({ HttpPoolProperties.class, MerchantConfiguration.class })
public class WxPayAllocationRestTemplateConfig {

	@Bean(name = "wxPayAllocationRestTemplate")
	public RestTemplate restTemplate(ClientHttpRequestFactory wxPayAllocationHttpRequestFactory) {
		RetryOneTimeRestTemplate template = new RetryOneTimeRestTemplate(wxPayAllocationHttpRequestFactory);
		template.setErrorHandler(new DefaultResponseErrorHandler() {
			/**
			 * 错误发生时不抛出父类会抛出的这三类异常：HttpClientErrorException | HttpServerErrorException |
			 * UnknownHttpStatusCodeException
			 */
			@Override
			protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
				return;
			}
		});
		return template;
	}

	@Bean(name = "wxPayAllocationHttpRequestFactory")
	public ClientHttpRequestFactory httpRequestFactory(HttpClient wxPayAllocationHttpClient) {
		return new HttpComponentsClientHttpRequestFactory(wxPayAllocationHttpClient);
	}

	@Bean(name = "wxPayAllocationHttpClient")
	public HttpClient httpClient(HttpPoolProperties httpPoolProperties) {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		connectionManager.setMaxTotal(httpPoolProperties.getMaxTotal());
		connectionManager.setDefaultMaxPerRoute(httpPoolProperties.getDefaultMaxPerRoute());
		connectionManager.setValidateAfterInactivity(httpPoolProperties.getValidateAfterInactivity());

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(httpPoolProperties.getSocketTimeout()) // 服务器返回数据(response)的超时时间
				.setConnectTimeout(httpPoolProperties.getConnectTimeout()) // 连接上服务器(握手成功)的超时时间ConnectTimeoutException
				.setConnectionRequestTimeout(httpPoolProperties.getConnectionRequestTimeout())// 从连接池中获取连接的超时时间ConnectionPoolTimeoutException
				.build();

		HttpResponseInterceptor itcp = (response, context) -> {
			response.removeHeaders("Content-Type");
			response.setHeader("Content-Type", MediaType.APPLICATION_XML_VALUE);
		};
		return HttpClientBuilder.create().addInterceptorLast(itcp).setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
				.build();
	}
}