(function() {
    'use strict';

    angular
        .module('webfullApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home.historyInquiry', {
           //  parent: 'app',
            url: '/historyInquiry',
            templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html'
            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        });
    }
})();