package com.namami.bo;

public class UserDto {

	private int userId;
	
	private String name;
	
	private String city;
	
	private String userEmail;
	
	private String userMobile;
	
	private String password;
	
	private String userRollType;
	
	

	public String getUserRollType() {
		return userRollType;
	}

	public void setUserRollType(String userRollType) {
		this.userRollType = userRollType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
}
