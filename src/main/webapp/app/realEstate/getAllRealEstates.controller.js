/**
 * Created by Nina on 28-Jun-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('GetAllRealEstatesController', getAllRealEstatesController);


    function getAllRealEstatesController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.getAllRealEstates = getAllRealEstates;

        getAllRealEstates();

        vm.redirect = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/add_new_real_estate";

        }

        vm.redirect2 = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/add_new_advertisement";

        }



        function getAllRealEstates() {

            $http.get('/api/realEstate/getAllRealEstates')
                .then(function(response) {
                    console.log("all real estates: " + angular.toJson(response.data));
                    vm.allRealEstates =  response.data;


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }
    }
})();