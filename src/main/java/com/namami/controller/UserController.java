package com.namami.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureCustomer;
import com.namami.bo.LoginUserRequest;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;
import com.namami.common.CommonConstants;
import com.namami.common.RestURIConstants;
import com.namami.service.UserService;

/**
 * @author Anand Jha
 * 
 */

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(UserController.class);
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.LOGIN_INTERNAL_USER, method = RequestMethod.POST)
	public void loginInternalUser(@RequestBody final LoginUserRequest loginUserRequest){
		
		if(loginUserRequest.getUserType().equals("Admin")){
			
			loginUserRequest.setUserRoll(CommonConstants.ADMIN_ROLE);
			
		}else if(loginUserRequest.getUserType().equals("Associate")){
			
			loginUserRequest.setUserRoll(CommonConstants.ASSOCIATE_ROLE);
			
		} else{
				
			loginUserRequest.setUserRoll("invalidUser");
		}
		
		
		userService.loginUser(loginUserRequest);
		
		
	}
	
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.LOGIN_USER, method = RequestMethod.POST)
	public void loginUser(@RequestBody final LoginUserRequest loginUserRequest){
		
		loginUserRequest.setUserRoll(CommonConstants.USER_ROLE);
		
		userService.loginUser(loginUserRequest);
		
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.FIND_USER, method = RequestMethod.GET)
	@SecureCustomer
	public UserDto findUser(@PathVariable final String emailId){
		
		return userService.findUser(emailId);
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.REGISTER_USER, method = RequestMethod.POST)
	public void registerUser(@RequestBody final RegisterUserRequest userRequest){
		
		userRequest.setRoleType(CommonConstants.USER_ROLE);
		
		userService.registerUser(userRequest);
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.REGISTER_INTERNAL_USER, method = RequestMethod.POST)
	public void registerInternalUser(@RequestBody final RegisterUserRequest userRequest){
		
		if(userRequest.getUserType().equals("Admin")){
			
		userRequest.setRoleType(CommonConstants.ADMIN_ROLE);
		
		}else if(userRequest.getUserType().equals("Associate")){
		
		userRequest.setRoleType(CommonConstants.ASSOCIATE_ROLE);
		
		} else{
			
			userRequest.setRoleType("invalidUser");
		}
		userService.registerUser(userRequest);
	}
	
	
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.RESET_PASSWORD, method = RequestMethod.POST)
	public void resetPassword(@RequestBody final ResetPasswordRequest resetPasswordRequest){
		
		userService.resetPassword(resetPasswordRequest);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.RETRIEVE_USER_PROFILE, method = RequestMethod.GET)
	@SecureCustomer
	public UserDto retrieveUserProfile(){
		
		return userService.retrieveUserProfile();
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.UPDATE_USER_PROFILE, method = RequestMethod.POST)
	@SecureCustomer
	public void updateUserProfile(@RequestBody final UpdateUserProfileRequest updateUserProfileRequest){
		
		userService.updateUserProfile(updateUserProfileRequest);
	}
	

	/**
	 * Invalidating Security Context Holder on logout
	 * 
	 * @param req
	 * @param res
	 * @return String
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.LOGOUT_URL, method = RequestMethod.GET)
	@SecureCustomer
	public void logOut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		SecurityContextHolder.clearContext();
		

	}
	
	


}
