package com.namami.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.TokenDto;
import com.namami.bo.TokenRequest;
import com.namami.common.CommonConstants;
import com.namami.domain.types.TokenAcessType;
import com.namami.domain.types.TokenStatusType;
import com.namami.domain.types.TokenType;
import com.namami.entity.Token;
import com.namami.repositories.TokenRepository;

/**
 * @author Anand Jha
 * 
 */
@Service
public class TokenDaoImpl implements TokenDao {
	
	@Autowired
	TokenRepository tokenRepository;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(AgreementDaoImpl.class);

	@Override
	public void generateToken(String token, TokenRequest tokenRequest) {
		
		Token newtoken = new Token();
		newtoken.setTokenVal(token);
		if(tokenRequest.getTokenType().equals(CommonConstants.ASSOCIATE_ROLE)){
			
			newtoken.setTokenType(TokenType.ASSOCIATE.name());
			newtoken.setAccessType(TokenAcessType.ASSOCIATE.name());
			
		}else if(tokenRequest.getTokenType().equals(CommonConstants.ADMIN_ROLE)){
			
			newtoken.setTokenType(TokenType.ADMIN.name());
			newtoken.setAccessType(TokenAcessType.ADMIN.name());
			
		}
		
		newtoken.setStatus(TokenStatusType.ACTIVE.name());
		newtoken.setEmail(tokenRequest.getEmail());
		
		tokenRepository.saveAndFlush(newtoken);
		
		
	}

	@Override
	public TokenDto retrieveToken(String token) {
		
		Token retivedToken =  tokenRepository.findByTokenVal(token);
		TokenDto dto = null;
		if(null != retivedToken){
			dto = new TokenDto();
			dto.setAccessType(retivedToken.getAccessType());
			dto.setStatus(retivedToken.getStatus());
			dto.setTokenId(retivedToken.getTokenId());
			dto.setTokenType(retivedToken.getTokenType());
			dto.setTokenVal(retivedToken.getTokenVal());
		}
		
		return dto;
		
	}

	@Override
	public void deActivateToken(String token) {
		
		Token retivedToken =  tokenRepository.findByTokenVal(token);
		
		if(null != retivedToken){
			
			retivedToken.setStatus(TokenStatusType.USED.name());
		}
		
		tokenRepository.saveAndFlush(retivedToken);
	}
	

}
