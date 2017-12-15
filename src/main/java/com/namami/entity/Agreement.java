package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * Agreement entity class 
 */
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "agreement")
//@SequenceGenerator(name = "AGREEMENT_SEQUENCE", sequenceName = "AGREEMENT_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Agreement extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer agreementId;
	
	private Integer userId;
	
	private String agreementCity;
	
	private String agreementState;
	
	private Boolean isVaryingRent;
	
	private Double rentPerMonth;
	
	private Boolean isSecurityDepositRefundable;
	
	private Double securityDeposit;
	
	private Date agreementStartDate;
	
	private Integer agreementMonthPeriod;
	
	private String varyingRent;
	
	private Integer lockInPeriod;
	
	private Date rentPaymentdate;
	
	private Integer noticePeriod;
	
	private String agreementType;
	
	private Integer chequeNo;
	
	private Date dateOfCheque;
	
	private String nameOfBank;

	private String branchName;
	
	
	/*
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId")
	//@OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL)
    private List<Person> persons;	*/

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public Integer getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(Integer chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getDateOfCheque() {
		return dateOfCheque;
	}

	public void setDateOfCheque(Date dateOfCheque) {
		this.dateOfCheque = dateOfCheque;
	}

	public String getNameOfBank() {
		return nameOfBank;
	}

	public void setNameOfBank(String nameOfBank) {
		this.nameOfBank = nameOfBank;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getRentPaymentdate() {
		return rentPaymentdate;
	}

	public void setRentPaymentdate(Date rentPaymentdate) {
		this.rentPaymentdate = rentPaymentdate;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	private Integer postalAddressId; 
	
	
	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	/*public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

*/
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAgreementCity() {
		return agreementCity;
	}

	public void setAgreementCity(String agreementCity) {
		this.agreementCity = agreementCity;
	}

	public String getAgreementState() {
		return agreementState;
	}

	public void setAgreementState(String agreementState) {
		this.agreementState = agreementState;
	}

	public Double getRentPerMonth() {
		return rentPerMonth;
	}

	public void setRentPerMonth(Double rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}

	public Double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public Date getAgreementStartDate() {
		return agreementStartDate;
	}

	public void setAgreementStartDate(Date agreementStartDate) {
		this.agreementStartDate = agreementStartDate;
	}

	public Integer getAgreementMonthPeriod() {
		return agreementMonthPeriod;
	}

	public void setAgreementMonthPeriod(Integer agreementMonthPeriod) {
		this.agreementMonthPeriod = agreementMonthPeriod;
	}

	public Boolean getIsVaryingRent() {
		return isVaryingRent;
	}

	public void setIsVaryingRent(Boolean isVaryingRent) {
		this.isVaryingRent = isVaryingRent;
	}

	public Boolean getIsSecurityDepositRefundable() {
		return isSecurityDepositRefundable;
	}

	public void setIsSecurityDepositRefundable(Boolean isSecurityDepositRefundable) {
		this.isSecurityDepositRefundable = isSecurityDepositRefundable;
	}

	public String getVaryingRent() {
		return varyingRent;
	}

	public void setVaryingRent(String varyingRent) {
		this.varyingRent = varyingRent;
	}

	public Integer getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(Integer lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}

	public Integer getPostalAddressId() {
		return postalAddressId;
	}

	public void setPostalAddressId(Integer postalAddressId) {
		this.postalAddressId = postalAddressId;
	}
	
}
