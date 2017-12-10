package com.namami.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AddPostalAddressRequest;
import com.namami.bo.CreateTrackerRequest;
import com.namami.common.RequestValidator;
import com.namami.dao.PostalAddressDao;
import com.namami.domain.types.AgreementTrackerStatus;

@Service
public class PostalAddressServiceImpl implements PostalAddressService {
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(PostalAddressServiceImpl.class);
	@Autowired
	PostalAddressDao postalAddressDao;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private AgreementTrackerService agreementTrackerService;
	
	
	@Override
	public Integer addPostalAddressToAgreement(AddPostalAddressRequest addPostalAddressRequest) {
		
		requestValidator.validateaddPostalAddressToAgreement(addPostalAddressRequest);
		SLF4JLOGGER.info("Validation done");
		Integer postalAddressId = postalAddressDao.addPostalAddress(addPostalAddressRequest);
		SLF4JLOGGER.info("property added done");
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(addPostalAddressRequest.getAgreementId());
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.DATA_INPUT_IN_PROGRESS.getValue());
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.AGREEMENT_PROPERTY_ADDED.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		SLF4JLOGGER.info("Tracker done");
		
		return postalAddressId;
	}

}
