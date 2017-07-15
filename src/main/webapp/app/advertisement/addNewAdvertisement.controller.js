/**
 * Created by Nina on 14-Jul-17.
 */

(function () {

    angular.module("realEstateApp")
        .controller('AddNewAdvertisementController',AddNewAdvertisementController);


    //register page controller
    function AddNewAdvertisementController($http, $scope, $window, NewAdvertisementService) {

        var vm = this;
        vm.addNewAdvertisement = addNewAdvertisement;
        vm.types = ['Rent', 'Sale'];
        vm.currency = ['EUR', 'RSD', 'USD'];

        //method for user registration
        function addNewAdvertisement () {
            var id = NewAdvertisementService.setRealEstateId();
            console.log("USAO U dodaj novi add FUNKCIJU");
            vm.new_advertisement = {
                title : vm.newAd.title,
                price : parseFloat(vm.newAd.price),
                endingDate : vm.newAd.endingDate,
                phoneNumber : vm.newAd.phoneNumber,
                type : vm.newAd.type,
                ////id od nekretnine!!!
                id : id,
                currency : vm.newAd.currency,
                realEstateId : id
            }

            $window.location.href = "http://" + $window.location.host + "/#!/profile";

            $http.post('/api/advertisement/addNewAdvertisement', vm.new_advertisement).then(function (response) {

              /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });
        }
    }
})();