package com.namami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureAdmin;
import com.namami.bo.AgreementDto;
import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.AssociateAssignmentRequest;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.bo.UnAssignedAgreementsResponse;
import com.namami.bo.UserDto;
import com.namami.common.RestURIConstants;
import com.namami.service.AdminService;
import com.namami.service.AgreementService;
import com.namami.service.UserService;
/**
 * @author Anand Jha
 * 
 */

@RestController
public class AdminController {
	
	
	
	@Autowired
	AgreementService agreementService; 
	
	@Autowired
	AdminService adminService; 

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_ADMIN_AGREEMENTS, method = RequestMethod.GET)
	@SecureAdmin
	public List<AgreementDto> retriveAdminAgreements() {
		
		
		return  agreementService.retriveAdminAgreements();
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.ASSIGN_AGREEMENT_TO_ASSOCIATE, method = RequestMethod.POST)
	@SecureAdmin
	public void AssignAgreementToAssociate(@RequestBody final AssociateAssignmentRequest associateAssignmentRequest) {
		
		
		 adminService.AssignAgreementToAssociate(associateAssignmentRequest);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_UN_ASSIGNED_AGREEMENTS, method = RequestMethod.GET)
	@SecureAdmin
	public UnAssignedAgreementsResponse retriveUnAssignAgreementsAndAllAssociatesList() {
		
		return  adminService.retriveUnAssignAgreementsAndAllAssociatesList();
		
	}

}
