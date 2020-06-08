package com.evchong.wxpayscore.configuration.httppool;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RetryOneTimeRestTemplate extends RestTemplate {

	public RetryOneTimeRestTemplate() {
		super();
	}

	public RetryOneTimeRestTemplate(ClientHttpRequestFactory requestFactory) {
		super(requestFactory);
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,
			Object... uriVariables) throws RestClientException {
		try {
			return super.postForEntity(url, request, responseType, uriVariables);
		} catch (RestClientException e) {
			return super.postForEntity(url, request, responseType, uriVariables);
		}
	}

	@Override
	public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,
			Map<String, ?> uriVariables) throws RestClientException {
		try {
			return super.postForEntity(url, request, responseType, uriVariables);
		} catch (RestClientException e) {
			return super.postForEntity(url, request, responseType, uriVariables);
		}
	}
}
