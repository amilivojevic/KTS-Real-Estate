/**
 * Created by Korisnik on 6/3/2017.
 */
angular
    .module('realEstateApp', [
        'ngResource',
        'ngRoute',
        'ngCookies',
        'restangular'
    ])
    .config(function($routeProvider){
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
            })
            .otherwise({
                redirectTo: '/'
            });

});