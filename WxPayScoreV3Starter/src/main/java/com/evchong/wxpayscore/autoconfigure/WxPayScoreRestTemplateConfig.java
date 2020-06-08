package com.evchong.wxpayscore.autoconfigure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.evchong.wxpayscore.configuration.MerchantConfiguration;
import com.evchong.wxpayscore.configuration.httppool.HttpPoolProperties;
import com.evchong.wxpayscore.configuration.httppool.RetryOneTimeRestTemplate;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.CertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

/**
 * 微信支付分 RestTemplate 定制化配置
 * 
 * @author fanyuwen
 *
 */
@Configuration
@EnableConfigurationProperties({ HttpPoolProperties.class, MerchantConfiguration.class })
public class WxPayScoreRestTemplateConfig {

	@Bean(name = "wxPayScoreRestTemplate")
	public RestTemplate wxPayScoreRestTemplate(ClientHttpRequestFactory wxPayScoreHttpRequestFactory) {
		RetryOneTimeRestTemplate template = new RetryOneTimeRestTemplate(wxPayScoreHttpRequestFactory);
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

	@Bean(name = "wxPayScoreHttpRequestFactory")
	public ClientHttpRequestFactory wxPayScoreHttpRequestFactory(HttpClient wxPayScoreHttpClient) {
		return new HttpComponentsClientHttpRequestFactory(wxPayScoreHttpClient);
	}

	@Bean(name = "wxPayScoreHttpClient")
	public HttpClient wxPayScoreHttpClient(HttpPoolProperties httpPoolProperties, MerchantConfiguration merchant,
			Verifier wxPayCertificatesVerifier) {
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

		return builder(merchant, wxPayCertificatesVerifier).setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager).build();
	}

	@Bean(name = "wxPayCertificatesVerifier")
	public Verifier certificatesVerifier(MerchantConfiguration merchant) {
		PrivateKey merchantPrivateKey = PemUtil
				.loadPrivateKey(new ByteArrayInputStream(StringUtils.getBytesUtf8(merchant.getPrivateKey())));
		boolean autoUpdateCertificates = !org.springframework.util.StringUtils.isEmpty(merchant.getApiV3Key());
		if (autoUpdateCertificates) {
			return new AutoUpdateCertificatesVerifier(
					new WechatPay2Credentials(merchant.getMchId(),
							new PrivateKeySigner(merchant.getMchSerialNo(), merchantPrivateKey)),
					StringUtils.getBytesUtf8(merchant.getApiV3Key()));
		}
		X509Certificate wechatpayCertificate = PemUtil
				.loadCertificate(new ByteArrayInputStream(StringUtils.getBytesUtf8(merchant.getCertificate())));
		ArrayList<X509Certificate> listCertificates = new ArrayList<>();
		listCertificates.add(wechatpayCertificate);
		return new CertificatesVerifier(listCertificates);
	}

	private WechatPayHttpClientBuilder builder(MerchantConfiguration merchant, Verifier wxPayCertificatesVerifier) {
		PrivateKey merchantPrivateKey = PemUtil
				.loadPrivateKey(new ByteArrayInputStream(StringUtils.getBytesUtf8(merchant.getPrivateKey())));
		return WechatPayHttpClientBuilder.create()
				.withMerchant(merchant.getMchId(), merchant.getMchSerialNo(), merchantPrivateKey)
				.withValidator(new WechatPay2Validator(wxPayCertificatesVerifier));
	}
}