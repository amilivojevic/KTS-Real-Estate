/**
 * Created by Sandra on 6/13/2017.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('CompanyService', companyService);

    function companyService($http) {
        var BASE_URL = "api/users/company";

        return {
            findAll: findAll
        };

        /*        function pathWithId(id) {
         return BASE_URL + '/' + id;
         }*/

        function findAll() {
            return $http.get(BASE_URL + "/all");
        }
    }
}(angular));