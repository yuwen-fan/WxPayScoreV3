# 以下为开发时的关键步骤
一、WechatPay-API-v3 认证
    - https://wechatpay-api.gitbook.io/wechatpay-api-v3/ren-zheng
    
	1、申请商户API证书(商户证书序列号、商户私钥)
	    > https://kf.qq.com/faq/161222NneAJf161222U7fARv.html
	    > 用于商户端请求
	2、设置商户APIv3密钥
	    > https://kf.qq.com/faq/180830E36vyQ180830AZFZvu.html
	    > 用于(自动)获取微信支付平台证书、加解密数据等
	3、获取商户API密钥
	    > https://kf.qq.com/faq/180830E36vyQ180830AZFZvu.html
	    > 用于wx.openBusinessView#ExtraData中的签名生成等
	4、使用官方工具获取微信支付平台证书
	    > https://github.com/wechatpay-apiv3/CertificateDownloader
	    > 用于微信平台端响应或回调等验签
	    
二、支付分
    - https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/payscore/chapter3_8.shtml
    
    1、使用官方工具实现对平台证书的自动同步更新、请求的自动签名、响应的自动验签
        > https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
    2、回调数据验签和解密
三、分账
    - https://pay.weixin.qq.com/wiki/doc/api/allocation.php?chapter=4_5&index=1
		// TODO