package com.namami.bo;
/**
 * 
 * @author Anand Jha
 *
 */
public class CreateTrackerRequest {
	
    private Integer agreementId;
	
	private Integer agreementTrackerStatusId;
	
	//private Integer agreementStatusId;

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getAgreementTrackerStatusId() {
		return agreementTrackerStatusId;
	}

	public void setAgreementTrackerStatusId(Integer agreementTrackerStatusId) {
		this.agreementTrackerStatusId = agreementTrackerStatusId;
	}

	/*public Integer getAgreementStatusId() {
		return agreementStatusId;
	}

	public void setAgreementStatusId(Integer agreementStatusId) {
		this.agreementStatusId = agreementStatusId;
	}*/
	
	
	
}
