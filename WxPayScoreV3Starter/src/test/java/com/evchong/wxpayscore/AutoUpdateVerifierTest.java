package com.evchong.wxpayscore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

public class AutoUpdateVerifierTest {

  private static String mchId = "1235000902"; // 商户号
  private static String mchSerialNo = "24B6BD4A93DCB76EE0D5A795C402D17A63DECEE4"; // 商户证书序列号
  private static String apiV3Key = "2e9dbbb0c85f526307db6b0f6ddb26b6"; // api密钥

  private CloseableHttpClient httpClient;
  private AutoUpdateCertificatesVerifier verifier;

  // 你的商户私钥
  private static String privateKey = "-----BEGIN PRIVATE KEY-----\n"
	  + "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDpGdduJokI12in0yX8FEXh5wEzsqIDzSnXmsuN7f086sv4pFc7h2JX7nhmqfGXslLbIR4LNGgjuOPPkBv0v7Xv2LXD2xbV7+PhL0t8soN9BqhguLYwRofbv+XTX1ZkbS5p40gbA/QtdXdAzgPyyQJmdHEqPLggWShbXF3aM68RRFU2G/ZD7tlOquxHvtHEksk9RLCGrVqcvku+LFFxa3EYkx3nW1iQCAgWSZBhCCVaVaB1QA6GS7heitw4K1BHBRfEcsRbCg5X7J4Qggj1+yhDrYJpmpjkDZy1InygxKfGRPoFuAOenkEJVffnOVS2UCke5542V+dKcqIgy+uVLTJbAgMBAAECggEAUhR0ZGgTVtarFnVmabYlyV8TcEXVgOsscawjQy3rPxkai8oqoV6RxY7GPLhYxubXACxQLPfeN73yEfOXtH4RLYysrHOoPdsD7hY+SYF+hXbjsXM5TBR54GpZHi3y+NftQQH0MPcacytqu5oPj90yCCu2cX4CA/oBGPKUZbJPUg+GurzCw3dQWUVyVzt0cSG25dlFUg075T8rIID2zRKV/Ct+arK8yPlkD3XCOXPADfyCDwTQHrYvzs0CRJdbdgK43n82ahCMB2gSiFegcuaArIzY/PcPCyA2pLDFIWQLSjkWU/wyt85cxWs2SofoZ6yNcg96OhfKjKyqEuCYLqekOQKBgQD2Z6T11RfYt9vcCctSugJWGSc9PkLskyrlOJetxP9tAFl1LzU/XvXXSxwiL/bGYZQjjUPiDfjUDiFSbUYJeEqkmvgh8O2scSAT/YeO0IA7JVtBi7RWqOH0l4lnYmUobZfGz5qvfHFvla+sZyCKffBHaGLBsL+OcelUItB/U8xVNQKBgQDyLZLGFGgMmL3IzgLPAZ8PLyl+6onCBPiqwqMaBDLrDHYFs4KWxWAZMysD/kLGYA+6eq8pmiTTpBokjGlGb/ekTYrdaqp8jFT/qJHby3sWFdzHzsddIV1bc27uMYZqkNDH/m51DBk4k9TYehIq/d50csMRxkH6hoVvlnO6oHMrTwKBgQCpSq5gpEadBD1PIX+LV/cw9lkqT7OlXLt0/3vezOF9H2dXBtc7sd+QSq+KoY+X3wTrpCwGjPjvoMGuIxuid/44t6xRjXmOJ68VbPQM4B2qBVF8aCLAA4ozBUZHPKgprZpA4vPxK7u4IYywE66c2EgUl5h6YRhLIuJfhwfe41EZHQKBgQDV8yEZ0Wlix4XS4v1kOAB3f6jfMQDm4VF2OmGxWEhLk0SpIL+bx19wo926t2PysWbzzfL1g/JI708RPuK6kaxsAP1PQy6+vZNSEXBah1eEWWglNS63vgQmCMLVt/cSKkHojYSz7y5Q53lAjgeC56kho0lmaIpdc35q6LXxCFL2zQKBgD5LrDl4ZdWXqJnDqEXb2gBz9ZjO72VoENSWfpFqh/b1GUFG/35YXH6ANipFXYBj4Bb1oDbUXDo6x52Fa+LShggf3EUQWOEpSLxAA5faFz3XL4GuMx1tSk7VTUCQm3SuxhLKREpCHsISiVnHqYxqRgIxgejHCnVeStgkiKNt4FIn"
      + "-----END PRIVATE KEY-----\n";

  //测试AutoUpdateCertificatesVerifier的verify方法参数
  private static String serialNumber = "55EC9BB13278579C5A895E5B8D5D80963591AF6B";
  private static String message = "{\"data\":[{\"serial_no\":\"55EC9BB13278579C5A895E5B8D5D80963591AF6B\",\"effective_time\":\"2020-06-01T13:24:30+08:00\",\"expire_time\":\"2025-05-31T13:24:30+08:00\",\"encrypt_certificate\":{\"algorithm\":\"AEAD_AES_256_GCM\",\"nonce\":\"88d005078b0c\",\"associated_data\":\"certificate\",\"ciphertext\":\"8tjQrO1/0dlQBRUlhODs/Dlq0RZzzgWJxyhvtkKwqsaGRYrW7kkYLi3UjZz/YEdo8p+x80ZHMK0T2P0ait4+PRagYU/4QU2U8Id3QrQqjN9lfFbx16GYCG5NURbcEQRhAQX9AwIkHuc3jFdviHJxolK7/7J6FR3IJxupuYI8TVmb9OhuXCYE8srWiyC4yYAYzOdMdrT4SOR9fDRA+jTtPRJpC68CoEepTv+m53xe1GSwmFYFA91lK3SxgmdhHmMjIaLgvIOph+tszUeNTomvxqiAaa3iuEo3Y4S9m8axLcKK9aV1JfEHT3c///gzuxbL+8IOcyZSLD1VjvZN6ezVJ2Lb3WSvq2nZ1LMTOwRDnhG/sWKDx0fkhkNI2QSgHFrLqECTzuT/ZP1KzdXVedjkrxfKJ4+AwQet589SCejd2/o4acdLI37lxUY27xV8WaqC1hhOtCzUKLQD4y7BoGoRTGdOCjp3IxzEe9ESc34m6QD1he0aKBDalgW9P8qq6JPP6fpzLWS36DpHPwZ8CG/4MgsSsYxGAX7CEHPQcwQcf2BRPrKDEocvR7rTB/bLOOP233/lmy/QBWK204GwmG7WYLIYsZdX4CwrJRdyRGSzMQQ9HZzUg0B84bTcKeHM/RfvzQfCt+IcmrgN+OEA1YU9BLC7wTZt3Tpr1Ul386hblSgh8uMmd3sD8uUzFuuEUtyY1TfVvXLgB/iPgp17LijdBZUXnY1qXKF1leuC9wm6zvBJW4CWnqwpOGuxcWC068m9xj75fSg/Hze7JDvn26JvveD6cIwRS1Fw6PpyBFRpceQV/NO04nnTTPfxz5BcnFRyT7zWlKxRPfUq425Uv6rGdT2UJ4C0yep0JLywv1uA7vN2BWF06HPzAthtuPRvVFq9OGp8KFQ33+2n2KTwqehAv1Ks2fvT82ZprW8Us/3C2Y4V/sGZxtQjk8G/zklxn+a3NOHWgSh44H3eBAsQRM7UPflBZc2FJHsuQ7REYiqKVsi4xzsxwY6ML5kCSyitJX3kVi8IRK/003ITuF11mjVEGda+fisDYVFOF/GQgBh/bA0Pg+hzpSjeodzYOTnnukur+1kA3NS0i7L9I5KpDaewy74Kc8CD+D9sM8kudvRG9CRFLH78S2SykLnhAuBJ9Ar0NRdAqlyNm/xNHD+PWma1oghTTy6frsllWsdIuxvZ7MpIHtB8V5QnfMuSQ2kVwTwQyyi56Np5ejry6IatAA/nnTfD7f+0MjPi97VsiOuRHhLaF+FURDBJImgFhyK2p/B5aROHgxGYh26UjZzPO1EC6cpe3yaFes41wRUsQmED5x/5LwW8bvBzqBSO4xLkkbOI4dghb6TptAM01aj2Ql7wBa0SJKJHMcLq6J2JnBz6Y8NOjpP5+BD9tDbxdF9sjZ7IhddEPGU3wtx/eRO4M4fGhhZjig9DeNnW6KQKs3uaUt9SCvnhYRGxIaTtdZcBmNRk5odzNMBIYNOKtT+rIVC3UhPqHqGjjCgZs1STyWIC/PLvKuGU/vd0jHUIllLjjuTnbiMx7N4R7D2oK0fxCSkoumnpBsZTUxyVxqIPBkAVOCgDnPiEjcYGe2W0McI0j89LN4VCx0TF+Zvn3fJnxccEKtZYCzKMV/LkxKTt5mow/nDu8IMca5lhVAtvcr3mpXWJf7+NrWOvyBzdM5iW8yv3Cp6OYV1Wn7SGJbXYaLoWQzZvJ9tybZ0D/jyqskf7gaz0zhtZJRzGb9yedK8I4nNzlaguJfZex1BLItqsJEIVnZNAPq/lVn9OC+18L6xrUcjSBje4A7iMPRCYoLsFd9iNmBRCdmvV0bj587JSt0CrCAc/SJIg3zm+MmKlICQ+VIHygPGPGGdGOvNtjAN/tJoVemLuOn0Yvg==\"}}]}";
  private static String signature = "wD9kOnRXzDJ+TPdA83U6PoAgp8UKZXHZv0uLaMksDpq83LN/TNIXbo/OLQu9UN8WqmuUxoRc742b1O+wqsZ7243FGgxvrsSS23dkEHSN+7YGJ+DzZDEMjMFg+TT6UDTpqAk2LLTiyN9aC+Vn5xtgQz90rcy3eVu0kbskPVfERVM7CTvavOxGh2ArRPEpMtlp6hJx2IncLdqQFxlailHGxHbVVgX27bfWjHnivlVdP3uc5yKA3pI+pAnPg7n+mvcfULllnwbqLfNZBo5wSfMn+b5zi6KQkY/J3/aL5e2AY3G+T1afO5vOuSfeBEukwxtyGBjL94xL4u+98V2hsiICKw==";

  @Before
  public void setup() throws IOException {
    PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
        new ByteArrayInputStream(privateKey.getBytes("utf-8")));

    //使用自动更新的签名验证器，不需要传入证书
    verifier = new AutoUpdateCertificatesVerifier(
        new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
        apiV3Key.getBytes("utf-8"));

    httpClient = WechatPayHttpClientBuilder.create()
        .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
        .withValidator(new WechatPay2Validator(verifier))
        .build();
  }

  @After
  public void after() throws IOException {
    httpClient.close();
  }

  @Test
  public void autoUpdateVerifierTest() throws Exception {
    assertTrue(verifier.verify(serialNumber, message.getBytes("utf-8"), signature));
  }

  @Test
  public void getCertificateTest() throws Exception {
    URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
    HttpGet httpGet = new HttpGet(uriBuilder.build());
    httpGet.addHeader("Accept", "application/json");
    CloseableHttpResponse response1 = httpClient.execute(httpGet);
    assertEquals(200, response1.getStatusLine().getStatusCode());
    try {
      HttpEntity entity1 = response1.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      InputStream content = entity1.getContent();
      StringBuilder out = new StringBuilder();
      byte[] b = new byte[4096];
      for (int n; (n = content.read(b)) != -1;) {
           out.append(new String(b, 0, n));
      }
      System.out.println("message: " + out.toString());
      EntityUtils.consume(entity1);
    } finally {
      response1.close();
    }
  }
}
