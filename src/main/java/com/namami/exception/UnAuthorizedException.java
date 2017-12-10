/**
 * Unauthorised exception.
 */

package com.namami.exception;

/**
 * UnAuthorizedException exception class.
 * @author Anand Jha
 * 
 */
public class UnAuthorizedException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = -2308917713315945859L;

	/** Variable message. */
	private String message;

	// Variable errorCode
	private String errorCode;

	private String customData;

	/**
	 * Parameterise constructor used for Panstore.
	 * @param message
	 */
	public UnAuthorizedException(final String message) {

		super();
		this.setMessage(message);
	}

	/**
	 * Parameterise constructor
	 * @param errorCode
	 * @param message
	 */
	public UnAuthorizedException(final String errorCode, final String message) {

		super();
		this.setErrorCode(errorCode);
		this.setMessage(message);
	}

	/**
	 * Parameterise constructor
	 * @param errorCode
	 * @param message
	 */
	public UnAuthorizedException(final String errorCode, final String message, final String customData) {

		super();
		this.setErrorCode(errorCode);
		this.setMessage(message);
		this.setCustomData(customData);
	}

	public UnAuthorizedException(String value, String message, Exception e) {

		super(e);
		this.message = message;
		this.errorCode = value;

	}

	public UnAuthorizedException(String errorCode, Exception e) {

		super(e);
		this.errorCode = errorCode;
	}

	public UnAuthorizedException(final String errorCode, final String message, final String customData, Exception e) {

		super(e);
		this.setErrorCode(errorCode);
		this.setMessage(message);
		this.setCustomData(customData);
	}

	/**
	 * @param messageEx
	 *            the messageEx
	 */
	public final void setMessage(final String messageEx) {

		this.message = messageEx;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("UnAuthorizedException [message=");
		builder.append(message);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", customData=");
		builder.append(customData);
		builder.append("]");
		return builder.toString();
	}
}
