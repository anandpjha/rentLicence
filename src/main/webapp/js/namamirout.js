//angular.module('RoutingApp', ['ngRoute'])
angular.module('RoutingApp', ['ngMaterial', 'menuApp' , 'constants', 'ngRoute'])
	.config( ['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		
		alert("test");
		$routeProvider
			.when('/dashboard', {
				templateUrl: '/rentlicence/gui/newDashboard.html'
			})
			.when('profile', {
				templateUrl: '/rentlicence/gui/profile.html'
			})
			.otherwise({
				redirectTo: '/rentlicence/gui/login.html'
			});
		
		$locationProvider.html5Mode(true);
	}]);