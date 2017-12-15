package com.namami.dao;

import com.namami.bo.TokenDto;
import com.namami.bo.TokenRequest;



/**
 * @author Anand Jha
 * 
 */
public interface TokenDao {

	public void generateToken(String token, TokenRequest tokenRequest);

	public TokenDto retrieveToken(String token);

	public void deActivateToken(String token);
}
