package com.youku.share.crowdfunding.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.youku.share.crowdfunding.exception.BaseException;
import com.youku.share.crowdfunding.util.Utils;

public class ExceptionFilter implements Filter {

	private static final Logger logger = Logger.getLogger(ExceptionFilter.class);
	
	private String errorPage;// 跳转的错误信息页面

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		loggerParameter(request);
		// 捕获你抛出的业务异常
		try {
			chain.doFilter(req, res);
		} catch (Throwable t) {
			logger.error("Catch Throwable In ExceptionFilter", t);
			try{
				handleException(request,response,t);
			}catch(Throwable tt){
				logger.error("Catch Throwable In ExceptionFilter during handle", t);
			}
			
		}
	}

	// 初始化读取你配置的提示页面路径
	public void init(FilterConfig config) throws ServletException {
		// 读取错误信息提示页面路径
		errorPage = config.getInitParameter("errorPage");
		if (null == errorPage || "".equals(errorPage)) {
			throw new RuntimeException("ExceptionFilter init param required");
		}
	}
	
	private void handleException(HttpServletRequest request,HttpServletResponse response,Throwable t) throws ServletException, IOException{
		/*
		 * 注意这里没有对Ajax请求做处理
		 * 可以参考
		 * com.youku.share.crowdfunding.web.security.AccessDeniedHandlerImpl
		 * 做修改
		 * 搞定了 0.0
		 */
		boolean isAjaxRequest = Utils.isAjaxRequest(request);
		if(isAjaxRequest){
			ajaxHandle(request,response,t);
		}else{
			Throwable cause = t.getCause();
			if (cause instanceof BaseException) {// 如果是你定义的业务异常
				BaseException be = (BaseException)cause;
				logger.error("code=" + be.getCode());
				logger.error("message=" + be.getMessage());
				request.setAttribute("CrowdfundingException", cause);// 存储业务异常信息类
				request.getRequestDispatcher(errorPage).forward(request,
						response);// 跳转到信息提示页面！！
			}else{
				PlatformExcepton
				request.setAttribute("OtherException", t);// 存储业务异常信息类
				request.getRequestDispatcher(errorPage).forward(request,
						response);// 跳转到信息提示页面！！
			}
		}
	}
	
	private String getTipPage(BusinessException be){
		return null;
	}
	
	private void ajaxHandle(HttpServletRequest request,HttpServletResponse response,Throwable t) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(getJsonForHandle(t));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	private String getJsonForHandle(Throwable t){
		String json = "{\"flag\":false,\"message\":\""+t.getCause().getMessage()+"\"}";
		logger.info("ajax response : "+json);
		return json;
	}
	
	//debug级别输出所有参数
	private void loggerParameter(HttpServletRequest request){
		
		/*
		 * 此部分功能待 Spring Security 3 搞定之后再放开
		 * 获得当前登陆用户对应的对象。
		 * UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
		 * 	    .getAuthentication()
		 * 	    .getPrincipal();
		 * 获得当前登陆用户所拥有的所有权限。
		 * GrantedAuthority[] authorities = userDetails.getAuthorities();
		 * 
		 * logger用户身份信息
		 */
		String getAuthType = request.getAuthType();
		String getCharacterEncoding = request.getCharacterEncoding();
		String getContentType = request.getContentType();
		String getContentLength = "" + request.getContentLength();
		String getContextPath = request.getContextPath();
		String getLocaleGetCountry = request.getLocale().getCountry();
		String getLocaleGetLanguage = request.getLocale().getLanguage();
		//String getLocales = request.getLocales();
		String getLocalName = request.getLocalName();
		String getLocalAddr = request.getLocalAddr();
		String getMethod = request.getMethod();
		String getPathInfo = request.getPathInfo();
		String getProtocol = request.getProtocol();
		String getQueryString = request.getQueryString();
		String getRemoteAddr = request.getRemoteAddr();
		String getRemotePort = "" + request.getRemotePort();
		String getRemoteUser = request.getRemoteUser();
		String getRequestedSessionId = request.getRequestedSessionId();
		String getRequestURI = request.getRequestURI();
		String getRequestURL = request.getRequestURL().toString();
		String getScheme = request.getScheme();
		String getServerName = request.getServerName();
		String getServerPort = "" + request.getServerPort();
		String getServletPath = request.getServletPath();
		String getUserPrincipalGetName = request.getUserPrincipal() == null ? "null" : request.getUserPrincipal().getName();
		
		logger.trace("===== request info start =====");
		logger.debug("getAuthType == " + getAuthType);
		logger.debug("getCharacterEncoding == " + getCharacterEncoding);
		logger.debug("getContentType == " + getContentType);
		logger.debug("getContentLength == " + getContentLength);
		logger.debug("getContextPath == " + getContextPath);
		logger.debug("getLocaleGetCountry == " + getLocaleGetCountry);
		logger.debug("getLocaleGetLanguage == " + getLocaleGetLanguage);
		logger.debug("getLocalName == " + getLocalName);
		logger.debug("getLocalAddr == " + getLocalAddr);
		logger.debug("getMethod == " + getMethod);
		logger.debug("getPathInfo == " + getPathInfo);
		logger.debug("getProtocol == " + getProtocol);
		logger.debug("getQueryString == " + getQueryString);
		logger.debug("getRemoteAddr == " + getRemoteAddr);
		logger.debug("getRemotePort == " + getRemotePort);
		logger.debug("getRemoteUser == " + getRemoteUser);
		logger.debug("getRequestedSessionId == " + getRequestedSessionId);
		logger.debug("getRequestURI == " + getRequestURI);
		logger.debug("getRequestURL == " + getRequestURL);
		logger.debug("getScheme == " + getScheme);
		logger.debug("getServerName == " + getServerName);
		logger.debug("getServerPort == " + getServerPort);
		logger.debug("getServletPath == " + getServletPath);
		logger.debug("getUserPrincipalGetName == " + getUserPrincipalGetName);
		logger.debug("isAjaxRequest === " + Utils.isAjaxRequest(request));
		
		logger.debug("===== attribute info start =====");
		@SuppressWarnings("rawtypes")
		Enumeration attributeNameEnum = request.getAttributeNames();
		while(attributeNameEnum.hasMoreElements()){
			Object attributeName = attributeNameEnum.nextElement();
			String attributeNameString = attributeName.toString();
			Object attributeValue = request.getAttribute(attributeNameString);
			String attributeValueString = attributeValue.toString();
			logger.debug(attributeNameString+" === " + attributeValueString);
		}
		
		logger.debug("===== header info start =====");
		@SuppressWarnings("rawtypes")
		Enumeration headerNameEnum = request.getHeaderNames();
		while(headerNameEnum.hasMoreElements()){
			Object headerName = headerNameEnum.nextElement();
			String headerNameString = headerName.toString();
			String headerValueString = request.getHeader(headerNameString);
			logger.debug(headerNameString+" === " + headerValueString);
		}
				
		logger.debug("===== parameter info start =====");
		@SuppressWarnings("rawtypes")
		Enumeration parameterNameEnum = request.getParameterNames();
		while(parameterNameEnum.hasMoreElements()){
			Object parameterName = parameterNameEnum.nextElement();
			String parameterNameString = parameterName.toString();
			String parameterValueString = request.getParameter(parameterNameString);
			logger.debug(parameterNameString+" === " + parameterValueString);
		}
		
		logger.debug("===== request info end =====");
		
	}
}