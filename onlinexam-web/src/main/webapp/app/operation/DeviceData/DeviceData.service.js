(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('DeviceDataService', DeviceDataService);

    DeviceDataService.$inject = ['$resource'];

    function DeviceDataService($resource) {
        var service = {

        };

        service.deviceTree = $resource('web30/oauth/api/v1/device', {});

        service.deviceList = $resource('web30/oauth/api/v1/pi', {

        });

        service.charts = $resource('web30/oauth/api/chart/:PiName/:Period', { PiName: '@PiName', Period: '@Period' }, {
            'get': {
                method: 'GET',
                isArray: true
            },
            'query': {
                method: 'GET',
                isArray: true
            }
        });

        return service;
    }
})();
