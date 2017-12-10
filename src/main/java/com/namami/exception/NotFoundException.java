/**
 * 
 */

package com.namami.exception;


/**
 * @author Anand Jha
 * 
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4962321737722528322L;

	private String errorMessage;

	private String errorCode;

	/**
	 * Parameterise constructor.
	 * @param messageEx
	 *            the messageEx
	 */
	public NotFoundException(final String messageEx) {

		super();
		this.errorMessage = messageEx;
	}

	/**
	 * 
	 * @param errCode
	 * @param errMsg
	 */
	public NotFoundException(String errCode, String errMsg) {

		super();
		errorMessage = errMsg;
		errorCode = errCode;
	}

	/**
	 * 
	 * @param errCode
	 * @param errMsg
	 * @param e
	 */
	public NotFoundException(String errCode, String errMsg, Throwable e) {

		errorMessage = errMsg;
		errorCode = errCode;
		handleException(e);
	}

	/**
	 * private Constructor (This must be private - Anupam.).
	 * @param e
	 *            - RuntimeException
	 */
	private NotFoundException(final Throwable e) {

		super(e);
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
	 * @return the erroCode
	 */
	public String getErrorCode() {

		return errorCode;
	}

	/**
	 * @param erroCode
	 *            the erroCode to set
	 */
	public void setErrorCode(String erroCode) {

		this.errorCode = erroCode;
	}

	/**
	 * Common Exception Handling.
	 * @param e
	 * @throws InternalServerException
	 */
	public static void handleException(Throwable e) {

		/** Please add more code for future use */
		throw new NotFoundException(e);
	}

	

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("NotFoundException [errorMessage=");
		builder.append(errorMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append("]");
		return builder.toString();
	}

}
