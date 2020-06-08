package com.wechat.pay.contrib.apache.httpclient.auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.SecureRandom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

import com.wechat.pay.contrib.apache.httpclient.Credentials;
/**
 * 来自官方：https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
 * <p> 修改点：获取body时返回UTF-8内容，使其支持中文
 */
public class WechatPay2Credentials implements Credentials {
	private final Log log = LogFactory.getLog(getClass());

  private static final String SYMBOLS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final SecureRandom RANDOM = new SecureRandom();
  protected String merchantId;
  protected Signer signer;

  public WechatPay2Credentials(String merchantId, Signer signer) {
    this.merchantId = merchantId;
    this.signer = signer;
  }

  public String getMerchantId() {
    return merchantId;
  }

  protected long generateTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  protected String generateNonceStr() {
    char[] nonceChars = new char[32];
    for (int index = 0; index < nonceChars.length; ++index) {
      nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(nonceChars);
  }

  @Override
  public final String getSchema() {
    return "WECHATPAY2-SHA256-RSA2048";
  }

  @Override
  public final String getToken(HttpUriRequest request) throws IOException {
    String nonceStr = generateNonceStr();
    long timestamp = generateTimestamp();

    String message = buildMessage(nonceStr, timestamp, request);
    log.info("authorization message=[" + message + "]");

    Signer.SignatureResult signature = signer.sign(message.getBytes("utf-8"));

    String token = "mchid=\"" + getMerchantId() + "\","
        + "nonce_str=\"" + nonceStr + "\","
        + "timestamp=\"" + timestamp + "\","
        + "serial_no=\"" + signature.certificateSerialNumber + "\","
        + "signature=\"" + signature.sign + "\"";
    log.info("authorization token=[" + token + "]");

    return token;
  }

  protected final String buildMessage(String nonce, long timestamp, HttpUriRequest request)
      throws IOException {
    URI uri = request.getURI();
    String canonicalUrl = uri.getRawPath();
    if (uri.getQuery() != null) {
      canonicalUrl += "?" + uri.getRawQuery();
    }

    String body = "";
    // PATCH,POST,PUT
    if (request instanceof HttpEntityEnclosingRequestBase) {
      body = getBody(request);
    }
    return request.getRequestLine().getMethod() + "\n"
        + canonicalUrl + "\n"
        + timestamp + "\n"
        + nonce + "\n"
        + body + "\n";
  }
  
  /**
   * Modified by fanyuwen 2020-06-05
   * <p> 原代码：body = EntityUtils.toString(((HttpEntityEnclosingRequestBase) request).getEntity());
   */
  private String getBody(HttpUriRequest request) throws IOException {
	  InputStream content = ((HttpEntityEnclosingRequestBase) request).getEntity().getContent();

      ByteArrayOutputStream array = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = content.read(buffer)) != -1) {
        array.write(buffer, 0, length);
      }
      return array.toString("utf-8");
  }

}
