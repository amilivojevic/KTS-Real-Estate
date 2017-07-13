(function () {
    angular.module("realEstateApp")
        .controller('LoginController', loginController);

        //login page controller
    function loginController($scope,$location, $http,$window,LoginFactory) {

        var vm = this;

        vm.loggedIn = false;
        // login and logout methods
        vm.login = login;
        vm.logout = logout;
        vm.getLoggedUserData = getLoggedUserData;
        checkIfLogged();

        function checkIfLogged(){

            if($window.localStorage.getItem("token")){
                console.log("Logged");
                vm.loggedIn = true;
                vm.token = $window.localStorage.getItem("token");

                getLoggedUserData();
                var user = angular.fromJson($window.localStorage['loggedUser']);

                switch (user.role){
                    case "OWNER" :
                        $window.location = "#!/profile"; break;
                    case "SYS_ADMIN" :
                        $window.location = "#!/admin"; break;
                    case "VERIFYER" :
                        $window.location = "#!/verifyer"; break;
                }
            }
            else{
                vm.loggedIn = false;
            }
            console.log("loggedin = " + vm.loggedIn);
        }

        //login method, takes form data (username and password) and calls login method from parent Controller
        function login() {
            console.log(vm.username+" and "+vm.password);
            var userData =  { "username": vm.username, "password": vm.password };

            $http.post('/api/users/login', userData)
                .then(function(token) {
                    $window.localStorage.setItem("token",token.data.response);
                    checkIfLogged();

            }, function(response) {
                alert(response.data.response);
                console.log("Wrong username and password combination");
            });
        }



        function getLoggedUserData() {
            var promise = LoginFactory.getLoggedUserData(vm.token);
            promise.then(
                function(loggedUser) {
                    console.log("ucitan u funkciji: " + JSON.stringify(loggedUser));
                    $window.localStorage['loggedUser'] = angular.toJson(loggedUser);
                    $scope.loggedUser = loggedUser;

                }
            );
        };


        // method for deleting user data - token
        function logout() {
            console.log("usao u logout");
            $window.localStorage.removeItem("token");
            checkIfLogged();
            $location.path('/');
        }
        ;

    }
})();