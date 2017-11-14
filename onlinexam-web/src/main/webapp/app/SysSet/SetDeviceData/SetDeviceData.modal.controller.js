(function() {
    'use strict';
    angular
        .module('webfullApp')
        .controller('SetDeviceDataModalCtrl', SetDeviceDataModalCtrl);

    SetDeviceDataModalCtrl.$inject = ['$scope', '$uibModalInstance', '$http', '$filter', 'charts'];

    function SetDeviceDataModalCtrl($scope, $uibModalInstance, $http, $filter, charts) {
        var $ctrl = this;

        $ctrl.selected = '2';


        $ctrl.ok = function() {
            $uibModalInstance.close();
        };

        $ctrl.cancel = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $ctrl.ChangeChart = function() {
            //alert($ctrl.selected);
        }

        initChart(charts.limitLow, charts.limitHigh);

        function initChart(limitLow, limitHigh) {
            //   CurrentAlarmService.query({ PiName: vm.charts.name, Period: '2' }, chartsSuccess);
            $http.get('app/testData/testData.json').success(function(data) {
                // alert(data);
                console.info(data[0].dps);
                $scope.data = dataForChart(data[0].dps);
            });

            function chartsSuccess(result, headers) {
                alert(response);
            }

            limitLow = 3;
            limitHigh = 3.5;

            function dataForChart(dps) {
                var low = [];
                var high = [];
                var data = [];
                for (var key in dps) {
                    console.info(key);
                    var dt = new Date(key * 1000);
                    data.push({ x: dt, y: dps[key] });
                    low.push({ x: dt, y: limitLow });
                    high.push({ x: dt, y: limitHigh });
                }

                return [{
                    values: high, //values - represents the array of {x,y} data points
                    key: '上限值', //key  - the name of the series.
                    color: '#EF4836', //color - optional: choose your own line color.
                    //   strokeWidth: 2,
                    //   classed: 'dashed'
                }, {
                    values: data,
                    key: charts.name,
                    color: '#0088cc'
                        //   area: true //area - set to true if you want this line to turn into a filled area chart.
                }, {
                    values: low,
                    key: '下限值 ',
                    color: '#EF4836'
                }];
            }





            $scope.options = {
                chart: {
                    type: 'lineChart',
                    height: 450,
                    margin: {
                        top: 20,
                        right: 20,
                        bottom: 40,
                        left: 55
                    },
                    x: function(d) {
                        return d.x;
                    },
                    y: function(d) {
                        return d.y;
                    },
                    useInteractiveGuideline: true,
                    dispatch: {
                        stateChange: function(e) { console.log("stateChange"); },
                        changeState: function(e) { console.log("changeState"); },
                        tooltipShow: function(e) { console.log("tooltipShow"); },
                        tooltipHide: function(e) { console.log("tooltipHide"); }
                    },
                    xAxis: {
                        //axisLabel: '时间'
                        width: 90,
                        // staggerLabels:true,
                        // rotateLabels:10,
                        ticks: 4,
                        tickFormat: function(d) {
                            // return d3.format('.04f')(d);
                            // d3.time.format("%Y-%m-%d %H:%M:%S").parse(d);
                            return $filter('date')(d, 'yyyy-MM-dd hh:mm:ss');
                            // return d;
                        }
                    },
                    yAxis: {
                        //axisLabel: 'Voltage (v)',
                        tickFormat: function(d) {
                            return d3.format('.04f')(d);
                        },
                        axisLabelDistance: -10
                    },
                    callback: function(chart) {
                        console.log("!!! lineChart callback !!!");
                    }
                },
                title: {
                    enable: true,
                    text: charts.name + charts.describe
                }
            };
        }

    }
})();
