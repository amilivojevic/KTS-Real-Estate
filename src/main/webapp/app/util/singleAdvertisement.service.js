/**
 * Created by Nina on 15-Jul-17.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('SingleAdvertisementService', function() {


            var advertisementId;

            var setAdvertisementId = function(id) {
                console.log("postavio id" + id);
                advertisementId = id;
            };

            var getAdvertisementId = function(){
                console.log("citam id" + advertisementId);
                return advertisementId;
            };

            return {
                setAdvertisementId: setAdvertisementId,
                getAdvertisementId: getAdvertisementId
            };

        });
}(angular));