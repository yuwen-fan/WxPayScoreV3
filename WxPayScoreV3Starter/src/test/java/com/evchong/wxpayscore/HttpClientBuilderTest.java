package com.evchong.wxpayscore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

public class HttpClientBuilderTest {

  private static String mchId = "1235000902"; // 商户号
  private static String mchSerialNo = "24B6BD4A93DCB76EE0D5A795C402D17A63DECEE4"; // 商户证书序列号

  private CloseableHttpClient httpClient;

  private static String reqdata = "{\n"
      + "    \"stock_id\": \"9433645\",\n"
      + "    \"stock_creator_mchid\": \"1900006511\",\n"
      + "    \"out_request_no\": \"20190522_001中文11\",\n"
      + "    \"appid\": \"wxab8acb865bb1637e\"\n"
      + "}";

  // 你的商户私钥
  private static String privateKey = "-----BEGIN PRIVATE KEY-----\n"
	  + "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDpGdduJokI12in\n" + 
	  "0yX8FEXh5wEzsqIDzSnXmsuN7f086sv4pFc7h2JX7nhmqfGXslLbIR4LNGgjuOPP\n" + 
	  "kBv0v7Xv2LXD2xbV7+PhL0t8soN9BqhguLYwRofbv+XTX1ZkbS5p40gbA/QtdXdA\n" + 
	  "zgPyyQJmdHEqPLggWShbXF3aM68RRFU2G/ZD7tlOquxHvtHEksk9RLCGrVqcvku+\n" + 
	  "LFFxa3EYkx3nW1iQCAgWSZBhCCVaVaB1QA6GS7heitw4K1BHBRfEcsRbCg5X7J4Q\n" + 
	  "ggj1+yhDrYJpmpjkDZy1InygxKfGRPoFuAOenkEJVffnOVS2UCke5542V+dKcqIg\n" + 
	  "y+uVLTJbAgMBAAECggEAUhR0ZGgTVtarFnVmabYlyV8TcEXVgOsscawjQy3rPxka\n" + 
	  "i8oqoV6RxY7GPLhYxubXACxQLPfeN73yEfOXtH4RLYysrHOoPdsD7hY+SYF+hXbj\n" + 
	  "sXM5TBR54GpZHi3y+NftQQH0MPcacytqu5oPj90yCCu2cX4CA/oBGPKUZbJPUg+G\n" + 
	  "urzCw3dQWUVyVzt0cSG25dlFUg075T8rIID2zRKV/Ct+arK8yPlkD3XCOXPADfyC\n" + 
	  "DwTQHrYvzs0CRJdbdgK43n82ahCMB2gSiFegcuaArIzY/PcPCyA2pLDFIWQLSjkW\n" + 
	  "U/wyt85cxWs2SofoZ6yNcg96OhfKjKyqEuCYLqekOQKBgQD2Z6T11RfYt9vcCctS\n" + 
	  "ugJWGSc9PkLskyrlOJetxP9tAFl1LzU/XvXXSxwiL/bGYZQjjUPiDfjUDiFSbUYJ\n" + 
	  "eEqkmvgh8O2scSAT/YeO0IA7JVtBi7RWqOH0l4lnYmUobZfGz5qvfHFvla+sZyCK\n" + 
	  "ffBHaGLBsL+OcelUItB/U8xVNQKBgQDyLZLGFGgMmL3IzgLPAZ8PLyl+6onCBPiq\n" + 
	  "wqMaBDLrDHYFs4KWxWAZMysD/kLGYA+6eq8pmiTTpBokjGlGb/ekTYrdaqp8jFT/\n" + 
	  "qJHby3sWFdzHzsddIV1bc27uMYZqkNDH/m51DBk4k9TYehIq/d50csMRxkH6hoVv\n" + 
	  "lnO6oHMrTwKBgQCpSq5gpEadBD1PIX+LV/cw9lkqT7OlXLt0/3vezOF9H2dXBtc7\n" + 
	  "sd+QSq+KoY+X3wTrpCwGjPjvoMGuIxuid/44t6xRjXmOJ68VbPQM4B2qBVF8aCLA\n" + 
	  "A4ozBUZHPKgprZpA4vPxK7u4IYywE66c2EgUl5h6YRhLIuJfhwfe41EZHQKBgQDV\n" + 
	  "8yEZ0Wlix4XS4v1kOAB3f6jfMQDm4VF2OmGxWEhLk0SpIL+bx19wo926t2PysWbz\n" + 
	  "zfL1g/JI708RPuK6kaxsAP1PQy6+vZNSEXBah1eEWWglNS63vgQmCMLVt/cSKkHo\n" + 
	  "jYSz7y5Q53lAjgeC56kho0lmaIpdc35q6LXxCFL2zQKBgD5LrDl4ZdWXqJnDqEXb\n" + 
	  "2gBz9ZjO72VoENSWfpFqh/b1GUFG/35YXH6ANipFXYBj4Bb1oDbUXDo6x52Fa+LS\n" + 
	  "hggf3EUQWOEpSLxAA5faFz3XL4GuMx1tSk7VTUCQm3SuxhLKREpCHsISiVnHqYxq\n" + 
	  "RgIxgejHCnVeStgkiKNt4FIn"
      + "-----END PRIVATE KEY-----";

  // 你的微信支付平台证书
  private static String certificate =  "-----BEGIN CERTIFICATE-----\n"
	  + "MIID3DCCAsSgAwIBAgIUVeybsTJ4V5xaiV5bjV2AljWRr2swDQYJKoZIhvcNAQEL\n" + 
	  "BQAwXjELMAkGA1UEBhMCQ04xEzARBgNVBAoTClRlbnBheS5jb20xHTAbBgNVBAsT\n" + 
	  "FFRlbnBheS5jb20gQ0EgQ2VudGVyMRswGQYDVQQDExJUZW5wYXkuY29tIFJvb3Qg\n" + 
	  "Q0EwHhcNMjAwNjAxMDUyNDMwWhcNMjUwNTMxMDUyNDMwWjBuMRgwFgYDVQQDDA9U\n" + 
	  "ZW5wYXkuY29tIHNpZ24xEzARBgNVBAoMClRlbnBheS5jb20xHTAbBgNVBAsMFFRl\n" + 
	  "bnBheS5jb20gQ0EgQ2VudGVyMQswCQYDVQQGDAJDTjERMA8GA1UEBwwIU2hlblpo\n" + 
	  "ZW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDOVls7v0y619MDQatL\n" + 
	  "+nJfIg1djKEhYidRjKA6glHsWQc4gr6K5WHTw9jqWJCeVXptyvzrKjcj4s3/bSOt\n" + 
	  "LHStu+VrA0fvez2m6Sl1MVS5tDZv4pgS+jxSQvvyMsbB2tZuRaFRp3VfSq6yK+al\n" + 
	  "sUT6ep2UGXQjaTFYrZAp6D5nErHAlVi8hVLds4Lr13sF3TZM3rb5u9/azW+olxjv\n" + 
	  "WEbiwdrjuataBVjRfxZyCrgAJyGACLWlOwQ9qHEYprJMsaT5dlov6ckpHT7X89dG\n" + 
	  "nO1yztSC6zBkcJSKYLdpYRes6OvD/V15gpuURDy8UeYUxajGrdgB9SpcnvVoQPw6\n" + 
	  "C5A/AgMBAAGjgYEwfzAJBgNVHRMEAjAAMAsGA1UdDwQEAwIE8DBlBgNVHR8EXjBc\n" + 
	  "MFqgWKBWhlRodHRwOi8vZXZjYS5pdHJ1cy5jb20uY24vcHVibGljL2l0cnVzY3Js\n" + 
	  "P0NBPTFCRDQyMjBFNTBEQkMwNEIwNkFEMzk3NTQ5ODQ2QzAxQzNFOEVCRDIwDQYJ\n" + 
	  "KoZIhvcNAQELBQADggEBALkaID0aj4VVNzQZ6EUSwibeWqTMMobEcP1PhYF4XwG2\n" + 
	  "TfDPEoB/JPwelNhuknqIUfw+B+eLlke9oBtT/QVKdNrNg4+P6j8ya7or4KdZCPhp\n" + 
	  "zMjSpQHRqVd/+FUn0FOxK6l9V4BLKaoGpw9NPOlNx8xmN0bbasHgtOWQgb9D6Yw1\n" + 
	  "qNJuByMOjwvj+4NCb2vpcGRdaUCa1vfaLeKcoe7UlkRgVmn4cgCexK3Y0Fz8ldlU\n" + 
	  "U9AZyKJOmvNqJBpGPSKUa7IIsCx0OtE07ytfFrSJ894QF6qz2N7HZVE5rLmDZnKT\n" + 
	  "RcCaJXyQ0FHDrmW972/w0nNanl37QcsNCVAv5cPwAwY=\n"
      + "-----END CERTIFICATE-----";

  @Before
  public void setup() throws IOException  {
    PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
        new ByteArrayInputStream(privateKey.getBytes("utf-8")));
    X509Certificate wechatpayCertificate = PemUtil.loadCertificate(
        new ByteArrayInputStream(certificate.getBytes("utf-8")));

    ArrayList<X509Certificate> listCertificates = new ArrayList<>();
    listCertificates.add(wechatpayCertificate);

    httpClient = WechatPayHttpClientBuilder.create()
        .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
        .withWechatpay(listCertificates)
        .build();
  }

  @After
  public void after() throws IOException {
    httpClient.close();
  }

  @Test
  public void getCertificateTest() throws Exception {
    URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
    uriBuilder.setParameter("p", "1&2");
    uriBuilder.setParameter("q", "你好");

    HttpGet httpGet = new HttpGet(uriBuilder.build());
    httpGet.addHeader("Accept", "application/json");

    CloseableHttpResponse response1 = httpClient.execute(httpGet);

    assertEquals(200, response1.getStatusLine().getStatusCode());

    try {
      HttpEntity entity1 = response1.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      EntityUtils.consume(entity1);
    } finally {
      response1.close();
    }
  }

  @Test
  public void getCertificatesWithoutCertTest() throws Exception {
    PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
        new ByteArrayInputStream(privateKey.getBytes("utf-8")));

    httpClient = WechatPayHttpClientBuilder.create()
        .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
        .withValidator(response -> true)
        .build();

    getCertificateTest();
  }

  @Test
  public void postNonRepeatableEntityTest() throws IOException {
    HttpPost httpPost = new HttpPost(
        "https://api.mch.weixin.qq.com/v3/marketing/favor/users/oHkLxt_htg84TUEbzvlMwQzVDBqo/coupons");


    InputStream stream = new ByteArrayInputStream(reqdata.getBytes("utf-8"));
    InputStreamEntity reqEntity = new InputStreamEntity(stream);
    reqEntity.setContentType("application/json");
    httpPost.setEntity(reqEntity);
    httpPost.addHeader("Accept", "application/json");

    CloseableHttpResponse response = httpClient.execute(httpPost);
    assertTrue(response.getStatusLine().getStatusCode() != 401);
    try {
      HttpEntity entity2 = response.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      EntityUtils.consume(entity2);
    } finally {
      response.close();
    }
  }

  @Test
  public void postRepeatableEntityTest() throws IOException {
    HttpPost httpPost = new HttpPost(
        "https://api.mch.weixin.qq.com/v3/marketing/favor/users/oHkLxt_htg84TUEbzvlMwQzVDBqo/coupons");

    // NOTE: 建议指定charset=utf-8。低于4.4.6版本的HttpCore，不能正确的设置字符集，可能导致签名错误
    StringEntity reqEntity = new StringEntity(
        reqdata, ContentType.create("application/json", "utf-8"));
    httpPost.setEntity(reqEntity);
    httpPost.addHeader("Accept", "application/json");

    CloseableHttpResponse response = httpClient.execute(httpPost);
    assertTrue(response.getStatusLine().getStatusCode() != 401);
    try {
      HttpEntity entity2 = response.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      EntityUtils.consume(entity2);
    } finally {
      response.close();
    }
  }

}
