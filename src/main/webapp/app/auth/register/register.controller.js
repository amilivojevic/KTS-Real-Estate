/**
 * Created by Korisnik on 6/11/2017.
 */
(function () {
    angular.module("realEstateApp")
        .controller('RegisterController', registerController);

    //register page controller
    function registerController($http, $scope) {

        var vm = this;
        vm.register = register;
        vm.type = "advertiser";

        //method for user registration
        function register () {
            if(vm.pass != vm.repeatpass){
                alert("Passwords must match!");
                return;
            }
            var userData = {
                "username": vm.username, "password": vm.pass, "email": vm.email,
                "firstName": vm.firstName, "lastName": vm.lastName
            };
            //get radio option

            $http.post('/api/users/register/'+vm.type, userData).then(function (response) {
                if (response) {
                    $scope.loginCtrl.login(userData);
                }
            },function(response){
                alert(response.data.response);
            });
        }
    }
})();