package com.namami.service;

import java.util.List;

import com.namami.bo.LoginUserRequest;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;

/**
 * @author Anand Jha
 * 
 */
public interface UserService {

	void registerUser(RegisterUserRequest userRequest);

	UserDto findUser(String userId);
	
	//private String createUserSecurePassword(String password);

	void loginUser(LoginUserRequest loginUserRequest);
	
	void makeLoggedinToUser(String email, String password);

	void resetPassword(ResetPasswordRequest resetPasswordRequest);

	void updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest);

	UserDto retrieveUserProfile();

	List<UserDto> retrieveAllAssociateUser();

}
