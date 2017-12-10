package com.namami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureAdmin;
import com.namami.bo.TokenRequest;
import com.namami.common.RestURIConstants;
import com.namami.service.TokenService;

/**
 * @author Anand Jha
 * 
 */

@RestController
public class TokenController {
	
	@Autowired
	TokenService tokenService;
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.REQUEST_TOKEN, method = RequestMethod.POST)
	@SecureAdmin
	public String generateToken(@RequestBody final TokenRequest tokenRequest){
		
		return tokenService.generateToken(tokenRequest);
			
	}

}
