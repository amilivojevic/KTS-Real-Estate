/**
 * Created by Nina on 13-Jul-17.
 */

(function () {

    angular.module("realEstateApp")
        .controller('verificationController',VerificationController);


    function VerificationController($http, $scope, $window, authorizationService) {

        var vm = this;
        vm.verifie = verifie;

        $scope.redirect = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/login";

        }

        $http.post('/api/verify/', vm.new_user).then(function (response) {
            $scope.redirect();

        },function(response){
            alert("Error during verification of profile!");
        });

    }
})();