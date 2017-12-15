package com.namami.bo;
/**
 * @author Anand Jha
 */

public class RegisterUserRequest {
	
	
	private String email;
	
	private String mobile;
	
	private String password;

	private String roleType;
	
	private String verificationToken;
	
	private String userType;
	
	
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userEmail) {
		this.email = userEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String userMobile) {
		this.mobile = userMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	

}
