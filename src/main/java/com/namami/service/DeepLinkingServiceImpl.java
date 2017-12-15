package com.namami.service;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.dao.DeepLinkingDao;
/**
 * @author Anand Jha
 */

@Service
public class DeepLinkingServiceImpl implements DeepLinkingService {

	@Autowired
	DeepLinkingDao deepLinkingDao;
	
	@Override
	public void approveDraftAgreement(String dpId , String dp) {
		
		deepLinkingDao.approveDraftAgreement(dpId, dp);
		
	}

	@Override
	public void createApprovalDeepLink(Integer agreementId, Integer personId, Integer personType) {
	
		deepLinkingDao.createApprovalDeepLink(agreementId, personId, personType, generateDeepLinkParam());
		
	}
	
	private String generateDeepLinkParam(){
		
		String uuid = UUID.randomUUID().toString().substring(0, 7);
		int randomToken = (int)(Math.random()*8000)+5000;
		
		return uuid + String.valueOf(randomToken);
	}
	
}
