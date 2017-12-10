package com.namami.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.namami.common.CommonConstants;
import com.namami.common.session.ContextUtil;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.UnAuthorizedException;
import com.namami.exception.ValidatorUtil;

/**
 * 
 * @author Anand Jha
 *
 */
@Aspect
@Component
public class SecureCustomerAspect {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(SecureCustomerAspect.class);
	
	public SecureCustomerAspect() {
		
	}
	
	
	@Before("secureCustomerAnnotatedMethods()")
    public void validateCustomer(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		SLF4JLOGGER.info("Start validateCustomer in SecureCustomerAspect in Method: "+methodName);
		String user = ContextUtil.getUserId();
		if ( ValidatorUtil.isEmpty( user ) ) {
			SLF4JLOGGER.info(" User is  NULL in Context: "+methodName);
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is NULL" );
		}
		SLF4JLOGGER.info("End validateCustomer in SecureCustomerAspect  in Method: "+methodName);

    }
	
	@Before("secureAdminAnnotatedMethods()")
	@SecureCustomer
    public void validateAdmin(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		SLF4JLOGGER.info("Start validateAdmin in SecureCustomerAspect in Method: "+methodName);
		String userRole = ContextUtil.getUserRollType();
		if(!userRole.equals(CommonConstants.ADMIN_ROLE)){
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not admin user" );
		}
		SLF4JLOGGER.info("End validateAdmin in SecureCustomerAspect  in Method: "+methodName);
    }
	
	@Before("secureAssociateAnnotatedMethods()")
	@SecureCustomer
    public void validateAssociate(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		SLF4JLOGGER.info("Start validateAssociate in SecureCustomerAspect in Method: "+methodName);
		String userRole = ContextUtil.getUserRollType();
		if(!userRole.equals(CommonConstants.ASSOCIATE_ROLE)){
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Associate user" );
		}		
		SLF4JLOGGER.info("End validateAssociate in SecureCustomerAspect  in Method: "+methodName);

    }

	@Before("secureAdminAssociateAnnotatedMethods()")
	@SecureCustomer
    public void validateAdminAssociate(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		SLF4JLOGGER.info("Start validateAdminAssociate in SecureCustomerAspect in Method: "+methodName);
		String userRole = ContextUtil.getUserRollType();
		SLF4JLOGGER.info("userRole: "+userRole);
		Boolean isAdminOrAssociate = false;
		isAdminOrAssociate = (userRole.equals(CommonConstants.ASSOCIATE_ROLE) || userRole.equals(CommonConstants.ADMIN_ROLE));
		if(!isAdminOrAssociate){
			throw new UnAuthorizedException( GlobalErrorConstants.UNAUTHORIZED_EXCEPTION.getValue(), "User is not Associate or Admin user" );
		}
		SLF4JLOGGER.info("End validateAdminAssociate in SecureCustomerAspect  in Method: "+methodName);

    }


	
	@Pointcut("@annotation(com.namami.aspect.SecureCustomer)")
    private void secureCustomerAnnotatedMethods() {

    }
	
	@Pointcut("@annotation(com.namami.aspect.SecureAdmin)")
    private void secureAdminAnnotatedMethods() {

    }

	@Pointcut("@annotation(com.namami.aspect.SecureAssociate)")
    private void secureAssociateAnnotatedMethods() {

    }
	
	@Pointcut("@annotation(com.namami.aspect.SecureAdminAssociate)")
    private void secureAdminAssociateAnnotatedMethods() {

    }



}
