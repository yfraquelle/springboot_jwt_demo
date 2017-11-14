(function() {
    'use strict';

    angular
        .module('webfullApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('about', {
            url: '/about?message',
            views: {
                'menuContent@': {
                    templateUrl: 'app/about/about.html',
                    controller: 'AboutController',
                 //   controllerAs: 'vm'
                }
            }
        });
    }
})();