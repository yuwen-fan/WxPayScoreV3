package com.evchong.wxpayscore.allocation.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * 开启普通直连分账模块
 * 
 * @author fanyuwen
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ WxPayAllocationRestTemplateConfig.class, WxPayAllocationAutoConfiguration.class })
public @interface EnableWxPayAllocation {

}
