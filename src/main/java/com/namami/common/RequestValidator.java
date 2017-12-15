package com.namami.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.namami.bo.AddPersonRequest;
import com.namami.bo.AddPostalAddressRequest;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.LoginUserRequest;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.UpdateAgreementStatusRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;
import com.namami.exception.ConflictException;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;
import com.namami.service.PersonType;
import com.namami.service.UserService;

/**
 * @author Anand Jha
 * 
 * Validation Utils class 
 */
@Component("requestValidator")
public class RequestValidator  {
	
	@Autowired
	private UserService userService;
	
	public void validateRegisterUserRequest(RegisterUserRequest request){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		errors = validatePassword(request.getPassword(), errors);
		errors = validateEmail(request.getEmail(), errors);
		errors = validateMobile(request.getMobile(), errors);
		if(errors.size()> 0){
			throw new ValidationException(errors, "registerUserRequest", "Please verify your inputs");
		}
		
		validateRegisteredEmail(request.getEmail());
		
	}
	
	public void validateUserProfileRequest(UpdateUserProfileRequest request){
		
		final String newPass = request.getUserNewPassword();
		final String newMob = request.getUserMobile();
		
		if(null != newPass){
			
			request.setIsPassChange(CommonConstants.TRUE);
			
		}
		if(null != newMob  ){
			
			request.setIsMobChange(CommonConstants.TRUE);
			
		}
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(null != request.getIsPassChange() && request.getIsPassChange().equals(CommonConstants.TRUE)){
			errors = validatePassword(request.getUserNewPassword(), errors);
		} else if(null != request.getIsMobChange() && request.getIsMobChange().equals(CommonConstants.TRUE)){
			errors = validateMobile(request.getUserMobile(), errors);
		} else{
			throw new ValidationException(errors, "UpdateUserProfileRequest", "Please verify your inputs");
		}
		
		errors = validateEmail(request.getUserEmail(), errors);
		
		if(errors.size()> 0){
			throw new ValidationException(errors, "UpdateUserProfileRequest", "Please verify your inputs");
		}
		
		
	}
	
	public void validateResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		errors = validateEmail(resetPasswordRequest.getUserEmail(), errors);
		
		if(errors.size()> 0){
			
			throw new ValidationException(errors, "resetPasswordRequest", "Please verify your inputs");
		}
		
	}

	public void validatefindUser(String emailId) {
	
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		errors = validateEmail(emailId, errors);
		
		if(errors.size()> 0){
			throw new ValidationException(errors, "validatefindUser", "Please verify your inputs");
		}
		
	}

	public void validateLoginUserRequest(LoginUserRequest loginUserRequest) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		errors = validatePassword(loginUserRequest.getUserPassword(), errors);
		errors = validateEmail(loginUserRequest.getUserEmail(), errors);
		
		if(errors.size()> 0){
			throw new ValidationException(errors, "loginUserRequest", "Please verify your inputs");
		}
		
	}

	
	private List<ValidationError> validatePassword (String password, List<ValidationError> errors) {
		if(password.equals("") || password.length()<= 0 || password.length() > 10){
			ValidationError error = new ValidationError("password", GlobalErrorConstants.INVALID_BIN_OR_RANGE.getValue());
			errors.add(error);
		}
		return errors;	
	}
	
	private List<ValidationError> validateEmail (String email, List<ValidationError> errors) {
		boolean result = true;
		try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		
		if(!result || email.equals("") || !email.contains("@") || email.length() > 30){
			ValidationError error = new ValidationError("email", GlobalErrorConstants.INVALID_BIN_OR_RANGE.getValue());
			errors.add(error);
		}
		
		return errors;	
	}
	
	private List<ValidationError> validateMobile (String mobile, List<ValidationError> errors) {
		if(mobile.equals("") ||  mobile.length() > 13){
			ValidationError error = new ValidationError("mobile", GlobalErrorConstants.INVALID_BIN_OR_RANGE.getValue());
			errors.add(error);
		}
		return errors;	
	}
	
	private void validateRegisteredEmail (String email) {
		
		UserDto user = userService.findUser(email);
		if(null != user){
			throw new ConflictException("You email is already registered with us, Please login or reset your password in case you don’t remember", "email");
		}
		
	}

	public void validateCreateAgreementReq(CreateAgreementReq createAgreementReq) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		errors = validateAgreementData(createAgreementReq, errors);
		//errors = validateEmail(createAgreementReq.getUserEmail(), errors);
		
		if(errors.size()> 0){
			throw new ValidationException(errors, "createAgreementReq", "Please verify your inputs");
		}
		
		
	}

	private List<ValidationError> validateAgreementData(CreateAgreementReq createAgreementReq,
			List<ValidationError> errors) {
		
		if(null == createAgreementReq /*|| null == createAgreementReq.getAgreementCity() || null == createAgreementReq.getAdvanceDeposit() || null == createAgreementReq.getAgreementPeriodInMonths()
				|| null == createAgreementReq.getAgreementStartDate() || null == createAgreementReq.getAgreementState() || null == createAgreementReq.getLockInPeriod() 
				|| null == createAgreementReq.getAgreementState()*/){
			ValidationError error = new ValidationError("createAgreementReq", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
		}
		
		return errors;
	}

	public void validateMiscellaneousTermsReq(MiscellaneousTermsReq miscellaneousTermsReq) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(CommonUtils.isEmpty(String.valueOf(miscellaneousTermsReq.getAgreementId()))){
			
			throw new ValidationException(errors, "miscellaneousTermsReq", "Please Save Agreement Terms tab first");
			
		}
		
		/*if(null == miscellaneousTermsReq || null == miscellaneousTermsReq.getMiscellaneousTerms() ){
			ValidationError error = new ValidationError("createAgreementReq", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
		}

		if(errors.size()> 0){
			throw new ValidationException(errors, "miscellaneousTermsReq", "Not a valid miscellaneousTermsReq");
		}*/
		
	}

	public void validateAddPersonRequest(AddPersonRequest request, PersonType personType) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(CommonUtils.isEmpty(String.valueOf(request.getAgreementId()))){
			
			throw new ValidationException(errors, "AddPersonRequest", "Please Save Agreement Terms tab first");
			
		}
		
		if(null != personType && personType.equals(PersonType.OWNER)){
			if(CommonUtils.isEmpty(request.getAadharUid()) || CommonUtils.isEmpty(request.getMobNumber()) || CommonUtils.isEmpty(request.getPanNo())
					 || CommonUtils.isEmpty(request.getOccupation()) /*|| CommonUtils.isEmpty(request.getEmail())*/
					|| CommonUtils.isEmpty(request.getPersonName()) || CommonUtils.isEmpty(String.valueOf(request.getAgreementId()))){
				ValidationError error = new ValidationError("AddPersonRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
				errors.add(error);
			}
		}else if(null != personType && personType.equals(PersonType.TENANT)){
			
			if(CommonUtils.isEmpty(request.getAadharUid()) || CommonUtils.isEmpty(request.getMobNumber()) || CommonUtils.isEmpty(request.getPanNo())
					 || CommonUtils.isEmpty(request.getOccupation()) /*|| CommonUtils.isEmpty(request.getEmail())*/
					|| CommonUtils.isEmpty(request.getPersonName()) || CommonUtils.isEmpty(String.valueOf(request.getAgreementId()))){
				ValidationError error = new ValidationError("AddPersonRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
				errors.add(error);
			}
			
		}else if(null != personType && personType.equals(PersonType.WITNESS)){
			
			if(CommonUtils.isEmpty(request.getAadharUid())
					|| CommonUtils.isEmpty(request.getPersonName()) || CommonUtils.isEmpty(String.valueOf(request.getAgreementId()))){
				ValidationError error = new ValidationError("AddPersonRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
				errors.add(error);
			}
			
		}

		if(errors.size()> 0){
			throw new ValidationException(errors, "AddPersonRequest", "Please verify your inputs");
		}

	}

	public void validateaddPostalAddressToAgreement(AddPostalAddressRequest addPostalAddressRequest) {
	
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(CommonUtils.isEmpty(String.valueOf(addPostalAddressRequest.getAgreementId()))){
			
			ValidationError error = new ValidationError("AddPostalAddressRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "addPostalAddressRequest", "Please Save Agreement Terms tab first");
			
		}
		
		
	}

	public void validateUpdateAgreementStatusRequest(UpdateAgreementStatusRequest updateAgreementStatusRequest) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(CommonUtils.isEmpty(updateAgreementStatusRequest.getAgreementId()) || CommonUtils.isEmpty(updateAgreementStatusRequest.getStatusType())){
			ValidationError error = new ValidationError("updateAgreementStatusRequest", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
		}

		if(errors.size()> 0){
			throw new ValidationException(errors, "UpdateAgreementStatusRequest", "Please verify your inputs");
		}
		
	}

	
	

		
	


}
