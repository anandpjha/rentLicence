package com.namami.common.session;

/**
 * this will hold the data required to be in context
 * @author Anand Jha
 *
 */
public class UserContext {
	
	private String userId;

    private String emailAddress;
	
	private String name;
	
	private String city;
	
	private String mobileNumber;
	
	private boolean hasPwd;
	
	private String userStatus;
	
	private String lastLogin;
	
    private String ipAddress;
    
    private String sessionId;
    
    private String userRollType;
    
    
    
    
    
	public String getUserRollType() {
		return userRollType;
	}

	public void setUserRollType(String userRollType) {
		this.userRollType = userRollType;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isHasPwd() {
		return hasPwd;
	}

	public void setHasPwd(boolean hasPwd) {
		this.hasPwd = hasPwd;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserContext [userId=");
		builder.append(userId);
		builder.append(", emailAddress=");
		builder.append(emailAddress);
		builder.append(", firstName=");
		builder.append(name);
		builder.append(", lastName=");
		builder.append(city);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", hasPwd=");
		builder.append(hasPwd);
		builder.append(", userStatus=");
		builder.append(userStatus);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append("]");
		return builder.toString();
	}
}
