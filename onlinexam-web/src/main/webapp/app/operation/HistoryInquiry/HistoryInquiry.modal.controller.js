(function() {
    'use strict';
    angular
        .module('webfullApp')
        .controller('HistoryInquiryModalCtrl', HistoryInquiryModalCtrl);

    HistoryInquiryModalCtrl.$inject = ['$scope', '$uibModalInstance', '$http', '$filter', 'charts', 'HistoryInquiryService'];

    function HistoryInquiryModalCtrl($scope, $uibModalInstance, $http, $filter, charts, HistoryInquiryService) {
        var $ctrl = this;

        $ctrl.selected = '4';


        $ctrl.ok = function() {
            $uibModalInstance.close();
        };

        $ctrl.cancel = function() {
            $uibModalInstance.dismiss('cancel');
        };

        $ctrl.ChangeChart = function() {
            initChart(charts.limitLow, charts.limitHigh);
        }

        initChart(charts.limitLow, charts.limitHigh);

        function initChart(limitLow, limitHigh) {
            HistoryInquiryService.charts.query({ PiName: charts.name, Period: $ctrl.selected }, chartsSuccess);
            // $http.get('app/testData/testData.json').success(function(data) {
            //     // alert(data);
            //     console.info(data[0].dps);
            //     $scope.data = dataForChart(data[0].dps);
            // });

            function chartsSuccess(result, headers) {
                $scope.data = dataForChart(result[0].dps_mid, result[0].dps_min, result[0].dps_max);
            }

            //  limitLow=3;
            // limitHigh=3.5;
            function dataForChart(dps, dps_min, dps_max) {
                var low = [];
                var high = [];
                var data = [];
                var data_min = [];
                var data_max = [];
                // console.info("dps-length:"+dps.length);
                var i = 0;
                for (var key in dps) {
                    // console.info(key);
                    i++;
                    var dt = new Date(key * 1000);
                    data.push({ x: dt, y: dps[key] });
                    low.push({ x: dt, y: limitLow });
                    high.push({ x: dt, y: limitHigh });
                }
                for (var key in dps_min) {
                    var dt = new Date(key * 1000);
                    data_min.push({ x: dt, y: dps_min[key] });
                }
                for (var key in dps_max) {
                    var dt = new Date(key * 1000);
                    data_max.push({ x: dt, y: dps_max[key] });
                }

                console.info("dps-length:" + i);
                return [{
                    values: high, //values - represents the array of {x,y} data points
                    key: '上限值', //key  - the name of the series.
                    //color: '#EF4836', //color - optional: choose your own line color.
                     //  strokeWidth: 2,
                     //  classed: 'dashed'
                     color:'#FF0000'
                }, {
                    values: low,
                    key: '下限值 ',
                  //  color: '#EF4836',
                  color:'#FF0000'
                }, {
                    values: data_max,
                    key: '最大值',
                    color: '#A9A9A9'
                }, {
                    values: data_min,
                    key: '最小值',
                    color: '#A9A9A9'
                }, {
                    values: data,
                    key: charts.name,
                    color: '#0088cc',
                  //  area: true 
                    // strokeWidth: 4,
                    // classed: 'dashed'
                        //   area: true //area - set to true if you want this line to turn into a filled area chart.
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
                            return $filter('date')(d, 'yyyy-MM-dd HH:mm:ss');
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
                    text: charts.name + charts.describe+"("+ $ctrl.selected+"小时)"
                }
            };
        }

    }
})();
