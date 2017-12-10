var app = angular.module('newAgreementApp', ['ngMaterial', 'menuApp', 'constants']);

app.controller('main', ['$scope', '$http', 'urls', 'toolkit', function($scope, $http, $urls, $toolkit){ 
	

	$scope.propertyTypes = [ {'type': 'Flat', 'value' : 1}, {'type': 'Individual House', 'value' : 2} ];

	$scope.propertyIdentityTypes = [ {'type': 'Plot No', 'value' : 1}, {'type': 'CTS No', 'value' : 2}, {'type': 'Survey No', 'value' : 3}, {'type': 'Gata No', 'value' : 4} ];
	
	$scope.selectedIndex = 0;
    $scope.agrTermsTabIdx = 0;
    $scope.ownerTabIdx = 1;
    $scope.tenentTabIdx = 2;
    $scope.witnessTabIdx = 3;
    $scope.propertyTabIdx = 4;
    $scope.miscTermsTabIdx = 5;
    
    // Start as not visible but when button is tapped it will show as true 
    $scope.visible = false;
    
    // editIdx. -1 means new insert, Otherwise it represents index of the editing element in array.
    $scope.onrEditIdx = -1;
	$scope.onrDeleteIdx = -1;

	//Fetch agreement for edit or Create an blank Agreement 
	$scope.fetchAgreementSuccessCallBack = function(response) {
		$scope.loading = false;
		console.log(response.data);
        
        $scope.agreement = response.data;
        $scope.trm = $scope.agreement.terms;
        $scope.prp = $scope.agreement.property;
        $scope.mis = {};
        $scope.mis.miscellaneousTerms = $scope.agreement.miscellaneousTerms;
        
        if($scope.agreement.terms && $scope.agreement.terms.agreementStartDate) {
            $scope.agreement.terms.agreementStartDate = new Date($scope.agreement.terms.agreementStartDate);
        }        
                
		}
	

	$scope.fetchAgreementErrorCallBack = function errorCallback(response) {
		$scope.loading = false;
			$scope.errorMessage = "Failed to fetch agreement.";
		}
		
	$scope.agreement = [];
	$scope.agreementId = $toolkit.getUrlParameter('agreementId');
	if($scope.agreementId) {
		console.log($scope.agreementId);
		$http.get($urls.GET_AGREEMENT_DETAILS + $scope.agreementId)
		.then($scope.fetchAgreementSuccessCallBack, $scope.fetchAgreementErrorCallBack)
			
	} else {	
		$scope.agreement = [];
		// Create the array to hold the list of Owners
		$scope.agreement.owners = [];
		// Create the array to hold the list of Tenants
		$scope.agreement.tenants = [];
		// Create the array to hold the list of witnesses
		$scope.agreement.witnesses = []; 
		$scope.agreement.property = {};
		$scope.agreement.terms={};
		$scope.agreement.terms.varyingRents=[{monthNumber: 1}];
	}
	
	
	
    
	$scope.onrReset = function() {
    	$scope.onr = {};
		$scope.onrEditIdx = -1;
		$scope.onrDeleteIdx = -1;
		}
	$scope.onrReset(); 	
	
	$scope.submitOnrOnSucess = function successCallback(response) {
		
		$scope.loading = false;
				if($scope.onrEditIdx == -1) { 
					$scope.onr.personId = response.data;
					$scope.agreement.owners.push(angular.copy($scope.onr));	 
				} else {    		
					$scope.agreement.owners.splice($scope.onrEditIdx, 1, angular.copy($scope.onr));						
				}
				$scope.onrReset(); 
				$scope.onrForm.$setPristine();
				$scope.onrForm.$setUntouched();
		
		  }
	
	$scope.submitOnrOnError = function errorCallback(response) {
		$scope.loading = false;
			$scope.onr.errorMessage = "Error Occured while saving, please try again.";
		  }
	
	// Create the function to push the data into the "owners" array
    $scope.submitOnr = function() { 
    	$scope.loading = true;
        if(!$scope.agreement.owners) {
            $scope.agreement.owners = [];
        }
		if($scope.onrEditIdx == -1) { // -1 means first owner is gettign submitted.
			$scope.onr.agreementId = $scope.agreement.agreementId;
			$scope.onr.personOrder = $scope.agreement.owners.length+1;			
			};
		$http({
		  method: 'POST',
		  url: $urls.ADD_OWNER,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.onr) // pass in data as strings
		  // 
		}).then($scope.submitOnrOnSucess, $scope.submitOnrOnError);		  
    };
    
    // Create the function to delete the owner. Update data into the "owners" array
	$scope.deleteOnrOnSucess = function successCallback(response) {
		$scope.loading = false;
		$scope.selectedIndex = $scope.ownerTabIdx;
		$scope.agreement.owners.splice($scope.onrDeleteIdx, 1);		
		$scope.onrDeleteIdx = -1;
		}
	$scope.deleteOnrOnError = function errorCallback(response) {
		$scope.loading = false;
			$scope.onr.errorMessage = "Error Occured while saving, please try again.";
		}
    $scope.deleteOnr = function(index) {
		$scope.onrDeleteIdx = index;
		$http({
		  method: 'POST',
		  url: $urls.DELETE_PERSON,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.agreement.owners[index]) // pass in data as strings
		  // 
		}).then($scope.deleteOnrOnSucess, $scope.deleteOnrOnError);		  
		};
	
	$scope.editOnr = function(index) {
    	$scope.selectedIndex = $scope.ownerTabIdx;
    	$scope.onrEditIdx = index;      	
		$scope.onr = angular.copy($scope.agreement.owners[index]);
    }
    
	
	// Tenants
	$scope.tntEditIdx = -1;
	$scope.tntDeleteIdx = -1;
	$scope.tntReset = function() {
    	$scope.tnt = {};
		$scope.tntEditIdx = -1;
		$scope.tntDeleteIdx = -1;
	}
	$scope.tntReset();
	
    // Create the function to push the data into the "tenants" array
	$scope.submitTntOnSucess = function successCallback(response) {
		$scope.loading = false;
		if($scope.tntEditIdx == -1) { 
			$scope.tnt.personId = response.data;
			$scope.agreement.tenants.push(angular.copy($scope.tnt));	 
		} else {    		
			$scope.agreement.tenants.splice($scope.tntEditIdx, 1, angular.copy($scope.tnt));						
		}
		$scope.tntReset(); 
		$scope.tntForm.$setPristine();
		$scope.tntForm.$setUntouched();
		}	
	$scope.submitTntOnError = function errorCallback(response) {
		$scope.loading = false;
		$scope.tnt.errorMessage = "Error Occurred while saving, please try again.";
		}
	$scope.submitTnt = function() {
		$scope.loading = true;
         if(!$scope.agreement.tenants) {
            $scope.agreement.tenants = [];
        }
		$scope.tnt.agreementId = $scope.agreement.agreementId;
		if($scope.tntEditIdx == -1) {
			$scope.tnt.personOrder = $scope.agreement.tenants.length+1;
		};
    	if($scope.tntEditIdx == -1) {
			$scope.tnt.personOrder = $scope.agreement.tenants.length+1;
			};
		$http({
		  method: 'POST',
		  url: $urls.ADD_TENANT,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.tnt) // pass in data as strings
		  // 
		}).then($scope.submitTntOnSucess, $scope.submitTntOnError);		  
    };
    
	// Create the function to delete the owner. Update data into the "tenants" array
	$scope.deleteTntOnSucess = function(response) {
		$scope.loading = false;
		$scope.selectedIndex = $scope.tenentTabIdx;		 	
		$scope.agreement.tenants.splice($scope.tntDeleteIdx, 1);		
		$scope.tntDeleteIdx = -1;
		};
	$scope.deleteTntOnError = function(response) {
		$scope.loading = false;
		$scope.tnt.errorMessage = "Error Occurred while saving, please try again.";
		};
    $scope.deleteTnt = function(index) {
		$scope.tntDeleteIdx = index;
		$http({
		  method: 'POST',
		  url: $urls.DELETE_PERSON,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.agreement.tenants[index]) // pass in data as strings
		  // 
		}).then($scope.deleteTntOnSucess, $scope.deleteTntOnError);		  
		};
		
    // Create the function to update the data into the "tenants" array
    $scope.editTnt = function(index) {
    	$scope.selectedIndex = $scope.tenentTabIdx;
    	$scope.tntEditIdx = index;  	
    	$scope.tnt = angular.copy($scope.agreement.tenants[index]);
    }

	
	// witnesses
	$scope.wtnEditIdx = -1;
	$scope.wtnDeleteIdx = -1;
	$scope.wtnReset = function() {
    	$scope.wtn = {};
		$scope.wtnEditIdx = -1;
		$scope.wtnDeleteIdx = -1;
	}
	$scope.wtnReset();
	
   // Create the function to push the data into the "witnesses" array
	$scope.submitWtnOnSucess = function successCallback(response) {
		$scope.loading = false;
		if($scope.wtnEditIdx == -1) { 
			$scope.wtn.personId = response.data;
			$scope.agreement.witnesses.push(angular.copy($scope.wtn));	 
		} else {    		
			$scope.agreement.witnesses.splice($scope.wtnEditIdx, 1, angular.copy($scope.wtn));						
		}
		$scope.wtnReset(); 
		$scope.wtnForm.$setPristine();
		$scope.wtnForm.$setUntouched();
		}	
	$scope.submitWtnOnError = function errorCallback(response) {
		$scope.loading = false;
		$scope.wtn.errorMessage = "Error Occurred while saving, please try again.";
		}
	$scope.submitWtn = function() {
         if(!$scope.agreement.witnesses) {
            $scope.agreement.witnesses = [];
        }
		$scope.wtn.agreementId = $scope.agreement.agreementId;
		if($scope.wtnEditIdx == -1) {
			$scope.wtn.personOrder = $scope.agreement.witnesses.length+1;
		};
    	if($scope.wtnEditIdx == -1) {
			$scope.wtn.personOrder = $scope.agreement.witnesses.length+1;
			};
		$http({
		  method: 'POST',
		  url: $urls.ADD_WITNESS,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.wtn) // pass in data as strings
		  // 
		}).then($scope.submitWtnOnSucess, $scope.submitWtnOnError);		  
    };
    
	// Create the function to delete the owner. Update data into the "witnesses" array
	$scope.deleteWtnOnSucess = function(response) {
		$scope.loading = false;
		$scope.selectedIndex = $scope.witnessTabIdx;		 	
		$scope.agreement.witnesses.splice($scope.wtnDeleteIdx, 1);		
		$scope.wtnDeleteIdx = -1;
		};
	$scope.deleteWtnOnError = function(response) {
		$scope.wtn.errorMessage = "Error Occurred while saving, please try again.";
		};
    $scope.deleteWtn = function(index) {
		$scope.wtnDeleteIdx = index;
		$http({
		  method: 'POST',
		  url: $urls.DELETE_PERSON,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.agreement.witnesses[index]) // pass in data as strings
		  // 
		}).then($scope.deleteWtnOnSucess, $scope.deleteWtnOnError);		  
		};
		
    // Create the function to update the data into the "witnesses" array
    $scope.editWtn = function(index) {
    	$scope.selectedIndex = $scope.witnessTabIdx;
    	$scope.wtnEditIdx = index;  	
    	$scope.wtn = angular.copy($scope.agreement.witnesses[index]);
	};
	
    
    $scope.submitPropOnSucess = function(index) {
    	$scope.loading = false;
        $scope.prp.errorMessage = "Saved.";
    }
    
    $scope.submitPropOnError = function(index) {
    	$scope.loading = false;
        $scope.prp.errorMessage = "Error Occured.";
    }
    
	$scope.submitProp = function() {
		$scope.prp.agreementId = $scope.agreement.agreementId;
        $scope.prp.propertyIdentityType = $toolkit.convertToInt($scope.prp.propertyIdentityType);
		$scope.prp.propertyType = $toolkit.convertToInt($scope.prp.propertyType);
		
        $http({
		  method: 'POST',
		  url: $urls.ADD_POSTAL_ADDRESS,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  $scope.prp // pass in data as strings
		  // 
		}).then($scope.submitPropOnSucess, $scope.submitPropOnError);		  
    };
	




$scope.addNewVaryingRent = function(editItemIndex) {
    var lastItemIdx = $scope.trm.varyingRents.length-1;
    var newItemNo = $scope.trm.varyingRents.length+1;
	if( editItemIndex == lastItemIdx ) {
	    $scope.trm.varyingRents.push({'monthNumber':lastItemIdx+1});
	} else {
		//$scope.trm.varyingRents.push({'monthNumber':lastItem.lastItemIdx+1});
	}
  };
    
  $scope.removeVaryingRent = function() {
    if( $scope.trm.varyingRents.length == 1) {
		return;
	};
	
	$scope.trm.varyingRents.splice($scope.trm.varyingRents.length-1);
  };
    
    
	$scope.submitTrmOnSucess = function successCallback(response) {
		$scope.loading = false;
        $scope.agreement.terms = angular.copy($scope.trm);	  				
		$scope.agreement.agreementId = response.data
        
        $scope.trmForm.$setPristine();
		$scope.trmForm.$setUntouched();
		
		}
	
	$scope.submitTrmOnError = function errorCallback(response) {
		$scope.loading = false;
		$scope.trm.errorMessage = "Error Occured while saving, please try again.";
        }
	
	// Create the function to push the data into the "owners" array
    $scope.submitTrm = function() { 
		if($scope.trmEditIdx == -1) {
			$scope.trm.agreementId = $scope.agreement.agreementId;
			$scope.trm.personOrder = $scope.agreement.owners.length+1;			
			};
		$http({
		  method: 'POST',
		  url: $urls.SUBMIT_AGR_TERMS,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.trm) // pass in data as strings
		  // 
		}).then($scope.submitTrmOnSucess, $scope.submitTrmOnError);		  
    };
    
    
    
    
    $scope.submitMisTrmOnSucess = function successCallback(response) {
    	$scope.loading = false;
		$scope.agreement.miscellaneousTerms = angular.copy($scope.mis);	 
		$scope.misForm.$setPristine();
		$scope.misForm.$setUntouched();
		
        }
	
	$scope.submitMisTrmOnError = function errorCallback(response) {
		$scope.loading = false;
			$scope.mis.errorMessage = "Error Occured while saving, please try again.";
		  }
	
	// Create the function to push the data into the "owners" array
    $scope.submitMis = function() { 
		$scope.mis.agreementId = $scope.agreement.agreementId;
		$http({
		  method: 'POST',
		  url: $urls.MISCELLANEOUS_TERMS,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.mis) // pass in data as strings
		  // 
		}).then($scope.submitMisTrmOnSucess, $scope.submitMisTrmOnError);		  
    };
    
    
    
}]);