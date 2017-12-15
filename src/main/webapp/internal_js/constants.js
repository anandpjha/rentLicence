angular.module('constants', [])
.constant('urls', (function() { 
	// Define your variable
	//var DOMAIN = 'http://localhost:8080/rentlicence/api/v1';
	 //var DOMAIN = 'http://rentagreement-builderhub.rhcloud.com/api/v1';
	 //var DOMAIN = 'https://localhost:8443/rentlicence/api/v1';
	var DOMAIN = 'https://rentagreement-builderhub.rhcloud.com/api/v1';
	 //var DOMAIN_UI = 'http://agreementui-builderhub.rhcloud.com/'
	 //var DOMAIN_UI = 'https://localhost:8443/rentlicence/';
	var DOMAIN_UI = 'https://rentagreement-builderhub.rhcloud.com/';
	//var DOMAIN_UI = 'http://localhost:8080/rentagreement/gui/';
	//var DOMAIN_UI = 'http://rentagreement-builderhub.rhcloud.com/gui/';
	  
	
  // Use the variable in your constants
	return {
		ADD_PERSON : DOMAIN + "/addPerson/{userId}",
		GET_PERSON : DOMAIN + "/getPerson/{personId}",
		GET_PERSONS : DOMAIN + "/getPersons/{userId}",
		UPDATE_PERSON : DOMAIN + "/updatePerson/",
		REGISTER_INTERNAL_USER : DOMAIN + "/registerInternalUser/",
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
		LOGIN_INTERNAL_USER : DOMAIN + "/loginInternal/",
		GET_ADMIN_AGREEMENTS : DOMAIN + "/getAdminAgreements/",
		GET_ASSOCIATE_AGREEMENTS : DOMAIN + "/getAssociateAgreements/",
		GET_USER_POFILE : DOMAIN + "/retrieveUserProfile/",
		UPDATE_USER_POFILE : DOMAIN + "/updateUserProfile/",
		CONFIRM_AGR_DATA : DOMAIN + "/confirmAgreementData/",
		REQUEST_TOKEN : DOMAIN + "/generateToken/",
		REQUEST_ASSIGN_WORK : DOMAIN + "/AssignAgreementToAssociate/",
		GET_UN_ASSIGNED_AGREEMENTS : DOMAIN + "/getUnAssignedAgreements/",
		GET_STATUS_LIST : DOMAIN + "/getstatusList/",
		UPDATE_AGREEMENT_STATUS : DOMAIN + "/updateAgreementStaus/",
		UPLOAD_DRAFT_AGREEMENT : DOMAIN + "/uploadDraftAgreement/", 
		
		
		
		
		//UI URL
		LOGIN_UI : "login",
		DASHBOARD_UI : DOMAIN_UI + "internal_newDashboard.html",
		NEW_AGREEMENT_UI : DOMAIN_UI + "internal_newAgreement.html",
		PROFILE_UI : DOMAIN_UI + "internal_profile.html"
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

