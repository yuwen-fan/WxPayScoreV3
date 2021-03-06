package com.evchong.wxpayscore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

/**
 * 替换默认的HttpServletRequestWrapper为自定义的RepeatableReadHttpServletRequestWrapper
 * 
 * @see ServletComponentScan
 * @author fanyuwen
 *
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "httpServletRequestWrapperReplaceFilter")
public class HttpServletRequestWrapperReplaceFilter implements Filter {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Initalizing ...");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
			ServletRequest requestWrapper = RepeatableReadHttpServletRequestWrapper
					.of((HttpServletRequest) servletRequest);
			filterChain.doFilter(requestWrapper, servletResponse);
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		log.info("Destroying ...");
	}
}