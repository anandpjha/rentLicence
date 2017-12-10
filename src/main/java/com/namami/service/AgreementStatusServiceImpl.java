package com.namami.service;
/**
 * @author Anand Jha
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.dao.AgreementStatusDao;
import com.namami.exception.GlobalExceptionHandler;

@Service
public class AgreementStatusServiceImpl implements AgreementStatusService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private AgreementStatusDao agreementStatusdao;

	@Override
	public void createAgreementStatus(Integer agreementId) {
		
		//TODO to validate agrement id
		
		agreementStatusdao.createAgreementStatus(agreementId);
		
	}

	@Override
	public void updateAgreementStatus(Integer agreementId, Integer agreementStatusId) {
		
		
		agreementStatusdao.updateAgreementStatus(agreementId, agreementStatusId);
		
	}

	@Override
	public Integer retrieveAgreementStatus(Integer agreementId) {
		
		return agreementStatusdao.retrieveAgreementStatus(agreementId);
	}
	

	
	
}
