/**
 * Created by Korisnik on 6/3/2017.
 */


(function(angular) {
    'use strict';
    angular
        .module('realEstateApp', [
            'ngResource',
            'ngRoute',
            'ngCookies',
            'restangular'
        ])
        .config(function($routeProvider, $httpProvider){
            $routeProvider
                .when('/', {
                    templateUrl: 'app/static/notlogged_home.html'

                })
                .when('/login', {
                    templateUrl: 'app/auth/login/login.html',
                    controller: 'LoginController',
                    controllerAs: 'loginCtrl'
                })
                .when('/register', {
                    templateUrl: 'app/auth/register/register.html',
                    controller: 'RegisterController',
                    controllerAs: 'registerCtrl'
                })
                .when('/profile', {
                    templateUrl: 'app/user/owner/owner_profile.html',
                    controller: 'OwnerController',
                    controllerAs: 'ownerCtrl',
                })
                .when('/listings', {
                    templateUrl: 'app/static/listings.html',
                })
                .when('/about', {
                    templateUrl: 'app/static/about.html',
                })
                .when('/contact', {
                    templateUrl: 'app/static/contact.html',
                })
                .when('/modifyOwner', {
                    templateUrl: 'app/user/owner/owner_modify.html',
                    controller: 'OwnerModifyController',
                    controllerAs: 'ownerModifyCtrl'
                })
                .when('/addNewRealEstate', {
                    templateUrl: 'app/realEstate/addNewRealEstate.html',
                    controller: 'AddNewRealEstateController',
                    controllerAs: 'addNewRealEstateCtrl'
                })
                .otherwise({
                    redirectTo: '/'
                });

            $httpProvider
                .interceptors.push(['$q', '$window',
                function($q, $window) {
                    return {
                        'request': function(config) {
                            config.headers = config.headers || {};
                            if($window.localStorage.token) {
                                config.headers["X-Auth-Token"] = $window.localStorage.token;
                            }
                            return config;
                        },
                        'responseError': function(response) {
                            if (response.status === 401 || response.status === 403) {

                                console.log("nesto kod interseptora je pogresno");
                            }
                            return $q.reject(response);
                        }
                    };
                }
            ]);

    })


})(angular);
