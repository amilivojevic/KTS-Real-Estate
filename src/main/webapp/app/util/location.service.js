/**
 * Created by Korisnik on 6/11/2017.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('LocationDataService', locationDataService);

    function locationDataService($http) {
        var BASE_URL = "api/location/all";

        return {
            findAll: findAll
        };

        /*        function pathWithId(id) {
         return BASE_URL + '/' + id;
         }*/

        function findAll() {
            return $http.get(BASE_URL);
        }
    }
}(angular));