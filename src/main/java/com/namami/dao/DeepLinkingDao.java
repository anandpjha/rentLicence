package com.namami.dao;

/**
 * @author Anand Jha
 * 
 */
public interface DeepLinkingDao {

	void approveDraftAgreement(String dpId, String dp);

	void createApprovalDeepLink(Integer agreementId, Integer personId, Integer personType, String deeplinkParam);

	
	
}
