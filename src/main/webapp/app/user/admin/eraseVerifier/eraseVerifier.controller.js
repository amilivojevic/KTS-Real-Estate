/**
 * Created by Sandra on 7/15/2017.
 */
/**
 * Created by Sandra on 7/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('EraseVerifierController', eraseVerifierController);


    function eraseVerifierController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllVerifiers = getAllVerifiers;
        vm.erase = erase;

        getAllVerifiers();

        function getAllVerifiers() {
            $http.get('/api/users/verifier/getAll')
                .then(function(response) {
                    console.log("all verifiers: " + angular.toJson(response.data));
                    vm.allVerifiers =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function erase(username){
            if (confirm("Are you sure you want to erase user: " + username + "?") == true) {

                $http.get('/api/users/verifier/erase/'+ username)
                    .then(function(response) {


                        getAllVerifiers();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }

    }
})();