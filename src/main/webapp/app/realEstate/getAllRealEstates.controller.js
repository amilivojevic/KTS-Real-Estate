/**
 * Created by Nina on 14-Jul-17.
 */
(function () {

    angular.module("realEstateApp")
        .controller('GetAllRealEstatesController',GetAllRealEstatesController);

    //register page controller
    function GetAllRealEstatesController($http, $scope, $window) {

        var vm = this;
        vm.getAllRealEstates = getAllRealEstates;
        vm.types = ['Apartment', 'House', 'Office', 'Garage'];
        vm.typesOfHeating = ['None', 'Central', 'Fireplace', 'Solar'];





        //method for user registration
        function getAllRealEstates () {

            var token = $window.localStorage.getItem("token");
            vm.new_real_estate = {


                description: vm.newRE.description,
                imageUrl: 'images/img1.jpg',
                city: vm.newRE.city,
                cityArea: vm.newRE.cityArea,
                street: vm.newRE.street,
                streetNumber: vm.newRE.streetNumber,
                state: vm.newRE.state,
                zipCode: vm.newRE.zipCode,
                furniture: vm.newRE.furniture,
                parking: vm.newRE.parking,
                area: parseFloat(vm.newRE.area),
                constructionYear: vm.newRE.constructionYear,
                roomsNumber: parseInt(vm.newRE.roomsNumber),
                bathroomsNumber: parseInt(vm.newRE.bathroomsNumber),
                heatingType: vm.newRE.heatingType,
                rs_type: vm.newRE.rs_type,
                token: token

            }



            $http.post('/api/realEstate/getAllRealEstates', vm.new_real_estate).then(function (response) {

                /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });


        }



    }
})();