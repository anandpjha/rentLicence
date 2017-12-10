package com.namami.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AssociateAssignmentRequest;
import com.namami.entity.AgreementAssociate;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.AgreementAssociateRepository;

/**
 * @author Anand Jha
 * 
 */
@Service
public class AdminDaoImpl implements AdminDao {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);
	
	@Autowired
	AgreementAssociateRepository agreementAssociateRepository;

	@Override
	public void AssignAgreementToAssociate(
			AssociateAssignmentRequest associateAssignmentRequest) {
		
		try{
			AgreementAssociate agreementAssociate = agreementAssociateRepository.findByAgreementId(associateAssignmentRequest.getAgreementId());
			
			if(null != agreementAssociate){
		
				
				agreementAssociate.setAssociateId(Integer.valueOf(associateAssignmentRequest.getAssociateId()));
				agreementAssociate.setAssociateType("NORMAL");
				agreementAssociate.setProgressStatus("ASSIGNED");
				agreementAssociateRepository.saveAndFlush(agreementAssociate);
				
			}else{
				
				SLF4JLOGGER.error("AssignAgreementToAssociate :: Error while AssignAgreementToAssociate in DB: Entry is not found");
				throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue() , "Agreement Id not found : AssignAgreementToAssociate in DB" );
				
			}
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("AssignAgreementToAssociate :: Error while AssignAgreementToAssociate in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while AssignAgreementToAssociate in DB" );
			
		}
		
		
		
	}

	@Override
	public List<String> retriveUnAssignAgreements() {
		List<String> agreement = null;
		
		try{
		
			List<AgreementAssociate> agreementAssociate = agreementAssociateRepository.findByProgressStatus("UNASSIGNED");
			
			if(null != agreementAssociate){
				
				agreement = new ArrayList<String>();
			
				for (AgreementAssociate agreementass : agreementAssociate) {
					
					agreement.add(agreementass.getAgreementId().toString());
				}
			}
		
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveUnAssignAgreements :: Error while retrieveTracker Tracker in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retriveUnAssignAgreements Tracker in DB" );
			
		}
		return agreement;
	}
	
	

}
