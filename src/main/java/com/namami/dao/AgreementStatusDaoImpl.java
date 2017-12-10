package com.namami.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.mapper.AgreementStatusMapper;
import com.namami.domain.types.AgreementStatusType;
import com.namami.entity.AgreementStatus;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.AgreementStatusRepository;
/**
 * @author Anand Jha
 */

@Service
public class AgreementStatusDaoImpl implements AgreementStatusDao {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	

	@Autowired
	private AgreementStatusMapper agreementStatusMapper;
	
	@Autowired
	private AgreementStatusRepository agreementStatusRepository;

	@Override
	public void createAgreementStatus(Integer agreementId) {
		
		try{
		AgreementStatus agreementStatus = new AgreementStatus(); 
		
		agreementStatus.setAgreementId(agreementId);
		agreementStatus.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		agreementStatusRepository.saveAndFlush(agreementStatus);
		
		}catch (Exception e){
			
			SLF4JLOGGER.error("createAgreementStatus :: Error while createAgreementStatus in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while createAgreementStatus in DB" );
			
		}
		
	}

	@Override
	public void updateAgreementStatus(Integer agreementId, Integer agreementStatusId) {
		try{
		AgreementStatus agreementStatus = agreementStatusRepository.findByAgreementId(agreementId);
		if(null != agreementStatus){
		agreementStatus.setAgreementStatusId(agreementStatusId);
		
		agreementStatusRepository.saveAndFlush(agreementStatus);
		}else{
			throw new NotFoundException("Agreement Id Not found in DB: "+agreementId);
		}
		}catch (Exception e){
			
			SLF4JLOGGER.error("updateAgreementStatus :: Error while updateAgreementStatus in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while updateAgreementStatus in DB" );
			
		}
		
	}

	@Override
	public Integer retrieveAgreementStatus(Integer agreementId) {
		try{
			AgreementStatus agreementStatus = agreementStatusRepository.findByAgreementId(agreementId);
			if(null != agreementStatus){
			
				return agreementStatus.getAgreementStatusId();
			
			}else{
				
				throw new NotFoundException("retrieveAgreementStatus: Agreement Id Not found in DB: "+agreementId);
			}
			}catch (Exception e){
				
				SLF4JLOGGER.error("retrieveAgreementStatus :: Error while retrieveAgreementStatus in DB", e.getMessage());
				throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrieveAgreementStatus in DB" );
				
			}
	}


	
	
}
