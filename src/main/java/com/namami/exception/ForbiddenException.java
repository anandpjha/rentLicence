
package com.namami.exception;

/**
 * ForbiddenException class used to handle all the forbidden exceptions
 * @author Anand Jha
 * 
 */
public class ForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3357956562697075357L;

	private String errorCode;

	private String errorMessage;

	private String customData;

	public ForbiddenException(String errorCode, String errorMessage, String cusomtData) {

		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.customData = cusomtData;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {

		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {

		this.errorCode = errorCode;
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
	public void setErrorMessage(String errorMessage) {

		this.errorMessage = errorMessage;
	}

	/**
	 * @return the customData
	 */
	public String getCustomData() {

		return customData;
	}

	/**
	 * @param customData
	 *            the customData to set
	 */
	public void setCustomData(String customData) {

		this.customData = customData;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("ForbiddenException [errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", customData=");
		builder.append(customData);
		builder.append("]");
		return builder.toString();
	}

}
