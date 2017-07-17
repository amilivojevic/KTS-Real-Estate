/**
 * Created by Nina on 14-Jul-17.
 */

(function () {

    angular.module("realEstateApp")
        .controller('ReportBanedAdvertisementController',reportBanedAdvertisementController);


    //register page controller
    function reportBanedAdvertisementController($http, $scope, $window,SingleAdvertisementService) {

        var vm = this;
        vm.reportBanedAdvertisement = reportBanedAdvertisement;
        vm.reason = ['Invalid image', 'Sold', 'Other'];


        function reportBanedAdvertisement () {

            var id = SingleAdvertisementService.getAdvertisementId();

            vm.new_report = {
                description : vm.newReport.description,
                date : vm.newReport.date,
                banningReason: vm.newReport.banningReason,
                ////id od advertisementa!!!
                advertisementId : id
            }

            $http.post('/api/verifierReport/reportBanedAdvertisement/' + id, vm.new_report).then(function (response) {

                $window.location.href = "http://" + $window.location.host + "/#!/approve_advertisement";
                /*  $scope.redirect();*/


            },function(response){
                alert("Check you email and activate your account!");
            });
        }
    }
})();