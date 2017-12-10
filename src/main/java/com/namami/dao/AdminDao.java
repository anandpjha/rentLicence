package com.namami.dao;

import java.util.List;

import com.namami.bo.AssociateAssignmentRequest;


/**
 * @author Anand Jha
 * 
 */
public interface AdminDao {

	void AssignAgreementToAssociate(
			AssociateAssignmentRequest associateAssignmentRequest);

	List<String> retriveUnAssignAgreements();

	
}
