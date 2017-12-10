package com.namami.common.session;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Setting user authorities for the authenticated user
 * 
 * @author Anand Jha
 * 
 */
public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = -7991207156935622771L;

	private UserContext userContext;

	private static final String AUTHENTICATED_USER = SessionConstants.AUTHENTICATED_USER.getValue();
	private static final String USER_AUTHORITY = SessionConstants.AUTHENTICATED.getValue();

	public static final GrantedAuthority AUTHORITY = new GrantedAuthority() {

		private static final long serialVersionUID = -5987944264385948245L;

		public String getAuthority() {
			return USER_AUTHORITY;
		}
	};

	/**
	 * Added PARTIAL_AUTHORITY GrantedAuthority to provide limited functionality access.
	 */
	public static final GrantedAuthority PARTIAL_AUTHORITY = new GrantedAuthority() {

		private static final long serialVersionUID = 112400960894944382L;

		public String getAuthority() {
			return SessionConstants.PARTIAL_AUTHORITY.getValue() ;
		}
	};
	
	private Collection<? extends GrantedAuthority> userAuthorities;

	private UsernamePasswordAuthenticationToken credentials;

	public UserAuthentication() {
		this.userAuthorities = Arrays.asList(AUTHORITY);
		this.credentials = new UsernamePasswordAuthenticationToken(AUTHENTICATED_USER, AUTHENTICATED_USER, userAuthorities);
    }

	public UserAuthentication(GrantedAuthority... authorities) {
		this.userAuthorities = Arrays.asList(authorities);
		this.credentials = new UsernamePasswordAuthenticationToken(AUTHENTICATED_USER, AUTHENTICATED_USER, userAuthorities);
	}

    public String getName() {
		return " User";
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userAuthorities;
	}

	public UsernamePasswordAuthenticationToken getCredentials() {
		return credentials;
	}

	public Object getDetails() {
		return this.userContext;
	}

	public void setDetails(UserContext userContext) {
		this.userContext = userContext;
	}

	public Object getPrincipal() {
		return credentials.getPrincipal();
	}

	public boolean isAuthenticated() {
		return true;
	}

	public void setAuthenticated(boolean authenticated) {

	}

}