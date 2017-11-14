(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('DeviceDataController', DeviceDataController);

    DeviceDataController.$inject = ['$scope', '$timeout', '$stateParams', '$resource', '$uibModal', '$filter', 'DeviceDataService'];

    function DeviceDataController($scope, $timeout, $stateParams, $resource, $uibModal, $filter, DeviceDataService) {
        var vm = this;
        vm.jstree = {
            selectedNodeId: '',
            selectedLevel: ''
        };
        vm.table = {
            deviceArray: []
        };
        vm.pagination = {
            maxSize: 5,
            bigCurrentPage: 1,
            bigTotalItems: '',
            itemsPerPage: 6
        };

        var newId = 1;
        vm.jstree.ignoreChanges = false;
        vm.jstree.newNode = {};
        vm.jstree.treeData = [];


        vm.jstree.treeConfig = {
            core: {
                multiple: false,
                animation: true,
                error: function(error) {
                    $log.error('treeCtrl: error from js tree - ' + angular.toJson(error));
                },
                check_callback: true,
                worker: true
            },
            types: {
                default: {
                    icon: 'fa fa-folder icon-state-warning icon-lg'
                },
                file: {
                    icon: 'fa fa-file icon-state-warning icon-lg'
                }
            },
            version: 1,
            plugins: ['types']
        };


        vm.reCreateTree = function() {
            vm.jstree.ignoreChanges = true;
            vm.jstree.treeConfig.version++;
        };

        vm.simulateAsyncData = function() {
            //var deviceTree = $resource('web30/oauth/api/v1/device', {});
            vm.promise = DeviceDataService.deviceTree.query({}, function(response) {

                var json = angular.toJson(response);
                vm.jstree.treeData = angular.fromJson(json);

                vm.reCreateTree();
            });


        };

        vm.addNewNode = function() {
            vm.jstree.treeData.push({ id: (newId++).toString(), parent: vm.newNode.parent, text: vm.newNode.text });
        };

        this.setNodeType = function() {
            var item = _.findWhere(this.treeData, { id: this.selectedNode });
            item.type = this.newType;
            //  toaster.pop('success', 'Node Type Changed', 'Changed the type of node ' + this.selectedNode);
        };

        this.readyCB = function() {
            $timeout(function() {
                vm.jstree.ignoreChanges = false;
                //   toaster.pop('success', 'JS Tree Ready', 'Js Tree issued the ready event')
            });
        };

        this.createCB = function(e, item) {
            // $timeout(function() { toaster.pop('success', 'Node Added', 'Added new node with the text ' + item.node.text) });
        };

        this.applyModelChanges = function() {
            return !vm.jstree.ignoreChanges;
        };

        this.select_nodeCB = function(node, selected, event) {
            console.info(selected);
            vm.jstree.selectedNodeId = selected.node.id;
            vm.jstree.selectedLevel = selected.node.data.level;
            vm.pagination.bigCurrentPage = 1;

            getDevList();
        }

        vm.reloadJstree = function() {
            vm.simulateAsyncData();
        }

        vm.simulateAsyncData();
        //  getDevList('140000', 1);

        function getDevList() {
            vm.promise = DeviceDataService.deviceList.get({
                size: vm.pagination.itemsPerPage,
                page: vm.pagination.bigCurrentPage - 1,
                nodeid: vm.jstree.selectedNodeId,
                level: vm.jstree.selectedLevel
            }, function(response) {
                vm.table.deviceArray = response.content;
                console.info("content" + vm.table.deviceArray);
                vm.pagination.bigTotalItems = response.totalElements;

            });
        }

        vm.pageChanged = function() {
            //   $log.log('Page changed to: ' + $scope.currentPage);
            console.info('Page changed to: ' + $scope.bigCurrentPage);
            getDevList();
            //  vm.alarmsArray = [];

        };
        vm.reloadDeviceData = function() {
            //vm.pagination.bigCurrentPage = 1;
            // alert("ss");
            getDevList();
        }

        $scope.open = function(describe, name, paramType, limitLow, limitHigh) {
            //这里很关键,是打开模态框的过程
            var modalInstance = $uibModal.open({
                //     animation: $scope.animationsEnabled, //打开时的动画开关
                templateUrl: 'app/operation/DeviceData/DeviceData.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
                controller: 'DeviceDataModalCtrl', //这是模态框的控制器,是用来控制模态框的
                controllerAs: '$ctrl',
                size: 'lg', //模态框的大小尺寸
                scope: $scope,
                keyboard: false,
                resolve: { //这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
                    charts: function() { //items是一个回调函数
                        var chart = {
                            name: name,
                            describe: describe,
                            limitLow: limitLow,
                            limitHigh: limitHigh
                        }
                        return chart; //这个值会被模态框的控制器获取到
                    }
                }
            });
        }



    }
})();
