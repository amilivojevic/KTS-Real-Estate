/**
 * Created by Sandra on 7/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('EraseOwnerController', eraseOwnerController);


    function eraseOwnerController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllOwners = getAllOwners;

        getAllOwners();
        function getAllOwners() {

            $http.get('/api/users/owner/getAll')
                .then(function(response) {
                    console.log("all users: " + angular.toJson(response.data));
                    vm.allOwners =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });

            //return ["pera","zika", "mika"];
        }


    }
})();