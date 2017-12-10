/**
 * ValidationException class will be used to handle all validation exceptions.
 */

package com.namami.exception;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Anand Jha
 * 
 */
public class ValidationException extends RuntimeException {

	/**
	 * Unique serial version id.
	 */
	private static final long serialVersionUID = 4937873528498781509L;

	private String customData;

	private String invokeUrl;

	/** validationErrors variable - list of errors. */
	private List<ValidationError> validationErrors;

	public ValidationException(final List<ValidationError> errors, final String url, String customData) {

		super();
		this.validationErrors = errors;
		this.invokeUrl = url;
		this.customData = customData;
	}

	/**
	 * This constructor is added for Panstore error response.
	 * @param errors
	 * @param url
	 */
	public ValidationException(final List<ValidationError> errors, final String url) {

		super();
		this.validationErrors = errors;
		this.invokeUrl = url;
	}

	public String getCustomData() {

		return customData;
	}

	public String getInvokeUrl() {

		return invokeUrl;
	}

	/**
	 * This method returns list of validation errors.
	 * @return the validationErrors
	 */
	public final List<ValidationError> getValidationErrors() {

		if (validationErrors == null) {
			validationErrors = new ArrayList<ValidationError>();
		}
		return validationErrors;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("ValidationException [customData=");
		builder.append(customData);
		builder.append(", invokeUrl=");
		builder.append(invokeUrl);
		builder.append(", validationErrors=");
		builder.append(validationErrors);
		builder.append("]");
		return builder.toString();
	}
}
