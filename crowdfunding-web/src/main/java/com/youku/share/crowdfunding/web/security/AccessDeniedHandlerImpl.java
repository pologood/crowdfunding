package com.youku.share.crowdfunding.web.security;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.youku.share.crowdfunding.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private static final Logger logger = Logger.getLogger(AccessDeniedHandlerImpl.class);
	
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
		if (isAjaxRequest(request)) {

		} else {
			response.sendRedirect(accessDeniedUrl);
			String deniedMessage = accessDeniedException.getMessage();
			request.getSession().setAttribute(ACCESS_DENIED_MSG, deniedMessage);
		}
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjaxRequest = Utils.isAjaxRequest(request);
		logger.info("isAjaxRequest=" + isAjaxRequest);
		return isAjaxRequest;
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

}