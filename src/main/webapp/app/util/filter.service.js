/**
 * Created by Nina on 16-Jul-17.
 */
(function (angular) {
    'use strict';
    angular
        .module('realEstateApp')
        .service('FilterService', function() {

            var vm = this;
            vm.filter = {};

            var setFilter = function(filter) {
                vm.filter = filter;
                console.log("Postavljanje filter u servisu: " + JSON.stringify(vm.filter));
            };

            var getFilter = function(){
                console.log("dobavljanje filtera u servisu: " + JSON.stringify(vm.filter));
                return vm.filter;
            };

            return {
                setFilter: setFilter,
                getFilter: getFilter
            };

        });
}(angular));