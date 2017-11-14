(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('AuthServerProvider', AuthServerProvider);

    AuthServerProvider.$inject = ['$http', '$localStorage', '$sessionStorage', '$q'];

    function AuthServerProvider ($http, $localStorage, $sessionStorage, $q) {
        var service = {
            getToken: getToken,
            hasValidToken: hasValidToken,
            login: login,
            loginWithToken: loginWithToken,
            storeAuthenticationToken: storeAuthenticationToken,
            logout: logout,
            username:null
        };

        return service;

        function getToken () {
            //return $localStorage.authenticationToken || $sessionStorage.authenticationToken;
             return $sessionStorage.authenticationToken;
        }

        function hasValidToken () {
            var token = this.getToken();
            return token && token.expires && token.expires > new Date().getTime();
        }

        function login (credentials) {
            console.info(credentials.username);
            var data = {
                username: credentials.username,
                password: credentials.password,
                rememberMe: credentials.rememberMe
            };
            return $http.post('web30/api/webport/jwt/authenticate', data).success(authenticateSuccess).error(authenticateFail);

            function authenticateSuccess (data, status, headers) {
                // var bearerToken = headers('Authorization');
                // if (angular.isDefined(bearerToken) && bearerToken.slice(0, 7) === 'Bearer ') {
                //     var jwt = bearerToken.slice(7, bearerToken.length);
                //     service.storeAuthenticationToken(jwt, credentials.username);
                //    // service.username = credentials.username;
                //     return jwt;
                // }
                
                alert("data="+data+"status="+status);
                window.location.href = data.redirecturl+'/index1.html#/redirect?jwt='+data.jwt+'&username='+data.username;
             // window.location.href = data.redirecturl+'index1.html#/redirect';

            }
            function authenticateFail(data, status, headers){
                alert("data="+data.message);
            }
        }

        function loginWithToken(jwt, username) {
            var deferred = $q.defer();

            if (angular.isDefined(jwt)) {
                this.storeAuthenticationToken(jwt, username);
                deferred.resolve(jwt);
            }
            else {
                deferred.reject();
            }

            return deferred.promise;
        }

        function storeAuthenticationToken(jwt, username) {
           // if(rememberMe){
           //     $localStorage.authenticationToken = jwt;
           // } else {
                $sessionStorage.authenticationToken = jwt;
                $sessionStorage.username = username;
           // }
            window.token=jwt;
        }

        function logout () {
          //  delete $localStorage.authenticationToken;
            delete $sessionStorage.authenticationToken;
            delete $sessionStorage.username;
        }
    }
})();
