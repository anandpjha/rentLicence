package com.namami.service;

/**
 * @author Anand Jha
 */

public interface DeepLinkingService {

	void approveDraftAgreement(String dpId, String dp);
	
	void createApprovalDeepLink(Integer agreementId, Integer personId, Integer personType);

	
	

}
