package com.namami.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namami.aspect.SecureCustomer;
import com.namami.bo.AddPersonRequest;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.PersonDto;
import com.namami.common.CommonUtils;
import com.namami.common.RequestValidator;
import com.namami.dao.PersonDao;
import com.namami.domain.types.AgreementTrackerStatus;
import com.namami.entity.AgreementPerson;
import com.namami.entity.Person;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;

/**
 * @author Anand Jha
 * 
 */
@Service ( "personService" )
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private AgreementTrackerService agreementTrackerService;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Override
	@Transactional
	@SecureCustomer
	public PersonDto addPerson(AddPersonRequest request, PersonType personType) {
		
		requestValidator.validateAddPersonRequest(request, personType);
		
		//check if it is a update call
		if( request.getPersonId() != null ) {
			return updatePerson(request);
		}
		
		PersonDto person = personDao.addPerson(request, personType); 
		//TODO: to update agreement status
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(request.getAgreementId());
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		
		if(personType.equals(PersonType.OWNER)){
			
			Integer agreementTrackerStatusId = AgreementTrackerStatus.AGREEMENT_OWNER_ADDED.getValue();
			createTrackerRequest.setAgreementTrackerStatusId(agreementTrackerStatusId);
			
		}else if(personType.equals(PersonType.TENANT)){
			
			Integer agreementTrackerStatusId = AgreementTrackerStatus.AGREEMENT_TENENT_ADDED.getValue();
			createTrackerRequest.setAgreementTrackerStatusId(agreementTrackerStatusId);
			
		}else if(personType.equals(PersonType.WITNESS)){
			
			Integer agreementTrackerStatusId = AgreementTrackerStatus.AGREEMENT_WITNESS_ADDED.getValue();
			createTrackerRequest.setAgreementTrackerStatusId(agreementTrackerStatusId);
			
		}
		
		
		
		agreementTrackerService.createTracker(createTrackerRequest);


		return person;
	}

	@Override
	@Transactional
	@SecureCustomer
	public PersonDto updatePerson(AddPersonRequest request) {
		
		requestValidator.validateAddPersonRequest(request, null);
		
		PersonDto person = personDao.updatePerson(request, null); 
		//TODO: to update agreement status
		//TODO to think how to track 
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(request.getAgreementId());
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_DATA_UPDATED.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		
		return person;
	}

	
	@Override
	@SecureCustomer
	public PersonDto retrivePerson(String personId) {
		
		if(CommonUtils.isEmpty(personId)){
			List<ValidationError> errors = new ArrayList<ValidationError>();
			ValidationError error = new ValidationError("AddPersonRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "AddPersonRequest", "Please verify your inputs");
		}
		
		return personDao.retrivePerson(personId);
	}


	
	
	private AgreementPerson preparePersonAgreement(AddPersonRequest request,
			Person person) {
		AgreementPerson agreementPerson = new AgreementPerson();
		agreementPerson.setAgreementId(request.getAgreementId());
		agreementPerson.setPersonId(person.getPersonId());		

		return agreementPerson;
	}

	

	@Override
	@SecureCustomer
	public void deletePerson(AddPersonRequest request) {
		
		
		int personId = request.getPersonId();
		if(CommonUtils.isEmpty(String.valueOf(personId))){
			List<ValidationError> errors = new ArrayList<ValidationError>();
			ValidationError error = new ValidationError("deletePerson", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "deletePerson", "Please verify your inputs");
		}
		
		personDao.deletePerson(personId);
		//TODO to think how to track 
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(request.getAgreementId());
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_DATA_DELETED.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);

	}

}
