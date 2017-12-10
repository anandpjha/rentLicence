package com.namami.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agreement_associate")
public class AgreementAssociate extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer associateAgreementId;
	
	private Integer agreementId;
	
	private Integer associateId;
	
	private String associateType;
	
	private String progressStatus;

	
	
	public String getAssociateType() {
		return associateType;
	}

	public void setAssociateType(String associateType) {
		this.associateType = associateType;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public Integer getAssociateAgreementId() {
		return associateAgreementId;
	}

	public void setAssociateAgreementId(Integer associateAgreementId) {
		this.associateAgreementId = associateAgreementId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Integer associateId) {
		this.associateId = associateId;
	}

	
	
	
}
