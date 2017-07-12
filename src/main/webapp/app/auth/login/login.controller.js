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
            var token = $window.localStorage.getItem("token");
            console.log("local storage: " + JSON.stringify(token));
            if($window.localStorage.getItem("token")){
                console.log("Logged");
                vm.loggedIn = true;
                $window.location = "#!/profile";
                console.log("logged user data: " + getLoggedUserData(token).data);
                //$window.localStorage.setItem("loggedUser", getLoggedUserData(token));
            }
            else{
                vm.loggedIn = false;
                //$location.path('/');
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

        function getLoggedUserData(token) {
            //var retVal;
            return $http.get('/api/users/data', token)
                .then(function(loggedUserData) {
                    console.log("tralala" + JSON.stringify(loggedUserData.data));
                    return loggedUserData.data;
            });
            //console.log("AAAAA" + JSON.stringify(retVal));
            //return retVal;
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