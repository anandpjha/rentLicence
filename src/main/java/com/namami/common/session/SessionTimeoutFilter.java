package com.namami.common.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namami.common.RestURIConstants;

/**
 * this filter is used to skip non session urls from session
 * management and session time out
 * @author Anand Jha
 */
public class SessionTimeoutFilter implements Filter {

	private static final Logger LOGGER = Logger
			.getLogger(SessionTimeoutFilter.class.getName());

	/** list of urls which should be bypassed from the filter */
	private static List<String> nonSessionUrls = new ArrayList<String>();

	/**
	 * String representation of numeric 1000
	 */
	private static final int ONETHOUSAND = 1000;

	static {
		//nonSessionUrls.add(RestAccountURIConstants.GET_COUNTRY_LIST);
		
	}

	/**
	 * initialize block
	 * @author 
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * filter the request which are timed out
	 * @param request - httpservlet request coming from browser
	 * @param response - httpservlet response coming from browser
	 * @param filterChain - list of filters need to apply on request and/or response
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			// is session expire control required for this request?
			if (isSessionControlRequiredForThisResource(httpServletRequest) && isSessionInvalid(httpServletRequest)) {

				// is session invalid?
				String timeoutUrl = httpServletRequest.getContextPath()	+ RestURIConstants.SESSION_EXPIRED_URL;
				LOGGER.info("session is invalid! redirecting to expired url : "	+ timeoutUrl);
				httpServletResponse.sendRedirect(timeoutUrl);
				return;
			}
		}
		LOGGER.info("/****  SessionTimeoutFilter.doFilter : session used last time: "	+ new Date());
		filterChain.doFilter(request, response);
	}

	/**
	 * method checks if session should be checked for that request.
	 * @author ANJU GARG, Sumit bhawsar
	 * @param request - httpservlet request coming from browser
	 */
	private boolean isSessionControlRequiredForThisResource(
			HttpServletRequest httpServletRequest) {
		String[] requestPatSseperatedUrl = httpServletRequest.getRequestURI().split(RestURIConstants.SYMBOL_SLASH);
		if(requestPatSseperatedUrl.length > 2){
			for (String url : nonSessionUrls) {
				// check request url is non secure
				String[] sperateurls = url.split("/");
				if(sperateurls.length > 1 &&  requestPatSseperatedUrl[2].equals(sperateurls[1])) {
					return false;
				}				
	
			}
		}
		return true;
	}

	
	 /** method to check if session is valid, session will be null if already
	 * invalidated Or we compare session last accessed time with Max inactive
	 * Interval
	 * @author ANJU GARG, Sumit bhawsar
	 * @param request - httpservlet request coming from browser
	 */
	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession(false);
		if(null == session) {
			return true;
		} else if (null != session) {
			long lastAccessTime = session.getLastAccessedTime();
			long currentTime = new Date().getTime();
			LOGGER.info("Validating session:  " +session.getId()+" , at : "	+ new Date(currentTime));
			if (getDiffInSeconds(lastAccessTime, currentTime) >= session
					.getMaxInactiveInterval()) {
				return true;
			}
		}
		return false;
	}

	
	/** to get difference of current time and session last accessed time in
	 * seconds
	 *  @author ANJU GARG, Sumit bhawsar
	 * @param request - httpservlet request coming from browser
	 */
	private Long getDiffInSeconds(Long lastAccessTime, Long currentTime) {
		Long diff = currentTime - lastAccessTime;
		Long diffInSeconds = diff / ONETHOUSAND;// In Seconds
		// Long diffInMins = diffInSeconds / 60;// In Minutes
		return diffInSeconds;
	}

	/**
	 * don't need to implement as per current requirement 
	 */
	@Override
	public void destroy() {
		// doing nothing
	}
}
