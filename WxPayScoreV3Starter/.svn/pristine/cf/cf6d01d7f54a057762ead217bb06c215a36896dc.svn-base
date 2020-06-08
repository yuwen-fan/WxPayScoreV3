package com.evchong.wxpayscore.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.evchong.wxpayscore.configuration.inteceptor.WxPayScoreWebMvcConfiguration;

/**
 * 开启微信支付分模块
 * 
 * @author fanyuwen
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ WxPayScoreRestTemplateConfig.class, WxPayScoreAutoConfiguration.class, WxPayScoreWebMvcConfiguration.class })
public @interface EnableWxPayScore {

}
