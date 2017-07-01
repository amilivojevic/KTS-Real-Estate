(function () {
    angular.module("realEstateApp")
        .controller('LoginController', loginController);


        //login page controller
    function loginController($scope, $rootScope,$location, $http,$window) {

        var vm = this;

        vm.loggedIn = false;
        // login and logout methods
        vm.login = login;
        vm.logout = logout;
        vm.getLoggedUserData = getLoggedUserData;
        checkIfLogged();

        function checkIfLogged(){
            console.log("local storage: " + JSON.stringify($window.localStorage.getItem("token")));
            if($window.localStorage.getItem("token")){
                console.log("Logged");
                vm.loggedIn = true;
                $window.location = "#!/profile"
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
                    console.log("Da li je moguce da glupi token radi??? " + token.data.response);
                    $window.localStorage.setItem("token",token.data.response);


            }, function(response) {
                alert(response.data.response);
                console.log("Wrong username and password combination");
            });
        }

        function getLoggedUserData(token) {
            return $http.post('/api/users/login', token)
                .then(function(loggedUserData) {
                    return loggedUserData.data;
                })
        };


        // method for deleting user data - cookies
        function logout() {
            //$localStorage.$reset();
            $scope.loggedUser = false;
            $rootScope.currentUser = false;
            $location.path('/');
        }
        ;

    }
})();