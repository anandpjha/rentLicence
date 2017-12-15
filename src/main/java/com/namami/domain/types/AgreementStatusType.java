package com.namami.domain.types;

public enum AgreementStatusType {

	/**
	 * When User creates a agreement very first time.
	 *//*
	STARTED 	(1),
	
	*/
	/**
	 * User is entering agreement data like owners, tenants, witness, etc...
	 */
	DATA_INPUT_IN_PROGRESS (1),
	
	/**
	 * User has finished submitting all required data like owners, tenants, witness, etc...
	 */
	DATA_INPUT_DONE (2),
	
	/**
	 * Payment has made by the user.
	 * 
	 */
	PAYMENT_DONE (3),
	
	/**
	 * User has finished submitting all required data, and draft is generated.
	 */
	DRAFT_COPY_GENERATED (4),
	
	
	/**
	 * Draft version is approved by the Owners, Tenants
	 */
	DRAFT_APPROVED (5),
	
	
	/**
	 * RentLicece is in progress for collecting biometric from one or more persons.
	 */
	BIOMETRIC_IN_PROGRESS (6),
	
	/**
	 * Rent Licence has collected the biometric information.
	 */
	BIOMETRIC_DONE (7),
	
	GOVERNMENT_PROCESS_IN_PROGRESS(8),
	
	GOVERNMENT_PROCESS_DONE(9),
	
	/**
	 * Printout has taken and hard copy has dispatched.
	 */
	HARD_COPY_DISPATCHED (10),
	
	
	/**
	 * LnL agreement has been completed successfully.
	 */
	COMPLETED (11),
	
	/**
	 * Modification after the draft approval.
	 */
	MODIFICATION_IN_PROGRESS (12),
	
	;
	
	
	
	private final Integer value;
	
	AgreementStatusType(Integer agreementStatus) {
		this.value = agreementStatus;
	}

	public Integer getValue() {
		return value;
	}
	
}
