package com.wechat.pay.contrib.apache.httpclient.auth;

/**
 * 来自官方：https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
 */
public interface Signer {
  SignatureResult sign(byte[] message);

  class SignatureResult {
    String sign;
    String certificateSerialNumber;

    public SignatureResult(String sign, String serialNumber) {
      this.sign = sign;
      this.certificateSerialNumber = serialNumber;
    }
  }
}
