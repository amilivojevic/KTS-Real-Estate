/**
 * Created by Nina on 28-Jun-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('GetAllRealEstatesController', getAllRealEstatesController);


    function getAllRealEstatesController($http, $scope, $cookies, $window,NewAdvertisementService) {

        var vm = this;
        vm.eraseRealEstate = eraseRealEstate;
        vm.getAllRealEstates = getAllRealEstates;

        getAllRealEstates();

        vm.redirect = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/add_new_real_estate";

        }

        vm.redirect2 = function(id){
            NewAdvertisementService.setRealEstateId(id);
            $window.location.href = "http://" + $window.location.host + "/#!/add_new_advertisement";

        }



        function getAllRealEstates() {

            $http.get('/api/realEstate/getAllMyRealEstates')
                .then(function(response) {

                    vm.allMyRealEstates = response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function eraseRealEstate(id){
            if (confirm("Are you sure you want to erase this real estate: " + id + "?") == true) {

                $http.get('/api/realEstate/erase/'+ id)
                    .then(function(response) {


                        getAllRealEstates();
                    }, function(response) {
                        alert(JSON.stringify(response.data));
                    });
            }
        }
    }
})();