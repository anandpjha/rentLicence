package com.namami.bo.mapper;

import org.springframework.stereotype.Component;

import com.namami.bo.MiscellaneousTermsReq;
import com.namami.entity.Miscellaneous;

@Component
public class MiscellaneousMapper implements BaseMapper<MiscellaneousTermsReq, Miscellaneous>{

	@Override
	public MiscellaneousTermsReq map(Miscellaneous miscellaneous) {
		MiscellaneousTermsReq dto = new MiscellaneousTermsReq();
		
		dto.setAgreementId(String.valueOf(miscellaneous.getAgreementId()));
		
		dto.setMiscellaneousTerms(miscellaneous.getMiscellaneousTerms());
		
		dto.setPetAllowType(miscellaneous.getPetAllowType());
		dto.setRegChargePaidByType(miscellaneous.getRegChargePaidByType());
		
		
		dto.setFurnitureAndAppliancesSelected(miscellaneous.isFurnitureAndAppliancesSelected());
		dto.setFans(miscellaneous.getFans());
		dto.setTubeLight(miscellaneous.getTubeLight());
		dto.setBed(miscellaneous.getBed());
		dto.setSofa(miscellaneous.getSofa());
		dto.setTable(miscellaneous.getTable());
		dto.setChair(miscellaneous.getChair());
		dto.setAirConditioner(miscellaneous.getAirConditioner());
		dto.setGeyser(miscellaneous.getGeyser());
		
		return dto;
	}

	@Override
	public Miscellaneous reverseMap(MiscellaneousTermsReq miscellaneousTermsReq) {
		Miscellaneous entity = new Miscellaneous();
		
		entity.setAgreementId(Integer.valueOf(miscellaneousTermsReq.getAgreementId()));
		
		entity.setMiscellaneousTerms(miscellaneousTermsReq.getMiscellaneousTerms());
		
		entity.setPetAllowType(miscellaneousTermsReq.getPetAllowType());
		entity.setRegChargePaidByType(miscellaneousTermsReq.getRegChargePaidByType());
		
		entity.setFurnitureAndAppliancesSelected(miscellaneousTermsReq.isFurnitureAndAppliancesSelected());
		entity.setFans(miscellaneousTermsReq.getFans());
		entity.setTubeLight(miscellaneousTermsReq.getTubeLight());
		entity.setBed(miscellaneousTermsReq.getBed());
		entity.setSofa(miscellaneousTermsReq.getSofa());
		entity.setTable(miscellaneousTermsReq.getTable());
		entity.setChair(miscellaneousTermsReq.getChair());
		entity.setAirConditioner(miscellaneousTermsReq.getAirConditioner());
		entity.setGeyser(miscellaneousTermsReq.getGeyser());
		
		return entity;
	}


}
