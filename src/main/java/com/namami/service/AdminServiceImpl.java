package com.namami.service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AssociateAssignmentRequest;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.UnAssignedAgreementsResponse;
import com.namami.bo.UserDto;
import com.namami.dao.AdminDao;
import com.namami.domain.types.AgreementTrackerStatus;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.NotFoundException;
/**
 * @author Anand Jha
 */

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	AdminDao admindao;
	
	@Autowired
	private AgreementTrackerService agreementTrackerService;
	
	@Autowired
	AgreementService agreementService;
	
	@Autowired
	private UserService userService;

	@Override
	public void AssignAgreementToAssociate(
			AssociateAssignmentRequest associateAssignmentRequest) {
		
		//Once agreement data done by end user, admin can able to assign this agreement to particular associate to work with user
		 
		
		String associateIdOrName = associateAssignmentRequest.getAssociateId();
		String associateId ="";
		Boolean associateIdFound = false;
		Boolean associateNameFound = false;
		
		List<UserDto> associatesList = userService.retrieveAllAssociateUser();
		if(null != associatesList){
		for (UserDto userDto : associatesList) {
			
			if(String.valueOf(userDto.getUserId()).equalsIgnoreCase(associateIdOrName)){
				associateIdFound = true;
				associateId = associateIdOrName;
				break;
			}else if(userDto.getName().equalsIgnoreCase(associateIdOrName)){
				associateNameFound = true;
				associateId = String.valueOf(userDto.getUserId());
				break;
			}
			
		}
	}
		
		if(associateIdFound || associateNameFound){
			
			associateAssignmentRequest.setAssociateId(associateId);
			
		}else{
			
			throw new NotFoundException("Associate not found");
		}
		
		//Agreement id has been already put as unassign status when all the data confirm by the user. So we need to just update it as assign
		
		//TODO: to update agreement status
		admindao.AssignAgreementToAssociate(associateAssignmentRequest);
		
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(associateAssignmentRequest.getAgreementId());
		
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.ASSOCIATE_ASSIGNED.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		
		
		
	}

	@Override
	public UnAssignedAgreementsResponse retriveUnAssignAgreementsAndAllAssociatesList() {
		
		UnAssignedAgreementsResponse response = null;
		
		response = new UnAssignedAgreementsResponse();
		
		List<UserDto> associatesDtos = userService.retrieveAllAssociateUser();
		
		List<String> UnAssignedAgreementlist = admindao.retriveUnAssignAgreements();
		
		if( null != associatesDtos){
			
			List<String> associates = new ArrayList<String>();
			for (UserDto userDto : associatesDtos) {
				String associate = userDto.getName();
				
				if(null == associate){
					
					associate = String.valueOf(userDto.getUserId());
				}
				associates.add(associate);
				
			}
			
			response.setAssociates(associates);
			
		}
		
		if(null != UnAssignedAgreementlist){
			
			response.setUnAssignedAgreements(UnAssignedAgreementlist);
			
		}	
		
		return response;
	}

	

}
