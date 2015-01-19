package com.youku.share.crowdfunding.web.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Used by {@link ExceptionTranslationFilter} to handle an
 * <code>AccessDeniedException</code>.
 *
 * @author Ben Alex
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private static final Logger logger = LogManager.getLogger(AccessDeniedHandlerImpl.class);
	
	private static String ACCESS_DENIED_MSG = "message";
	private String accessDeniedUrl;

	public AccessDeniedHandlerImpl() {}

	public AccessDeniedHandlerImpl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

    /**
     * Handles an access denied failure.
     *
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     *
     * @throws IOException in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		logger.error("requestURL = " + request.getRequestURL().toString());
		
		if (Utils.isAjaxRequest(request)) {
			//logger.error("ajax deny unimplements !");
			Utils.ajaxResponse(request, response, "{\"result\":false,\"message\":\"无权限\"}");
		} else {
			String deniedMessage = accessDeniedException.getMessage();
			request.getSession().setAttribute(ACCESS_DENIED_MSG, deniedMessage);
			logger.error("deny message = " + deniedMessage);
			response.sendRedirect(request.getContextPath() + accessDeniedUrl);
		}
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

}