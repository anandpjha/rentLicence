package com.namami.service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.UpdateAgreementStatusRequest;
import com.namami.common.RequestValidator;
import com.namami.domain.types.AgreementStatusType;
import com.namami.domain.types.AgreementTrackerStatus;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;
/**
 * @author Anand Jha
 */

@Service
public class AssociateServiceImpl implements AssociateService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	AgreementTrackerService agreementTrackerService;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private AgreementStatusService agreementStatusService;

	@Override
	public List<String> retriveStatusType() {
		
		List<String> list = new ArrayList<String>();
		list.add("USR_DRAFT_GENERATED");

		list.add("USR_DRAFT_SENT_FOR_APPROVAL");

//		list.add("DRAFT_APPROVED_BY_PERSONS");

//		list.add("DRAFT_REJECTED_BY_PERSONS");

		list.add("DRAFT_MODIFICATION_REQ_BY_PERSONS");

		list.add("DRAFT_MODIFICATION_IN_PROGRESS");

		list.add("DRAFT_MODIFICATION_DONE");

		list.add("OWNER_BIOMETRIC_IN_PROGRESS");

		list.add("OWNER_BIOMETRIC_IN_DONE");

		list.add("TENENT_BIOMETRIC_IN_PROGRESS");

		list.add("TENENT_BIOMETRIC_IN_DONE");

		list.add("WITNESS_BIOMETRIC_IN_PROGRESS");

		list.add("WITNESS_BIOMETRIC_DONE");

		list.add("BIOMETRIC_DONE");

		list.add("CHALAN_CREATED");

		list.add("GOVT_DRAFT_CREATED");

		list.add("GOVT_DRAFT_SUBMITTED");

		list.add("GOVT_LNL_APPROVED");

		list.add("HARD_COPY_DISPATCHED_IN_PROGRESS");

		list.add("HARD_COPY_DISPATCHED_TO_USER");

		list.add("COMPLETED");

		return list;
	}

	@Override
	public void updateAgreementstatus(UpdateAgreementStatusRequest updateAgreementStatusRequest) {
		
		requestValidator.validateUpdateAgreementStatusRequest(updateAgreementStatusRequest);
		
		//TODO to validate if agreement is in proper status to update
		Integer agreementId = Integer.valueOf(updateAgreementStatusRequest.getAgreementId());
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(agreementId);
		//TODO to derive agreement status on the basis of input tracker status
		if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 10 
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 11){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.DRAFT_COPY_GENERATED.getValue());
		
		/*//TODO **************** below code should move when user approve the draft**************************************
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 12 ){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.DRAFT_APPROVED.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 13
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 14
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 15){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.MODIFICATION_IN_PROGRESS.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 16 ){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.DRAFT_APPROVED.getValue());
			//******************************************************	
		*/}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 17
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 18
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 19
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 20
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 21
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 22){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.BIOMETRIC_IN_PROGRESS.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 23 ){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.BIOMETRIC_DONE.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 24
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 25
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 26){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.GOVERNMENT_PROCESS_IN_PROGRESS.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 27
				|| AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 28){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.GOVERNMENT_PROCESS_DONE.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 29 ){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.HARD_COPY_DISPATCHED.getValue());
			
		}else if(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue() == 30 ){
			
			agreementStatusService.updateAgreementStatus(agreementId, AgreementStatusType.COMPLETED.getValue());
			
		}else{
			
			List<ValidationError> errors = new ArrayList<ValidationError>();
		
			ValidationError error = new ValidationError("updateAgreementStatusRequest", GlobalErrorConstants.INVALID_FORMAT.getValue());
			errors.add(error);
		
			throw new ValidationException(errors, "updateAgreementStatusRequest", "Please verify your inputs");
			
			
		}
		
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.valueOf(updateAgreementStatusRequest.getStatusType()).getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		
	}

	


}
