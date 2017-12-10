package com.namami.bo;

public class TokenDto {
	
	private Integer tokenId;
	
	private String tokenVal;
	
	private String tokenType; //createAccount, ..
	
	private String accessType;//User, Associte, Admin
	
	private String status; //Active, Used

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenVal() {
		return tokenVal;
	}

	public void setTokenVal(String tokenVal) {
		this.tokenVal = tokenVal;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
