
package com.namami.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.namami.bo.UserDto;
import com.namami.common.RestURIConstants;
import com.namami.common.session.UserAuthentication;
import com.namami.common.session.UserContext;
import com.namami.service.AuthenticationService;
import com.namami.service.UserService;


/**
 * this controller will host all the session, context and security handling
 * 
 * @author Anand Jha
 * 
 */
@RestController
public class SessionController {
	//@Autowired
	//private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.CREATE_SESSION_CONTEXT, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String setSecurityContext(@PathVariable String userId) {

		SecurityContextHolder.clearContext();
		// A call to service layer for User Details
		UserDto user = userService.findUser(userId);
		// Populating ContextUtil after user land on home page
		createSessionContext(userId, user);
		return ("success");

	}

	/**
	 * Invalidating Security Context Holder on logout
	 * 
	 * @param req
	 * @param res
	 * @return String
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.LOGOUT_URL, method = RequestMethod.POST)
	public void logOut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		

		SecurityContextHolder.clearContext();

	}

	/**
	 * Session Expired URL
	 * 
	 * @param req
	 * @param res
	 * @return String
	 */
	@RequestMapping(value = RestURIConstants.SESSION_EXPIRED_URL, method = RequestMethod.GET)
	public String sessionExpired(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		SecurityContextHolder.clearContext();
	
		return ("session-expired");

	}

	

	/**
	 * @param userDetailsServiceRequest
	 * @param registrationServiceResponse
	 * @param user
	 */
	private void createSessionContext(String identifier, UserDto user) {

		SecurityContextHolder.clearContext();

		final UserContext userContext = new UserContext();

		userContext.setUserId(identifier);
		userContext.setEmailAddress(user.getUserEmail());

		// call to update the customer status in user context.
		//authenticationService.updateCustomerStatusInSessionContext(userContext, user.getCustProfileStatus());

		//userContext.setFirstName(user.getUserFirstName());
	
		//userContext.setLastName(user.getUserLastName());
		userContext.setMobileNumber(user.getUserMobile());
		
		
		UserAuthentication userAuthentication = new UserAuthentication();

		Authentication authentication = userAuthentication.getCredentials();
		// put the session in the context
		userContext.setSessionId(RequestContextHolder.getRequestAttributes().getSessionId());
		userAuthentication.setDetails(userContext);

		authentication = authenticationService.authenticate(authentication);

		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		
		
	}

	
}
