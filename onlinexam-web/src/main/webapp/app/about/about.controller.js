(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('AboutController', AboutController);

    AboutController.$inject = ['$rootScope', '$scope', '$state','$timeout', '$stateParams'];

    function AboutController($rootScope, $scope, $state,$timeout, $stateParams) {
        var vm = this;
        var message = $stateParams.message;
        $scope.message = message;

        $timeout(function() {
            $state.go("login");
        }, 30000);

     //   alert(message);
    }
})();
