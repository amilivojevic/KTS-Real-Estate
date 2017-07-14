/**
 * Created by Sandra on 7/13/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('AdminModifyController', adminModifyController);

    function adminModifyController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;
        //$scope.userData = $window.localStorage.getItem("loggedUser");

        vm.userData = angular.fromJson($window.localStorage['loggedUser']);
        
        vm.change = function () {
            vm.changed_user = {
                type : "admin",
                role : "admin",
                username: vm.userData.username,
                password: vm.userData.password,
                email: vm.userData.email,
                name: vm.userData.name,
                surname: vm.userData.surname,
                birthDate: vm.userData.birthdate,
                phoneNumber: vm.userData.phoneNumber,
                address: vm.userData.address,
                city: vm.userData.city,
                country: vm.userData.country,
                accountNumber: vm.userData.accountNumber,
                imageUrl: 'images/img1.jpg'
            }

            $http.post('/api/users/admin/modify', vm.changed_user).then(function (response) {

                //dovlace se podaci ulogovanog iz baze
                var promise = LoginFactory.getLoggedUserData(vm.token);
                promise.then(
                    function(loggedUser) {
                        console.log("ucitan u funkciji: " + JSON.stringify(loggedUser));
                        $window.localStorage['loggedUser'] = angular.toJson(loggedUser);
                        $scope.userData = loggedUser;


                    }
                );


            },function(response){
                alert(response.data.response);
            });


        }


    }
})();