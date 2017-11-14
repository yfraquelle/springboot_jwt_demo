(function() {
    'use strict';

    angular
        .module('webfullApp', [
            'ngStorage',
            //  'tmh.dynamicLocale',
            //  'pascalprecht.translate', 
            'ngResource',
            'ngCookies',
            //  'ngAria',
            //  'ngCacheBuster',
            'angularFileUpload',
            'ui.bootstrap',
            //  'ui.bootstrap.datetimepicker',
            'ui.router',
            'nvd3',
            'ngJsTree'
            //  'infinite-scroll',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            //  'angular-loading-bar'
        ])
        .run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
        console.log("index");
        //    translationHandler.initialize();
    }
})();
