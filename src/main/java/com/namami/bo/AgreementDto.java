package com.namami.bo;

import java.util.Date;
import java.util.List;

public class AgreementDto {

	private PostalAddressDto property;
	
	private List<PersonDto> owners;
	
	private List<PersonDto> tenants;
	
	private List<PersonDto> witnesses;
	
	private AgreementTrackerStatusDto agreementTrackerStatus;
	
	private CreateAgreementReq terms;
	
	private MiscellaneousTermsReq miscellaneousTerms;
	
	private Integer agreementId;
	
	private String agreementCity;
	
	private String agreementState;
	
	private Double rentPerMonth;
	
	private Double advanceDeposit;
	
	private Date agreementStartDate;
	
	private Integer agreementPeriodInMonths;
	
	private Integer lockInPeriod;

	private String agreementType;
	
	private Integer chequeNo;
	
	private Date dateOfCheque;
	
	private String nameOfBank;

	private String branchName;
	
	private String agreementStatus;
	
	private SystemCalculatedData calculatedData;
	
	
	public SystemCalculatedData getCalculatedData() {
		return calculatedData;
	}

	public void setCalculatedData(SystemCalculatedData calculatedData) {
		this.calculatedData = calculatedData;
	}

	public String getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

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

	public AgreementTrackerStatusDto getAgreementTrackerStatus() {
		return agreementTrackerStatus;
	}

	public void setAgreementTrackerStatus(AgreementTrackerStatusDto agreementTrackerStatus) {
		this.agreementTrackerStatus = agreementTrackerStatus;
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

	public PostalAddressDto getProperty() {
		return property;
	}

	public void setProperty(PostalAddressDto postalAddressDto) {
		this.property = postalAddressDto;
	}

	public List<PersonDto> getOwners() {
		return owners;
	}

	public void setOwners(List<PersonDto> owners) {
		this.owners = owners;
	}

	public List<PersonDto> getTenants() {
		return tenants;
	}

	public void setTenants(List<PersonDto> tenants) {
		this.tenants = tenants;
	}

	public List<PersonDto> getWitnesses() {
		return witnesses;
	}

	public void setWitnesses(List<PersonDto> witnesses) {
		this.witnesses = witnesses;
	}

	public CreateAgreementReq getTerms() {
		return terms;
	}

	public void setTerms(CreateAgreementReq terms) {
		this.terms = terms;
	}

	public Integer getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(Integer lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}

	public MiscellaneousTermsReq getMiscellaneousTerms() {
		return miscellaneousTerms;
	}

	public void setMiscellaneousTerms(MiscellaneousTermsReq miscellaneousTerms) {
		this.miscellaneousTerms = miscellaneousTerms;
	}

	
}

