realEstateApp.controller('registerController', function ($scope, $http, $state, $stateParams, authService) {
    $scope.user = {};

    /*
    $scope.toLogin = function () {
        $state.transitionTo('login');
    };
    */

    $scope.register = function () {
        if ($scope.registerForm.$valid) {
            authService.register($scope.user, function (data) {
                authService.setUser(data);
                $state.transitionTo("/home");
            }, function () {
                alert('Registracija nije uspela! Proverite da li ste ispravno uneli sve parametre forme.');
            });
        }
    };
});