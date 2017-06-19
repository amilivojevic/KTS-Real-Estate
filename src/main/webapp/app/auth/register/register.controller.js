/**
 * Created by Korisnik on 6/11/2017.
 */
(function () {

    angular.module("realEstateApp")
        .controller('RegisterController',RegisterController);

/*    RegisterController.$inject = ['LocationDataService'];*/

    //register page controller
    function RegisterController($http, $scope, CompanyService) {

        var vm = this;
        vm.register = register;
        vm.types = ['Owner', 'Company', 'Private Acc in Company'];

     /*   findAllLocations();*/
        findAllCompanies();

        /*function findAllLocations() {
            /*vm.locations = ['novi sad', 'bg', 'zr'];/*
            LocationDataService.findAll()
                .then(function (data) {
                    //console.log(JSON.stringify(data.data));
                    vm.locations = data.data;
                });
        }*/

        function findAllCompanies(){
            CompanyService.findAll()
                .then(function (data) {
                    console.log(JSON.stringify(data.data));
                    vm.companies = data.data;
                });
        }

        //method for user registration
        function register () {
            vm.new_user = {
                type : vm.newUser.userType,
                role : "owner",
                username: vm.newUser.username,
                password: vm.newUser.password,
                email: vm.newUser.email,
                name: vm.newUser.name,
                surname: vm.newUser.surname,
                birthDate: vm.newUser.birthdate,
                phoneNumber: vm.newUser.phoneNumber,
                address: vm.newUser.address,
                city: vm.newUser.city,
                country: vm.newUser.country,
                accountNumber: vm.newUser.accountNumber,
                imageUrl: 'images/img1.jpg'

            }

            console.log("novi korisnik: " + JSON.stringify(vm.newUser) )

            if(vm.newUser.password != vm.newUser.repeatpass){
                alert("Passwords must match!");
                return;
            }
            var type = "owner";

            switch(vm.new_user.type) {
                case "Owner":
                    type = "owner";
                    break;
                case "Company":
                    type = "company";
                    vm.new_user.pib = vm.newUser.pib;
                    vm.new_user.site = vm.newUser.site;
                    vm.new_user.fax = vm.newUser.fax;
                    break;
                case "Private Acc in Company":
                    type = "privateAcc";
                    break;
            }
            vm.new_user.type = type;


            $http.post('/api/users/'+type+'/register', vm.new_user).then(function (response) {
/*                if (response) {
                    $scope.loginCtrl.login(userData);
                }*/

            },function(response){
                alert(response.data.response);
            });

        }


    }
})();