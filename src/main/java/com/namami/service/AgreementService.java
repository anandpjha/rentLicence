package com.namami.service;
/**
 * @author Anand Jha
 */
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.namami.bo.AgreementDto;
import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.entity.Agreement;

public interface AgreementService {

	Agreement createBlankAgreement();

	Integer createAgreement(CreateAgreementReq createAgreementReq);

	AgreementDto retriveAgreementDetails(String agreementId);

	List<AgreementDto> retriveUserAgreements();

	void updateMiscellaneous(MiscellaneousTermsReq miscellaneousTermsReq);
	
	public void confirmAgreementData(CreateTrackerRequest createTrackerRequest);

	List<AgreementDto> retriveAdminAgreements();

	List<AgreementDto> retriveAssociateAgreements();

	void putEntryInAssociateAgreement(Integer agreementid);
	
	public List<Integer> retrieveAssociateAgreementIds(Integer associateId);

}
