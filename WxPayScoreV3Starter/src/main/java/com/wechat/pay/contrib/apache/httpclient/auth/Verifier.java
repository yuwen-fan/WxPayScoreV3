package com.wechat.pay.contrib.apache.httpclient.auth;

/**
 * 来自官方：https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
 */
@FunctionalInterface
public interface Verifier {
  boolean verify(String serialNumber, byte[] message, String signature);
}
