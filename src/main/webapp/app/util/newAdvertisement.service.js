/**
 * Created by Nina on 15-Jul-17.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('NewAdvertisementService', function() {


        var realEstateId;

        var setRealEstateId = function(id) {
            realEstateId = id;
        };

        var getRealEstateId = function(){
            return realEstateId;
        };

        return {
            setRealEstateId: setRealEstateId,
            getRealEstateId: getRealEstateId
        };

    });
}(angular));