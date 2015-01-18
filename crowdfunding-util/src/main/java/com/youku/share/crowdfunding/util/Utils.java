package com.youku.share.crowdfunding.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils{
	private static final Logger logger = LogManager.getLogger(Utils.class);
	
	public static final String dateFormatExp = "yyyy-MM-dd HH:mm:ss";
	public static final String dateFormatExp1 = "yyyyMMddHHmmss";
    public static final DateFormat standDateFormator = new SimpleDateFormat(dateFormatExp);
    public static final DateFormat standDateFormator1 = new SimpleDateFormat(dateFormatExp1);
    
    private static AtomicLong al = new AtomicLong();
    
    
	public static boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjaxRequest = false;
		@SuppressWarnings("rawtypes")
		Enumeration headerNameEnum = request.getHeaderNames();
		while(headerNameEnum.hasMoreElements()){
			Object headerName = headerNameEnum.nextElement();
			String headerNameString = headerName.toString();
			String headerValueString = request.getHeader(headerNameString);
			if("X-Requested-With".equalsIgnoreCase(headerNameString)){
				if("XMLHttpRequest".equalsIgnoreCase(headerValueString)){
					isAjaxRequest = true;
					break;
				}
			}
		}
		return isAjaxRequest;
	}
	
	public static String generateSerialNumber(){
		long serial = al.getAndIncrement();
		String dateStr = standDateFormator1.format(new Date());
		return dateStr + "_" + serial;
	}
	
	public static void ajaxResponse(HttpServletRequest request,HttpServletResponse response, String jsonResponse) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control","no-cache");
		response.getWriter().write(jsonResponse);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	//debug级别输出所有参数
	public static void loggerRequestParameter(HttpServletRequest request){
		
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
	
	//debug级别输出所有参数
	public static void loggerRequestParameterImport(HttpServletRequest request){
		
		String getRequestURL = request.getRequestURL().toString();

		logger.trace("===== request info start =====");

		logger.debug("getRequestURL == " + getRequestURL);

		logger.debug("isAjaxRequest === " + Utils.isAjaxRequest(request));
				
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