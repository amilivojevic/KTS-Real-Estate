/**
 * Created by Nina on 28-Jun-17.
 */
/**
 * Created by Korisnik on 6/11/2017.
 */
(function () {

    angular.module("realEstateApp")
        .controller('AddNewRealEstateController',AddNewRealEstateController);

    AddNewRealEstateController.$inject = ['LocationDataService'];

    //register page controller
    function AddNewRealEstateController($http, $scope, $window, LocationDataService) {

        var vm = this;
        vm.types = ['Apartment', 'House', 'Office', 'Garage'];
        vm.addNewRealEstate = addNewRealEstate;


      /*  findAllLocations(); */


     /*   function findAllLocations() {
         vm.locations = ['novi sad', 'bg', 'zr'];
         LocationDataService.findAll()
         .then(function (data) {
         //console.log(JSON.stringify(data.data));
         vm.locations = data.data;
         });
         }*/


  /*      $scope.redirect = function(){
            $window.location.href = "http://" + $window.location.host + "/#!/login";

        } */

        //method for user registration
        function addNewRealEstate () {
            vm.new_real_estate = {
                description: vm.newRE.description,
                imageUrl: 'images/img1.jpg',
                furniture: vm.newRE.furniture,
                parking: vm.newRE.parking,
                area: vm.newRE.area,
                constructionYear: vm.newRE.constructionYear,
                roomsNumber: vm.newRE.roomsNumber,
                bathroomsNumber: vm.newRE.bathroomsNumber,
                heatingType: vm.newRE.heatingType,
                re_type: vm.newRE.REType
            }


            console.log("novi re: " + JSON.stringify(vm.newRE) )


            switch(vm.new_real_estate.type) {
                case "APARTMENT":
                    type = "APARTMENT";
                    break;
                case "HOUSE":
                    type = "HOUSE";
                    break;
                case "OFFICE":
                    type = "OFFICE";
                    break;
                case "GARAGE":
                    type = "GARAGE"
                    break;
            }


            vm.new_real_estate.type = type;

            $http.post('/api/realEstate/'+type+'/addNewRealEstate', vm.new_user).then(function (response) {
                /*                if (response) {
                 $scope.loginCtrl.login(userData);
                 }*/
                $scope.redirect();

            },function(response){
                alert(response.data.response);
            });

        }



    }
})();