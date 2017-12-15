
package com.namami.exception;

/**
 * This Enum is created to map the validation errors with the error codes accos RL application
 * 
 * @author Anand Jha
 */
public enum GlobalErrorConstants {
	
	MISSING_MANDATORY_FIELD("RL_FV001"), 
	
	INVALID_FORMAT("RL_FV002"),

	MIN_VALUE_INCORRECT("RL_BV002"),

	MAX_VALUE_INCORRECT("RL_BV003"),

	INVALID_BIN_OR_RANGE("RL_BV006"),

	DUPLICATE_VALUE("RL_BV007"),

	SERVICE_EXCEPTION("RL_9010"), 
	
	SYSTEM_FAILURE_EXCEPTION("RL_9012"), 
	
	UNAUTHORIZED_EXCEPTION("RL_9014"), 
	
	VALIDATION_EXCEPTION("RL_9016"),

	NOT_FOUND_EXCEPTION("RL_S404"), 
	
	CONFLICT_EXCEPTION("RL_S409"), 
	
	FORBIDDEN_EXCEPTION("RL_S403");

	private String value;

	private GlobalErrorConstants(final String val) {

		value = val;
	}

	/**
	 * @return the value
	 */
	public String getValue() {

		return value;
	}

}
