package com.namami.bo;
/**
 * 
 * @author Anand Jha
 *
 */
public class PaymentResponse {
	
	private String registrationFee;
	private String stampDuty;
	private String governmentAuthorizationFee;
	private String eRegistrationFee;
	private String singlevisitFee;
	private String otherCharges;
	private String serviceTax;
	private String totalRegistrationCharges;
	
	public String geteRegistrationFee() {
		return eRegistrationFee;
	}
	public void seteRegistrationFee(String eRegistrationFee) {
		this.eRegistrationFee = eRegistrationFee;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public String getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	public String getGovernmentAuthorizationFee() {
		return governmentAuthorizationFee;
	}
	public void setGovernmentAuthorizationFee(String governmentAuthorizationFee) {
		this.governmentAuthorizationFee = governmentAuthorizationFee;
	}
	
	public String getSinglevisitFee() {
		return singlevisitFee;
	}
	public void setSinglevisitFee(String singlevisitFee) {
		this.singlevisitFee = singlevisitFee;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}
	public String getTotalRegistrationCharges() {
		return totalRegistrationCharges;
	}
	public void setTotalRegistrationCharges(String totalRegistrationCharges) {
		this.totalRegistrationCharges = totalRegistrationCharges;
	}
	
	
	
}
