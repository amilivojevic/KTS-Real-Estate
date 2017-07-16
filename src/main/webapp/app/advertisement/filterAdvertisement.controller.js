/**
 * Created by Nina on 15-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('FilterAdvertisementController', filterAdvertisementController);


    function filterAdvertisementController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllListings = getAllListings;


        vm.setFilter = function () {


            if(vm.filterConteiners.f1 == null){
                vm.filterConteiners.f1 = "All contracts";
            }
            if(vm.filterConteiners.f2 == undefined){
                vm.filterConteiners.f2 = "";
            }
            if(vm.filterConteiners.f3 == undefined){
                vm.filterConteiners.f3 = 10000;
            }
            if(vm.filterConteiners.f4 == undefined){
                vm.filterConteiners.f4 = 10000;
            }
            if(vm.filterConteiners.f5 == undefined){
                vm.filterConteiners.f5 = "All currencies";
            }
            if(vm.filterConteiners.f6 == undefined){
                vm.filterConteiners.f6 = "50";
            }
            if(vm.filterConteiners.f7 == undefined){
                vm.filterConteiners.f7 = 2;
            }
            if(vm.filterConteiners.f8 == undefined){
                vm.filterConteiners.f8 = 2;
            }
            if(vm.filterConteiners.f9 == undefined){
                vm.filterConteiners.f9 = "All heating types";
            }


            if(vm.filterConteiners.f10 == undefined){
                vm.filterConteiners.f10 = false;
            }
            if(vm.filterConteiners.f11 == undefined){
                vm.filterConteiners.f11 = false;
            }





            if(vm.filterConteiners.f11 == undefined){
                vm.filterConteiners.f11 = false;
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

        getAllListings();



        function getAllListings() {


            vm.filterConteiners = {};

            $(function () {
                $("#slider").responsiveSlides({
                    auto: true,
                    nav: true,
                    speed: 500,
                    namespace: "callbacks",
                    pager: true,
                });
            });

            $(document).ready(function () {
                $('#horizontalTab').easyResponsiveTabs({
                    type: 'default', //Types: default, vertical, accordion
                    width: 'auto', //auto or any width like 600px
                    fit: true   // 100% fit in a container
                });
            });


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
        }
    }
})();