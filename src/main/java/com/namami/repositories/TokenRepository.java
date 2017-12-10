package com.namami.repositories;
/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.Token;


@Repository("tokenRepository")
public interface TokenRepository extends JpaRepository<Token, String> {
		
	Token findByTokenId(Integer tokenId); 
	
	Token findByTokenVal(String val);
	

}
