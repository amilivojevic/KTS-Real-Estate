/**
 * Created by Korisnik on 6/14/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('OwnerController', ownerController);

    // owner controller
    function ownerController($http, $scope, $cookies, $window,LoginFactory) {
        var vm = this;
        vm.getAllAdvertisements = getAllAdvertisements;
        vm.eraseAdvertisement = eraseAdvertisement;

        getAllAdvertisements();


        vm.userData = angular.fromJson($window.localStorage['loggedUser']);
        console.log("vm.userData = " + JSON.stringify(vm.userData));
        vm.modify = function () {
            $window.location = "#!/owner_modify";
        }


        function getAllAdvertisements() {

            $http.get('/api/users/owner/getAllAdvertisements')
                .then(function(response) {


                    vm.allAdvertisements = [];
                    var ulogovani = angular.fromJson($window.localStorage['loggedUser']);
                    console.log("Ulogovani: " + JSON.stringify(ulogovani));
                    for (var i=0; i<response.data.length; i++) {
                        console.log("++advertisement: " + JSON.stringify(response.data[i]));
                        console.log("response.data[i].owner.username = "+response.data[i].owner.username );
                        console.log("ulogovani.username = " + ulogovani.username);
                        if ( new String(response.data[i].owner.username).valueOf() == new String(ulogovani.username).valueOf()){
                            vm.allAdvertisements.push(response.data[i]);

                        }
                    }

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