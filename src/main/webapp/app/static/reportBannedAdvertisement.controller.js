/**
 * Created by Nina on 14-Jul-17.
 */

(function () {

    angular.module("realEstateApp")
        .controller('ReportBanedAdvertisementController',reportBanedAdvertisementController);


    //register page controller
    function reportBanedAdvertisementController($http, $scope, $window) {

        var vm = this;
        vm.addReportBanedAdvertisement = addReportBanedAdvertisement;
        vm.reason = ['Invalid image', 'Sold', 'Other'];

        //method for user registration
        function addReportBanedAdvertisement () {

            var id = NewAdvertisementService.getRealEstateId();

            vm.new_report = {
                title : vm.newReport.title,
                price : parseFloat(vm.newReport.price),
                endingDate : vm.newReport.endingDate,
                phoneNumber : vm.newReport.phoneNumber,
                type : vm.newReport.type,
                ////id od nekretnine!!!
                id : id,
                currency : vm.newReport.currency,
            }

     /*       $window.location.href = "http://" + $window.location.host + "/#!/profile"; */

            $http.post('/api/advertisement/addNewAdvertisement', vm.new_report).then(function (response) {

                /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });
        }
    }
})();