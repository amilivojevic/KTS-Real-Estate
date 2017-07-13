/**
 * Created by Sandra on 7/13/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('AdminController', adminController);

    // owner controller
    function adminController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;
        //$scope.userData = $window.localStorage.getItem("loggedUser");

        vm.userData = angular.fromJson($window.localStorage['loggedUser']);

        vm.modify = function () {
            $window.location = "#!/admin_modify";
        }





    }
})();