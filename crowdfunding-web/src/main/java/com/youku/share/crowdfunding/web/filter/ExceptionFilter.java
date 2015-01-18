package com.youku.share.crowdfunding.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.youku.share.crowdfunding.exception.BaseException;
import com.youku.share.crowdfunding.util.Utils;

public class ExceptionFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(ExceptionFilter.class);
	
	private String errorPage;// 跳转的错误信息页面

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Utils.loggerRequestParameterImport(request);
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
			String jsonResponse = getJsonForResponse(t);
			Utils.ajaxResponse(request, response, jsonResponse);
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
				
				request.setAttribute("OtherException", t);// 存储业务异常信息类
				request.getRequestDispatcher(errorPage).forward(request,
						response);// 跳转到信息提示页面！！
			}
		}
	}
	
	private String getJsonForResponse(Throwable t){
		String json = "{\"flag\":false,\"message\":\""+t.getCause().getMessage()+"\"}";
		logger.info("ajax response : "+json);
		return json;
	}
	
}