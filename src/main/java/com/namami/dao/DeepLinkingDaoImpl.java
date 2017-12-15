package com.namami.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.entity.ApprovalDeepLinking;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.ApprovalDeepLinkingRepository;

/**
 * @author Anand Jha
 * 
 */
@Service
public class DeepLinkingDaoImpl implements DeepLinkingDao {

	@Autowired
	ApprovalDeepLinkingRepository approvalDeepLinkingRepository;

	
	@Override
	public void approveDraftAgreement(String dpId, String dp) {
		
		ApprovalDeepLinking approvalDeepLinking = approvalDeepLinkingRepository.findById(Integer.valueOf(dpId));
		
		if(null != approvalDeepLinking){
			try{
				
			approvalDeepLinking.setIsDrftApproved(true);
			approvalDeepLinking.setIslinkActive(false);
			
			approvalDeepLinkingRepository.saveAndFlush(approvalDeepLinking);
			}catch(Exception e){
				
				throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while ApproveDraftAgreement in DB", e );
				
			}
			
		}else{
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue() , "Link Id not found : ApproveDraftAgreement: ApprovalDeepLinking in DB" );
		}
		
	}


	@Override
	public void createApprovalDeepLink(Integer agreementId, Integer personId, Integer personType, String deeplinkParam) {
		
		ApprovalDeepLinking approvalDeepLinking = new ApprovalDeepLinking();
		
		approvalDeepLinking.setAgreementId(agreementId);
		approvalDeepLinking.setPersonId(personId);
		approvalDeepLinking.setPersonType(personType);
		approvalDeepLinking.setDeeplinkParam(deeplinkParam);
		try{
	
			approvalDeepLinkingRepository.saveAndFlush(approvalDeepLinking);
			
		}catch(Exception e){
			
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while createApprovalDeepLink in DB", e );
			
		}
		
	}
	
	
}
