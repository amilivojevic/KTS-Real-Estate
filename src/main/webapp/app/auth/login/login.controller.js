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

                //getLoggedUserData();


                var promise = LoginFactory.getLoggedUserData(vm.token);
                promise.then(
                    function(loggedUser) {

                        $window.localStorage['loggedUser'] = angular.toJson(loggedUser);
                        console.log("ucitan u funkciji window.localstorage: " + JSON.stringify($window.localStorage['loggedUser']));
                        $scope.loggedUser = loggedUser;
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
                );


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
                    console.log("token = " + $window.localStorage.getItem("token"));


                    //provera ako je firma ili owner u okviru firme da li je odobren nalog:
                    $http.post('/api/users/checkApproved',{})
                        .then(function(response){
                            console.log("*** approved response.data = " + response.data);
                            if(response.data){
                                checkIfLogged();
                            }
                            else{
                                alert("Your registration has not been approved yet");
                            }


                        }, function(response) {
                        alert(response.data.response);
                        console.log("Wrong username and password combination");
                    });





            }, function(response) {
                alert(response.data.response);
                console.log("Wrong username and password combination");
            });
        }



        function getLoggedUserData() {
            var promise = LoginFactory.getLoggedUserData(vm.token);
            promise.then(
                function(loggedUser) {

                    $window.localStorage['loggedUser'] = angular.toJson(loggedUser);
                    console.log("ucitan u funkciji window.localstorage: " + JSON.stringify($window.localStorage['loggedUser']));
                    $scope.loggedUser = loggedUser;

                }
            );
        };


        // method for deleting user data - token
        function logout() {
            console.log("usao u logout");
            $window.localStorage.removeItem("token");
            $window.localStorage.removeItem("loggedUser");
            checkIfLogged();
            $location.path('/');
        }
        ;

    }
})();