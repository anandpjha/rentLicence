package com.namami.dao;

import java.util.List;

import com.namami.bo.AgreementDto;
import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.entity.Agreement;

/**
 * @author Jagdish Kolhe
 * 
 */
public interface AgreementDao {

	/**
	 * Creates a blank agreement. 
	 * @return agreementId
	 */
	Agreement createBlankAgreement(String userId);

	Integer createAgreement(CreateAgreementReq createAgreementReq, Integer userId);

	AgreementDto retriveAgreement(String agreementId);
	
	AgreementDto retriveAgreementDetails(String userId);

	List<AgreementDto> retriveUserAgreements(String userId);

	void updateMiscellaneous(MiscellaneousTermsReq miscellaneousTermsReq, Integer userId);

	List<AgreementDto> retriveAdminAgreements();

	List<AgreementDto> retriveAssociateAgreements(List<Integer> assignedAgreementsIds);

	void putEntryInAssociateAgreement(Integer agreementid);

	List<Integer> retrieveAssociateAgreementIds(Integer associateId);
}
