/**
 * Created by Sandra on 7/15/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('ApprovePrivateAccountController', approvePrivateAccountController);


    function approvePrivateAccountController($http) {

        var vm = this;
        vm.getAllUnapproved = getAllUnapproved;
        vm.approve = approve;

        getAllUnapproved();

        function getAllUnapproved() {
            $http.get('/api/users/private_acc/all/unapproved')
                .then(function(response) {
                    console.log("unapproved private accounts: " + angular.toJson(response.data));
                    vm.allUnapproved =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function approve(id,username){
            console.log("id ovog garija: " + id);
            if (confirm("Are you sure you want to approve company: " + username + "?") == true) {

                $http.get('/api/users/private_acc/approve/'+ id)
                    .then(function(response) {


                        getAllUnapproved();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }

    }
})();