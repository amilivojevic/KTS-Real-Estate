/**
 * Created by Korisnik on 6/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('OwnerController', ownerController);

    // owner controller
    function ownerController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;
        vm.getAllMyAdvertisements = getAllMyAdvertisements;
        vm.eraseAdvertisement = eraseAdvertisement;

        getAllMyAdvertisements();


        vm.userData = angular.fromJson($window.localStorage['loggedUser']);
        console.log("vm.userData = " + JSON.stringify(vm.userData));
        vm.modify = function () {
            $window.location = "#!/owner_modify";
        }


        function getAllMyAdvertisements() {

            $http.get('/api/users/owner/getAllMyAdvertisements')
                .then(function(response) {
                    console.log("Svi moji oglasi: " + JSON.stringify(response.data));
                    vm.allMyAdvertisements = response.data;
                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function eraseAdvertisement(id){
            if (confirm("Are you sure you want to erase this advertisement: " + id + "?") == true) {

                $http.get('/api/advertisement/erase/'+ id)
                    .then(function(response) {


                        getAllAdvertisements();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }
    }


})();