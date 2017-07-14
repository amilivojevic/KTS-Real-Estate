/**
 * Created by Sandra on 7/13/2017.
 */

(function() {
    angular.module("realEstateApp")
        .controller('VerifyerController', verifyerController);

    // owner controller
    function verifyerController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;
        //$scope.userData = $window.localStorage.getItem("loggedUser");

        vm.userData = angular.fromJson($window.localStorage['loggedUser']);
        console.log("vm.userData = " + JSON.stringify(vm.userData));
        vm.modify = function () {
            $window.location = "#!/verifyer_modify";
        }

    }
})();