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
                templateUrl: 'app/static/new_home.html'

            })
            .when('/login', {
                templateUrl: 'app/auth/login/new_login.html',
                controller: 'LoginController',
                controllerAs: 'loginCtrl'
            })
            .when('/register', {
                templateUrl: 'app/auth/register/new_register.html',
                controller: 'RegisterController',
                controllerAs: 'registerCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });

});