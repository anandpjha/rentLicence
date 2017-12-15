package com.namami.bo;
/**
 * 
 * @author Anand Jha
 *
 */
public class AgreementStatusDto {
	
	private Integer statusId;
	
	private Integer agreementId;
	
	private Integer agreementStatusId;
	
	private String anyMsg;

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

	public Integer getAgreementStatusId() {
		return agreementStatusId;
	}

	public void setAgreementStatusId(Integer agreementStatusId) {
		this.agreementStatusId = agreementStatusId;
	}

	public String getAnyMsg() {
		return anyMsg;
	}

	public void setAnyMsg(String anyMsg) {
		this.anyMsg = anyMsg;
	}

	
	
}
