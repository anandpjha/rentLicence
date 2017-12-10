package com.namami.common.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * This class will redirect to URL if spring security fails
 * @author Anand Jha
 *
 */
public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	
	    public AuthenticationEntryPoint(String loginUrl)  {
	        super(loginUrl);
	    }

	    @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	    	response.setStatus(Integer.parseInt(HttpStatus.UNAUTHORIZED.toString()));
	    }

}
