/**
 * Created by Sandra on 7/15/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('ApproveCompanyController', approveCompanyController);


    function approveCompanyController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllUnapprovedCompanies = getAllUnapprovedCompanies;
        vm.approve = approve;

        getAllUnapprovedCompanies();

        function getAllUnapprovedCompanies() {
            $http.get('/api/users/company/all/unapproved')
                .then(function(response) {
                    console.log("unapproved companies: " + angular.toJson(response.data));
                    vm.allUnapprovedCompanies =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function approve(username){
            if (confirm("Are you sure you want to approve company: " + username + "?") == true) {

                $http.get('/api/users/company/approve/'+ username)
                    .then(function(response) {


                        getAllUnapprovedCompanies();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }

    }
})();