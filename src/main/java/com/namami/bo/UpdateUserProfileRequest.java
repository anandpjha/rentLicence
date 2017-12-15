package com.namami.bo;
/**
 * 
 * @author Anand Jha
 *
 */
public class UpdateUserProfileRequest {
	
	
	private String userMobile;
	
	private String userNewPassword;
	
	private String isPassChange;
	
	private String isMobChange;
	
	private String name;
	
	private String city;
	
	private String password;

	private String userEmail;
	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserNewPassword() {
		return userNewPassword;
	}

	public void setUserNewPassword(String userNewPassword) {
		this.userNewPassword = userNewPassword;
	}

	public String getIsPassChange() {
		return isPassChange;
	}

	public void setIsPassChange(String isPassChange) {
		this.isPassChange = isPassChange;
	}

	public String getIsMobChange() {
		return isMobChange;
	}

	public void setIsMobChange(String isMobChange) {
		this.isMobChange = isMobChange;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	
}
