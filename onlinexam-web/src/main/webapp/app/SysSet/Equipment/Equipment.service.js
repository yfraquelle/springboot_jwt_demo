(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('EquipmentService', EquipmentService);

    EquipmentService.$inject = ['$resource'];

    function EquipmentService($resource) {
        var service = {

        };

        service.getEquipmentPage = $resource('web30/oauth/api/v2/equipment', {});
        service.deleteEquipment = $resource('web30/oauth/api/v2/equipment/:id', {id:'@id'});
        service.batchdeleteEquipment = $resource('web30/oauth/api/v2/equipment-del', {});
        service.updateEquipment = $resource('web30/oauth/api/v2/equipment', {});
        return service;
    }
})();
