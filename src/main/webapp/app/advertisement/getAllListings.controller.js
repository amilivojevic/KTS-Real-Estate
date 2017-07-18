/**
 * Created by Nina on 15-Jul-17.
 */

(function() {
    angular.module("realEstateApp")
        .controller('GetAllListingsController', getAllListingsController);


    function getAllListingsController($http, $scope, $cookies, $window, FilterService) {

        var vm = this;
        vm.getAllListings = getAllListings;

        vm.setFilter = function () {

            console.log("fileter!! : " + JSON.stringify(vm.filterConteiners));
            FilterService.setFilter(vm.filterConteiners);

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


            $http.get('/api/advertisement/getAllAcceptedListings')
                .then(function(response) {

                    //console.log("all advertisements: " + angular.toJson(response.data));
                    vm.allListings =  response.data;

                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }
    }
})();