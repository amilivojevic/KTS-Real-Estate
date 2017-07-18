/**
 * Created by Nina on 15-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('FilterAdvertisementController', filterAdvertisementController);


    function filterAdvertisementController($http, $scope, $cookies, $window,FilterService) {

        var vm = this;
        vm.previousFilter = FilterService.getFilter();
        vm.filtratedAdvertisements = [];
        vm.setFilter = setFilter;
        vm.filterConteiners = {};
        vm.filterConteiners.currency="EUR";
        vm.buy = buy;

        getAllListings();

        function buy(id){
            $http.get('/api/advertisement/buy/' + id)
                .then(function(response) {
                    console.log("prodato");
                    getAllListings();
                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }


        function setFilter() {

            console.log("fileter: " + JSON.stringify(vm.filterConteiners));
            $http.post("/api/advertisement/filterListings",vm.filterConteiners)
                .then(function(response) {
                    console.log("*** filtrirani: " + JSON.stringify(response.data));
                    vm.filtratedAdvertisements = response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

        function getAllListings() {

            $http.get('/api/advertisement/getAllAcceptedListings')
                .then(function(response) {

                    vm.filtratedAdvertisements =  response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }

    }
})();