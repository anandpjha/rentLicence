/**
 * ConflictException class will be used to handle all conflict exceptions.
 */

package com.namami.exception;


/**
 * @author Anand Jha
 * 
 */
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -2792868919577991970L;

	private String errorMsg;

	private String customData;

	public ConflictException(final String errorMsg, String customData) {

		super();
		this.errorMsg = errorMsg;
		this.customData = customData;
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

	public String getErrorMsg() {

		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {

		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("ConflictException [errorMsg=");
		builder.append(errorMsg);
		builder.append(", customData=");
		builder.append(customData);
		builder.append("]");
		return builder.toString();
	}
}
