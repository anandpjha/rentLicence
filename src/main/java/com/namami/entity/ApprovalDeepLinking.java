package com.namami.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "approval_deeplinking")
public class ApprovalDeepLinking extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Integer agreementId;
	
	private Integer personType;
	
	private Integer personId;
	
	private String deeplinkParam;
	
	private Boolean isDrftApproved;//true or false
	
	private Boolean islinkActive;//true or false

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getDeeplinkParam() {
		return deeplinkParam;
	}

	public void setDeeplinkParam(String deeplinkParam) {
		this.deeplinkParam = deeplinkParam;
	}

	public Boolean getIsDrftApproved() {
		return isDrftApproved;
	}

	public void setIsDrftApproved(Boolean isDrftApproved) {
		this.isDrftApproved = isDrftApproved;
	}

	public Boolean getIslinkActive() {
		return islinkActive;
	}

	public void setIslinkActive(Boolean islinkActive) {
		this.islinkActive = islinkActive;
	}
	
	
	
	
	


}
