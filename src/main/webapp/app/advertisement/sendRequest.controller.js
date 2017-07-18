/**
 * Created by Nina on 18-Jul-17.
 */
(function() {
    angular.module("realEstateApp")
        .controller('SendRequestController', sendRequestController);


    function sendRequestController($http, $scope, $cookies, $window) {

        var vm = this;
        vm.sendRequest = sendRequest;



        function sendRequest() {

            $http.get('/api/advertisement/sendRequest')
                .then(function(response) {


                }, function(response) {
                    alert(JSON.stringify(response.data));
                });
        }
    }
})();
