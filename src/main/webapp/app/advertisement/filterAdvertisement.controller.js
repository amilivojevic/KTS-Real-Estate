/**
 * Created by Nina on 15-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('FilterAdvertisementController', filterAdvertisementController);


    function filterAdvertisementController($http, $scope, $cookies, $window,FilterService) {

        var vm = this;
        vm.getAllListings = getAllListings;
        vm.previousFilter = FilterService.getFilter();

        getAllFiltrated();


        vm.setFilter = function () {
//fileter!! : {"type":"All contracts","location":"","minPrice":10000,"maxPrice":10000,"currency":"All currencies"}

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

            console.log("fileter1!! : " + vm.filterConteiners.f1);
            console.log("fileter2!! : " + vm.filterConteiners.f2);
            console.log("fileter3!! : " + vm.filterConteiners.f3);
            console.log("fileter4!! : " + vm.filterConteiners.f4);
            console.log("fileter5!! : " + vm.filterConteiners.f5);
            console.log("fileter6!! : " + vm.filterConteiners.f6);
            console.log("fileter7!! : " + vm.filterConteiners.f7);
            console.log("fileter8!! : " + vm.filterConteiners.f8);
            console.log("fileter9!! : " + vm.filterConteiners.f9);
            console.log("fileter10!! : " + vm.filterConteiners.f10);
            console.log("fileter11!! : " + vm.filterConteiners.f11);


            $window.location.href = "http://" + $window.location.host + "/#!/filterAdvertisement";


        }





        function getAllFiltrated() {


            /*
            vm.filterConteiners = {};

            $http.get('/api/advertisement/getAllListings')
                .then(function(response) {

                    console.log("all advertisements: " + angular.toJson(response.data));
                    vm.allListings =  response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });

            $http.post('/api/advertisement/filterListings', vm.filterConteiners).then(function (response) {



            },function(response){
                alert("Check you email and activate your account!");
            });
            */
        }
    }
})();