(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('HistoryInquiryController', HistoryInquiryController);

    HistoryInquiryController.$inject = ['$scope', '$stateParams', '$resource', '$uibModal', '$filter', 'HistoryInquiryService'];

    function HistoryInquiryController($scope, $stateParams, $resource, $uibModal, $filter, HistoryInquiryService) {
        var vm = this;
        vm.pageChanged = pageChanged;
        vm.searchQueryChanged = searchQueryChanged;

        vm.device = {
            selected: '',
            devices: ''
        };
        vm.period = {
            selected: '1'
        };
        vm.table = {
            alarmsArray: ''
        };
        vm.pagination = {
            maxSize: 5,
            bigCurrentPage: 1,
            bigTotalItems: ''
        };


        function pageChanged() {
            console.info('Page changed to: ' + vm.pagination.bigCurrentPage);
            getHistoryPage(vm.pagination.bigCurrentPage);
        };

        function searchQueryChanged() {
            getHistoryPage(1);
        }


        init();

        function init() {
            initSelect();
            getHistoryPage(vm.pagination.bigCurrentPage);
        }

        function initSelect() {
            HistoryInquiryService.device.query(function(response) {
                vm.device.devices = response;
            });
        }

        function getHistoryPage(page) {
            HistoryInquiryService.alarms.get({
                date: vm.period.selected,
                equipmentId: vm.device.selected,
                page: page-1,
                size: 10,
                sort: 'raiseTime,desc'
            }, function(response) {
                vm.pagination.bigTotalItems = response.totalElements;
                vm.table.alarmsArray = response.content;
            });


        }

        $scope.open = function(describe, name, raiseTime, piTime, limitLow, limitHigh) {
            //这里很关键,是打开模态框的过程
            var modalInstance = $uibModal.open({
                //     animation: $scope.animationsEnabled, //打开时的动画开关
                templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
                controller: 'HistoryInquiryModalCtrl', //这是模态框的控制器,是用来控制模态框的
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
