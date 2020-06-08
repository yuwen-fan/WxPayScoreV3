package com.evchong.wxpayscore.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 商户信息配置
 * 
 * @author fanyuwen
 *
 */
@Data
@ConfigurationProperties(prefix = "merchant")
public class MerchantConfiguration {
	/**
	 * 微信公众平台分配的与传入的商户号建立了支付绑定关系的appid，可在公众平台查看绑定关系，此参数需在本系统先进行配置。
	 */
	private String appId;
	/**
	 * 该服务ID有本接口对应产品的权限
	 * <p>
	 * 申请接入微信支付分后官方反馈的"服务ID"
	 */
	private String serviceId;
	/**
	 * 风险金上限, 单位分
	 */
	private Integer maxRiskFund;

	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 商户证书序列号
	 * 
	 * <p>
	 * 商户API证书首次需申请，以获取“商户证书序列号”和“商户私钥”
	 * 
	 * @see https://kf.qq.com/faq/161222NneAJf161222U7fARv.html
	 */
	private String mchSerialNo;
	/**
	 * 商户私钥
	 * 
	 * <p>
	 * 商户API证书首次需申请，以获取“商户证书序列号”和“商户私钥”
	 * <p>
	 * 是下载证书后apiclient_key.pem文件中的内容(配置时可删除其中的换行符)
	 * 
	 * @see https://kf.qq.com/faq/161222NneAJf161222U7fARv.html
	 */
	private String privateKey;
	/**
	 * API密钥: 【商户平台】->【账户中心】->【账户设置】->【API安全】->【API密钥】
	 */
	private String apiKey;
	/**
	 * APIv3密钥: 【商户平台】->【账户中心】->【账户设置】->【API安全】->【APIv3密钥】
	 * <p>
	 * apiV3Key 与 certificate只需配置一项即可，若同时配置，以apiV3Key为准
	 * 
	 * <p>
	 * 为了保证安全性，微信支付在回调通知和平台证书下载接口中，对关键信息进行了AES-256-GCM加密。API v3密钥是加密时使用的对称密钥
	 * <p>
	 * 由于证书存在有效期的限制，微信支付会不定期地更换平台证书以确保交易安全。建议配置apiV3Key
	 * 
	 * @see https://wechatpay-api.gitbook.io/wechatpay-api-v3/ren-zheng/api-v3-mi-yao
	 * @see https://kf.qq.com/faq/180830E36vyQ180830AZFZvu.html
	 */
	private String apiV3Key;
	/**
	 * 微信支付平台证书
	 * <p>
	 * 配置了apiV3Key时可使用自动更新的签名验证器，那么就不需要传入平台证书
	 * <p>
	 * 首次下载平台证书时使用CertificateDownloader去-c参数方式获取certificate
	 * 
	 * @see https://github.com/wechatpay-apiv3/CertificateDownloader
	 */
	private String certificate;

	/**
	 * 商户回调地址
	 */
	private MerchantCallbackUrl callbackUrl;
}
