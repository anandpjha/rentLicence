package com.namami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.namami.service.DeepLinkingService;
import com.namami.service.UserService;
/**
 * @author Anand Jha
 * 
 */

@RestController
public class DeepLinkingController {
	
	
	@Autowired
	DeepLinkingService deepLinkingService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.APPROVE_DRF_AGREEMENT, method = RequestMethod.POST)
	@SecureAdmin
	/**
	 * 
	 * @param deeplinking id
	 * @param Deeplinkingparam
	 */
	public String approveDraftAgreement(@PathVariable final String dpId, @PathVariable final String dp) {
		
		
		deepLinkingService.approveDraftAgreement(dpId,  dp);
		 
		 return "Thanks for your Approval";
		
	}
	
	
}
