
package com.namami.common.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.UnAuthorizedException;

/**
 * Class to hold user context details
 * @author Anand Jha
 * 
 */

@Service("contextUtil")
public final class ContextUtil {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(ContextUtil.class);

	private ContextUtil() {

	}
	
	/**
	 * To get the user details.
	 * @return
	 * @throws Exception
	 */
	public static UserContext getUserContext() {
		UserAuthentication userAuthentication = null;
		UserContext userContext = null;
		try {
			// added this line to prevent class cast exception in case of anonymous user
			//SecurityContext securityContext = (SecurityContext) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SPRING_SECURITY_CONTEXT");
			SecurityContext  scontext = SecurityContextHolder.getContext();
			Object obj = SecurityContextHolder.getContext().getAuthentication();
			if (null != obj && obj instanceof UserAuthentication) {
				userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
			}
		} catch (RuntimeException re) {
			
			SLF4JLOGGER.info(" exception in getUserContext: "+re.getMessage());
			re.printStackTrace();
			
		}
		if (null != userAuthentication && null != userAuthentication.getDetails()) {
			userContext = (UserContext) userAuthentication.getDetails();
		}
		return userContext;
	}

	public static String getUserId() {
		
		UserContext userContext = getUserContext();

		if(userContext == null) {
			SLF4JLOGGER.info("############### userContext is null ###############");
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is is NULL" );
		}
		
		return userContext.getUserId();
	}

	public static String getEmailAddress() {
		UserContext userContext = getUserContext();
		if(userContext == null) {
			SLF4JLOGGER.info("############### User is null in the session ###############");
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is is NULL" );
		}
		return userContext.getEmailAddress();
	}

	public static String getName() {

		
		return getUserContext().getName();
	}

	

	public static String getCity() {

		
		return getUserContext().getCity();
	}

	public static String getMobileNumber() {

		
		return getUserContext().getMobileNumber();
	}

	public static String getUserStatus() {

		
		return getUserContext().getUserStatus();
	}

	

	public static String getlastLogin() {

		
		return getUserContext().getLastLogin();
	}
	
	public static String getUserRollType() {

		
		return getUserContext().getUserRollType();
	}

	
}