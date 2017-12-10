package com.namami.service;


import org.springframework.security.core.Authentication;

import com.namami.common.session.UserContext;


/**
 * interface for Authentication Service
 * @author Anand Jha
 *
 */
public interface AuthenticationService {
	
	Authentication authenticate(Authentication auth) ;
	
	void updateCustomerStatusInSessionContext( UserContext userContext, String customerStatus );

}
