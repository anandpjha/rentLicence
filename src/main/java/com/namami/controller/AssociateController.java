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

import com.namami.aspect.SecureAdminAssociate;
import com.namami.aspect.SecureAssociate;
import com.namami.bo.AgreementDto;
import com.namami.bo.UpdateAgreementStatusRequest;
import com.namami.common.RestURIConstants;
import com.namami.service.AgreementService;
import com.namami.service.AssociateService;
import com.namami.service.UserService;

/**
 * @author Anand Jha
 * 
 */

@RestController
public class AssociateController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AgreementService agreementService; 
	
	@Autowired
	AssociateService associateService;
	

	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_ASSOCIATE_AGREEMENTS, method = RequestMethod.GET)
	@SecureAssociate
	public List<AgreementDto> retriveAssociateAgreements() {
		
		return  agreementService.retriveAssociateAgreements();
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.UPDATE_AGREEMENT_STATUS, method = RequestMethod.POST)
	@SecureAdminAssociate
	public void updateAgreementstatus(@RequestBody final UpdateAgreementStatusRequest updateAgreementStatusRequest) {
		
		associateService.updateAgreementstatus(updateAgreementStatusRequest);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_STATUS_LIST, method = RequestMethod.GET)
	@SecureAdminAssociate
	public List<String> retriveStatusType() {

		return associateService.retriveStatusType();
		

	}
	

}
