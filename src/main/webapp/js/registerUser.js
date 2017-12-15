var app = angular.module('namami', ['ngMaterial', 'menuApp' , 'constants', 'ngRoute']);
app.controller('regUserCtr', ['$scope', '$window', '$http', 'urls', 'toolkit', function($scope, $window, $http, $urls, $toolkit){ 
	
	$scope.regReset = function() {
    	$scope.register = {};
		}
	$scope.regReset(); 	
	
	$scope.submitRegOnSucess = function successCallback(response) {
		
			//if (response.status == 200) {	
				$scope.regReset(); 
				$scope.registerForm.$setPristine();
				$scope.registerForm.$setUntouched();
				$scope.loading = false;
				$window.location = $urls.LOGIN_UI;
			//}else{
				
				//$scope.register.validationErrors = response.validationErrors;

			//}
		
		  }
	
	$scope.submitRegOnError = function errorCallback(response) {
		$scope.loading = false;

		if (response.status == 0) {
			$scope.register.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 401) {
			$scope.register.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 405) {
			$scope.register.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status ===  '+response.status;
		} else if (response.status == 500) {
			$scope.register.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 400) {
			$scope.register.errorMessage = 'Please enter the correct details:: status === '+response.status;
		} else if (response.status == 409) {
			$scope.register.errorMessage = 'Email is already registered, Please login or if you forgot your password please reset:: status === '+response.status;
		} else {
			$scope.register.errorMessage = "status: : "+response.status;
			$scope.register.validationErrors = response.validationErrors;
		}
			
	}
	


 $scope.submitRegister = function() { 
	 $scope.loading = true;
		$http({
		  method: 'POST',
		  url: $urls.REGISTER_USER,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.register)
		  // 
		}).then($scope.submitRegOnSucess, $scope.submitRegOnError);		  
    };


	
}]);


 