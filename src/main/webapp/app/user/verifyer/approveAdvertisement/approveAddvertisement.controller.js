/**
 * Created by Sandra on 7/16/2017.
 */
(function() {
    angular.module("realEstateApp")
        .controller('ApproveAdvertisementController', approveAdvertisementController);

    function approveAdvertisementController($http, $scope, $cookies, $window, SingleAdvertisementService) {
        var vm = this;
        vm.getAllWaitingAdvertisements = getAllWaitingAdvertisements;

        getAllWaitingAdvertisements();

        vm.redirect = function(id){
            console.log("id u redirektu" + id);
            SingleAdvertisementService.setRealEstateId(id);
            $window.location.href = "http://" + $window.location.host + "/#!/single_advertisement";

        }

        function getAllWaitingAdvertisements() {


            $http.get('/api/advertisement/getAllWaiting')
                .then(function(response) {

                    //console.log("all waiting advertisements: " + angular.toJson(response.data));
                    vm.allWaitingAdvertisements =  response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

    }


})();