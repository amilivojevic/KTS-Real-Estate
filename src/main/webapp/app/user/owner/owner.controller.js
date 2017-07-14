/**
 * Created by Korisnik on 6/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('OwnerController', ownerController);

    // owner controller
    function ownerController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;

        vm.userData = angular.fromJson($window.localStorage['loggedUser']);
        console.log("vm.userData = " + JSON.stringify(vm.userData));
        vm.modify = function () {
            $window.location = "#!/owner_modify";
        }


    }
})();