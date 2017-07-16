/**
 * Created by Nina on 15-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('SingleAdvertisementController', singleAdvertisementController);


    function singleAdvertisementController($http, $scope, $cookies, $window, SingleAdvertisementService) {

        var vm = this;
        vm.getSingleAdvertisement = getSingleAdvertisement;


        var id = SingleAdvertisementService.getRealEstateId();

        getSingleAdvertisement();


        function getSingleAdvertisement() {

            $http.get('/api/advertisement/getSingleAdvertisement/' + id)
                .then(function(response) {

                    console.log("single advertisement: " + response.data);
                    vm.singleAdvertisement =  response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }
    }
})();