package com.namami.dao;

import java.util.List;

import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;

/**
 * @author Anand Jha
 * 
 */
public interface UserDao {



	void registerUser(RegisterUserRequest userRequest);

	UserDto findUser(String userId);

	void resetPassword(ResetPasswordRequest resetPasswordRequest, String newPassword);

	void updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest);

	List<UserDto> retrieveAllAssociateUser();

}
