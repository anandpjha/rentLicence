package com.namami.dao;
/**
 * @author Anand Jha
 */



public interface AgreementStatusDao {

	void createAgreementStatus(Integer agreementId);

	void updateAgreementStatus(Integer agreementId, Integer agreementStatusId);

	Integer retrieveAgreementStatus(Integer agreementId);

	
}
