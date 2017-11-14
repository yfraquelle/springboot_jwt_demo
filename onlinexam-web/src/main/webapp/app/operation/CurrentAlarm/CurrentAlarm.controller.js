(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('CurrentAlarmController', CurrentAlarmController);

    CurrentAlarmController.$inject = ['$scope', '$http', '$stateParams', '$resource', '$uibModal', '$filter', 'CurrentAlarmService'];

    function CurrentAlarmController($scope, $http, $stateParams, $resource, $uibModal, $filter, CurrentAlarmService) {
        //    $scope.totalItems = 64;
        //    $scope.currentPage = 1;
        var vm = this;
        vm.devices = [];
        vm.alarmsArray = [];
        vm.initSelect = initSelect;
        vm.changeSelect = changeSelect;
        vm.getAlarmsPage = getAlarmsPage;
        vm.selected = '';

        //  $scope.describe="常顶回流量变化速率数据超过上阈值";
        vm.charts = {
            describe: '',
            name: ''
        }


        $scope.maxSize = 5;

        $scope.setPage = function(pageNo) {
            $scope.currentPage = pageNo;
        };

        $scope.pageChanged = function() {
            //   $log.log('Page changed to: ' + $scope.currentPage);
            console.info('Page changed to: ' + $scope.bigCurrentPage);
            vm.getAlarmsPage($scope.bigCurrentPage);
            //  vm.alarmsArray = [];

        };


        init();

        function init() {
            vm.initSelect();
            vm.getAlarmsPage(1);
        }

        $scope.open = function(describe, name, raiseTime, piTime, limitLow, limitHigh) {
            //这里很关键,是打开模态框的过程
            console.info(raiseTime);
            console.info($filter('date')(piTime, 'yyyy-MM-dd hh:mm:ss'));


            // $scope.describe = describe;
            vm.charts.describe = describe;
            vm.charts.name = name;
            //  initChart(limitLow, LimitHigh);

            var modalInstance = $uibModal.open({
                //     animation: $scope.animationsEnabled, //打开时的动画开关
                templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
                controller: 'CurrentAlarmModalCtrl', //这是模态框的控制器,是用来控制模态框的
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

        $scope.bigCurrentPage = 1;


        function initSelect() {
            var device = $resource('web30/web/api/v2/ui/equipment', {});
            // console.info(vm.devices);
            device.query(function(response) {
                // $ionicLoading.hide();
                vm.devices = response;
                console.info(vm.devices);
                // alert(response);

            });
        }

        function changeSelect(value) {
            getAlarmsPage(1);
            $scope.bigCurrentPage = 1;
        }



        function getAlarmsPage(page) {
            var alarms = $resource('web30/web/api/v2/alarm', {
                page: page-1,
                size: 10,
                sort: 'raiseTime,desc',
                equipmentId: vm.selected
            });

            alarms.get(function(response) {
                console.info(response.content);
                $scope.bigTotalItems = response.totalElements;
                //   var content = response.content;
                vm.alarmsArray = response.content;
                //    vm.alarmsArray.push({content:response.content});
                //   console.info(vm.alarmsArray);

            });


        }
    }

})();
