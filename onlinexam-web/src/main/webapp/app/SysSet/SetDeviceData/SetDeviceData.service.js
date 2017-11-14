(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('SetDeviceDataService', SetDeviceDataService);

    SetDeviceDataService.$inject = ['$resource'];

    function SetDeviceDataService($resource) {
        var service = {

        };

        service.deviceTree = $resource('web30/oauth/api/v1/device', {});

        service.deviceList = $resource('web30/oauth/api/v1/pi', {
          
        });

        return service;
    }
})();
