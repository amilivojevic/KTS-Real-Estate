/**
 * Created by Korisnik on 6/3/2017.
 */
angular
    .module('realEstateApp', [
        'ngResource',
        'ngRoute',
        'ngCookies',
        'restangular',
        'ui.bootstrap',
        'lodash'
    ])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', {
                //templateUrl: '/app/static/new_home.html',
                templateUrl: '/app/static/new_login.html',
                controller: "LoginController",
                controllerAs: "loginCtrl"
            })
/*            .when('/login', {
                templateUrl: '/app/auth/login/new_login.html',
                controller: "loginController",
                controllerAs: "loginCtrl"

            })*/
            .when('/register', {
                templateUrl: '/app/auth/register/new_register.html',
                controller: "RegisterController",
                controllerAs: "registerCtrl"
            })
/*            .when('/profile', {
                templateUrl: '/app/auth/register/new_profile.html',
                controller: "ProfileController",
                controllerAs: "profileCtrl"
            })*/
            .otherwise({
                redirectTo: '/'
            });
    }])
    .run(['Restangular', '$log', function(Restangular, $log) {
        Restangular.setBaseUrl('realEstate');
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true;
            }
            return true;
        });

    }]);