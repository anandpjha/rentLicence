package com.namami.service;

import java.util.List;

import com.namami.bo.UpdateAgreementStatusRequest;

/**
 * @author Anand Jha
 */

public interface AssociateService {

	List<String> retriveStatusType();

	void updateAgreementstatus(
			UpdateAgreementStatusRequest updateAgreementStatusRequest);

	

}
