(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('DeviceController', DeviceController);

    DeviceController.$inject = ['$scope', '$uibModal', 'DeviceService'];

    function DeviceController($scope, $uibModal, DeviceService) {
        var vm = this;
        vm.pageChanged = pageChanged;
        vm.refresh = refresh;
        //  vm.deleteDevice = deleteDevice;
        vm.readFromExcel = readFromExcel;
        //  vm.batchdeleteDevice = batchdeleteDevice;
        //  vm.editDevice = editDevice;

        // vm.open = open;
        vm.all = all;
        vm.chk = chk;
        // vm.deleteUser = deleteUser;
        vm.table = {
            deviceArray: []
        };
        vm.pagination = {
            maxSize: 5,
            bigCurrentPage: 1,
            // bigTotalItems: ''
        };
        vm.check = {
            allArray: [],
            selectedArray: []
        };

        getDevicePage();

        // function editDevice(data){
        //   var modalInstance = $uibModal.open({
        //         //     animation: $scope.animationsEnabled, //打开时的动画开关
        //         templateUrl: 'app/SysSet/Equipment/EquipmentEdit.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
        //         controller: 'EquipmentEditModalCtrl', //这是模态框的控制器,是用来控制模态框的
        //         controllerAs: '$ctrl',
        //         size: 'lg', //模态框的大小尺寸
        //         scope: $scope,
        //         keyboard: false,
        //         resolve: { //这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
        //             equipment: function() { //items是一个回调函数
        //                 return data;
        //             }
        //         }
        //     });
        // }


        // function batchdeleteDevice(){
        //     EquipmentService.batchdeleteEquipment.delete({ids:vm.check.selectedArray},success);
        //     function success(){
        //         alert("批量删除成功！");
        //         getEquipmentPage();
        //     }
        // }

        function readFromExcel() {
            var modalInstance = $uibModal.open({
                //     animation: $scope.animationsEnabled, //打开时的动画开关
                templateUrl: 'app/SysSet/Device/DeviceAdd.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
                controller: 'DeviceAddModalCtrl', //这是模态框的控制器,是用来控制模态框的
                controllerAs: '$ctrl',
                size: 'lg', //模态框的大小尺寸
                scope: $scope,
                keyboard: false,
                // resolve: { //这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
                //     userInfo: function() { //items是一个回调函数
                //         return data;
                //     }
                // }
            });
        }

        // function deleteDevice(id) {
        //     EquipmentService.deleteEquipment.delete({ id: id }, success);

        //     function success() {
        //         alert("删除成功！");
        //         getEquipmentPage();
        //     }
        // }

        // getEquipmentPage();

        function refresh() {
            getDevicePage();
        }

        function pageChanged() {
            getDevicePage();
        };

        function getDevicePage() {
            DeviceService.getDevicePage.get({
                page: vm.pagination.bigCurrentPage - 1,
                size: 10
            }, function(response) {
                vm.table.deviceArray = response.content;
                vm.pagination.bigTotalItems = response.totalElements;
                initCheckArray();
                console.info(vm.check.allArray);
            });
        }

        function all(checkAll) {
            if (checkAll == true) {
                vm.check.selectedArray = vm.check.allArray;
            } else {
                vm.check.selectedArray = [];
            }
            console.info(vm.check.selectedArray);
        }

        function chk(checkOne, id) {
            if (checkOne == true) {
                vm.check.selectedArray.push(id);
            } else {
                vm.check.selectedArray.remove(id);
            }
            console.info(vm.check.selectedArray);
        }

        function initCheckArray() {
            vm.check.allArray = [];
            for (var i = 0; i < vm.table.deviceArray.length; i++) {
                vm.check.allArray.push(vm.table.deviceArray[i].id);
            }
        }

        Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };

        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };

    }

})();
