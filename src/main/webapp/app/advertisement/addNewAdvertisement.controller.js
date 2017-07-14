/**
 * Created by Nina on 14-Jul-17.
 */

(function () {

    angular.module("realEstateApp")
        .controller('AddNewAdvertisementController',AddNewAdvertisementController);


    //register page controller
    function AddNewAdvertisementController($http, $scope, $window) {

        var vm = this;
        vm.addNewRealEstate = addNewRealEstate;
        vm.types = ['Rent', 'Sell'];
        vm.currency = ['EUR', 'RSD', 'USD'];

        //method for user registration
        function addNewRealEstate () {
            console.log("USAO U dodaj novi add FUNKCIJU");
            vm.new_advertisement = {
                title : vm.newAd.title,
                price : vm.newAd.price,
                endingDate : vm.newAd.endingDate,
                phoneNumber : vm.newAd.phoneNumber,
                type : vm.newAd.type,
                id : vm.newAd.id,
                currency : vm.newAd.currency,
                token : vm.newAd.token

            }

            $http.post('/api/advertisements/'+type+'/addNewAdvertisement', vm.new_advertisement).then(function (response) {

              /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });
        }
    }
})();