
package com.namami.exception;

/**
 * GlobalExceptionHandler class will be used to handle exception
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Anand Jha
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/** Logger. */
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String UI_SYSTEM_FAIL_MSG = "Sorry, There is some technical problem, Please try again";
	private static final String UI_NOT_FOUND_MSG ="Sorry, Plesae check your inputs & Try again";
	private static final String UI_UNAUTHORIZED_MSG ="Sorry, Plesae provide correct inputs & Try again";
	/**
	 * Method will be used for Bad request.
	 * @param ve - Validation
	 * @return errorResponse
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public final ValidationResponse handleException(final ValidationException ve) {

		SLF4JLOGGER.warn("Validation exception", ve);
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
		validationResponse.setValidationErrors(ve.getValidationErrors());
		validationResponse.setCustomData(ve.getCustomData());
		return validationResponse;
	}

	@ExceptionHandler(SystemFailureException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleSystemFailureException(SystemFailureException sfe) {
		SLF4JLOGGER.error("System Failure Exception", sfe);
		ErrorResponse resp = new ErrorResponse();
		resp.setErrorCode(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue());
		resp.setUiMessage(UI_SYSTEM_FAIL_MSG);
		return resp;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(Exception ex) {
		SLF4JLOGGER.error("Exception", ex);
		ex.printStackTrace();
		ErrorResponse resp = new ErrorResponse();
		resp.setErrorCode(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue());
		resp.setUiMessage(UI_SYSTEM_FAIL_MSG);
		return resp;
	}

	/**
	 * NotFoundException.
	 * @param notFoundException the notFoundException
	 * @return response, ErrorResponse type
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public final ErrorResponse handleException(final NotFoundException notFoundException) {
		SLF4JLOGGER.error("NOTFOUND Exception occured, Error Message : " + notFoundException.getErrorMessage() + " : Exception message : ",
				notFoundException.getMessage(), notFoundException);
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue());
		response.setErrorMessage(notFoundException.getErrorMessage());
		response.setUiMessage(UI_NOT_FOUND_MSG);
		return response;
	}

	/**
	 * UnAuthorizedException.
	 * @param unAuthorizedException the unAuthorizedException
	 * @return response
	 */
	@ExceptionHandler(UnAuthorizedException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public final ErrorResponse handleException(final UnAuthorizedException unAuthorizedException) {
		SLF4JLOGGER.error("UnAuthorizedException occured, Error code : " + unAuthorizedException.getErrorCode() + " : Exception message : "
				+ unAuthorizedException.getMessage(), unAuthorizedException);
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(unAuthorizedException.getErrorCode());
		response.setUiMessage(UI_UNAUTHORIZED_MSG);
		return response;
	}

	

	/**
	 * Method will be used for handling exception of type Duplicate Requests.
	 * 
	 * @param ce - ConflictException
	 * @return conflictErrorRespone - ConflictErrorRespone
	 */
	@ExceptionHandler(ConflictException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ErrorResponse handleException(final ConflictException ce) {
		SLF4JLOGGER.warn("ConflictException occured, Exception message : " + ce.getErrorMsg(), ce);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(GlobalErrorConstants.CONFLICT_EXCEPTION.getValue());
		errorResponse.setUiMessage(ce.getErrorMsg());
		return errorResponse;
	}

	/**
	 * Method will be used for Forbidden Exception.
	 * @param fe ForbiddenException
	 * @return errorResponse
	 **/
	@ExceptionHandler(ForbiddenException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public final ErrorResponse handleForbiddenException(final ForbiddenException fe) {
		SLF4JLOGGER.error("Forbidden Exception", fe);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(GlobalErrorConstants.FORBIDDEN_EXCEPTION.getValue());
		errorResponse.setUiMessage(fe.getErrorMessage());
		return errorResponse;
	}
	
}
