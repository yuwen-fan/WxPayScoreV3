package com.wechat.pay.contrib.apache.httpclient;

import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
/**
 * 来自官方：https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
 */
@FunctionalInterface
public interface Validator {
  boolean validate(CloseableHttpResponse response) throws IOException;
}
