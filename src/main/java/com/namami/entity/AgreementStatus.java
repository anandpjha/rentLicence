package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * AgreementTracker entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agreement_status")
public class AgreementStatus extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer statusId;
	
	private Integer AgreementStatusId;
	
	private Integer agreementId;
	
	private String anyMessage;

	public Integer getAgreementStatusId() {
		return AgreementStatusId;
	}

	public void setAgreementStatusId(Integer agreementStatusId) {
		AgreementStatusId = agreementStatusId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public String getAnyMessage() {
		return anyMessage;
	}

	public void setAnyMessage(String anyMessage) {
		this.anyMessage = anyMessage;
	}
	
	
	
	}
