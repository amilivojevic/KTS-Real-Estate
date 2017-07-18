/**
 * Created by Sandra on 7/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('EraseOwnerController', eraseOwnerController);


    function eraseOwnerController($http, $scope, $cookies, $window) {

        var vm = this;
        //vm.getAllUnapprovedCompanies = getAllUnapprovedCompanies;
        vm.erase = erase;

        //getAllUnapprovedCompanies();
        getAllOwners();

        function getAllOwners() {
            $http.get('/api/users/owner/getAll')
                .then(function(response) {
                    console.log("all users: " + angular.toJson(response.data));
                    vm.allOwners =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function erase(username){
            if (confirm("Are you sure you want to erase user: " + username + "?") == true) {

                $http.get('/api/users/owner/erase/'+ username)
                    .then(function(response) {

                        getAllOwners()
                        //getAllUnapprovedCompanies();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }

    }
})();