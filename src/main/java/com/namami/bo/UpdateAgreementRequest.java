package com.namami.bo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateAgreementRequest {
	
	
	private Integer agreementId;
	
	private String agreementCity;
	
	private String agreementType;
	
	private String agreementState;
	
	private Double rentPerMonth;
	
	private Double advanceDeposit;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date agreementStartDate;
	
	private Integer agreementPeriodInMonths;

	private Boolean isVaryingRent = false;
	
	private List<RentDto> varyingRents;
	
	private Boolean isRefundable;
	
	private Integer lockInPeriod;
	
	private Integer noticePeriod;
	
	private Integer chequeNo;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfCheque;
	
	private String nameOfBank;

	private String branchName;
	
	private Boolean assignToAssociate;
	
	
	public Boolean getAssignToAssociate() {
		return assignToAssociate;
	}

	public void setAssignToAssociate(Boolean assignToAssociate) {
		this.assignToAssociate = assignToAssociate;
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

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	public Integer getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
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

	public Double getAdvanceDeposit() {
		return advanceDeposit;
	}

	public void setAdvanceDeposit(Double advanceDeposit) {
		this.advanceDeposit = advanceDeposit;
	}

	public Date getAgreementStartDate() {
		return agreementStartDate;
	}

	public void setAgreementStartDate(Date agreementStartDate) {
		this.agreementStartDate = agreementStartDate;
	}

	public Integer getAgreementPeriodInMonths() {
		return agreementPeriodInMonths;
	}

	public void setAgreementPeriodInMonths(Integer agreementMonthPeriod) {
		this.agreementPeriodInMonths = agreementMonthPeriod;
	}

	public Boolean isVaryingRent() {
		return isVaryingRent;
	}

	public void setIsVaryingRent(Boolean isVaryingRent) {
		this.isVaryingRent = isVaryingRent;
	}

	public List<RentDto> getVaryingRents() {
		return varyingRents;
	}

	public void setVaryingRents(List<RentDto> varyingRents) {
		this.varyingRents = varyingRents;
	}

	public Boolean getIsRefundable() {
		return isRefundable;
	}

	public void setIsRefundable(Boolean isRefundable) {
		this.isRefundable = isRefundable;
	}

	public Boolean getIsVaryingRent() {
		return isVaryingRent;
	}

	public Integer getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(Integer lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}
	
}
