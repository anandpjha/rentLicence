angular.module('constants', [])
.constant('urls', (function() { 
	//var DOMAIN = 'http://localhost:8080/rentagreement-1.0/api/v1';
	//var DOMAIN_UI = 'http://localhost:8080/rentagreement-1.0/';

	var DOMAIN = 'http://http://ec2-54-202-208-228.us-west-2.compute.amazonaws.com/rentagreement-1.0/api/v1';
    var DOMAIN_UI = 'http://http://ec2-54-202-208-228.us-west-2.compute.amazonaws.com/rentagreement-1.0/';


	
  // Use the variable in your constants
	return {
		ADD_PERSON : DOMAIN + "/addPerson/{userId}",
		GET_PERSON : DOMAIN + "/getPerson/{personId}",
		GET_PERSONS : DOMAIN + "/getPersons/{userId}",
		UPDATE_PERSON : DOMAIN + "/updatePerson/",
		REGISTER_USER : DOMAIN + "/registerUser/",
		FIND_USER : DOMAIN + "/findUser/{userId}",
		CREATE_SESSION_CONTEXT : DOMAIN + "/createSessionContext/{userId}",
		LOGOUT_URL : DOMAIN + "/logout/",
		SESSION_EXPIRED_URL : DOMAIN + "/sessionExpired/",
		CREATE_BLANK_AGREEMENT : DOMAIN + "/createBlankAgreement/",
		ADD_OWNER : DOMAIN + "/addOwner/",
		ADD_TENANT : DOMAIN + "/addTenant/",
		ADD_WITNESS : DOMAIN + "/addWitness/",
		ADD_PROPERTY_TO_AGREEMENT : DOMAIN + "/addProperty/",
		DELETE_PERSON : DOMAIN + "/deletePerson/",
		ADD_POSTAL_ADDRESS : DOMAIN + "/addPostalAddress/", 
		GET_POSTAL_ADDRESS : DOMAIN + "/getPostalAddress/",
        SUBMIT_AGR_TERMS : DOMAIN + "/agreementTerms/",
        MISCELLANEOUS_TERMS : DOMAIN + "/miscellaneousTerms/",
        GET_AGREEMENT_DETAILS : DOMAIN + "/getAgreementDetails/",
		RESET_PASSWORD : DOMAIN + "/resetPassword/",
		LOGIN : DOMAIN + "/login/",
		GET_USER_AGREEMENTS : DOMAIN + "/getUserAgreements/",
		GET_USER_POFILE : DOMAIN + "/retrieveUserProfile/",
		UPDATE_USER_POFILE : DOMAIN + "/updateUserProfile/",
		CONFIRM_AGR_DATA : DOMAIN + "/confirmAgreementData/",
		GET_PAYMENT_DETAIL : DOMAIN + "/getPaymentDetail/",
		UPDATE_PAYMENT_STATUS : DOMAIN + "/updatePaymentStatus/",
		GET_AGREEMENT_DETAIL_PDF : DOMAIN + "/getAgreementDetailsPdf/", 
		
		//UI URL
		LOGIN_UI : "login",
		DASHBOARD_UI : DOMAIN_UI + "newDashboard.html",
		NEW_AGREEMENT_UI : DOMAIN_UI + "newAgreement.html",
		PROFILE_UI : DOMAIN_UI + "profile.html"
		//UI URL
	}
})())
.service('toolkit',function(){

return{

    getUrlParameter: function(sParam){
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

		for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
		}
    },

	convertToInt: function (value) {
        return parseInt(value);
    },
		
    /**
     *  Returns a 32 character ID [ XXXXXXXX - XXXXXXXXXXXXXXXXXXXXXXXX ]
     * @returns {string}
     */

    newGUID: function(){

        // ...
    }

  };
});
;

