package com.namami.service;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * @author Anand Jha
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AgreementDto;
import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.AgreementTrackerStatusDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.bo.PaymentResponse;
import com.namami.bo.PersonDto;
import com.namami.bo.PostalAddressDto;
import com.namami.bo.RetrieveTrackerRequest;
import com.namami.bo.SystemCalculatedData;
import com.namami.common.CommonUtils;
import com.namami.common.RequestValidator;
import com.namami.common.session.ContextUtil;
import com.namami.dao.AgreementDao;
import com.namami.domain.types.AgreementStatusType;
import com.namami.domain.types.AgreementTrackerStatus;
import com.namami.entity.Agreement;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;

@Service
public class AgreementServiceImpl implements AgreementService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	AgreementDao agreementDao;
	
	@Autowired
	ContextUtil contextUtil;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private AgreementTrackerService agreementTrackerService;
	
	@Autowired
	private AgreementStatusService agreementStatusService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Override
	public Agreement createBlankAgreement() {
		return agreementDao.createBlankAgreement(contextUtil.getUserId());
	}
	

	@Override
	public Integer createAgreement(CreateAgreementReq createAgreementReq) {
		
		requestValidator.validateCreateAgreementReq(createAgreementReq);
		String user = ContextUtil.getUserId();
		Integer userId=new Integer(user);
		int agreementId = agreementDao.createAgreement(createAgreementReq, userId);
		//If agreement id is in request means it an update request. In this case we don't need to change status & tracker
		if(createAgreementReq.getAgreementId()== null){
			agreementStatusService.createAgreementStatus(agreementId);
			CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
			createTrackerRequest.setAgreementId(agreementId);
			createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_TERMS_ADDED.getValue());
			agreementTrackerService.createTracker(createTrackerRequest);
		}
		
		return agreementId;
		
	}

	@Override
	public void updateMiscellaneous(MiscellaneousTermsReq miscellaneousTermsReq) {
		
		String user = ContextUtil.getUserId();
		
		requestValidator.validateMiscellaneousTermsReq(miscellaneousTermsReq);
		
		Integer userId=new Integer(user);
		agreementDao.updateMiscellaneous(miscellaneousTermsReq, userId);
		
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(Integer.valueOf(miscellaneousTermsReq.getAgreementId()));
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_MISCE_ADDED.getValue());
		
	}

	//@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public AgreementDto retriveAgreementDetails(String agreementId) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(null == agreementId ){
			ValidationError error = new ValidationError("createAgreementReq", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementId", "Please verify your inputs");
		}

		
		//TODO to validate user have that agreement id linked in DB otherwise throw UNAUTHORIZED_EXCEPTION 
		
		return agreementDao.retriveAgreementDetails(agreementId);
		
	}

	@Override
	public List<AgreementDto> retriveUserAgreements() {
		
		List<AgreementDto> list = null;
		
		String user = ContextUtil.getUserId();
		
		list = agreementDao.retriveUserAgreements(user);
		if(null != list){
			for (AgreementDto agreementDto : list) {
				
				RetrieveTrackerRequest retrieveTrackerRequest = new RetrieveTrackerRequest();
				retrieveTrackerRequest.setAgreementId(agreementDto.getAgreementId());
				List<AgreementTrackerDto> agreementTrackerDtoList = agreementTrackerService.retrieveTracker(retrieveTrackerRequest);

				Integer agreementStatusId = agreementStatusService.retrieveAgreementStatus(agreementDto.getAgreementId());
				
				String agreementStatus = findAgreementStatus(agreementStatusId);
			
				AgreementTrackerStatusDto agreementTrackerStatusDto = findAgreementTrackerStatus(agreementTrackerDtoList);
				
				agreementDto.setAgreementTrackerStatus(agreementTrackerStatusDto);
				
				agreementDto.setAgreementStatus(agreementStatus);
				
				//Calculate cost & set
				PaymentResponse paymentResponse = paymentService.retrievePaymentDetail(String.valueOf(agreementDto.getAgreementId()));
				SystemCalculatedData calculatedData = new SystemCalculatedData();
				if(null != paymentResponse){
					
					String totalCost = paymentResponse.getTotalRegistrationCharges();
					
					calculatedData.setAgreementTotalCost(Integer.valueOf(totalCost));
					
					
				}
				
				//Calculate percentageDone & set
				Double percentageDone = findAgreementPercentage(agreementTrackerStatusDto);
				calculatedData.setAgreementPercentageDone(percentageDone);
				
				agreementDto.setCalculatedData(calculatedData);
				
				
			}
		}
		
		
		return list; 
		
	}


	private Double findAgreementPercentage(AgreementTrackerStatusDto agreementTrackerStatusDto) {
		
		
		if(null != agreementTrackerStatusDto){
			
			Double percentage = 0.00;
			final Double totalStatus = 30.00;
			Double statusCount = 0.00;		
			
			
			 try{
			/* Class classObj = AgreementTrackerStatusDto.class;
			 Constructor constructor = classObj.getConstructor();

             Object testClass = constructor.newInstance();

             Class clazz = testClass.getClass();*/

             Method[] methods = AgreementTrackerStatusDto.class.getMethods();
             
             for (Method potentialMethod : methods) {

                 if (potentialMethod.getName().startsWith("get") && !potentialMethod.getName().equals("getClass")){
                	 
                	 SLF4JLOGGER.info("Method name: "+potentialMethod.getName());
                	 
                	  Boolean returnValue = ((Boolean) potentialMethod.invoke(agreementTrackerStatusDto)).booleanValue(); //(Boolean)potentialMethod.invoke(agreementTrackerStatusDto);
                	  
                	 if(returnValue){
         				
         				statusCount = statusCount + 1;
         			} 
                	 
                 }
             }
			 }catch(Exception e){
				 
				 e.printStackTrace();
				 
			 }
			
			percentage = Math.floor(((statusCount*100)/totalStatus));
			
			return percentage;
			
			
		}else{
			
			return 0.00;
		}
		
		
	}


	private AgreementTrackerStatusDto findAgreementTrackerStatus( List<AgreementTrackerDto> agreementTrackerDtoList) {
		
		AgreementTrackerStatusDto agreementTrackerStatusDto = null;
		
		Boolean AGREEMENT_TERMS_ADDED 	= false;
		
		Boolean	AGREEMENT_OWNER_ADDED = false;
			
		Boolean	AGREEMENT_TENENT_ADDED = false;
				
		Boolean	AGREEMENT_WITNESS_ADDED = false;
			
		Boolean	AGREEMENT_PROPERTY_ADDED = false;
			
		Boolean	AGREEMENT_MISCE_ADDED  = false;
		
		Boolean	AGREEMENT_DATA_IN_PROGRESS = false;
			
		Boolean	AGREEMENT_ALL_REQ_DATA_ADDED = false;
			
		Boolean	USR_PYMENT_DONE = false;
			
		Boolean	ASSOCIATE_ASSIGNED = false;
			
		Boolean	USR_DRAFT_GENERATED = false;
			
		Boolean	USR_DRAFT_SENT_FOR_APPROVAL = false;
			
		Boolean	DRAFT_APPROVED_BY_OWNER = false;
		
		Boolean	DRAFT_APPROVED_BY_TENENT = false;
			
		Boolean	DRAFT_REJECTED_BY_OWNER = false;
		
		Boolean	DRAFT_REJECTED_BY_TENENT = false;
			
		Boolean	DRAFT_MODIFICATION_REQ_BY_PERSONS= false;
			
		Boolean	DRAFT_MODIFICATION_IN_PROGRESS = false;
			
		Boolean	DRAFT_MODIFICATION_DONE = false;
			
		Boolean	OWNER_BIOMETRIC_IN_PROGRESS = false;
			
		Boolean	OWNER_BIOMETRIC_IN_DONE = false;
			
		Boolean	TENENT_BIOMETRIC_IN_PROGRESS = false;
			
		Boolean	TENENT_BIOMETRIC_IN_DONE = false;
			
		Boolean	WITNESS_BIOMETRIC_IN_PROGRESS = false;
			
		Boolean	WITNESS_BIOMETRIC_DONE = false;
			
		Boolean	BIOMETRIC_DONE = false;
			
		Boolean	CHALAN_CREATED = false;
			
		Boolean	GOVT_DRAFT_CREATED = false;
			
		Boolean	GOVT_DRAFT_SUBMITTED = false;
			
		Boolean	GOVT_LNL_APPROVED = false;
			
		Boolean	HARD_COPY_DISPATCHED_IN_PROGRESS = false;
			
		Boolean	HARD_COPY_DISPATCHED_TO_USER = false;
			
		Boolean	COMPLETED = false;
			
		Boolean	AGREEMENT_DATA_DELETED = false;
			
		Boolean	AGREEMENT_DATA_UPDATED = false;
		
		if(null != agreementTrackerDtoList){
								
			for (AgreementTrackerDto agreementTrackerDto : agreementTrackerDtoList) {
				
				int agreementTrackerStatusId = agreementTrackerDto.getAgreementTrackerStatusId();
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_TERMS_ADDED.getValue()){
					
					AGREEMENT_TERMS_ADDED = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_OWNER_ADDED.getValue()){
					
					AGREEMENT_OWNER_ADDED = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_PROPERTY_ADDED.getValue()){
					
					AGREEMENT_PROPERTY_ADDED = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_TENENT_ADDED.getValue()){
					
					AGREEMENT_TENENT_ADDED = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_WITNESS_ADDED.getValue()){
					
					AGREEMENT_WITNESS_ADDED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.ASSOCIATE_ASSIGNED.getValue()){
					
					ASSOCIATE_ASSIGNED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.BIOMETRIC_DONE.getValue()){
					
					BIOMETRIC_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.CHALAN_CREATED.getValue()){
					
					CHALAN_CREATED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.COMPLETED.getValue()){
					
					COMPLETED = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_APPROVED_BY_OWNER.getValue()){
					
					DRAFT_APPROVED_BY_OWNER = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_APPROVED_BY_TENENT.getValue()){
					
					DRAFT_APPROVED_BY_TENENT = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_REJECTED_BY_OWNER.getValue()){
					
					DRAFT_REJECTED_BY_OWNER = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_REJECTED_BY_TENENT.getValue()){
					
					DRAFT_REJECTED_BY_TENENT = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_MODIFICATION_DONE.getValue()){
					
					DRAFT_MODIFICATION_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_MODIFICATION_IN_PROGRESS.getValue()){
					
					DRAFT_MODIFICATION_IN_PROGRESS = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.DRAFT_MODIFICATION_REQ_BY_PERSONS.getValue()){
					
					DRAFT_MODIFICATION_REQ_BY_PERSONS = true;
					
				}
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.GOVT_DRAFT_CREATED.getValue()){
					
					GOVT_DRAFT_CREATED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.GOVT_DRAFT_SUBMITTED.getValue()){
					
					GOVT_DRAFT_SUBMITTED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.GOVT_LNL_APPROVED.getValue()){
					
					GOVT_LNL_APPROVED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.HARD_COPY_DISPATCHED_IN_PROGRESS.getValue()){
					
					HARD_COPY_DISPATCHED_IN_PROGRESS = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.HARD_COPY_DISPATCHED_TO_USER.getValue()){
					
					HARD_COPY_DISPATCHED_TO_USER = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.OWNER_BIOMETRIC_IN_DONE.getValue()){
					
					OWNER_BIOMETRIC_IN_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.OWNER_BIOMETRIC_IN_PROGRESS.getValue()){
					
					OWNER_BIOMETRIC_IN_PROGRESS = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.TENENT_BIOMETRIC_IN_DONE.getValue()){
					
					TENENT_BIOMETRIC_IN_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.TENENT_BIOMETRIC_IN_PROGRESS.getValue()){
					
					TENENT_BIOMETRIC_IN_PROGRESS = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.USR_DRAFT_GENERATED.getValue()){
					
					USR_DRAFT_GENERATED = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.USR_DRAFT_SENT_FOR_APPROVAL.getValue()){
					
					USR_DRAFT_SENT_FOR_APPROVAL = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.USR_PYMENT_DONE.getValue()){
					
					USR_PYMENT_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.WITNESS_BIOMETRIC_DONE.getValue()){
					
					WITNESS_BIOMETRIC_DONE = true;
					
				}
				if(agreementTrackerStatusId == AgreementTrackerStatus.WITNESS_BIOMETRIC_IN_PROGRESS.getValue()){
					
					WITNESS_BIOMETRIC_IN_PROGRESS = true;
					
				}

				
				if(agreementTrackerStatusId >= AgreementTrackerStatus.AGREEMENT_OWNER_ADDED.getValue() && agreementTrackerStatusId <= AgreementTrackerStatus.AGREEMENT_MISCE_ADDED.getValue()){
					
					AGREEMENT_DATA_IN_PROGRESS = true;
					
				} 
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.AGREEMENT_ALL_REQ_DATA_ADDED.getValue()){
					
					AGREEMENT_ALL_REQ_DATA_ADDED = true;
					
				} 
				
				if(agreementTrackerStatusId == AgreementTrackerStatus.USR_DRAFT_SENT_FOR_APPROVAL.getValue()){
					
					USR_DRAFT_SENT_FOR_APPROVAL = true;
				}
				
				agreementTrackerStatusDto = new AgreementTrackerStatusDto();
				agreementTrackerStatusDto.setAgreement_terms_added(AGREEMENT_TERMS_ADDED);
				agreementTrackerStatusDto.setData_input_in_progress(AGREEMENT_DATA_IN_PROGRESS);
				agreementTrackerStatusDto.setAgreement_all_req_data_added(AGREEMENT_ALL_REQ_DATA_ADDED);
				agreementTrackerStatusDto.setUsr_draft_sent_for_approval(USR_DRAFT_SENT_FOR_APPROVAL);
				
				agreementTrackerStatusDto.setAgreement_data_deleted(AGREEMENT_DATA_DELETED);
				agreementTrackerStatusDto.setAgreement_data_updated(AGREEMENT_DATA_UPDATED);
				agreementTrackerStatusDto.setAgreement_misce_added(AGREEMENT_MISCE_ADDED);
				agreementTrackerStatusDto.setAgreement_owner_added(AGREEMENT_OWNER_ADDED);
				agreementTrackerStatusDto.setAgreement_property_added(AGREEMENT_PROPERTY_ADDED);
				agreementTrackerStatusDto.setAgreement_tenent_added(AGREEMENT_TENENT_ADDED);
				
				agreementTrackerStatusDto.setAgreement_witness_added(AGREEMENT_WITNESS_ADDED);
				agreementTrackerStatusDto.setAssociate_assigned(ASSOCIATE_ASSIGNED);
				agreementTrackerStatusDto.setBiometric_done(BIOMETRIC_DONE);
				agreementTrackerStatusDto.setChalan_created(CHALAN_CREATED);
				agreementTrackerStatusDto.setCompleted(COMPLETED);
				agreementTrackerStatusDto.setDraft_approved_by_owner(DRAFT_APPROVED_BY_OWNER);
				agreementTrackerStatusDto.setDraft_approved_by_tenent(DRAFT_APPROVED_BY_TENENT);
				agreementTrackerStatusDto.setDraft_modification_done(DRAFT_MODIFICATION_DONE);
				agreementTrackerStatusDto.setDraft_modification_in_progress(DRAFT_MODIFICATION_IN_PROGRESS);
				agreementTrackerStatusDto.setDraft_modification_req_by_persons(DRAFT_MODIFICATION_REQ_BY_PERSONS);
				agreementTrackerStatusDto.setDraft_rejected_by_owner(DRAFT_REJECTED_BY_OWNER);
				agreementTrackerStatusDto.setDraft_rejected_by_tenent(DRAFT_REJECTED_BY_TENENT);
				agreementTrackerStatusDto.setGovt_draft_created(GOVT_DRAFT_CREATED);
				agreementTrackerStatusDto.setGovt_draft_submitted(GOVT_DRAFT_SUBMITTED);
				agreementTrackerStatusDto.setGovt_lnl_approved(GOVT_LNL_APPROVED);
				agreementTrackerStatusDto.setHard_copy_dispatched_in_progress(HARD_COPY_DISPATCHED_IN_PROGRESS);
				agreementTrackerStatusDto.setHard_copy_dispatched_to_user(HARD_COPY_DISPATCHED_TO_USER);
				agreementTrackerStatusDto.setOwner_biometric_in_done(OWNER_BIOMETRIC_IN_DONE);
				agreementTrackerStatusDto.setTenent_biometric_in_done(TENENT_BIOMETRIC_IN_DONE);
				agreementTrackerStatusDto.setOwner_biometric_in_progress(OWNER_BIOMETRIC_IN_PROGRESS);
				agreementTrackerStatusDto.setTenent_biometric_in_progress(TENENT_BIOMETRIC_IN_PROGRESS);
				agreementTrackerStatusDto.setUsr_draft_generated(USR_DRAFT_GENERATED);
				
				agreementTrackerStatusDto.setUsr_pyment_done(USR_PYMENT_DONE);
				agreementTrackerStatusDto.setWitness_biometric_done(WITNESS_BIOMETRIC_DONE);
				agreementTrackerStatusDto.setWitness_biometric_in_progress(WITNESS_BIOMETRIC_IN_PROGRESS);
				
			}
			
			
			
		}
		
		return agreementTrackerStatusDto;
	}


	private String findAgreementStatus(Integer agreementStatusId) {
		String agreementStatus = "";
		if(agreementStatusId == AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue()){
			
			agreementStatus = "INPUTS IN PROGRESS";
			
		}else if(agreementStatusId == AgreementStatusType.DATA_INPUT_DONE.getValue()){
			
			agreementStatus = "INPUTS COMPLETED";
			
		}else if(agreementStatusId == AgreementStatusType.PAYMENT_DONE.getValue()){
			
			agreementStatus = "PAYMENT RECEIVED";
			
		}else if(agreementStatusId == AgreementStatusType.DRAFT_COPY_GENERATED.getValue()){
			
			agreementStatus = "DRAFT COPY GENERATED";
			
		}else if(agreementStatusId == AgreementStatusType.DRAFT_APPROVED.getValue()){
			
			agreementStatus = "DRAFT APPROVED";
			
		}else if(agreementStatusId == AgreementStatusType.BIOMETRIC_IN_PROGRESS.getValue()){
			
			agreementStatus = "BIOMETRIC IS IN PROGRESS";
			
		}else if(agreementStatusId == AgreementStatusType.BIOMETRIC_DONE.getValue()){
			
			agreementStatus = "BIOMETRIC DONE";
			
		}else if(agreementStatusId == AgreementStatusType.GOVERNMENT_PROCESS_IN_PROGRESS.getValue()){
			
			agreementStatus = "GOVERNMENT PROCESS IS IN PROGRESS";
			
		}else if(agreementStatusId == AgreementStatusType.GOVERNMENT_PROCESS_DONE.getValue()){
			
			agreementStatus = "GOVERNMENT PROCESS DONE";
			
		}else if(agreementStatusId == AgreementStatusType.HARD_COPY_DISPATCHED.getValue()){
			
			agreementStatus = "HARD COPY DISPATCHED";
			
		}else if(agreementStatusId == AgreementStatusType.COMPLETED.getValue()){
			
			agreementStatus = "COMPLETED";
			
		}else if(agreementStatusId == AgreementStatusType.MODIFICATION_IN_PROGRESS.getValue()){
			
			agreementStatus = "MODIFICATION IS IN PROGRESS";
			
		}
		return agreementStatus;
	}


	@Override
	public void confirmAgreementData(CreateTrackerRequest createTrackerRequest) {
	
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(null == createTrackerRequest ){
			ValidationError error = new ValidationError("confirmAgreementData", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementId", "Please verify your inputs");
		}
		
		Integer agreementid = createTrackerRequest.getAgreementId();
		
		if(null == agreementid ){
			ValidationError error = new ValidationError("confirmAgreementData", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementId", "Please verify your inputs");
		}
		
		
		//TODO to check if the agreement id belongs to correct user?
		//TODO to validate all the agreement data
		
		AgreementDto agreementDetails =  retriveAgreementDetails(String.valueOf(agreementid));
		
		if(null != agreementDetails){
			
			CreateAgreementReq terms = agreementDetails.getTerms();
			 List<PersonDto>  owners = agreementDetails.getOwners();
			 List<PersonDto>  witness = agreementDetails.getWitnesses();
			 List<PersonDto>  tenents = agreementDetails.getTenants();
			 PostalAddressDto property = agreementDetails.getProperty();
			 MiscellaneousTermsReq miscellaneousTerms = agreementDetails.getMiscellaneousTerms();
			 
			 if(null == terms || null == owners || null == witness || null == tenents || null == property || null == miscellaneousTerms){
				 
				 ValidationError error = new ValidationError("confirmAgreementData", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
				 errors.add(error);
				 throw new ValidationException(errors, "agreementData", "Please verify your inputs");
				 
			 }
			 
			
			
		} else{
			ValidationError error = new ValidationError("confirmAgreementData", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementData", "Please verify your inputs");
		}
		
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_DONE.getValue());
		agreementStatusService.updateAgreementStatus(agreementid, AgreementStatusType.DATA_INPUT_DONE.getValue());
		
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_ALL_REQ_DATA_ADDED.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		
		putEntryInAssociateAgreement(agreementid);
	}


	@Override
	public List<AgreementDto> retriveAdminAgreements() {
		List<AgreementDto> list = null;
		
		//TODO to get the parameter from UI side to display agreements
		list = agreementDao.retriveAdminAgreements();
		if(null != list){
			for (AgreementDto agreementDto : list) {
				RetrieveTrackerRequest retrieveTrackerRequest = new RetrieveTrackerRequest();
				retrieveTrackerRequest.setAgreementId(agreementDto.getAgreementId());
				List<AgreementTrackerDto> agreementTrackerDtoList = agreementTrackerService.retrieveTracker(retrieveTrackerRequest);

				Integer agreementStatusId = agreementStatusService.retrieveAgreementStatus(agreementDto.getAgreementId());
				
				String agreementStatus = findAgreementStatus(agreementStatusId);
			
				AgreementTrackerStatusDto agreementTrackerStatusDto = findAgreementTrackerStatus(agreementTrackerDtoList);
				
				agreementDto.setAgreementTrackerStatus(agreementTrackerStatusDto);
				
				agreementDto.setAgreementStatus(agreementStatus);
			}
		}
		
		
		return list; 
		
	}


	@Override
	public List<AgreementDto> retriveAssociateAgreements() {
		List<AgreementDto> list = null;
		
		String user = ContextUtil.getUserId();
		
		//get assigned agreements to this associates
		
		List<Integer> assignedAgreementsIds = retrieveAssociateAgreementIds(Integer.valueOf(user));
		
		list = agreementDao.retriveAssociateAgreements(assignedAgreementsIds);
		
		if(null != list){
			for (AgreementDto agreementDto : list) {
				RetrieveTrackerRequest retrieveTrackerRequest = new RetrieveTrackerRequest();
				retrieveTrackerRequest.setAgreementId(agreementDto.getAgreementId());
				List<AgreementTrackerDto> agreementTrackerDtoList = agreementTrackerService.retrieveTracker(retrieveTrackerRequest);

				Integer agreementStatusId = agreementStatusService.retrieveAgreementStatus(agreementDto.getAgreementId());
				
				String agreementStatus = findAgreementStatus(agreementStatusId);
			
				AgreementTrackerStatusDto agreementTrackerStatusDto = findAgreementTrackerStatus(agreementTrackerDtoList);
				
				agreementDto.setAgreementTrackerStatus(agreementTrackerStatusDto);
				
				agreementDto.setAgreementStatus(agreementStatus);
			}
		}
		
		
		return list; 
	}


	


	@Override
	public void putEntryInAssociateAgreement(Integer agreementid) {
		
		agreementDao.putEntryInAssociateAgreement(agreementid);
		
	}
	
	@Override
	public List<Integer> retrieveAssociateAgreementIds(Integer associateId) {
		
		return agreementDao.retrieveAssociateAgreementIds(associateId);
		
	}


}
