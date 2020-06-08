package com.evchong.wxpayscore.configuration.httppool;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Http 连接池配置
 * 
 * @author fanyuwen
 *
 */
@ConfigurationProperties(prefix = "http-pool")
@Data
public class HttpPoolProperties {

	private Integer maxTotal;
	private Integer defaultMaxPerRoute;
	private Integer connectTimeout;
	private Integer connectionRequestTimeout;
	private Integer socketTimeout;
	private Integer validateAfterInactivity;

}