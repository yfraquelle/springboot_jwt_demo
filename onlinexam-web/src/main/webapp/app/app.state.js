(function() {
    'use strict';

    angular
        .module('webfullApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
        //   Auth.authorize();
        $stateProvider.state('app', {
            abstract: true,
            resolve: {
                authorize: ['Auth',
                    function(Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        });

        $stateProvider.state('login', {
            url: '/login:code',
            data: {

            },
            // templateUrl: 'app/login/login.html'

            views: {
                'menuContent@': {
                    templateUrl: 'app/login/login1.html',
                    controller: 'LoginController',
                    controllerAs: 'vm'
                }
            }
        }).state('redirect', {
            url: '/redirect?jwt&username',
            views: {
                'menuContent@': {
                    templateUrl: 'app/login/redirect.html',
                    controller: 'RedirectController',
                    controllerAs: 'vm'
                }

            }
        }).state("home", {
            url: '/',
            parent: 'app',
            data: {
                authorities: ['']
            },
            views: {
                'menuContent@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                },
                '@home': {
                    templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm1.html',
                    controller: 'CurrentAlarmController',
                    controllerAs: 'vm'
                }
            }
        }).state('home.currentAlarm', {
            //  parent: 'app',
            url: 'currentAlarm',
            templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm1.html',
            controller: 'CurrentAlarmController',
            controllerAs: 'vm'
                // views: {
                //      'mainContent@home': {
                //         templateUrl: 'app/operation/CurrentAlarm/CurrentAlarm1.html',
                //         controller:'CurrentAlarmController',
                //         controllerAs: 'vm'
                //     }
                // }
        }).state('home.historyInquiry', {
            //  parent: 'app',
            url: 'historyInquiry',
            templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            controller: 'HistoryInquiryController',
            controllerAs: 'vm'
                // views: {
                //      'mainContent@home': {
                //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
                //    //     controller:'CurrentAlarmController',
                //   //      controllerAs: 'vm'
                //     }
                // }
        }).state('home.deviceData', {
            //  parent: 'app',
            url: 'deviceData',
            templateUrl: 'app/operation/DeviceData/DeviceData.html',
            controller: 'DeviceDataController',
            controllerAs: 'vm'
                // views: {
                //      'mainContent@home': {
                //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
                //    //     controller:'CurrentAlarmController',
                //   //      controllerAs: 'vm'
                //     }
                // }
        }).state('home.user', {
            //  parent: 'app',
            url: 'user',
            templateUrl: 'app/SysSet/User/User.html',
            controller: 'UserController',
            controllerAs: 'vm'
                // views: {
                //      'mainContent@home': {
                //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
                //    //     controller:'CurrentAlarmController',
                //   //      controllerAs: 'vm'
                //     }
                // }
        }).state('home.setDeviceData', {
            //  parent: 'app',
            url: 'setDeviceData',
            templateUrl: 'app/SysSet/SetDeviceData/SetDeviceData.html',
            controller: 'SetDeviceDataController',
            controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        }).state('home.Equipment', {
            //  parent: 'app',
            url: 'Equipment',
            templateUrl: 'app/SysSet/Equipment/Equipment.html',
            controller: 'EquipmentController',
            controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        }).state('home.Device', {
            //  parent: 'app',
            url: 'Device',
            templateUrl: 'app/SysSet/Device/Device.html',
            controller: 'DeviceController',
            controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        }).state('home.DataOption', {
            //  parent: 'app',
            url: 'DataOption',
            templateUrl: 'app/SysSet/DataOption/DataOption.html',
            // controller: 'EquipmentController',
            // controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        }).state('home.SamplingPoint', {
            //  parent: 'app',
            url: 'SamplingPoint',
            templateUrl: 'app/SysSet/SamplingPoint/SamplingPoint.html',
            // controller: 'EquipmentController',
            // controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        }).state('home.Parameter', {
            //  parent: 'app',
            url: 'Parameter',
            templateUrl: 'app/SysSet/Parameter/Parameter.html',
            // controller: 'EquipmentController',
            // controllerAs: 'vm'

            // views: {
            //      'mainContent@home': {
            //       templateUrl: 'app/operation/HistoryInquiry/HistoryInquiry.html',
            //    //     controller:'CurrentAlarmController',
            //   //      controllerAs: 'vm'
            //     }
            // }
        });
     //   $urlRouterProvider.otherwise('/');
    }
})();
