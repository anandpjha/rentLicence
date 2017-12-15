package com.namami.bo.mapper;

import org.springframework.stereotype.Component;

import com.namami.bo.CreateTrackerRequest;
import com.namami.entity.AgreementTracker;

@Component
public class AgreementTrackerMapper implements BaseMapper<CreateTrackerRequest, AgreementTracker>{

	@Override
	public CreateTrackerRequest map(AgreementTracker agreementTracker) {
		CreateTrackerRequest dto = new CreateTrackerRequest();
		dto.setAgreementId(agreementTracker.getAgreementId());
		//dto.setAgreementStatusId(agreementTracker.getAgreementStatusId());
		dto.setAgreementTrackerStatusId(agreementTracker.getAgreementTrackerStatusId());
		return dto;
	}

	@Override
	public AgreementTracker reverseMap(CreateTrackerRequest createTrackerRequest) {
		AgreementTracker entity = new AgreementTracker();
		entity.setAgreementId(createTrackerRequest.getAgreementId());
		//entity.setAgreementStatusId(createTrackerRequest.getAgreementStatusId());
		entity.setAgreementTrackerStatusId(createTrackerRequest.getAgreementTrackerStatusId());
		
		return entity;
	}


}
