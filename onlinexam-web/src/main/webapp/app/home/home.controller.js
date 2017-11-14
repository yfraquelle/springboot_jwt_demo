(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','$location','$state', 'Principal','$sessionStorage','WechatAuthServerProvider','AuthServerProvider'];

    function HomeController ($scope,$location,$state, Principal,$sessionStorage,WechatAuthServerProvider,AuthServerProvider) {
        // var vm = this;

        // vm.account = null;
        // vm.isAuthenticated = null;
        // vm.login = LoginService.open;
        // $scope.$on('authenticationSuccess', function() {
        //     getAccount();
        // });

        // getAccount();

        // function getAccount() {
        //     Principal.identity().then(function(account) {
        //         vm.account = account;
        //         vm.isAuthenticated = Principal.isAuthenticated;
        //     });
        // }
        var vm = this;
        vm.now = new Date();
        vm.username = $sessionStorage.username;
        vm.logout = logout;
        // var queryString = $location.search().code;
        // alert(queryString);
        // WechatAuthServerProvider.wechatAuth(queryString);
        function logout(){
            AuthServerProvider.logout();
            //$state.go('home');
            window.location.reload();

        }

    }
})();
