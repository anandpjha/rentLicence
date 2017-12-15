var app = angular.module('namami', ['ngMaterial', 'menuApp' , 'constants']);

//app.controller('resetPasswordCtr', ['$scope', '$window' ,'$http', function($scope, $window, $http){ 
app.controller('resetPasswordCtr', ['$scope', '$window', '$http', 'urls', 'toolkit', function($scope, $window, $http, $urls, $toolkit){ 
	
//$scope.agreement.owners = [];
	$scope.resetPasswordReset = function() {
    	$scope.resetPassword = {};
		//$scope.onrEditIdx = -1;
		//$scope.onrDeleteIdx = -1;
		}
	$scope.resetPasswordReset(); 	
	
	$scope.submitResetOnSucess = function successCallback(response) {
		$scope.loading = false;
				/*if($scope.onrEditIdx == -1) { 
					$scope.onr.personId = response.data;
					$scope.agreement.owners.push(angular.copy($scope.onr));	 
				} else {    		
					$scope.agreement.owners.splice($scope.onrEditIdx, 1, angular.copy($scope.onr));						
				}*/
				$scope.resetPasswordReset(); 
				$scope.resetPasswordForm.$setPristine();
				$scope.resetPasswordForm.$setUntouched();
				$window.location = $urls.LOGIN_UI;
		
		  }
	
	$scope.submitResetOnError = function errorCallback(response) {
		$scope.loading = false;
		if (response.status === 0) {
			$scope.resetPassword.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 401) {
			$scope.resetPassword.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 405) {
			$scope.resetPassword.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status ===  '+response.status;
		} else if (response.status == 500) {
			$scope.resetPassword.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 404) {
			$scope.resetPassword.errorMessage = 'Email is not registered, Please singup. status === '+response.status;
		} else {
			$scope.resetPassword.errorMessage = "status: : "+response.status;
		}
		  }
	


 $scope.submitresetPassword = function() { 
	 $scope.loading = true;
		$http({
		  method: 'POST',
		  url: $urls.RESET_PASSWORD,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.login) // pass in data as strings
		  // 
		}).then($scope.submitResetOnSucess, $scope.submitResetOnError);		  
    };


	
}]);


 