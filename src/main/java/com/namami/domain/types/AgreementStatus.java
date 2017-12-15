package com.namami.domain.types;
//Unused class - remove it
public enum AgreementStatus {

	/**
	 * When User creates a agreement very first time.
	 */
	STARTED 	(1),
	
	
	/**
	 * User is entering agreement data like owners, tenants, witness, etc...
	 */
	IN_PROGRESS (2),
	
	
	/**
	 * User has finished submitting all required data, and draft is created.
	 */
	USR_DRAFT_CREATED (3),
	
	
	/**
	 * Payment has made by the user.
	 * 
	 * Lets not keep this as a status, instead maintain payment information in separate table.
	 */
	//PAYMENT_DONE (4),
	
	
	/**
	 * Draft version is approved by the Owners, Tenants
	 */
	DRAFT_APPROVED (5),
	
	
	/**
	 * Rent Licence has taken an appointment with Owner/Tenant
	 */
	BIOMETRIC_APPOINMENT_TAKEN (6),
	
	
	/**
	 * Rent Licence has created chalan for Stamp duty and registration charges.
	 */
	CHALAN_CREATED (7),
	
	
	/**
	 * Draft created for government. (For now it's igrmaharashtra website. Later we may add few more govt sites.)
	 */
	GOVT_DRAFT_CREATED (8),
	
	
	/**
	 * RentLicece is in progress for collecting biometric from one or more persons.
	 */
	BIOMETRIC_IN_PROGRESS (9),
	
	
	/**
	 * Rent Licence has collected the biometric information.
	 */
	BIOMETRIC_DONE (10),
	
	
	/**
	 * Draft version is submitted to government. (Igrmaharashtra)
	 */
	GOVT_DRAFT_SUBMITTED (11),
	
	
	/**
	 * Government has approved the LnL Agreement.
	 */
	GOVT_LNL_APPROVED(12),
	
	
	/**
	 * Printout has taken and hard copy has dispatched.
	 */
	HARD_COPY_DISPATCHED (13),
	
	
	/**
	 * LnL agreement has been completed successfully.
	 */
	COMPLETED (14),
	
	/**
	 * Modification after the draft approval.
	 */
	MODIFICATION_IN_PROGRESS (15),
	
	;
	
	
	
	private final Integer value;
	
	AgreementStatus(Integer agreementStatus) {
		this.value = agreementStatus;
	}

	public Integer getValue() {
		return value;
	}
	
}
