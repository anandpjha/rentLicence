package com.namami.service;
/**
 * @author Anand Jha
 */



public interface AgreementStatusService {
	
	public void createAgreementStatus(Integer agreementId);
	public void updateAgreementStatus(Integer agreementId, Integer agreementStatusId);
	public Integer retrieveAgreementStatus(Integer agreementId);
	
}
