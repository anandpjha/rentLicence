package com.namami.service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.namami.bo.EmailServiceRequest;
import com.namami.bo.LoginUserRequest;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.ResetPasswordRequest;
import com.namami.bo.TokenDto;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.bo.UserDto;
import com.namami.common.CommonUtils;
import com.namami.common.CommonConstants;
import com.namami.common.RequestValidator;
import com.namami.common.session.ContextUtil;
import com.namami.common.session.UserAuthentication;
import com.namami.common.session.UserContext;
import com.namami.dao.UserDao;
import com.namami.dao.UserDaoImpl;
import com.namami.domain.types.TokenStatusType;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.UnAuthorizedException;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;
import com.namami.exception.ValidatorUtil;

/**
 * @author Anand Jha
 * 
 */
@Service ( "userService" )
public class UserServiceImpl implements UserService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public void registerUser(RegisterUserRequest userRequest) {
		
		requestValidator.validateRegisterUserRequest(userRequest);
		String token = "";
		Boolean acceptToRegister = false;
		Boolean checkBeforeRegister = false;
		TokenDto tokenDto = null;
		
		if(userRequest.getRoleType()!= null || !userRequest.getRoleType().equals("")){
			checkBeforeRegister = true;
		
		if(userRequest.getRoleType().equals(CommonConstants.USER_ROLE)){
			
			
			acceptToRegister= true;
			
		}
		
		if(userRequest.getRoleType().equals(CommonConstants.ADMIN_ROLE)){
			
			
			
			token = userRequest.getVerificationToken();
			//TODO to think more on this, This token should be triggered through mail to admin email id
			if(token.equals("Namami Samishan")){
				acceptToRegister= true;
			}
		}
		
		if(userRequest.getRoleType().equals(CommonConstants.ASSOCIATE_ROLE)){
			
			
			
			token = userRequest.getVerificationToken();
			
			tokenDto = tokenService.retrieveToken(token);
			
			if(null != tokenDto){
				
				if(tokenDto.getStatus().equals(TokenStatusType.ACTIVE.name()) && tokenDto.getAccessType().equals(CommonConstants.ASSOCIATE_ROLE)){
					
					acceptToRegister= true;
					
				}
				
			}
			
			
		}
	}
		if(checkBeforeRegister){
			
			if(!acceptToRegister){
				
				throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not autherised to Register" );
				
			}
			
		}
		
		
		String textPassword = userRequest.getPassword();
		
		String securePassword = createUserSecurePassword(textPassword);
		userRequest.setPassword(securePassword);
		userDao.registerUser(userRequest);
		userRequest.setPassword(textPassword);
		
		if(null != tokenDto){
			
			if(tokenDto.getStatus().equals(TokenStatusType.ACTIVE.name())){
				
				tokenService.deActivateToken(tokenDto.getTokenVal());
				
				
			}
			
		}
		
		//makeLoggedinToUser(userRequest.getEmail(), textPassword);
		
		EmailServiceRequest emailServiceRequest = emailService.createWelcomeEmailRequest(userRequest);
		
		//emailService.sendEmail(emailServiceRequest);
		
	}

	private String createUserSecurePassword(String password) {
		
		String passwordToHash = password;
        String salt = null;;
		try {
			salt = commonUtils.getSalt();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			
			e.printStackTrace();
		}
         
        String securePassword = commonUtils.getSecurePassword(passwordToHash, salt);
         
		return securePassword;
	}

	@Override
	public UserDto findUser(String emailId) {
		
		requestValidator.validatefindUser(emailId);
		
		return userDao.findUser(emailId);
	}
	
	

	@Override
	public void loginUser(LoginUserRequest loginUserRequest) {
		
		requestValidator.validateLoginUserRequest(loginUserRequest);
		
		boolean isAuthenticated = false;
		String email = loginUserRequest.getUserEmail();
		
		String userRoll = loginUserRequest.getUserRoll();
		
		UserDto userDto = findUser(email);
		
		
		if(null == userDto){
			
			
			SLF4JLOGGER.info("loginUser :: Email Not found in DB:: Email ::"+email);
			SLF4JLOGGER.info("#######User Not Found############");
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "loginUser :: Email Not found in DB:: Email ::"+email);
			
		}
		
		if(null != userDto){
			
			if(null == userRoll){
				
				if(userDto.getUserRollType().equals(CommonConstants.ADMIN_ROLE) || userDto.getUserRollType().equals(CommonConstants.ASSOCIATE_ROLE)){
					
					throw new UnAuthorizedException(GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Authorized to login ", "UserEmail: "+email);
					
				}
				
				
			}
			
			if(null != userRoll){
				if( null == userDto.getUserRollType() || !userDto.getUserRollType().equals(userRoll)){
					
					throw new UnAuthorizedException(GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Authorized to login ", "UserEmail: "+email);
				}
			}
			
			String userPassword = userDto.getPassword();
			
			String securePassword = createUserSecurePassword(loginUserRequest.getUserPassword());
			
			if(securePassword.equals(userPassword)){
				
				isAuthenticated = true;
				
			}
			
		}
		SLF4JLOGGER.info("#######isAuthenticated############"+isAuthenticated);
		if(isAuthenticated){
			
			createSessionContext(String.valueOf(userDto.getUserId()), userDto);
			
			SLF4JLOGGER.info("#######createSessionContext############");
			
		}else{
		
			throw new UnAuthorizedException(GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Authorized to login ", "UserEmail: "+email);
		}
		
	}

	/**
	 * @param userDetailsServiceRequest
	 * @param registrationServiceResponse
	 * @param user
	 */
	private void createSessionContext(String identifier, UserDto user) {
		
		SLF4JLOGGER.info("#######inside createSessionContext############");

		SecurityContextHolder.clearContext();

		final UserContext userContext = new UserContext();

		userContext.setUserId(identifier);
		userContext.setEmailAddress(user.getUserEmail());

		// call to update the customer status in user context.
		//authenticationService.updateCustomerStatusInSessionContext(userContext, /*user.getCustProfileStatus()*/null);

		userContext.setName(user.getName());
	
		userContext.setCity(user.getCity());
		userContext.setMobileNumber(user.getUserMobile());
		
		userContext.setUserRollType(user.getUserRollType());
		
		
		UserAuthentication userAuthentication = new UserAuthentication();

		Authentication authentication = userAuthentication.getCredentials();
		// put the session in the context
		userContext.setSessionId(RequestContextHolder.getRequestAttributes().getSessionId());
		
		SLF4JLOGGER.info("#######inside createSessionContext: userContext.setSessionId: "+RequestContextHolder.getRequestAttributes().getSessionId());
		userAuthentication.setDetails(userContext);

		authentication = authenticationService.authenticate(authentication);

		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		
		
	}

	@Override
	public void makeLoggedinToUser(String email, String password) {
		
		LoginUserRequest loginUserRequest = new LoginUserRequest();
		loginUserRequest.setUserEmail(email);
		loginUserRequest.setUserPassword(password);
		loginUser( loginUserRequest);
		
	}

	@Override
	public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
		
		requestValidator.validateResetPasswordRequest(resetPasswordRequest);
		
		String uuid = UUID.randomUUID().toString().substring(0, 4);
		int randomPIN = (int)(Math.random()*9000)+1000;
		
		String newPassword = uuid + String.valueOf(randomPIN);
		
		String securePassword = createUserSecurePassword(newPassword);

		userDao.resetPassword(resetPasswordRequest, securePassword);
		
		//send email
		EmailServiceRequest emailServiceRequest = createResetPassEmailRequest(resetPasswordRequest.getUserEmail(), newPassword);
		
		emailService.sendEmail(emailServiceRequest);
		
	}
	
	private EmailServiceRequest createResetPassEmailRequest(
			String email, String newPassword) {
		//
		EmailServiceRequest emailServiceRequest = new EmailServiceRequest();
		emailServiceRequest.setMailTo(email);
		emailServiceRequest.setSubject("Greetings from NAMAMI!!! ");
		StringBuilder msg = new StringBuilder();
		
		msg.append("<b>You have succesfully reset your password !!!</b><br>");
		msg.append("<b>Please use below password to login: </b><br>");
		msg.append("<b>Password: </b>"+newPassword+"<br><br>");
		msg.append("<b>Thanks & Best Regards </b><br>");
		msg.append("<b>-NAMAMI Team </b><br>");
		emailServiceRequest.setMessage(msg);
		
		return emailServiceRequest;
	}

	@Override
	public void updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) {
		
		requestValidator.validateUserProfileRequest(updateUserProfileRequest);
		
		
		boolean isAuthenticated = false;
		String email = updateUserProfileRequest.getUserEmail();
		
		
		UserDto userDto = findUser(email);
		
		if(null == userDto){
			
			
			SLF4JLOGGER.info("loginUser :: Email Not found in DB:: Email ::"+email);
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "loginUser :: Email Not found in DB:: Email ::"+email);
			
		}
		
		if(null != userDto){
			
			String userPassword = userDto.getPassword();
			
			String securePassword = createUserSecurePassword(updateUserProfileRequest.getPassword());
			
			if(securePassword.equals(userPassword)){
				
				isAuthenticated = true;
				
			}
			
		}
		
		if(isAuthenticated){
			
			
			String textPassword ="";
			if(null != updateUserProfileRequest.getIsPassChange() && updateUserProfileRequest.getIsPassChange().equals(CommonConstants.TRUE)){
				textPassword = updateUserProfileRequest.getUserNewPassword();
				String securePassword = createUserSecurePassword(textPassword);
				updateUserProfileRequest.setUserNewPassword(securePassword);
			}
			
			userDao.updateUserProfile(updateUserProfileRequest);
			
			if(!textPassword.equals("")){
				updateUserProfileRequest.setUserNewPassword(textPassword);
			}
			
			EmailServiceRequest emailServiceRequest = emailService.createUpdateUserProfileEmailRequest(updateUserProfileRequest);
			
			emailService.sendEmail(emailServiceRequest);
			
		} else{
			
			throw new UnAuthorizedException(GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Authorized to change password ", "UserEmail: "+email);
		
		}
		
		
		
		
		
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@Override
	public UserDto retrieveUserProfile() {
		String emailId = ContextUtil.getEmailAddress();
		UserDto user = findUser(emailId);
		
		if(user == null){
			SLF4JLOGGER.info("retrieveUserProfile :: Email Not found in DB:: Email ::"+emailId);
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue(), "retrieveUserProfile :: Email Not found in DB:: Email ::"+emailId);
		}
		return user;
	}

	@Override
	public List<UserDto> retrieveAllAssociateUser() {
		
		List<UserDto> associates = userDao.retrieveAllAssociateUser();
		
		return associates;
	}


}
