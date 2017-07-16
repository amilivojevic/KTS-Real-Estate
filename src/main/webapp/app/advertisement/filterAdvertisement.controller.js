/**
 * Created by Nina on 15-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('FilterAdvertisementController', filterAdvertisementController);


    function filterAdvertisementController($http, $scope, $cookies, $window,FilterService) {

        var vm = this;
        vm.getAllFiltrated = getAllFiltrated;
        vm.previousFilter = FilterService.getFilter();
        vm.setFilter = setFilter;

        getAllFiltrated();


        function setFilter() {
//fileter!! : {"type":"All contracts","location":"","minPrice":10000,"maxPrice":10000,"currency":"All currencies"}

/*

            if(vm.filterConteiners.type == null){
                vm.filterConteiners.type = "All contracts";
            }
            if(vm.filterConteiners.location == undefined){
                vm.filterConteiners.location = "";
            }
            if(vm.filterConteiners.minPrice == undefined){
                vm.filterConteiners.minPrice = 10000;
            }
            if(vm.filterConteiners.maxPrice == undefined){
                vm.filterConteiners.maxPrice = 10000;
            }
            if(vm.filterConteiners.currency == undefined){
                vm.filterConteiners.currency = "All currencies";
            }
            if(vm.filterConteiners.area == undefined){
                vm.filterConteiners.area = "50";
            }
            if(vm.filterConteiners.roomsNu == undefined){
                vm.filterConteiners.roomsNu = 2;
            }
            if(vm.filterConteiners.bathroomsNu == undefined){
                vm.filterConteiners.bathroomsNu = 2;
            }
            if(vm.filterConteiners.heatingType == undefined){
                vm.filterConteiners.heatingType = "All heating types";
            }

            if(vm.filterConteiners.furniture == undefined){
                vm.filterConteiners.furniture = false;
            }
            if(vm.filterConteiners.parking == undefined){
                vm.filterConteiners.parking = false;
            }

*/

            console.log("fileter: " + JSON.stringify(vm.filterConteiners));
            $http.post("/api/advertisement/filterListings",vm.filterConteiners)
                .then(function(response) {


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });


        }





        function getAllFiltrated() {



        }
    }
})();