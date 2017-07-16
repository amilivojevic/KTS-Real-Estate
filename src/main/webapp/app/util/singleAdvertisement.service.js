/**
 * Created by Nina on 15-Jul-17.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('SingleAdvertisementService', function() {


            var advertisementId;

            var setRealEstateId = function(id) {
                console.log("postavio id" + id);
                advertisementId = id;
            };

            var getRealEstateId = function(){
                console.log("citam id" + advertisementId);
                return advertisementId;
            };

            return {
                setRealEstateId: setRealEstateId,
                getRealEstateId: getRealEstateId
            };

        });
}(angular));