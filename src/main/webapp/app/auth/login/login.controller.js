(function () {
    angular.module("realEstateApp")
        .controller('LoginController', loginController);

    //login page controller
    function loginController($http, $cookies,$window) {

        var vm = this;

        vm.loggedIn = false;
        // login and logout methods
        vm.login = login;
        vm.logout = logout;
        checkIfLogged();

        //get if there is user cookie, if so - redirect user to profile page (#profile)
        function checkIfLogged(){
            if($cookies.get("token") != undefined){
                console.log("IF Logged");
                vm.loggedIn = true;
                $window.location = "#/profile";
            }
            console.log("loggedin = " + vm.loggedIn);
        }

        //login method, takes form data (username and password) and calls login method from parent Controller
        function login() {
            console.log(vm.username+" and "+vm.password);
            var userData =  { "username": vm.username, "password": vm.password };

            $http.post('/api/users/login', userData).then(function(response) {
                // save user token to cookie
                console.log("response.data = " + response.data.response);
                $cookies.put("token", response.data.response);
                vm.loggedIn = true;

                $window.location.href = "http://" + $window.location.host + "/#!/profile";
               /* $window.location = "#/profile";*/

                console.log("loggedIn = " + vm.loggedIn);
            }, function(response) {
                alert(response.data.response);
                console.log("Wrong username and password combination");
            });
        }


        // method for deleting user data - cookies
        function logout() {
            console.log("USAO JE U GLUPI LOGOUT");
            vm.loggedIn = false;
            var cookies = $cookies.getAll();
            for ( var x in cookies) {
                $cookies.remove(x);
            }
        }
        ;

    }
})();