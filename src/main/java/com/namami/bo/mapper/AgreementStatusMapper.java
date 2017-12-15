package com.namami.bo.mapper;

import org.springframework.stereotype.Component;

import com.namami.bo.AgreementStatusDto;
import com.namami.entity.AgreementStatus;

@Component
public class AgreementStatusMapper implements BaseMapper<AgreementStatusDto, AgreementStatus>{

	@Override
	public AgreementStatusDto map(AgreementStatus agreementStatus) {
		AgreementStatusDto dto = new AgreementStatusDto();
		dto.setAgreementId(agreementStatus.getAgreementId());
		dto.setAgreementStatusId(agreementStatus.getAgreementStatusId());
		dto.setAnyMsg(agreementStatus.getAnyMessage());
		return dto;
	}

	@Override
	public AgreementStatus reverseMap(AgreementStatusDto agreementStatusDto) {
		AgreementStatus entity = new AgreementStatus();
		entity.setAgreementId(agreementStatusDto.getAgreementId());
		entity.setAgreementStatusId(agreementStatusDto.getAgreementStatusId());
		entity.setAnyMessage(agreementStatusDto.getAnyMsg());
		
		return entity;
	}


}
