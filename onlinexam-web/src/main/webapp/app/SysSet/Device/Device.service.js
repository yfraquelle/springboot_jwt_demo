(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('DeviceService', DeviceService);

    DeviceService.$inject = ['$resource'];

    function DeviceService($resource) {
        var service = {

        };
        service.getDevicePage = $resource('web30/oauth/api/v1/devicePage', {});
        service.batchdeleteDevice = $resource('web30/oauth/api/v2/device-del', {});
        return service;
    }
})();
