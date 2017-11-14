(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('WechatAuthServerProvider', WechatAuthServerProvider);

    WechatAuthServerProvider.$inject = ['$http', '$state', '$localStorage', '$sessionStorage', '$q', 'AuthServerProvider'];

    function WechatAuthServerProvider($http, $state, $localStorage, $sessionStorage, $q, AuthServerProvider) {
        var service = {
            wechatAuth: wechatAuth
        };

        return service;



        function wechatAuth(code) {
            if (code == undefined)
                return;
            return $http.post('web30/api/wechat/authenticate', code).success(authenticateSuccess);

            function authenticateSuccess(data, status, headers) {
                // var bearerToken = headers('Authorization');
                // if (angular.isDefined(bearerToken) && bearerToken.slice(0, 7) === 'Bearer ') {
                //     var jwt = bearerToken.slice(7, bearerToken.length);
                //     service.storeAuthenticationToken(jwt, credentials.rememberMe);

                //     return jwt;
                // }
                //console.info("wechat:"+data);

                var url = "web30/api/webport/v2/auth/UtCloudPortal20160801/" + data.unionid;
                $http.get(url).success(success).error(fail);

                function success(data, status, headers) {
                    var jwt = data.token;
                    var nickname = data.displayName;
                   // var redirecturl = data.redirecturl;
                    AuthServerProvider.storeAuthenticationToken(jwt, nickname);
                    window.location.href = data.redirecturl+'/index1.html#/redirect?jwt='+jwt+'&username='+nickname;
                   // $state.go("home");

                }

                function fail(data, status, headers) {
                    if (data.status == '404' || data.status == '401') {
                        $state.go("about", { message: data.message });
                    }
                }

            }
        }


    }
})();
