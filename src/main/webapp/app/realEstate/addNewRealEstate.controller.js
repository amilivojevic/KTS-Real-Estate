/**
 * Created by Nina on 28-Jun-17.
 */
(function () {

    angular.module("realEstateApp")
        .controller('AddNewRealEstateController',AddNewRealEstateController);

    /*    RegisterController.$inject = ['LocationDataService'];*/

    //register page controller
    function AddNewRealEstateController($http, $scope, $window, CompanyService) {

        var vm = this;
        vm.addNewRealEstate = addNewRealEstate;
        vm.types = ['Apartment', 'House', 'Office', 'Garage'];
        vm.typesOfHeating = ['None', 'Central', 'Fireplace', 'Solar'];





        //method for user registration
        function addNewRealEstate () {
            console.log("USAO U REGISTER FUNKCIJU");
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

            console.log("novi korisnik: " + JSON.stringify(vm.newUser) )

            $http.post('/api/realEstate/addNewRealEstate', vm.new_real_estate).then(function (response) {

              /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });


        }



    }
})();