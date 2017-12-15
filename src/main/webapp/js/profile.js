var app = angular.module('namami', ['ngMaterial', 'menuApp' , 'constants']);

//var emailId = getUrlParameter('emailId');

app.controller('profileCtr', ['$scope', '$window', '$http', 'urls', 'toolkit', function($scope, $window, $http, $urls, $toolkit){ 

	$scope.loading = true;

	$http({url: $urls.GET_USER_POFILE/* + emailId +"/"*/, method: "GET"}).success(
   function(data, status, headers, config) {
	   $scope.loading = false;
     if (status === 200) {

        $scope.userEmail = data.userEmail;
		$scope.name = data.name;
		$scope.city = data.city;
		$scope.userMobile = data.userMobile;
		
		$scope.updateUserProfile = {
		
		name: $scope.name,
		city: $scope.city,
		userMobile: $scope.userMobile,
		userEmail: $scope.userEmail
		
		};
		//$scope.messageText = "Your profile has been updated";
     }
   }).error(function(data, status, headers, config) {
	   $scope.loading = false;
	if (status === 0) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 401) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 405) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 500) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    }else if (status == 400) {
       $scope.errorStatus = 'Please validate your email id:: status === '+status;
    }else if (status == 404) {
       $scope.errorStatus = 'Profile details not found:: status === '+status;
    } else {
      $scope.errorStatus = "status: : "+status;
    }

   });

	
   
   $scope.updateUserProfileReset = function() {
    	$scope.updateUserProfileUser = {};
		}
	$scope.updateUserProfileReset(); 	
	
	$scope.submitupdateUserProfileOnSucess = function successCallback(response) {
		$scope.loading = false;
				$scope.updateUserProfileReset(); 
				$scope.updateUserProfileUserForm.$setPristine();
				$scope.updateUserProfileUserForm.$setUntouched();
				$scope.messageText = "Your profile has been updated";
				//$window.location = $urls.PROFILE_UI +'?' + 'emailId='+$scope.updateUserProfile.userEmail;
			
		
		  }
	
	$scope.submitupdateUserProfileOnError = function errorCallback(response) {
		$scope.loading = false;
		if (response.status === 0) {
			$scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 401) {
			$scope.errorStatus = 'Please enter correct current password :: status === '+response.status;
		} else if (response.status == 405) {
			$scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status ===  '+response.status;
		} else if (response.status == 500) {
			$scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+response.status;
		} else if (response.status == 404) {
			$scope.errorStatus = 'Email is not registered, Please singup. status === '+response.status;
		} else {
			$scope.errorStatus = "status: : "+response.status;
		}
		  }
	


 $scope.submitupdateUserProfileUser = function() { 
		$http({
		  method: 'POST',
		   url: $urls.UPDATE_USER_POFILE,
		  headers : { 'Content-Type': 'application/json' },
		  data    :  angular.fromJson($scope.updateUserProfile) // pass in data as strings
		  // 
		}).then($scope.submitupdateUserProfileOnSucess, $scope.submitupdateUserProfileOnError);		  
    };

	
}]);


