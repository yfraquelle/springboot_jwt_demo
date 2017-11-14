(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('RedirectController', RedirectController);

    RedirectController.$inject = ['$rootScope', '$scope', '$state', '$timeout', '$location', '$sessionStorage','AuthServerProvider'];

    function RedirectController($rootScope, $scope, $state, $timeout, $location, $sessionStorage,AuthServerProvider) {
        var urljwt = $location.search().jwt;
        //var urljwt = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJod3ciLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTQ5NDgzODA0M30.8tLQxL9XTzpv3lS2wONc_jpYp_V1afWzB3d44wOGsBji4lSSJ9LQE1pl3u6sFHVnm3ZUDJhKbg804vqC6KcGrg';

         var urlusername = $location.search().username;
         alert("urljwt="+urljwt);
        //var urlusername = 'hww';
        if (urljwt != null) {
            AuthServerProvider.storeAuthenticationToken(urljwt,urlusername);
            // window.token = urljwt;
            // $sessionStorage.authenticationToken = urljwt;
            // $sessionStorage.username = urlusername;
        }
        $state.go("home");
    }
})();
