(function() {
    'use strict';

    angular
        .module('webfullApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home.currentAlarm', {
            parent: 'app',
            url: '/currentAlarm',
            templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm1.html'
            // views: {
            //      'mainContent@home': {
            //         templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm1.html',
            //         controller:'CurrentAlarmController',
            //         controllerAs: 'vm'
            //     }
            // }
        });
    }
})();