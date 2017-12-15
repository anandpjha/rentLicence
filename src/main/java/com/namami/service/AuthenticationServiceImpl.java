package com.namami.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;



/**
 * implementation for Authentication Service to authenticate login user
 * 
 * @author Anand Jha
 * 
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	
	@Override
	public Authentication authenticate(Authentication auth) {

		if (auth.getName().equals(auth.getCredentials())) {
			SLF4JLOGGER.info("#######authenticated");
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), getAuthorities());
		}
		throw new BadCredentialsException("Bad Credentials");

	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical
	 * role
	 * 
	 * @param role
	 *            the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(getRoles());
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * 
	 * @param role
	 *            the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
    public List<String> getRoles() {
        List<String> roles = new ArrayList<String>();        
    	roles.add("ROLE_USER"); 
        return roles;
 }



	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	
	public void updateCustomerStatusInSessionContext( com.namami.common.session.UserContext userContext, String customerStatus ) {

		// Check what is customer status.
		/*switch ( customerStatus ) {
			case RestAccountURIConstants.CUSTOMER_STATUS_ACCOUNT_CREATED :
				userContext.setUserStatus( RestAccountURIConstants.UI_STATUS_ACCOUNT_CREATED );
				break;
			case RestAccountURIConstants.CUSTOMER_STATUS_EMAIL_VERIFIED :
				userContext.setUserStatus( RestAccountURIConstants.UI_STATUS_EMAIL_VERIFIED);
				break;
			case RestAccountURIConstants.CUSTOMER_STATUS_FIRST_DEVICE_ACTIVATED :
				userContext.setUserStatus( RestAccountURIConstants.UI_STATUS_FIRST_DEVICE_ACTIVATED );
				break;
			case RestAccountURIConstants.CUSTOMER_STATUS_REGISTRATION_COMPLETE :
				userContext.setUserStatus( RestAccountURIConstants.UI_STATUS_REGISTRATION_COMPLETE );
				break;
		}*/
	}
	
}
