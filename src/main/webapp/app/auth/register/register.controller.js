/**
 * Created by Korisnik on 6/11/2017.
 */
(function () {

    angular.module("realEstateApp")
        .controller('RegisterController',RegisterController);

/*    RegisterController.$inject = ['LocationDataService'];*/

    //register page controller
    function RegisterController($http, $scope,LocationDataService) {

        var vm = this;
        vm.register = register;
        vm.types = ['Owner', 'Company', 'Private Acc in Company'];
        vm.test = test;
        findAllLocations();

        function test(){
            vm.new_user = {
                role : vm.newUser.userType,
                username: vm.newUser.username,
                password: vm.newUser.password,
                email: vm.newUser.email,
                name: vm.newUser.name,
                surname: vm.newUser.surname,
                birthdate: vm.newUser.birthdate,
                phoneNumber: vm.newUser.phoneNumber,
                addressId: vm.newUser.location,
                accountNumber: vm.newUser.accountNumber,
                imageUrl: 'images/img1.jpg'

            }
            console.log("novi korisnik: " + JSON.stringify(vm.newUser) )
        }

        function findAllLocations() {
            /*vm.locations = ['novi sad', 'bg', 'zr'];*/
            LocationDataService.findAll()
                .then(function (data) {
                    console.log(JSON.stringify(data.data));
                    vm.locations = data.data;
                });
        }

        //method for user registration
        function register () {
            vm.new_user = {
                role : vm.newUser.userType,
                username: vm.newUser.username,
                password: vm.newUser.password,
                email: vm.newUser.email,
                name: vm.newUser.name,
                surname: vm.newUser.surname,
                birthDate: vm.newUser.birthdate,
                phoneNumber: vm.newUser.phoneNumber,
                addressId: vm.newUser.location,
                accountNumber: vm.newUser.accountNumber,
                imageUrl: 'images/img1.jpg'

            }

            console.log("novi korisnik: " + JSON.stringify(vm.newUser) )

            if(vm.newUser.password != vm.newUser.repeatpass){
                alert("Passwords must match!");
                return;
            }
            var role = "owner";

            switch(vm.new_user.role) {
                case "Owner":
                    role = "owner";
                    break;
                case "Company":
                    role = "company";
                    break;
                case "Private Acc in Company":
                    role = "privateAcc";
                    break;
            }

            $http.post('/api/users/'+role + '/register', vm.new_user).then(function (response) {
/*                if (response) {
                    $scope.loginCtrl.login(userData);
                }*/
            },function(response){
                alert(response.data.response);
            });
        }
    }
})();