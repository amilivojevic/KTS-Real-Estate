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

            $http.get('/api/realEstate/getAllRealEstates')
                .then(function(response) {
                    //console.log("all real estates: " + angular.toJson(response.data));

                    vm.allRealEstates = [];
                    var ulogovani = angular.fromJson($window.localStorage['loggedUser']);
                    console.log("Ulogovani: " + JSON.stringify(ulogovani));
                    for (var i=0; i<response.data.length; i++) {
                        console.log("++real estate: " + JSON.stringify(response.data[i]));
                        console.log("response.data[i].owner.username = "+response.data[i].owner.username );
                        console.log("ulogovani.username = " + ulogovani.username);
                        if ( new String(response.data[i].owner.username).valueOf() == new String(ulogovani.username).valueOf()){
                            vm.allRealEstates.push(response.data[i]);
                        }
                    }

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