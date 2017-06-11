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
            new_user = {
                role : vm.newUser.userType,
                username: vm.newUser.username,
                password: vm.newUser.password,
                name: vm.newUser.name,
                surname: vm.newUser.surname,
                birthdate: vm.newUser.birthdate,
                phoneNumber: vm.newUser.phoneNumber,
                address: vm.newUser.location,
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
            if(vm.pass != vm.repeatpass){
                alert("Passwords must match!");
                return;
            }
            var userData = {
                "username": vm.username, "password": vm.pass, "email": vm.email,
                "firstName": vm.firstName, "lastName": vm.lastName
            };
            //get radio option

            $http.post('/api/users/register/'+vm.type, userData).then(function (response) {
                if (response) {
                    $scope.loginCtrl.login(userData);
                }
            },function(response){
                alert(response.data.response);
            });
        }
    }
})();