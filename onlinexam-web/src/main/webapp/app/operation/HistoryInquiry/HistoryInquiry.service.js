(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('HistoryInquiryService', HistoryInquiryService);

    HistoryInquiryService.$inject = ['$resource'];

    function HistoryInquiryService($resource) {
        var service = {

        };

        service.device = $resource('web30/web/api/v2/ui/equipment', {});

        service.alarms = $resource('web30/web/api/v2/alarmhist', {

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
