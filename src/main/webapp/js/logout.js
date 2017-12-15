var app = angular.module('namami', ['ngMaterial', 'menuApp' , 'constants']);
//var userId = getUrlParameter('userId');

app.controller('logoutCtr', ['$scope', '$window', '$http', 'urls', 'toolkit', function($scope, $window, $http, $urls, $toolkit){ 
	$scope.loading = true;
	$http({url: $urls.LOGOUT_URL/* + userId +"/"*/, method: "GET"}).success(
   function(data, status, headers, config) {
	   $scope.loading = false;
		$scope.logoutMsg = "You have sucessfully logout";

   });


   //$scope.agreement.owners = [];
	$scope.loginReset = function() {
    	$scope.loginUser = {};
		//$scope.onrEditIdx = -1;
		//$scope.onrDeleteIdx = -1;
		}
	$scope.loginReset(); 	
	
	$scope.submitloginOnSucess = function successCallback(response) {
		$scope.loading = false;
				/*if($scope.onrEditIdx == -1) { 
					$scope.onr.personId = response.data;
					$scope.agreement.owners.push(angular.copy($scope.onr));	 
				} else {    		
					$scope.agreement.owners.splice($scope.onrEditIdx, 1, angular.copy($scope.onr));						
				}*/
				$scope.loginReset(); 
				$scope.loginUserForm.$setPristine();
				$scope.loginUserForm.$setUntouched();
				//$location.path('http://rent-licence.rhcloud.com/api/v1/newAgreement.html');
				//$window.location = 'http://agreementui-builderhub.rhcloud.com/newDashboard.html?userId='+$scope.login.userEmail;
				$window.location = $urls.DASHBOARD_UI/* +'?' + 'userId='+$scope.login.userEmail*/;
		
		  }
	
	$scope.submitloginOnError = function errorCallback(response) {
		$scope.loading = false;
		if (response.status === 0) {
			$scope.loginUser.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 401) {
			$scope.loginUser.errorMessage = 'Please enter correct password :: status === '+response.status;
		} else if (response.status == 405) {
			$scope.loginUser.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status ===  '+response.status;
		} else if (response.status == 500) {
			$scope.loginUser.errorMessage = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 404) {
			$scope.loginUser.errorMessage = 'Email is not registered, Please singup. status === '+response.status;
		} else {
			$scope.loginUser.errorMessage = "status: : "+response.status;
		}
		  }
	


 $scope.submitloginUser = function() { 
		$http({
		  method: 'POST',
		   url: $urls.LOGIN,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.login) // pass in data as strings
		  // 
		}).then($scope.submitloginOnSucess, $scope.submitloginOnError);		  
    };

	
}]);


