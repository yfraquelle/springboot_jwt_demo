(function() {
    'use strict';

    angular
        .module('webfullApp')
        .factory('UserService', UserService);

    UserService.$inject = ['$resource'];

    function UserService($resource) {
        var service = {};

        service.getUserPage = $resource('web30/oauth/api/v1/profile/', {});
        service.deleteUser = $resource('web30/oauth/api/v1/profile/:uid',{uid:'@uid'});
        service.deleteBatchUser = $resource('web30/oauth/api/v1/profile/batchdelete',{});
        service.saveUser = $resource('web30/oauth/api/v1/profile1/',{});
        service.resetPassword = $resource('web30/oauth/api/v1/resetPassword', {});

        return service;
    }
})();
