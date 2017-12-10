package com.namami.bo;
/**
 * DTO used to store calculated data by system which can be used any where 
 * @author Anand Jha
 *
 */
public class SystemCalculatedData {
	
	//Total cost of agreement
	private Integer agreementTotalCost; 
	
	//How much progress in %
	private Double agreementPercentageDone; 

	public Integer getAgreementTotalCost() {
		return agreementTotalCost;
	}

	public void setAgreementTotalCost(Integer agreementTotalCost) {
		this.agreementTotalCost = agreementTotalCost;
	}

	public Double getAgreementPercentageDone() {
		return agreementPercentageDone;
	}

	public void setAgreementPercentageDone(Double agreementPercentageDone) {
		this.agreementPercentageDone = agreementPercentageDone;
	}
	
	

}
