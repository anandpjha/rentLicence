package com.namami.bo;


/**
 * 
 * @author Anand Jha
 *
 */
public class PaymentRequest {
	
	 private String noOfMonths;
     private String rentPerMonth;
     private String AgreementType;
     private String depositAmount;
	public String getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(String noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public String getRentPerMonth() {
		return rentPerMonth;
	}
	public void setRentPerMonth(String rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}
	public String getAgreementType() {
		return AgreementType;
	}
	public void setAgreementType(String agreementType) {
		AgreementType = agreementType;
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
     
     
	
	
	
}
