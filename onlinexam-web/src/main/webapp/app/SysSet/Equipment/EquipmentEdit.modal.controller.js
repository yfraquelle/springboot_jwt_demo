(function() {
    'use strict';
    angular
        .module('webfullApp')
        .controller('EquipmentEditModalCtrl', EquipmentEditModalCtrl);

    EquipmentEditModalCtrl.$inject = ['$scope', '$uibModalInstance', '$http', '$filter', 'equipment', 'EquipmentService'];

    function EquipmentEditModalCtrl($scope, $uibModalInstance, $http, $filter, equipment, EquipmentService) {
        var $ctrl = this;

        $ctrl.selected = '2';

        $ctrl.equipment = {
            companyName: equipment.companyName,
            factoryName: equipment.factoryName,
            workshopName: equipment.workshopName,
            deviceName: equipment.deviceName,
            systemName: equipment.systemName
        };

        //      console.info(userInfo);


        $ctrl.ok = function() {
            $uibModalInstance.close();
        };

        $ctrl.cancel = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $ctrl.ChangeChart = function() {
            //alert($ctrl.selected);
        }

        $ctrl.saveEquipment = function() {
            EquipmentService.updateEquipment.save({
                id:equipment.id,
                companyName: $ctrl.equipment.companyName,
                factoryName: $ctrl.equipment.factoryName,
                workshopName: $ctrl.equipment.workshopName,
                deviceName: $ctrl.equipment.deviceName,
                systemName: $ctrl.equipment.systemName
            });
        }
    }
})();
