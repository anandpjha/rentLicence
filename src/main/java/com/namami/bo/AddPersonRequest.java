package com.namami.bo;
/**
 * 
 * @author Anand Jha
 *
 */
public class AddPersonRequest {
	
	private Integer personId;
	
	private Integer personOrder;
	
	private String personName;
	
	private Integer personAge;
	
	private String occupation;
	
	private String aadharUid;
	
	private String panNo;
	
	private String mobNumber;

	private String email;
	
	private Integer agreementId;
	
	private String gender;
	
	private String contectAddress;

	
	public String getContectAddress() {
		return contectAddress;
	}

	public void setContectAddress(String contectAddress) {
		this.contectAddress = contectAddress;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getPersonOrder() {
		return personOrder;
	}

	public void setPersonOrder(Integer personOrder) {
		this.personOrder = personOrder;
	}

	

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPersonAge() {
		return personAge;
	}

	public void setPersonAge(Integer personAge) {
		this.personAge = personAge;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAadharUid() {
		return aadharUid;
	}

	public void setAadharUid(String aadharUid) {
		this.aadharUid = aadharUid;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailAddress) {
		this.email = emailAddress;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
