(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('authInterceptor', authInterceptor);

    authInterceptor.$inject = ['$rootScope', '$q', '$location', '$localStorage', '$sessionStorage'];

    function authInterceptor($rootScope, $q, $location, $localStorage, $sessionStorage) {
        var service = {
            request: request,
            response: response
        };

        return service;

        function request(config) {
            config = config || {};
            config.headers = config.headers || {};
            var urljwt = $location.search().jwt;
            var urlusername = $location.search().username;
            if (urljwt != null) {
                window.token = urljwt;
                $sessionStorage.authenticationToken = urljwt;
                $sessionStorage.username = urlusername;
                var token = $localStorage.authenticationToken || $sessionStorage.authenticationToken;

                if (token) {
                    config.headers.Authorization = 'Bearer ' + token;
                }
                return config;
            } else {
                /*jshint camelcase: false */
                var token = $localStorage.authenticationToken || $sessionStorage.authenticationToken;

                if (token) {
                    config.headers.Authorization = 'Bearer ' + token;
                }

                return config;

            }

        }

        function response(config) {
            /*jshint camelcase: false */
            config = config || {};
            config.headers = config.headers || {};
            var bearerToken = config.headers('Authorization');
            if (angular.isDefined(bearerToken) && bearerToken != null) {
                if (bearerToken.slice(0, 7) === 'Bearer ') {
                    var jwt = bearerToken.slice(7, bearerToken.length);
                    $localStorage.authenticationToken = jwt;
                    window.token = jwt;
                    document.cookie = "jwt=" + jwt;
                }
            }

            return config;
        }
    }
})();
