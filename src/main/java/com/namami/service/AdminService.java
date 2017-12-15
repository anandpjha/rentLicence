package com.namami.service;

import java.util.List;

import com.namami.bo.AssociateAssignmentRequest;
import com.namami.bo.UnAssignedAgreementsResponse;

/**
 * @author Anand Jha
 */

public interface AdminService {

	void AssignAgreementToAssociate(
			AssociateAssignmentRequest associateAssignmentRequest);

	UnAssignedAgreementsResponse retriveUnAssignAgreementsAndAllAssociatesList();

	

}
