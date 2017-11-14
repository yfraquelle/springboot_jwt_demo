(function() {
    'use strict';

    angular
        .module('webfullApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                },
                'mainContent@home':{
                    templateUrl: 'app/home/home.html',
                }
            },
            resolve: {
                // mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                //     $translatePartialLoader.addPart('home');
                //     return $translate.refresh();
                // }]
            }
        });
    }
})();
