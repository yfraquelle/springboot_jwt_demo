(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('CurrentAlarmService', CurrentAlarmService);

    CurrentAlarmService.$inject = ['$resource'];

    function CurrentAlarmService ($resource) {
        var charts = $resource('web30/oauth/api/chart/:PiName/:Period', {PiName:'@PiName',Period:'@Period'}, {
            'get': {
                method: 'GET',
                isArray: true
            },
            'query': {
                method: 'GET',
                isArray: true
            }
        });

        return charts;
    }
})();