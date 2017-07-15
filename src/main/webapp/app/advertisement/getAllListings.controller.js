/**
 * Created by Nina on 15-Jul-17.
 */
/**
 * Created by Nina on 28-Jun-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('GetAllListingsController', getAllListingsController);


    function getAllListingsController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllListings = getAllListings;

        vm.setFilter = function () {
            console.log("fileter1!! : " + vm.filterConteiners.f1);
            console.log("fileter2!! : " + vm.filterConteiners.f2);
            console.log("fileter3!! : " + vm.filterConteiners.f3);
            console.log("fileter4!! : " + vm.filterConteiners.f4);
            console.log("fileter5!! : " + vm.filterConteiners.f5);

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
        }
    }
})();