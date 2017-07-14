/**
 * Created by Sandra on 7/14/2017.
 */
(function () {

    angular.module("realEstateApp")
        .controller('AddAdminController',addAdminController);


    function addAdminController($http, $scope, $window) {

        var vm = this;
        vm.add = add;
        vm.newUser = {};
        vm.newUser.password = "default";
        vm.newUser.repeatpass = "default";
        function add () {
            console.log("USAO U ADD FUNKCIJU");
            vm.new_user = {
                role : "admin",
                username: vm.newUser.username,
                password: vm.newUser.password,
                email: vm.newUser.email,
                name: vm.newUser.name,
                surname: vm.newUser.surname,
                birthDate: vm.newUser.birthdate,
                phoneNumber: vm.newUser.phoneNumber,
                address: vm.newUser.address,
                city: vm.newUser.city,
                country: vm.newUser.country,
                accountNumber: vm.newUser.accountNumber,
                imageUrl: 'images/img1.jpg'

            }

            console.log("novi korisnik: " + JSON.stringify(vm.newUser) )

            if(vm.newUser.password != vm.newUser.repeatpass){
                alert("Passwords must match!");
                return;
            }



            $http.post('/api/users/admin/register', vm.new_user).then(function (response) {

                alert("New administrator " + vm.new_user.username + " created!");
                $window.location.href = "http://" + $window.location.host + "/#!/admin";
            },function(response){
                alert("Check you email and activate your account!");
            });


        }


    }
})();