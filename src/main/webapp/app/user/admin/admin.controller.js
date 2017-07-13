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

        getLoggedUserData();

        function getLoggedUserData() {
            var promise = LoginFactory.getLoggedUserData(vm.token);
            promise.then(
                function(loggedUser) {
                    $window.localStorage.setItem("loggedUser", loggedUser);
                    vm.userData = loggedUser;
                    console.log("$scope.userData = " +JSON.stringify(vm.userData));
                }
            );
        };




    }
})();