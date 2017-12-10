var app = angular.module('namami', [ 'ngMaterial', 'menuApp', 'constants',
		'ngRoute' ]);
// angular.module('RoutingApp', ['ngRoute'])
app.config([
		'$routeProvider',
		'$locationProvider',
		'$mdThemingProvider',
		function($routeProvider, $locationProvider, $mdThemingProvider) {
			$routeProvider.when('/login', {
				templateUrl : '/login.html',
				controller : 'loginCtr'
			}).when('/dashboard', {
				templateUrl : '/newDashboard.html',
				controller : 'dashboardCtr'
			}).when('/register', {
				templateUrl : '/registerUser.html',
				controller : 'regUserCtr'
			}).when('/newagreement', {
				templateUrl : '/newAgreement.html',
				controller : 'newAgreementCtr'
			}).when('/newagreement/:agreementid', {
				// templateUrl: function(params){ return
				// '/newAgreement.html?agreementId=' + params.agreementid; },
				templateUrl : '/newAgreement.html',
				controller : 'newAgreementCtr'
			}).when('/proceedPayment/:agreementid', {
				templateUrl : '/proceedPayment.html',
				controller : 'paymentCtr'
			}).when('/start.html', {
				templateUrl : '/registerAndLoginUser.html',
				controller : 'regUserLoginUserCtr'
			}).when('/profile', {
				templateUrl : '/profile.html',
				controller : 'profileCtr'
			}).when('/logout', {
				templateUrl : '/logout.html',
				controller : 'logoutCtr'
			}).when('/reset', {
				templateUrl : '/resetPassword.html',
				controller : 'resetPasswordCtr'
			}).when('/index.html', {
				templateUrl : '/index.html',
				controller : 'resetPasswordCtr'
			}).otherwise({
				templateUrl : '/logout.html',
				controller : 'logoutCtr'
			});

			$locationProvider.html5Mode(true);

			$mdThemingProvider.definePalette('amazingPaletteName', {
				'50' : 'ffebee',
				'100' : 'ffcdd2',
				'200' : 'ef9a9a',
				'300' : 'e57373',
				'400' : 'ef5350',
				'500' : 'f44336',
				'600' : 'e53935',
				'700' : 'd32f2f',
				'800' : 'c62828',
				'900' : 'b71c1c',
				'A100' : 'ff8a80',
				'A200' : 'ff5252',
				'A400' : 'ff1744',
				'A700' : 'd50000',
				'contrastDefaultColor' : 'light', // whether, by default, text
													// (contrast)
				// on this palette should be dark or light
				'contrastDarkColors' : [ '50', '100', // hues which contrast
														// should be 'dark' by
														// default
				'200', '300', '400', 'A100' ],
				'contrastLightColors' : undefined
			// could also specify this if default was 'dark'
			});

			$mdThemingProvider.theme('default').primaryPalette('teal')
					.accentPalette('blue');

		} ]);

/*
 * .config(function($mdThemingProvider) { $mdThemingProvider.theme('default')
 * .primaryPalette('blue') .accentPalette('red'); });
 */

app.factory('errorService', function($rootScope) {
	return {
		showError : function(response) {
			$rootScope.message = {};
			if (response.status === 0) {
				$rootScope.message.errorMessage = response.data.uiMessage;
			} else if (response.status == 401) {
				$rootScope.message.errorMessage = response.data.uiMessage;
			} else if (response.status == 405) {
				$rootScope.message.errorMessage = response.data.uiMessage;
			} else if (response.status == 500) {
				$rootScope.message.errorMessage = response.data.uiMessage;
			} else if (response.status == 404) {
				$rootScope.message.errorMessage = response.data.uiMessage;
			} else if (response.status == 400) {
				/*
				 * var errors = response.data.validationErrors; for (i = 0, len =
				 * errors.length, text = ""; i < len; i++) { text +=
				 * errors[i].errorCode + ","; } $rootScope.message.errorMessage =
				 * text;
				 */

				$rootScope.message.errorMessage = response.data.customData;

			} else {
				$rootScope.message.errorMessage = response.data.uiMessage;
			}
		}
	};
});

app
		.controller(
				'loginCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$rootScope',
						'errorService',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $rootScope, errorService) {
							$rootScope.isLoggedinUser = false;
							$scope.loginReset = function() {
								$scope.loginUser = {};
							};

							$scope.loginReset();

							$scope.submitloginOnSucess = function successCallback(
									response) {

								$scope.loginReset();
								$scope.loginUserForm.$setPristine();
								$scope.loginUserForm.$setUntouched();
								$rootScope.isLoggedinUser = true;
								$scope.loading = false;

								$location.path('/dashboard');

							};

							$scope.submitloginUser = function() {
								$scope.loading = true;
								$http({
									method : 'POST',
									url : $urls.LOGIN,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.login)
								}).then($scope.submitloginOnSucess,
										$scope.submitloginOnError);
							};

							$scope.submitloginOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.logoutMsg = "";
								$scope.loading = false;
								$scope.loginUser.errorMessage = $rootScope.message.errorMessage;
							};

						} ]);

app.controller('regUserCtr', [
		'$scope',
		'$window',
		'$http',
		'urls',
		'toolkit',
		'$location',
		'$rootScope',
		'errorService',
		function($scope, $window, $http, $urls, $toolkit, $location,
				$rootScope, errorService) {

			$scope.regReset = function() {
				$scope.register = {};
			};
			$scope.regReset();

			$scope.submitRegOnSucess = function successCallback(response) {

				$scope.regReset();
				$scope.registerForm.$setPristine();
				$scope.registerForm.$setUntouched();
				$scope.loading = false;
				$location.path('/login');
			};

			$scope.submitRegOnError = function errorCallback(response) {
				errorService.showError(response);
				$scope.loading = false;

				$scope.register.errorMessage = $rootScope.message.errorMessage;
			};

			$scope.submitRegister = function() {
				$scope.loading = true;
				$http({
					method : 'POST',
					url : $urls.REGISTER_USER,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson($scope.register)
				// 
				}).then($scope.submitRegOnSucess, $scope.submitRegOnError);
			};

		} ]);

app
		.controller(
				'dashboardCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$mdDialog',
						'errorService',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $mdDialog, errorService) {
							$scope.loading = true;

							$http({
								url : $urls.GET_USER_AGREEMENTS,
								method : "GET"
							}).success(function(data, status, headers, config) {
								$scope.loading = false;
								if (status === 200) {

									$scope.listdata = data;

								}
								;
							}).error(function(data, status, headers, config) {
								$scope.loading = false;
								if (status === 0) {
									$scope.errorStatus = data.uiMessage;
								} else if (status == 401) {
									$location.path('/login');
									$scope.errorStatus = data.uiMessage;
								} else if (status == 405) {
									$scope.errorStatus = data.uiMessage;
								} else if (status == 500) {
									$scope.errorStatus = data.uiMessage;
								} else {
									$scope.errorStatus = data.uiMessage;
								}

							});

							$scope.showProgress = function(ev, data) {
								var newScope = $scope.$new();
								newScope.data = data;
								$mdDialog.show({
									templateUrl : 'progressDialog.tmpl.html',
									parent : angular.element(document.body),
									targetEvent : ev,
									scope : newScope,
									clickOutsideToClose : true
								}).then(function(answer) {
									$location.path('/dashboard');
								});
							};

							$scope.viewAgreementDraft = function(ev, data) {

								if (data) {

									$http
											.get(
													$urls.GET_AGREEMENT_DETAILS
															+ data.agreementId)
											.then(
													$scope.fetchAgreementSuccessCallBack,
													$scope.fetchAgreementErrorCallBack);

								} else {
									$scope.agreement = [];
									// Create the array to hold the list of
									// Owners
									$scope.agreement.owners = [];
									// Create the array to hold the list of
									// Tenants
									$scope.agreement.tenants = [];
									// Create the array to hold the list of
									// witnesses
									$scope.agreement.witnesses = [];
									$scope.agreement.property = {};
									$scope.agreement.terms = {};
									$scope.agreement.miscellaneousTerms = {};
									$scope.agreement.terms.varyingRents = [ {
										monthNumber : 1
									} ];
								}

								var newScope = $scope.$new();
								// newScope.agreement = $scope.agreement;
								// newScope.trm = $scope.agreement.terms;
								$mdDialog
										.show(
												{
													/* controller: dashboardCtr, */
													templateUrl : 'viewAgreementDialog.tmpl.html',
													parent : angular
															.element(document.body),
													targetEvent : ev,
													scope : newScope,
													clickOutsideToClose : true
												}).then(function(answer) {
											$location.path('/dashboard');
										});
							};

							$scope.fetchAgreementSuccessCallBack = function(
									response) {
								$scope.loading = false;
								console.log(response.data);
								$scope.ismsg = true;

								$scope.ispersonmsg = false;
								$scope.TermMsg = "Below Agreement data has been submitted, You can change & resubmit if required!!";
								$scope.propertyMsg = "Below Property Details has been submitted, You can change & resubmit if required!!";
								$scope.miscellaneousMsg = "Miscellaneous Terms has been submitted, You can change & resubmit whenever required!!";

								$scope.agreement = response.data;
								$scope.trm = $scope.agreement.terms;
								$scope.prp = $scope.agreement.property;
								$scope.mis = $scope.agreement.miscellaneousTerms;

								// $scope.mis.miscellaneousTerms =
								// $scope.agreement.miscellaneousTerms;
								if (!$scope.trm.varyingRents) {
									$scope.trm.varyingRents = [ {
										monthNumber : 1
									} ];
								}
								if ($scope.agreement.terms
										&& $scope.agreement.terms.agreementStartDate) {
									$scope.agreement.terms.agreementStartDate = new Date(
											$scope.agreement.terms.agreementStartDate);
								}

								if ($scope.agreement.terms
										&& $scope.agreement.terms.agreementStartDate
										&& $scope.agreement.terms.agreementPeriodInMonths) {
									var month = $scope.agreement.terms.agreementPeriodInMonths;
									var startDate = new Date(
											$scope.agreement.terms.agreementStartDate);
									startDate.setMonth(startDate.getMonth()
											+ month);
									$scope.trm.agreementEndDate = startDate;
								}

								if ($scope.agreement.terms
										&& $scope.agreement.terms.dateOfCheque) {
									$scope.agreement.terms.dateOfCheque = new Date(
											$scope.agreement.terms.dateOfCheque);
								}

								if ($scope.mis.length == 0) {
									$scope.ismismsg = false;
								} else {
									$scope.ismismsg = true;
								}

								if ($scope.prp.length == 0) {
									$scope.isprpmsg = false;
								} else {
									$scope.isprpmsg = true;
								}

							};

							$scope.fetchAgreementPdfSuccessCallBack = function(
									response) {

								$scope.loading = false;

								openPDF(response.data, "myPDFdoc.pdf");

							};

							$scope.downloadPdf = function(id) {
								$scope.agreementId = id;

								if ($scope.agreementId) {

									$http
											.get(
													$urls.GET_AGREEMENT_DETAIL_PDF
															+ $scope.agreementId,
													{
														responseType : 'arraybuffer'
													})
											.then(
													$scope.fetchAgreementPdfSuccessCallBack,
													$scope.fetchAgreementPdfErrorCallBack);

								} else {
									$scope.errorMessage = "Failed to fetch Pdf";
								}
							};
							function openPDF(resData, fileName) {

								var a = document.createElement("a");
								document.body.appendChild(a);
								a.style = "display: none";
								var file = new Blob([ resData ], {
									type : 'application/pdf'
								});
								var fileURL = URL.createObjectURL(file);
								a.href = fileURL;
								a.download = fileName;
								a.click();
							}

							$scope.fetchAgreementPdfErrorCallBack = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.errorStatus = $rootScope.message.errorMessage;
							};

						} ]);

app
		.controller(
				'newAgreementCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$routeParams',
						'errorService',
						'$rootScope',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $routeParams, errorService,
								$rootScope) {

							$scope.propertyTypes = [ {
								'type' : 'Flat',
								'value' : 'Flat'
							}, {
								'type' : 'Shop',
								'value' : 'Shop'
							}, {
								'type' : 'Office',
								'value' : 'Office'
							} ];

							$scope.propertyIdentityTypes = [ {
								'type' : 'Plot No',
								'value' : 1
							}, {
								'type' : 'CTS No',
								'value' : 2
							}, {
								'type' : 'Survey No',
								'value' : 3
							}, {
								'type' : 'Gata No',
								'value' : 4
							} ];

							$scope.districts = [ {
								'type' : 'PUNE',
								'value' : 'PUNE'
							}, {
								'type' : 'MUMBAI',
								'value' : 'MUMBAI'
							} ];

							$scope.states = [ {
								'type' : 'Maharashtra',
								'value' : 'Maharashtra'
							} ];

							$scope.regChargePaidByTypes = [ {
								'type' : 'OWNER',
								'value' : 'OWNER'
							}, {
								'type' : 'TENANT',
								'value' : 'TENANT'
							}, {
								'type' : 'Both',
								'value' : 'Both'
							}, {
								'type' : 'Do Not Specify',
								'value' : 'DoNotSpecify'
							} ];

							$scope.petAllowTypes = [ {
								'type' : 'Yes',
								'value' : 'petAllow'
							}, {
								'type' : 'No',
								'value' : 'petNotAllow'
							}, {
								'type' : 'Do Not Specify',
								'value' : 'DoNotSpecify'
							} ];

							$scope.locked = true;

							$scope.selectedIndex = 0;
							$scope.agrTermsTabIdx = 0;
							$scope.ownerTabIdx = 1;
							$scope.tenentTabIdx = 2;
							$scope.witnessTabIdx = 3;
							$scope.propertyTabIdx = 4;
							$scope.miscTermsTabIdx = 5;

							// Start as not visible but when button is tapped it
							// will show as true
							$scope.visible = false;

							// editIdx. -1 means new insert, Otherwise it
							// represents index of the editing element in array.
							$scope.onrEditIdx = -1;
							$scope.onrDeleteIdx = -1;
							$scope.trmEditIdx = -1;

							/*
							 * $scope.trm.agreementCities = [ "PUNE", "MUMBAI" ];
							 */

							// Fetch agreement for edit or Create an blank
							// Agreement
							$scope.fetchAgreementSuccessCallBack = function(
									response) {
								$scope.loading = false;
								console.log(response.data);
								$scope.ismsg = true;

								$scope.ispersonmsg = false;
								$scope.TermMsg = "Below Agreement data has been submitted, You can change & resubmit if required!!";
								$scope.propertyMsg = "Below Property Details has been submitted, You can change & resubmit if required!!";
								$scope.miscellaneousMsg = "Miscellaneous Terms has been submitted, You can change & resubmit whenever required!!";

								$scope.agreement = response.data;
								$scope.trm = $scope.agreement.terms;
								$scope.prp = $scope.agreement.property;
								$scope.mis = $scope.agreement.miscellaneousTerms;
								if ($scope.trm.length != 0) {
									$scope.locked = false;
								}
								// $scope.mis.miscellaneousTerms =
								// $scope.agreement.miscellaneousTerms;
								if (!$scope.trm.varyingRents) {
									$scope.trm.varyingRents = [ {
										monthNumber : 1
									} ];
								}
								if ($scope.agreement.terms
										&& $scope.agreement.terms.agreementStartDate) {
									$scope.agreement.terms.agreementStartDate = new Date(
											$scope.agreement.terms.agreementStartDate);
								}

								if ($scope.agreement.terms
										&& $scope.agreement.terms.agreementStartDate
										&& $scope.agreement.terms.agreementPeriodInMonths) {
									var month = $scope.agreement.terms.agreementPeriodInMonths;
									var startDate = new Date(
											$scope.agreement.terms.agreementStartDate);
									startDate.setMonth(startDate.getMonth()
											+ month);
									$scope.trm.agreementEndDate = startDate;
								}

								if ($scope.agreement.terms
										&& $scope.agreement.terms.dateOfCheque) {
									$scope.agreement.terms.dateOfCheque = new Date(
											$scope.agreement.terms.dateOfCheque);
								}

								if ($scope.mis.length == 0) {
									$scope.ismismsg = false;
								} else {
									$scope.ismismsg = true;
								}

								if ($scope.prp.length == 0) {
									$scope.isprpmsg = false;
								} else {
									$scope.isprpmsg = true;
								}

							};

							$scope.fetchAgreementErrorCallBack = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.agreement = [];
							// $scope.agreementId =
							// $toolkit.getUrlParameter('agreementId');
							$scope.agreementId = $routeParams.agreementid;
							if ($scope.agreementId) {
								console.log($scope.agreementId);
								$http.get(
										$urls.GET_AGREEMENT_DETAILS
												+ $scope.agreementId).then(
										$scope.fetchAgreementSuccessCallBack,
										$scope.fetchAgreementErrorCallBack);

							} else {
								$scope.agreement = [];
								// Create the array to hold the list of Owners
								$scope.agreement.owners = [];
								// Create the array to hold the list of Tenants
								$scope.agreement.tenants = [];
								// Create the array to hold the list of
								// witnesses
								$scope.agreement.witnesses = [];
								$scope.agreement.property = {};
								$scope.agreement.terms = {};
								$scope.agreement.miscellaneousTerms = {};
								$scope.agreement.terms.varyingRents = [ {
									monthNumber : 1
								} ];
							}

							$scope.onrReset = function() {
								$scope.onr = {};
								$scope.onrEditIdx = -1;
								$scope.onrDeleteIdx = -1;
							};
							$scope.onrReset();

							$scope.submitOnrOnSucess = function successCallback(
									response) {
								$scope.ispersonmsg = false;
								$scope.loading = false;
								if ($scope.onrEditIdx == -1) {
									$scope.onr.personId = response.data;
									$scope.agreement.owners.push(angular
											.copy($scope.onr));
								} else {
									$scope.agreement.owners.splice(
											$scope.onrEditIdx, 1, angular
													.copy($scope.onr));
								}
								$scope.onrReset();
								$scope.onrForm.$setPristine();
								$scope.onrForm.$setUntouched();

							};
							$scope.submitOnrOnError = function errorCallback(
									response) {
								errorService.showError(response);

								$scope.ispersonmsg = true;
								$scope.loading = false;
								$scope.onr.errorMessage = $rootScope.message.errorMessage;

							};

							// Create the function to push the data into the
							// "owners" array
							$scope.submitOnr = function() {
								$scope.loading = true;
								if (!$scope.agreement.owners) {
									$scope.agreement.owners = [];
								}
								if ($scope.onrEditIdx == -1) { // -1 means
									// first owner
									// is gettign
									// submitted.
									$scope.onr.agreementId = $scope.agreement.agreementId;
									$scope.onr.personOrder = $scope.agreement.owners.length + 1;
								}
								;
								$http({
									method : 'POST',
									url : $urls.ADD_OWNER,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.onr)
								// pass in data as strings
								// 
								}).then($scope.submitOnrOnSucess,
										$scope.submitOnrOnError);
							};

							// Create the function to delete the owner. Update
							// data into the "owners" array
							$scope.deleteOnrOnSucess = function successCallback(
									response) {
								$scope.loading = false;
								$scope.ispersonmsg = false;
								$scope.selectedIndex = $scope.ownerTabIdx;
								$scope.agreement.owners.splice(
										$scope.onrDeleteIdx, 1);
								$scope.onrDeleteIdx = -1;
							};
							$scope.deleteOnrOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.ispersonmsg = true;
								$scope.loading = false;
								$scope.onr.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.deleteOnr = function(index) {
								$scope.onrDeleteIdx = index;
								$http(
										{
											method : 'POST',
											url : $urls.DELETE_PERSON,
											headers : {
												'Content-Type' : 'application/json'
											},
											data : angular
													.fromJson($scope.agreement.owners[index])
										// pass in data as strings
										// 
										}).then($scope.deleteOnrOnSucess,
										$scope.deleteOnrOnError);
							};

							$scope.editOnr = function(index) {
								$scope.selectedIndex = $scope.ownerTabIdx;
								$scope.onrEditIdx = index;
								$scope.onr = angular
										.copy($scope.agreement.owners[index]);
							};

							// Tenants
							$scope.tntEditIdx = -1;
							$scope.tntDeleteIdx = -1;
							$scope.tntReset = function() {
								$scope.tnt = {};
								$scope.tntEditIdx = -1;
								$scope.tntDeleteIdx = -1;
							};
							$scope.tntReset();

							// Create the function to push the data into the
							// "tenants" array
							$scope.submitTntOnSucess = function successCallback(
									response) {
								$scope.ispersonmsg = false;
								$scope.loading = false;
								if ($scope.tntEditIdx == -1) {
									$scope.tnt.personId = response.data;
									$scope.agreement.tenants.push(angular
											.copy($scope.tnt));
								} else {
									$scope.agreement.tenants.splice(
											$scope.tntEditIdx, 1, angular
													.copy($scope.tnt));
								}
								$scope.tntReset();
								$scope.tntForm.$setPristine();
								$scope.tntForm.$setUntouched();
							};
							$scope.submitTntOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.ispersonmsg = true;
								$scope.loading = false;
								$scope.tnt.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitTnt = function() {
								$scope.loading = true;
								if (!$scope.agreement.tenants) {
									$scope.agreement.tenants = [];
								}
								$scope.tnt.agreementId = $scope.agreement.agreementId;
								if ($scope.tntEditIdx == -1) {
									$scope.tnt.personOrder = $scope.agreement.tenants.length + 1;
								}
								;
								if ($scope.tntEditIdx == -1) {
									$scope.tnt.personOrder = $scope.agreement.tenants.length + 1;
								}
								;
								$http({
									method : 'POST',
									url : $urls.ADD_TENANT,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.tnt)
								// pass in data as strings
								// 
								}).then($scope.submitTntOnSucess,
										$scope.submitTntOnError);
							};

							// Create the function to delete the owner. Update
							// data into the "tenants" array

							$scope.deleteTntOnSucess = function(response) {
								$scope.ispersonmsg = false;
								$scope.loading = false;
								$scope.selectedIndex = $scope.tenentTabIdx;
								$scope.agreement.tenants.splice(
										$scope.tntDeleteIdx, 1);
								$scope.tntDeleteIdx = -1;
							};
							$scope.deleteTntOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.ispersonmsg = true;
								$scope.loading = false;
								$scope.tnt.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.deleteTnt = function(index) {
								$scope.tntDeleteIdx = index;
								$http(
										{
											method : 'POST',
											url : $urls.DELETE_PERSON,
											headers : {
												'Content-Type' : 'application/json'
											},
											data : angular
													.fromJson($scope.agreement.tenants[index])
										// pass in data as strings
										// 
										}).then($scope.deleteTntOnSucess,
										$scope.deleteTntOnError);
							};

							// Create the function to update the data into the
							// "tenants" array
							$scope.editTnt = function(index) {
								$scope.selectedIndex = $scope.tenentTabIdx;
								$scope.tntEditIdx = index;
								$scope.tnt = angular
										.copy($scope.agreement.tenants[index]);
							};

							// witnesses
							$scope.wtnEditIdx = -1;
							$scope.wtnDeleteIdx = -1;
							$scope.wtnReset = function() {
								$scope.wtn = {};
								$scope.wtnEditIdx = -1;
								$scope.wtnDeleteIdx = -1;
							};
							$scope.wtnReset();

							// Create the function to push the data into the
							// "witnesses" array
							$scope.submitWtnOnSucess = function successCallback(
									response) {
								$scope.ispersonmsg = false;
								$scope.loading = false;
								if ($scope.wtnEditIdx == -1) {
									$scope.wtn.personId = response.data;
									$scope.agreement.witnesses.push(angular
											.copy($scope.wtn));
								} else {
									$scope.agreement.witnesses.splice(
											$scope.wtnEditIdx, 1, angular
													.copy($scope.wtn));
								}
								$scope.wtnReset();
								$scope.wtnForm.$setPristine();
								$scope.wtnForm.$setUntouched();
							};
							$scope.submitWtnOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.ispersonmsg = true;
								$scope.loading = false;
								$scope.wtn.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitWtn = function() {
								if (!$scope.agreement.witnesses) {
									$scope.agreement.witnesses = [];
								}
								$scope.wtn.agreementId = $scope.agreement.agreementId;
								if ($scope.wtnEditIdx == -1) {
									$scope.wtn.personOrder = $scope.agreement.witnesses.length + 1;
								}
								;
								if ($scope.wtnEditIdx == -1) {
									$scope.wtn.personOrder = $scope.agreement.witnesses.length + 1;
								}
								;
								$http({
									method : 'POST',
									url : $urls.ADD_WITNESS,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.wtn)
								// pass in data as strings
								// 
								}).then($scope.submitWtnOnSucess,
										$scope.submitWtnOnError);
							};

							// Create the function to delete the owner. Update
							// data into the "witnesses" array
							$scope.deleteWtnOnSucess = function(response) {
								$scope.ispersonmsg = false;
								$scope.loading = false;
								$scope.selectedIndex = $scope.witnessTabIdx;
								$scope.agreement.witnesses.splice(
										$scope.wtnDeleteIdx, 1);
								$scope.wtnDeleteIdx = -1;
							};
							$scope.deleteWtnOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.wtn.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.deleteWtn = function(index) {
								$scope.wtnDeleteIdx = index;
								$http(
										{
											method : 'POST',
											url : $urls.DELETE_PERSON,
											headers : {
												'Content-Type' : 'application/json'
											},
											data : angular
													.fromJson($scope.agreement.witnesses[index])
										// pass in data as strings
										// 
										}).then($scope.deleteWtnOnSucess,
										$scope.deleteWtnOnError);
							};

							// Create the function to update the data into the
							// "witnesses" array
							$scope.editWtn = function(index) {
								$scope.selectedIndex = $scope.witnessTabIdx;
								$scope.wtnEditIdx = index;
								$scope.wtn = angular
										.copy($scope.agreement.witnesses[index]);
							};

							$scope.submitPropOnSucess = function(index) {
								$scope.loading = false;
								$scope.isprpmsg = true;
								$scope.propertyMsg = "Below Property Details has been submitted, You can change & resubmit if required!!";
							};
							$scope.submitPropOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.propertyMsg = "";
								$scope.isprpmsg = true;
								$scope.errorMessage = $rootScope.message.errorMessage;

							};

							$scope.submitProp = function() {
								$scope.prp.agreementId = $scope.agreement.agreementId;
								$scope.prp.propertyIdentityType = $toolkit
										.convertToInt($scope.prp.propertyIdentityType);

								$http({
									method : 'POST',
									url : $urls.ADD_POSTAL_ADDRESS,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : $scope.prp
								// pass in data as strings
								// 
								}).then($scope.submitPropOnSucess,
										$scope.submitPropOnError);
							};

							$scope.addNewVaryingRent = function(editItemIndex) {
								var lastItemIdx = $scope.agreement.terms.varyingRents.length - 1;
								var newItemNo = $scope.agreement.terms.varyingRents.length + 1;
								if (editItemIndex == lastItemIdx) {
									$scope.agreement.terms.varyingRents.push({
										'monthNumber' : lastItemIdx + 1
									});
								} else {
									// $scope.trm.varyingRents.push({'monthNumber':lastItem.lastItemIdx+1});
								}
							};

							$scope.findAgreementEndDate = function() {
								var month = $scope.agreement.terms.agreementPeriodInMonths;
								var startDate = new Date(
										$scope.agreement.terms.agreementStartDate);
								startDate
										.setMonth(startDate.getMonth() + month);
								$scope.trm.agreementEndDate = startDate;
							};

							$scope.removeVaryingRent = function() {
								if ($scope.agreement.terms.varyingRents.length == 1) {
									return;
								}
								;

								$scope.agreement.terms.varyingRents
										.splice($scope.agreement.terms.varyingRents.length - 1);
							};

							$scope.ismsg = false;
							$scope.isprpmsg = false;
							$scope.submitTrmOnSucess = function successCallback(
									response) {
								$scope.loading = false;
								$scope.agreement.terms = angular
										.copy($scope.agreement.terms);
								$scope.agreement.agreementId = response.data;
								$scope.trmEditIdx = 0;
								$scope.agreement.sucessMsg = "Agreement data has been submitted, You can change & resubmit whenever required!!";
								$scope.TermMsg = "";
								$scope.ismsg = true;
								$scope.trmForm.$setPristine();
								$scope.trmForm.$setUntouched();
								$scope.locked = false;

							};
							$scope.submitTrmOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.trm.errorMessage = $rootScope.message.errorMessage;
							};

							// Create the function to push the data into the
							// "owners" array
							$scope.submitTrm = function() {
								if ($scope.trmEditIdx != -1) {
									$scope.trm.agreementId = $scope.agreement.agreementId;
									// $scope.trm.personOrder =
									// $scope.agreement.owners.length+1;
								}
								;
								$http({
									method : 'POST',
									url : $urls.SUBMIT_AGR_TERMS,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.trm)
								// pass in data as strings
								// 
								}).then($scope.submitTrmOnSucess,
										$scope.submitTrmOnError);
							};

							$scope.submitMisTrmOnSucess = function successCallback(
									response) {

								$scope.loading = false;
								$scope.agreement.miscellaneousTerms = angular
										.copy($scope.mis);

								$scope.misForm.$setPristine();

								$scope.misForm.$setUntouched();

								$scope.mis.scussMessage = "Miscellaneous Terms has been submitted, You can change & resubmit whenever required!!";
								$scope.ismismsg = true;
								$scope.miscellaneousMsg = "";

							};
							$scope.submitMisTrmOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.ismismsg = true;
								$scope.miscellaneousMsg = "";
								$scope.mis.errorMessage = $rootScope.message.errorMessage;
							};

							// Create the function to push the data into the
							// "owners" array
							$scope.submitMis = function() {
								$scope.mis.agreementId = $scope.agreement.agreementId;

								$http({
									method : 'POST',
									url : $urls.MISCELLANEOUS_TERMS,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.mis)
								// pass in data as strings
								// 
								}).then($scope.submitMisTrmOnSucess,
										$scope.submitMisTrmOnError);
							};

							$scope.submitRevOnSucess = function successCallback(
									response) {

								$scope.loading = false;
								// $scope.agreement.miscellaneousTerms =
								// angular.copy($scope.mis);

								$scope.revForm.$setPristine();

								$scope.revForm.$setUntouched();

								$scope.rev.scussMessage = "Confirmation has been submitted, You can not change data now";
								$scope.isrevmsg = true;
								$scope.reviewMsg = "";

								$location.path('/dashboard');

							};
							$scope.submitRevOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.isrevmsg = true;
								$scope.reviewMsg = "";
								$scope.rev.errorMessage = $rootScope.message.errorMessage;
							};

							// Create the function to push the data into the
							// "owners" array
							$scope.submitRev = function() {

								$scope.rev.agreementId = $scope.agreement.agreementId;

								$http({
									method : 'POST',
									url : $urls.CONFIRM_AGR_DATA,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.rev)
								// pass in data as strings
								// 
								}).then($scope.submitRevOnSucess,
										$scope.submitRevOnError);
							};

						} ]);

// app.controller('profileCtr', ['$scope', '$window', '$http', 'urls',
// 'toolkit', function($scope, $window, $http, $urls, $toolkit){
app.controller('profileCtr', [
		'$scope',
		'$window',
		'$http',
		'urls',
		'toolkit',
		'$location',
		'errorService',
		function($scope, $window, $http, $urls, $toolkit, $location,
				errorService) {

			$scope.loading = true;

			$http({
				url : $urls.GET_USER_POFILE/*
											 * + emailId +"/"
											 */,
				method : "GET"
			}).success(function(data, status, headers, config) {
				$scope.loading = false;
				if (status === 200) {

					$scope.userEmail = data.userEmail;
					$scope.name = data.name;
					$scope.city = data.city;
					$scope.userMobile = data.userMobile;

					$scope.updateUserProfile = {

						name : $scope.name,
						city : $scope.city,
						userMobile : $scope.userMobile,
						userEmail : $scope.userEmail

					};
					// $scope.messageText =
					// "Your profile has been
					// updated";
				}
			}).error(function(data, status, headers, config) {
				$scope.loading = false;
				if (status === 0) {
					$scope.errorStatus = data.uiMessage;
				} else if (status == 401) {
					$scope.errorStatus = data.uiMessage;
				} else if (status == 405) {
					$scope.errorStatus = data.uiMessage;
				} else if (status == 500) {
					$scope.errorStatus = data.uiMessage;
				} else if (status == 400) {
					$scope.errorStatus = data.uiMessage;
				} else if (status == 404) {
					$scope.errorStatus = data.uiMessage;
				} else {
					$scope.errorStatus = data.uiMessage;
				}

			});

			$scope.updateUserProfileReset = function() {
				$scope.updateUserProfileUser = {};
			};
			$scope.updateUserProfileReset();

			$scope.submitupdateUserProfileOnSucess = function successCallback(
					response) {
				$scope.loading = false;
				$scope.updateUserProfileReset();
				$scope.updateUserProfileUserForm.$setPristine();
				$scope.updateUserProfileUserForm.$setUntouched();
				$scope.messageText = "Your profile has been updated";
				// $window.location = $urls.PROFILE_UI +'?' +
				// 'emailId='+$scope.updateUserProfile.userEmail;

			};
			$scope.submitupdateUserProfileOnError = function errorCallback(
					response) {
				errorService.showError(response);
				$scope.loading = false;
				$scope.errorStatus = $rootScope.message.errorMessage;
			};

			$scope.submitupdateUserProfileUser = function() {
				$http({
					method : 'POST',
					url : $urls.UPDATE_USER_POFILE,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : angular.fromJson($scope.updateUserProfile)
				// pass in data as strings
				// 
				}).then($scope.submitupdateUserProfileOnSucess,
						$scope.submitupdateUserProfileOnError);
			};

		} ]);

// app.controller('logoutCtr', ['$scope', '$window', '$http', 'urls', 'toolkit',
// function($scope, $window, $http, $urls, $toolkit){
app
		.controller(
				'logoutCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$rootScope',
						'errorService',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $rootScope, errorService) {
							$scope.loading = true;
							$http({
								url : $urls.LOGOUT_URL/* + userId +"/" */,
								method : "GET"
							})
									.success(
											function(data, status, headers,
													config) {
												$scope.loading = false;
												$rootScope.isLoggedinUser = false;
												$scope.logoutMsg = "You have sucessfully logout";

											});

							// $scope.agreement.owners = [];
							$scope.loginReset = function() {
								$scope.loginUser = {};
								// $scope.onrEditIdx = -1;
								// $scope.onrDeleteIdx = -1;
							};
							$scope.loginReset();

							$scope.submitloginOnSucess = function successCallback(
									response) {
								$scope.loading = false;
								$rootScope.isLoggedinUser = true;

								$scope.loginReset();
								$scope.loginUserForm.$setPristine();
								$scope.loginUserForm.$setUntouched();
								$location.path('/dashboard');

							};
							$scope.submitloginOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.logoutMsg = "";
								$scope.loading = false;
								$scope.loginUser.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitloginUser = function() {
								$http({
									method : 'POST',
									url : $urls.LOGIN,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.login)
								// pass in data as strings
								// 
								}).then($scope.submitloginOnSucess,
										$scope.submitloginOnError);
							};

						} ]);

// app.controller('resetPasswordCtr', ['$scope', '$window', '$http', 'urls',
// 'toolkit', function($scope, $window, $http, $urls, $toolkit){
app
		.controller(
				'resetPasswordCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'errorService',
						function($scope, $window, $http, $urls, $toolkit,
								$location, errorService) {

							// $scope.agreement.owners = [];
							$scope.resetPasswordReset = function() {
								$scope.resetPassword = {};
								// $scope.onrEditIdx = -1;
								// $scope.onrDeleteIdx = -1;
							};
							$scope.resetPasswordReset();

							$scope.submitResetOnSucess = function successCallback(
									response) {
								$scope.loading = false;
								/*
								 * if($scope.onrEditIdx == -1) {
								 * $scope.onr.personId = response.data;
								 * $scope.agreement.owners.push(angular.copy($scope.onr)); }
								 * else {
								 * $scope.agreement.owners.splice($scope.onrEditIdx,
								 * 1, angular.copy($scope.onr)); }
								 */
								$scope.resetPasswordReset();
								$scope.resetPasswordForm.$setPristine();
								$scope.resetPasswordForm.$setUntouched();
								// $window.location = $urls.LOGIN_UI;
								$location.path('/login');

							};
							$scope.submitResetOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.loading = false;
								$scope.resetPassword.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitresetPassword = function() {
								$scope.loading = true;
								$http({
									method : 'POST',
									url : $urls.RESET_PASSWORD,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.login)
								// pass in data as strings
								// 
								}).then($scope.submitResetOnSucess,
										$scope.submitResetOnError);
							};

						} ]);

app
		.controller(
				'regUserLoginUserCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$rootScope',
						'errorService',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $rootScope, errorService) {

							$rootScope.isLoggedinUser = false;

							$scope.regReset = function() {
								$scope.register = {};
							};
							$scope.regReset();

							$scope.submitRegOnSucess = function successCallback(
									response) {

								// if (response.status == 200) {
								$scope.regReset();
								$scope.registerForm.$setPristine();
								$scope.registerForm.$setUntouched();
								$scope.loading = false;
								// $window.location = $urls.LOGIN_UI;
								// $window.location.href = $urls.LOGIN_UI;
								$location.path('/login');

							};
							$scope.submitRegOnError = function errorCallback(
									response) {
								errorService.showError(response);

								$scope.loading = false;
								$scope.register.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitRegister = function() {
								$scope.loading = true;
								$http({
									method : 'POST',
									url : $urls.REGISTER_USER,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.register)
								// 
								}).then($scope.submitRegOnSucess,
										$scope.submitRegOnError);
							};

							// For Login

							// $scope.agreement.owners = [];
							$scope.loginReset = function() {
								$scope.loginUser = {};
								// $scope.onrEditIdx = -1;
								// $scope.onrDeleteIdx = -1;
							};
							$scope.loginReset();

							$scope.submitloginOnSucess = function successCallback(
									response) {

								$scope.loginReset();
								$scope.loginUserForm.$setPristine();
								$scope.loginUserForm.$setUntouched();
								$scope.loading = false;
								$rootScope.isLoggedinUser = true;

								$location.path('/dashboard');

							};

							$scope.submitloginOnError = function errorCallback(
									response) {
								errorService.showError(response);
								$scope.logoutMsg = "";
								$scope.loading = false;
								$scope.loginUser.errorMessage = $rootScope.message.errorMessage;
							};

							$scope.submitloginUser = function() {
								$scope.loading = true;
								$http({
									method : 'POST',
									url : $urls.LOGIN,
									headers : {
										'Content-Type' : 'application/json'
									},
									data : angular.fromJson($scope.login)
								// pass in data as strings
								// 
								}).then($scope.submitloginOnSucess,
										$scope.submitloginOnError);
							};
							// For login end

							// To show dyanamic header after login

							$rootScope.isSigningIn = true;
							$rootScope.isSigningUp = false;

							$rootScope.isSignInUp = function(isSignInUp) {

								if (isSignInUp) {
									$rootScope.isSigningUp = true;
									$rootScope.isSigningIn = false;

								} else {
									$rootScope.isSigningUp = false;
									$rootScope.isSigningIn = true;
								}
							};

						} ]);

app
		.controller(
				'paymentCtr',
				[
						'$scope',
						'$window',
						'$http',
						'urls',
						'toolkit',
						'$location',
						'$routeParams',
						'$mdDialog',
						function($scope, $window, $http, $urls, $toolkit,
								$location, $routeParams, $mdDialog) {
							$scope.paymentStatus = false;

							$scope.fetchPaymetSuccessCallBack = function(
									response) {
								$scope.loading = false;
								$scope.paymentDetails = response.data;

							};
							$scope.fetchPaymentErrorCallBack = function errorCallback(
									response) {
								$scope.loading = false;
								$scope.errorMessage = "Failed to fetch Payment Details.";
							};
							$scope.agreementId = $routeParams.agreementid;

							if ($scope.agreementId) {
								$http.get(
										$urls.GET_PAYMENT_DETAIL
												+ $scope.agreementId).then(
										$scope.fetchPaymetSuccessCallBack,
										$scope.fetchPaymentErrorCallBack);

							} else {
								$scope.paymentDetails = [];
							}

							$scope.showConfirm = function(ev) {
								// Appending dialog to document.body to cover
								// sidenav in docs app

								var confirm = $mdDialog
										.confirm()
										.title(
												'Would you like to proceed Payment for amount '
														+ $scope.paymentDetails.totalRegistrationCharges
														+ '?')
										.textContent(
												'You are being re-directed to a third party site. Please acknowledge the disclaimer before proceeding further. You are about to access a site, the accuracy or completeness of the materials or the reliability of any advice, opinion, statement or other information displayed or distributed through it, is not warranted by SBICPSL and shall be solely be construed to be set forth by the third party.You will access this site solely for the payment of your bills and you acknowledge that any reliance on any opinion, advice, statement, memorandum, or information available on the site shall be at your sole risk and consequences.SBICPSL and its affiliates, subsidiaries, employees, officers, directors and agents, expressly disclaim any liability for any deficiency in the services of the service provider whose site you are about to access. Neither SBICPSL nor any of its affiliates nor their directors, officers and employees will be liable to or have any responsibility of any kind for any loss that you incur in the event of any deficiency in the services of the service provider, failure or disruption of the site of the service provider, or resulting from the act or omission of any other party involved in making this site or the data contained therein available to you, or from any other cause relating to your access to, inability to access, or use of the site or these materials in accordance thereto SBICSPL and all its related parties described hereinabove stand indemnified from all proceedings or matters arising thereto.')
										.ariaLabel('Proceed for Payment')
										.targetEvent(ev).ok('OK').cancel(
												'Cancle');
								$mdDialog
										.show(confirm)
										.then(
												function() {

													$http
															.get($urls.UPDATE_PAYMENT_STATUS
																	+ $scope.agreementId);
													/*
													 * .then($scope.fetchPaymetUpdateSuccessCallBack,
													 * $scope.fetchPaymentUpdateErrorCallBack)
													 */
													$scope.paymentStatus = true;
													$location
															.path('/dashboard');
												},
												function() {
													$scope.paymentStatus = false;

												});
							};
						} ]);
