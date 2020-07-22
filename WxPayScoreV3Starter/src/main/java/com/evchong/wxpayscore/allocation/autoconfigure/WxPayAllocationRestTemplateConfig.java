package com.evchong.wxpayscore.allocation.autoconfigure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

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
import org.apache.http.ssl.SSLContexts;
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
	public HttpClient httpClient(HttpPoolProperties httpPoolProperties, MerchantConfiguration merchant) throws GeneralSecurityException {
		KeyStore keystore = getCertificate(merchant);
		SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keystore, merchant.getMchId().toCharArray()).build();
		SSLConnectionSocketFactory sslCSF = new SSLConnectionSocketFactory(sslContext);
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslCSF).build();
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
	
	/** 用于双方都需要证书的请求(如请求单次分账) */
	private KeyStore getCertificate(MerchantConfiguration merchant) throws GeneralSecurityException {
		try (FileInputStream inputStream = new FileInputStream(new File(merchant.getMchCertP12Path()))) {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(inputStream, merchant.getMchId().toCharArray());
			return keyStore;
		} catch (Exception e) {
			throw new GeneralSecurityException(e);
		}
	}
}