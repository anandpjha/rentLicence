package com.namami.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;
import com.namami.common.Converter;
import com.namami.common.CommonConstants;
import com.namami.entity.RegisteredUser;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.UserRepository;

/**
 * @author Anand Jha
 * 
 */
@Service ( "userDao" )
public class UserDaoImpl implements UserDao {

	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Converter converter;

	@Override
	public void registerUser(RegisterUserRequest userRequest) {
		
		RegisteredUser user = converter.convertToUser(userRequest);
		
		//user.setRoleType(CommonConstants.USER_ROLE);
		user.setStatus(CommonConstants.ACTIVE);
		
		try{
			
			userRepository.saveAndFlush(user);
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("registerUser :: Error while saving user in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while saving user in DB" );
			
		}
		
	}

	@Override
	public UserDto findUser(String emailId) {
		try {
		
			RegisteredUser user = userRepository.findByUserEmail(emailId);
			
			if(null != user){
				
				return converter.convertToUserDto(user);
				
			} else{
				
				SLF4JLOGGER.info("findUser :: Email Not found in DB");
				//throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "findUser :: Email Not found in DB");
				
			}
		
		} catch (Exception e){
		
			SLF4JLOGGER.error("findUser :: Error while finding user in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while finding user in DB" );
		
		}
		
		return null;
		
	}

	@Override
	public void resetPassword(ResetPasswordRequest resetPasswordRequest, String newPassword) {
		
		try{
			
			
			RegisteredUser user = userRepository.findByUserEmail(resetPasswordRequest.getUserEmail());
			
			if(null != user){
			
				user.setPassword(newPassword);
			
				userRepository.saveAndFlush(user);
			
			}else{
				
				SLF4JLOGGER.error("resetPassword :: Email Not found in DB");
				throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "resetPassword :: Email Not found in DB");
			}
			
		}	catch (Exception e){
			
			SLF4JLOGGER.error("resetPassword :: Email Not found in DB");
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "resetPassword :: Email Not found in DB");
			
		}
		
	}

	@Override
	public void updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) {
		
		try{
			
			
			RegisteredUser user = userRepository.findByUserEmail(updateUserProfileRequest.getUserEmail());
			
			if(null != user){
			
				if(null != updateUserProfileRequest.getIsPassChange() && updateUserProfileRequest.getIsPassChange().equals(CommonConstants.TRUE)){
					user.setPassword(updateUserProfileRequest.getUserNewPassword());
				}
				if(null != updateUserProfileRequest.getIsMobChange() && updateUserProfileRequest.getIsMobChange().equals(CommonConstants.TRUE)){
					if(updateUserProfileRequest.getUserMobile().equals(user.getUserMobile())){
						updateUserProfileRequest.setIsMobChange("false");
					}else{
						user.setUserMobile(updateUserProfileRequest.getUserMobile());
					}
				}
				user.setUserName(updateUserProfileRequest.getName());
				user.setCity(updateUserProfileRequest.getCity());
				userRepository.saveAndFlush(user);
			
			}else{
				
				SLF4JLOGGER.error("updateUserProfile :: Email Not found in DB");
				throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "updateUserProfile :: Email Not found in DB");
			}
			
		}	catch (Exception e){
			//TODO to check how to handle diffrent type of exceptions
			SLF4JLOGGER.error("updateUserProfile :: Email Not found in DB");
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "updateUserProfile :: Email Not found in DB");
			
		}
		
	}

	@Override
	public List<UserDto> retrieveAllAssociateUser() {
		List<UserDto> associatesDtos = null;
		try {
			
			List<RegisteredUser> associates = userRepository.findByRoleType(CommonConstants.ASSOCIATE_ROLE);
			
			if(null != associates){
				
				associatesDtos = new ArrayList<UserDto>();
				
				for (RegisteredUser registeredUser : associates) {
					
					UserDto associateDto = converter.convertToUserDto(registeredUser);
					associatesDtos.add(associateDto);
					
				}
				
			} else{
				
				SLF4JLOGGER.info("retrieveAllAssociateUser :: No any associates found in DB");
				//throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "findUser :: Email Not found in DB");
				
			}
		
		} catch (Exception e){
		
			SLF4JLOGGER.error("retrieveAllAssociateUser :: Error while finding associates in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while finding associates in DB" );
		
		}
		
		return associatesDtos;
	}

	
	
}
