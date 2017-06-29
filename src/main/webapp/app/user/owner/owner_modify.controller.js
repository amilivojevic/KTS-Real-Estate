/**
 * Created by Nina on 20-Jun-17.
 */

(function() {
    angular.module("realEstateApp")
        .controller('OwnerModifyController', ownerModifyController);

    // owner controller
    function ownerModifyController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.modifyOwner = modifyOwner;
        // redirektovati na login page ako nije ulogovan
        if ($cookies.get("token") === undefined)
            $window.location = "#/login";

        $scope.redirect = function(){
     /*       $window.location.href = "http://" + $window.location.host + "/#!/modifyOwner";*/

        }

        // ako jeste ulogovan dobavi podatke
        $http.get("/api/users/data", {headers : {'X-Auth-Token' : $cookies.get("token")}}).then(function(response) {
            // if status is ok - save user data to cookie
            $cookies.putObject('userdata', response.data);
            console.log(response.data);
            vm.userData = $cookies.getObject('userdata');
        }, function(error) {
            // log error response and maybe send it to
            // error monitor app
            console.error("Error ocurred: " + response.status);
        });


        function modifyOwner () {
            vm.owner_modified = {
                username: vm.ovnerModified.username,
                password: vm.ovnerModified.password,
                email: vm.ovnerModified.email,
                name: vm.ovnerModified.name,
                surname: vm.ovnerModified.surname,
                birthDate: vm.ovnerModified.birthdate,
                phoneNumber: vm.ovnerModified.phoneNumber,
                address: vm.ovnerModified.address,
                city: vm.ovnerModified.city,
                country: vm.ovnerModified.country,
                accountNumber: vm.ovnerModified.accountNumber,
                imageUrl: 'images/img1.jpg'

            }

            console.log("novi korisnik: " + JSON.stringify(vm.ovnerModified))

            $http.post('/api/users/'+type+'/modifyOwner', vm.new_user).then(function (response) {
                /*                if (response) {
                 $scope.loginCtrl.login(userData);
                 }*/
                $scope.redirect();

            },function(response){
                alert(response.data.response);
            });
        }
    }
})();