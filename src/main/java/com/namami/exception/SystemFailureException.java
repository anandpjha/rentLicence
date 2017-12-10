/**
 * SystemFailureException- A generalise exception.
 */

package com.namami.exception;

/**
 * System Failure Exception class.
 * @author Anand Jha
 * 
 */
public class SystemFailureException extends RuntimeException {

	/** Unique id for the class - serialVersionUID. */
	private static final long serialVersionUID = 4962321737722528322L;

	private String errorMessage;

	private String errorCode;

	/**
	 * private Constructor (This must be private - Anupam.).
	 * @param e
	 *            - RuntimeException
	 */
	@Deprecated
	private SystemFailureException(final Throwable e) {

		super(e);
	}

	/**
	 * Constructor.
	 * @param ee
	 *            - Exception
	 */
	@Deprecated
	public SystemFailureException(final Exception ee) {

		super(ee);
		handleException(ee);
	}

	/**
	 * @return the errorMessage
	 */
	public SystemFailureException(String errCode, String errMsg) {

		super(errMsg);
		errorMessage = errMsg;
		errorCode = errCode;
	}

	/**
	 * @return the errorMessage
	 */
	public SystemFailureException(String errCode, String errMsg, Throwable e) {

		super(errMsg);
		errorMessage = errMsg;
		errorCode = errCode;
		handleException(e);
	}

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
	 * @throws SystemFailureException
	 */
	public static void handleException(Throwable e) {

		/** Please add more code for future use */
		throw new SystemFailureException(e);
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("SystemFailureException [errorMessage=");
		builder.append(errorMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append("]");
		return builder.toString();
	}

}
