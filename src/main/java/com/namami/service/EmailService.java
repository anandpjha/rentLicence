package com.namami.service;

import com.namami.bo.EmailServiceRequest;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.UpdateUserProfileRequest;

/**
 * @author Anand Jha
 * 
 */
public interface EmailService {

	void sendEmail(EmailServiceRequest emailServiceRequest);
	public EmailServiceRequest createWelcomeEmailRequest(
			RegisterUserRequest userRequest);
	EmailServiceRequest createUpdateUserProfileEmailRequest(UpdateUserProfileRequest updateUserProfileRequest);


}
