/**
 * Created by Nina on 15-Jul-17.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('NewAdvertisementService', function() {

            var vm = this;
        vm.realEstateId = "aaaaa";

        var setRealEstateId = function(id) {
            vm.realEstateId = id;
            console.log("Postavljanje real estat4e id-a u servisu: " + vm.realEstateId);
        };

        var getRealEstateId = function(){
            console.log("dobavljanje real estat4e id-a u servisu: " + vm.realEstateId);
            return vm.realEstateId;
        };

        return {
            setRealEstateId: setRealEstateId,
            getRealEstateId: getRealEstateId
        };

    });
}(angular));