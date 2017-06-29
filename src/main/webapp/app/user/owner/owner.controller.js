/**
 * Created by Korisnik on 6/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('OwnerController', ownerController);

    // owner controller
    function ownerController($http, $scope, $cookies, $window) {

        var vm = this;

        // redirektovati na login page ako nije ulogovan
        if ($cookies.get("token") === undefined)
            $window.location = "#/login";

        $scope.redirect = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/modifyOwner";

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

        userData.birthDate = new Date(userData.birthDate);

        $scope.modifyUserInfo = function () {
            $http.post('/api/users/modifyOwner', $scope.userDS).success(function (data) {
                authorizationService.removeUser();
                authorizationService.setUser(data);

            }).error(function () {
                console.log("Modifikacija nije uspela. Proverite da li ste pravilno uneli sve parametre forme.");
            });
        };

        $scope.cancel = function () {
            $http.get('/api/bartender/one/' + $scope.user.id).success(function (data) {
                $scope.user = data;
                authorizationService.setUser(data);
            })
        };
    }
})();