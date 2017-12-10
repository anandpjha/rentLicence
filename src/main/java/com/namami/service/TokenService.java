package com.namami.service;

import com.namami.bo.TokenDto;
import com.namami.bo.TokenRequest;

/**
 * @author Anand Jha
 * 
 */
public interface TokenService {

	public String generateToken(final TokenRequest tokenRequest);
	
	public TokenDto retrieveToken(final String token);
	
	public void deActivateToken(final String token);

}
