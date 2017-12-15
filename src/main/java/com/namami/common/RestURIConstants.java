package com.namami.common;


/**
 * final class to keep the URI constants for different services
 * 
 * @author Anand Jha
 * 
 */
public final class RestURIConstants {

	private RestURIConstants() {

	}

	public static final String SYMBOL_SLASH = "/";

	public static final String ADD_PERSON = "/addPerson/{userId}";
	public static final String GET_PERSON = "/getPerson/{personId}";
	public static final String GET_PERSONS = "/getPersons/{userId}";
	public static final String UPDATE_PERSON = "/updatePerson/";
	public static final String DELETE_PERSON = "/deletePerson/";
	public static final String REGISTER_USER = "/registerUser/";
	public static final String FIND_USER = "/findUser/{emailId}";
	public static final String CREATE_SESSION_CONTEXT = "/createSessionContext/{userId}";
	public static final String LOGOUT_URL = "/logout/";
	public static final String SESSION_EXPIRED_URL = "/sessionExpired/";
	
	public static final String CREATE_BLANK_AGREEMENT = "/createBlankAgreement/";
	
	public static final String ADD_OWNER = "/addOwner/";
	public static final String ADD_TENANT = "/addTenant/";
	public static final String ADD_WITNESS = "/addWitness/";
	
	public static final String CREATE_AGREEMENT = "/agreementTerms/";
	public static final String UPDTATE_MISCELLANEOUS = "/miscellaneousTerms/";
	public static final String GET_AGREEMENT = "/getAgreement/{agreementId}";
	public static final String GET_USER_AGREEMENTS = "/getUserAgreements/";
	public static final String GET_AGREEMENT_DETAILS = "/getAgreementDetails/{agreementId}";
	public static final String ADD_POSTAL_ADDRESS = "/addPostalAddress/";
	public static final String GET_POSTAL_ADDRESS = "/getPostalAddress/";
	public static final String UPDATE_OWNER = "/updateOwner/";
	public static final String LOGIN_USER = "/login/";
	public static final String LOGIN_INTERNAL_USER = "/loginInternal/";
	//public static final String LOGIN_ADMIN_USER = "/loginAdmin/";
	public static final String RESET_PASSWORD = "/resetPassword/";
	public static final String RETRIEVE_USER_PROFILE = "/retrieveUserProfile/";
	public static final String UPDATE_USER_PROFILE = "/updateUserProfile/";
	public static final String CONFIRM_AGR_DATA = "/confirmAgreementData/";
	public static final String REGISTER_INTERNAL_USER = "/registerInternalUser/";
	
	public static final String REQUEST_TOKEN = "/generateToken/";
	public static final String GET_ADMIN_AGREEMENTS = "/getAdminAgreements/";
	public static final String GET_ASSOCIATE_AGREEMENTS = "/getAssociateAgreements/";
	public static final String RETRIEVE_ASSOCIATES_USER = "/retrieveAssociates/";
	public static final String ASSIGN_AGREEMENT_TO_ASSOCIATE = "/AssignAgreementToAssociate/";
	public static final String GET_UN_ASSIGNED_AGREEMENTS = "/getUnAssignedAgreements/";
	public static final String GET_STATUS_LIST = "/getstatusList/";
	public static final String UPDATE_AGREEMENT_STATUS = "/updateAgreementStaus/";
	public static final String GET_PAYMENT_DETAIL ="/getPaymentDetail/{agreementId}";
	public static final String UPDATE_PAYMENT_STATUS ="/updatePaymentStatus/{agreementId}";
	public static final String GET_AGREEMENT_DETAIL_PDF = "/getAgreementDetailsPdf/{agreementId}";
	public static final String APPROVE_DRF_AGREEMENT = "/approveDraft/{dpId}/{dp}";
	public static final String UPLOAD = "/uploadDraftAgreement/";
	
	
}
