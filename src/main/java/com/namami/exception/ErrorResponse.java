/**
 * ErrorResponse class will used as a DTO for errors.
 */

package com.namami.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Anand Jha
 * 
 */
@JsonInclude ( Include.NON_NULL )
public class ErrorResponse {

	private String errorCode;

	private String errorMessage;
	
	private String uiMessage;

	public String getUiMessage() {
		return uiMessage;
	}

	public void setUiMessage(String uiMessage) {
		this.uiMessage = uiMessage;
	}

	public String getErrorCode() {

		return errorCode;
	}

	/**
	 * @param erroCode
	 *            the erroCode to set
	 */
	public void setErrorCode( String erroCode ) {

		this.errorCode = erroCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {

		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage( String errorMessage ) {

		this.errorMessage = errorMessage;
	}

	/**
	 * @return the erroCode
	 */

	

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append( "ErrorResponse [errorCode=" );
		builder.append( errorCode );
		builder.append( ", errorMessage=" );
		builder.append( errorMessage );
		builder.append( ", lockTime=" );
		builder.append( uiMessage );
		builder.append( "]" );
		return builder.toString();
	}

}
