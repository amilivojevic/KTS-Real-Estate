/**
 * Created by Korisnik on 6/14/2017.
 */
(function() {
    angular.module("realEstateApp").
        controller('OwnerController', ownerController);

    // login page controller
    function ownerController($http, $scope, $cookies, $window) {

        var vm = this;
        // redirektovati na login page ako nije ulogovan
        if ($cookies.get("token") === undefined)
            $window.location = "#/login";

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
    }
})();