var app = angular.module('namami', ['ngMaterial', 'menuApp' , 'constants']);
app.controller('admindashboardCtr', ['$scope', '$window', '$http', 'urls', 'toolkit', '$mdDialog', function($scope, $window, $http, $urls, $toolkit, $mdDialog){
	
	$scope.loading = true;
	$http({url: $urls.GET_USER_AGREEMENTS , method: "GET"}).success(
   function(data, status, headers, config) {
	   $scope.loading = false;
     if (status === 200) {

        $scope.listdata = data;
        
        for (i = 0; i < data.length; i++) { 
            $scope.tracker_data = {
            		agreement_created: data[i].agreementTrackerStatus.agreement_created,
            		data_input_in_progress: data[i].agreementTrackerStatus.data_input_in_progress,
            		data_input_done: data[i].agreementTrackerStatus.data_input_done,
            		draft_copy_sent: data[i].agreementTrackerStatus.draft_copy_sent,
            		
            		agreement_status: data[i].agreementTrackerStatus.agreementStatus
            	  };
    		}
     }
   }).error(function(data, status, headers, config) {
	   $scope.loading = false;
	if (status === 0) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 401) {
	$window.location = $urls.LOGIN_UI;
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 405) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else if (status == 500) {
       $scope.errorStatus = 'Sorry, There is some technical problem, Please try again later:: status === '+status;
    } else {
      $scope.errorStatus = "status: : "+status;
    }



   });

   $scope.showAlert = function(ev) {
    $mdDialog.show(
      $mdDialog.alert()
        
        .clickOutsideToClose(true)
        .title('Agreement Tracking details')
        .textContent('Help Content')
        .ariaLabel('Agreement Tracking details')
        .ok('CLOSE')
        .targetEvent(ev)
    );
  };


	
}]);


