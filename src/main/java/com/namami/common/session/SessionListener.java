package com.namami.common.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Manages the Sessions for each user
 * The listener has two methods sessionCreated will be invoked 
 * whenever a new session is created. sessionDestroyed will be called whenever 
 * a session is going to be destroyed.
 * 
 * @author Anand Jha
 *
 */
public class SessionListener implements HttpSessionListener{
	
	
	//Constants
	private static final String TIMEOUT_DURATION_MINUTES = "TIMEOUT_DURATION_MINUTES";
	private static final int SIXTY = 60;
	private static final int SESSION_TIMEOUT_IN_MINUTES = 10;

	/**
	 * Called whenever a new session has being created
	 * @param HttpSessionEvent
	 * 
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		int minutes = SESSION_TIMEOUT_IN_MINUTES;
		String sessionTimeOut = "100000";//System.getProperty(TIMEOUT_DURATION_MINUTES);
		if (sessionTimeOut!=null) {
				minutes=Integer.parseInt(sessionTimeOut);
		}
		event.getSession().setMaxInactiveInterval(minutes * SIXTY);
	}

	/**
	 * Called whenever threshold to Maximum inactive Interval has been touched
	 * The user will be logged out automatically.
	 * the cardHolder cache will be cleaned.
	 * @param HttpSessionEvent
	 * 
	 * 
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		event.getSession().invalidate();
		SecurityContextHolder.clearContext();
	}
}