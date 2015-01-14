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

/*
 * 注意这里没有对Ajax请求做处理
 * 可以参考
 * com.youku.share.crowdfunding.web.security.AccessDeniedHandlerImpl
 * 做修改
 */
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
			if (t instanceof BaseException) {// 如果是你定义的业务异常
				request.setAttribute("BsException", t);// 存储业务异常信息类
				request.getRequestDispatcher(errorPage).forward(request,
						response);// 跳转到信息提示页面！！
			}
			logger.error("Catch Throwable", t);
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
		
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames();
		while(e.hasMoreElements()){
			Object parameterName = e.nextElement();
			String parameterNameString = parameterName.toString();
			String parameterValueString = request.getParameter(parameterNameString);
			logger.debug(parameterNameString+" === "+parameterValueString);
		}
		
	}
}