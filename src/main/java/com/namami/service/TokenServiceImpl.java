package com.namami.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.TokenDto;
import com.namami.bo.TokenRequest;
import com.namami.dao.TokenDao;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;

/**
 * @author Anand Jha
 * 
 */
@Service ( "tokenService" )
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenDao tokenDao;

	@Override
	public String generateToken(TokenRequest tokenRequest) {
		
		if(null == tokenRequest.getEmail() || null == tokenRequest.getTokenType()){
			
			List<ValidationError> errors = new ArrayList<ValidationError>();
			ValidationError error = new ValidationError("tokenRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			if(errors.size()> 0){
				throw new ValidationException(errors, "tokenRequest", "Please verify your inputs");
			}
			
		}
		
		
		String uuid = UUID.randomUUID().toString().substring(0, 4);
		int randomToken = (int)(Math.random()*9000)+1000;
		
		String token = uuid + String.valueOf(randomToken);
		
		tokenDao.generateToken(token, tokenRequest);
		
		// TODO to send token to admin email
		
		return token;
		
	}

	@Override
	public TokenDto retrieveToken(String token) {
		
		TokenDto tokenDto = tokenDao.retrieveToken(token);
			
		return tokenDto;
	}
	
	public void deActivateToken(String token) {
		
		tokenDao.deActivateToken(token);
		
	}
	
	
}
