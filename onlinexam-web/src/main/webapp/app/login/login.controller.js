(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$rootScope','$scope', '$state', '$timeout','$location', 'Auth','WechatAuthServerProvider'];

    function LoginController ($rootScope,$scope, $state, $timeout,$location, Auth,WechatAuthServerProvider) {
        var vm = this;

        vm.authenticationError = false;
        vm.cancel = cancel;
        vm.credentials = {};
        vm.login = login;
        vm.password = null;
        vm.register = register;
        vm.rememberMe = true;
        vm.requestResetPassword = requestResetPassword;
        vm.username = null;
        vm.changeLogin = changeLogin;
        vm.wxFlag = true;
      //  vm.now = now;


        vm.now = new Date();

        var vm = this;
        vm.now = new Date();
        var queryString = $location.search().code;
    //    alert(queryString);
        WechatAuthServerProvider.wechatAuth(queryString);


    //    alert($("#myCarousel").carousel());

        $timeout(function (){angular.element('#username').focus();
 
      });
      //  $("#myCarousel").carousel();

      //  angular.element('#myCarousel').carousel();

      function changeLogin(i){
            if(i == 1){
                vm.wxFlag= false;
             //   vm.now = "2016";
            }else{
                vm.wxFlag= true;
            }
            
          //  alert(vm.wxFlag);
         //   $scope.$apply();
      }

        function cancel () {
            vm.credentials = {
                username: null,
                password: null,
                rememberMe: true
            };
            vm.authenticationError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login (event) {
            event.preventDefault();
            Auth.login({
                username: vm.username,
                password: vm.password,
                rememberMe: vm.rememberMe
            }).then(function () {
                vm.authenticationError = false;
                if ($state.current.name === 'register' || $state.current.name === 'activate' ||
                    $state.current.name === 'login' || $state.current.name === 'requestReset') {
                    $state.go('home');
                }

                $rootScope.$broadcast('authenticationSuccess');

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is successful, go to stored previousState and clear previousState
                // if (Auth.getPreviousState()) {
                //     var previousState = Auth.getPreviousState();
                //     Auth.resetPreviousState();
                //     $state.go(previousState.name, previousState.params);
                // }
            }).catch(function () {
                vm.authenticationError = true;
            });
        }

        function register () {
            $uibModalInstance.dismiss('cancel');
            $state.go('register');
        }

        function requestResetPassword () {
            $uibModalInstance.dismiss('cancel');
            $state.go('requestReset');
        }
    }
})();
