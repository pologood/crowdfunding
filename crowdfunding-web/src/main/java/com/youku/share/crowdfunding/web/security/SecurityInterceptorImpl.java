package com.youku.share.crowdfunding.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

@Component
public class SecurityInterceptorImpl extends AbstractSecurityInterceptor
		implements Filter {
	
	private static final Logger logger = LogManager.getLogger(SecurityInterceptorImpl.class);
	
	@Autowired
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.error("SecurityInterceptorImpl.init !");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("SecurityInterceptorImpl.doFilter");
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	public void invoke(FilterInvocation fi) {
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			logger.error("SecurityInterceptorImpl.invoke", e);
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void destroy() {
		logger.error("SecurityInterceptorImpl.destroy !");
	}

	@Override
	public Class<?> getSecureObjectClass() {
		logger.error("SecurityInterceptorImpl.getSecureObjectClass !");
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		logger.error("SecurityInterceptorImpl.obtainSecurityMetadataSource !");
		return this.filterInvocationSecurityMetadataSource;
	}

	public void setFilterInvocationSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
		this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
	}

}
