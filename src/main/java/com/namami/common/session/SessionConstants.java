package com.namami.common.session;
/**
 * 
 * @author Anand Jha
 * this  enum defines constants for the security, session and context handling
 *
 */
public enum SessionConstants {
	
	AUTHENTICATED("ROLE_USER"), 
	
	/**
	 * Added PARTIAL_AUTHORITY Role to provide limited functionality access.
	 */
	PARTIAL_AUTHORITY("ROLE_PARTIAL_AUTHORITY"),
	
	AUTHENTICATED_USER("RL User");
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private SessionConstants(String value) {
		this.value = value;
	}
	
	
	
}
