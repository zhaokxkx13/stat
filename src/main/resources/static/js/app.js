$(function () {
    // 读取body data-type 判断是哪个页面然后执行相应页面方法，方法在下面。
    var dataType = $('body').attr('data-type');
    console.log(dataType);
    for (key in pageData) {
        if (key == dataType) {
            pageData[key]();
        }
    }
    //     // 判断用户是否已有自己选择的模板风格
    //    if(storageLoad('SelcetColor')){
    //      $('body').attr('class',storageLoad('SelcetColor').Color)
    //    }else{
    //        storageSave(saveSelectColor);
    //        $('body').attr('class','theme-black')
    //    }

    autoLeftNav();
    $(window).resize(function () {
        autoLeftNav();
        console.log($(window).width())
    });

    //    if(storageLoad('SelcetColor')){

    //     }else{
    //       storageSave(saveSelectColor);
    //     }
})

// 页面数据
var pageData = {
    // ===============================================
    // 首页
    // ===============================================
    'index': function indexData() {
        $('#example-r').DataTable({

            bInfo: false, //页脚信息
            dom: 'ti'
        });


        // ==========================
        // 百度图表A http://echarts.baidu.com/
        // ==========================

        var echartsA = echarts.init(document.getElementById('tpl-echarts'));
        var sourceData = [];
        var sourceDate = [];
        option = {
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                top: '3%',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: sourceDate
            }],
            yAxis: [{
                type: 'value'
            }],
            textStyle: {
                color: '#838FA1'
            },
            series: [{
                name: '工业总产值',
                type: 'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data: sourceData,
                itemStyle: {
                    normal: {
                        color: '#1cabdb',
                        borderColor: '#1cabdb',
                        borderWidth: '2',
                        borderType: 'solid',
                        opacity: '1'
                    },
                    emphasis: {}
                }
            }]
        };

        $.get("/indusAll/Year/distribute", function (data) {
            var str = JSON.parse(data);

            for (i = 0; i < str.length; i++) {
                sourceData.push(str[i].indusAll);
                sourceDate.push(str[i].dateStr);
            }
            echartsA.setOption({
                xAxis: {
                    data: sourceDate
                },
                series: [{
                    name: '工业总产值',
                    data: sourceData
                }]
            })
        })
        echartsA.setOption(option);


        var chartKline = echarts.init(document.getElementById('charts-kline'));


        $.getJSON('/kline', function (data) {
            var klineCategory = [];
            var klineValue = [];
            $.each(data, function (i, item) {
                klineCategory.push(item.date);
                var temp = [];
                temp.push(item.open);
                temp.push(item.close);
                temp.push(item.lowest);
                temp.push(item.highest);
                klineValue.push(temp);
            })

            function calculateMA(dayCount) {
                var result = [];
                for (var i = 0, len = klineValue.length; i < len; i++) {
                    if (i < dayCount) {
                        result.push('-');
                        continue;
                    }
                    var sum = 0;
                    for (var j = 0; j < dayCount; j++) {
                        sum += klineValue[i - j][1];
                    }
                    result.push(sum / dayCount);
                }
                return result;
            }

            klineOption = {
                title: {
                    text: '上证指数',
                    left: 0
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'line'
                    }
                },
                legend: {
                    data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
                },
                grid: {
                    left: '10%',
                    right: '10%',
                    bottom: '15%'
                },
                xAxis: {
                    type: 'category',
                    data: klineCategory,
                    scale: true,
                    boundaryGap: false,
                    axisLine: {onZero: false},
                    splitLine: {show: false},
                    splitNumber: 20,
                    min: 'dataMin',
                    max: 'dataMax'
                },
                yAxis: {
                    scale: true,
                    splitArea: {
                        show: true
                    }
                },
                dataZoom: [
                    {
                        type: 'inside',
                        start: 50,
                        end: 100
                    },
                    {
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 50,
                        end: 100
                    }
                ],
                series: [
                    {
                        name: '日K',
                        type: 'candlestick',
                        data: klineValue,
                        markPoint: {
                            label: {
                                normal: {
                                    formatter: function (param) {
                                        return param != null ? Math.round(param.value) : '';
                                    }
                                }
                            },
                            data: [
                                {
                                    name: 'XX标点',
                                    coord: ['2013/5/31', 2300],
                                    value: 2300,
                                    itemStyle: {
                                        normal: {color: 'rgb(41,60,85)'}
                                    }
                                },
                                {
                                    name: 'highest value',
                                    type: 'max',
                                    valueDim: 'highest'
                                },
                                {
                                    name: 'lowest value',
                                    type: 'min',
                                    valueDim: 'lowest'
                                },
                                {
                                    name: 'average value on close',
                                    type: 'average',
                                    valueDim: 'close'
                                }
                            ],
                            tooltip: {
                                formatter: function (param) {
                                    return param.name + '<br>' + (param.data.coord || '');
                                }
                            }
                        },
                        markLine: {
                            symbol: ['none', 'none'],
                            data: [
                                // [
                                //     {
                                //         name: 'from lowest to highest',
                                //         type: 'min',
                                //         valueDim: 'lowest',
                                //         symbol: 'circle',
                                //         symbolSize: 10,
                                //         label: {
                                //             normal: {show: false},
                                //             emphasis: {show: false}
                                //         }
                                //     },
                                //     {
                                //         type: 'max',
                                //         valueDim: 'highest',
                                //         symbol: 'circle',
                                //         symbolSize: 10,
                                //         label: {
                                //             normal: {show: false},
                                //             emphasis: {show: false}
                                //         }
                                //     }
                                // ],
                                {
                                    name: 'min line on close',
                                    type: 'min',
                                    valueDim: 'close'
                                },
                                {
                                    name: 'max line on close',
                                    type: 'max',
                                    valueDim: 'close'
                                }
                            ]
                        }
                    },
                    {
                        name: 'MA5',
                        type: 'line',
                        data: calculateMA(5),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA10',
                        type: 'line',
                        data: calculateMA(10),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA20',
                        type: 'line',
                        data: calculateMA(20),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },
                    {
                        name: 'MA30',
                        type: 'line',
                        data: calculateMA(30),
                        smooth: true,
                        lineStyle: {
                            normal: {opacity: 0.5}
                        }
                    },

                ]
            };

            chartKline.setOption(klineOption);
        })


    },
    // ===============================================
    // 图表页
    // ===============================================
    'chart': function chartData() {
        // ==========================
        // 百度图表A http://echarts.baidu.com/
        // ==========================

        var echartsC = echarts.init(document.getElementById('tpl-echarts-C'));


        optionC = {
            tooltip: {
                trigger: 'axis'
            },

            legend: {
                data: ['蒸发量', '降水量', '平均温度']
            },
            xAxis: [{
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }],
            yAxis: [{
                type: 'value',
                name: '水量',
                min: 0,
                max: 250,
                interval: 50,
                axisLabel: {
                    formatter: '{value} ml'
                }
            },
                {
                    type: 'value',
                    name: '温度',
                    min: 0,
                    max: 25,
                    interval: 5,
                    axisLabel: {
                        formatter: '{value} °C'
                    }
                }
            ],
            series: [{
                name: '蒸发量',
                type: 'bar',
                data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
                {
                    name: '降水量',
                    type: 'bar',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                },
                {
                    name: '平均温度',
                    type: 'line',
                    yAxisIndex: 1,
                    data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                }
            ]
        };

        echartsC.setOption(optionC);

        var echartsB = echarts.init(document.getElementById('tpl-echarts-B'));
        optionB = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                x: 'center',
                data: ['某软件', '某主食手机', '某水果手机', '降水量', '蒸发量']
            },
            radar: [{
                indicator: [
                    {text: '品牌', max: 100},
                    {text: '内容', max: 100},
                    {text: '可用性', max: 100},
                    {text: '功能', max: 100}
                ],
                center: ['25%', '40%'],
                radius: 80
            },
                {
                    indicator: [
                        {text: '外观', max: 100},
                        {text: '拍照', max: 100},
                        {text: '系统', max: 100},
                        {text: '性能', max: 100},
                        {text: '屏幕', max: 100}
                    ],
                    radius: 80,
                    center: ['50%', '60%'],
                },
                {
                    indicator: (function () {
                        var res = [];
                        for (var i = 1; i <= 12; i++) {
                            res.push({text: i + '月', max: 100});
                        }
                        return res;
                    })(),
                    center: ['75%', '40%'],
                    radius: 80
                }
            ],
            series: [{
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [{
                    value: [60, 73, 85, 40],
                    name: '某软件'
                }]
            },
                {
                    type: 'radar',
                    radarIndex: 1,
                    data: [{
                        value: [85, 90, 90, 95, 95],
                        name: '某主食手机'
                    },
                        {
                            value: [95, 80, 95, 90, 93],
                            name: '某水果手机'
                        }
                    ]
                },
                {
                    type: 'radar',
                    radarIndex: 2,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [{
                        name: '降水量',
                        value: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 75.6, 82.2, 48.7, 18.8, 6.0, 2.3],
                    },
                        {
                            name: '蒸发量',
                            value: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 35.6, 62.2, 32.6, 20.0, 6.4, 3.3]
                        }
                    ]
                }
            ]
        };
        echartsB.setOption(optionB);
        var echartsA = echarts.init(document.getElementById('tpl-echarts-A'));
        option = {

            tooltip: {
                trigger: 'axis',
            },
            legend: {
                data: ['邮件', '媒体', '资源']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: true,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }],

            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '邮件',
                type: 'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data: [120, 132, 101, 134, 90, 230, 210],
                itemStyle: {
                    normal: {
                        color: '#59aea2'
                    },
                    emphasis: {}
                }
            },
                {
                    name: '媒体',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: [220, 182, 191, 234, 290, 330, 310],
                    itemStyle: {
                        normal: {
                            color: '#e7505a'
                        }
                    }
                },
                {
                    name: '资源',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: [150, 232, 201, 154, 190, 330, 410],
                    itemStyle: {
                        normal: {
                            color: '#32c5d2'
                        }
                    }
                }
            ]
        };
        echartsA.setOption(option);
    },
    'table-income': function charDatea() {
        var echartsA = echarts.init(document.getElementById('charts-income'));

        var sourceDate = [];
        var mainServiceIncome = [];
        var indusAll = [];
        var techIncome = [];
        var htechIncome = [];
        var productIncome = [];
        var goodsIncome = [];

        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {type: 'cross'}
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['主营业务收入', '技术收入', '高新技术产品收入', '产品销售收入', '商品销售收入']
            },
            xAxis: [
                {
                    type: 'category',
                    data: sourceDate
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '金额',
                    axisLabel: {
                        formatter: '{value} 万元'
                    }
                },
                {
                    type: 'value',
                    name: '工业总产值',
                    axisLabel: {
                        formatter: '{value} 万元'
                    }
                }
            ],
            series: [
                {
                    name: '主营业务收入',
                    type: 'bar',
                    data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                },
                {
                    name: '技术收入',
                    type: 'bar',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                },
                {
                    name: '高新技术产品收入',
                    type: 'bar',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                },
                {
                    name: '产品销售收入',
                    type: 'bar',
                    data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                },
                {
                    name: '商品销售收入',
                    type: 'bar',
                    data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                },
                {
                    name: '工业总产值',
                    type: 'line',
                    yAxisIndex: 1,
                    data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
                }
            ]


        };

        echartsA.setOption(option);
        echartsA.showLoading();
        $.get("/indusAll/Year/distribute", function (data) {
            var str = JSON.parse(data);

            for (i = 0; i < str.length; i++) {
                sourceDate.push(str[i].dateStr);
                mainServiceIncome.push(str[i].mainServiceIncome);
                indusAll.push(str[i].indusAll);
                techIncome.push(str[i].techIncome);
                htechIncome.push(str[i].htProductSellIncome);
                productIncome.push(str[i].productSellIncome);
                goodsIncome.push(str[i].goodsSellIncome);
            }
            echartsA.hideLoading();
            echartsA.setOption({
                xAxis: {
                    data: sourceDate
                },
                series: [{
                    name: '工业总产值',
                    data: indusAll
                },
                    {
                        name: '商品销售收入',
                        data: goodsIncome
                    },
                    {
                        name: '高新技术产品收入',
                        data: htechIncome
                    },
                    {
                        name: '技术收入',
                        data: techIncome
                    },
                    {
                        name: '主营业务收入',
                        data: mainServiceIncome
                    },
                    {
                        name: '产品销售收入',
                        data: productIncome
                    }

                ]
            })
        })
    },
    'sell-kpi': function charData() {
        var consumerTop = echarts.init(document.getElementById('consumerSort'));
        var departmentSellKpi = echarts.init(document.getElementById('departmentSellKpi'));
        consumerTop.showLoading();
        $.get('/top/consumers', function (data) {
            consumerTop.hideLoading();
            var str = JSON.parse(data);
            var companyName = [];
            var sells = [];
            for (i = 0; i < str.length; i++) {
                companyName.push(str[i].customer.company.name);
                sells.push(str[i].moneyAmount);
            }
            var topConsumeOption = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: "{c}"
                },
                legend: {
                    data: ['2016年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01],
                    "axisLabel": {
                        "interval": 0,
                        formatter: '{value}',
                    }
                },
                yAxis: {
                    type: 'category',
                    data: companyName
                },
                series: [{
                    name: '客户贡献',
                    type: 'bar',
                    data: sells
                }]
            };

            consumerTop.setOption(topConsumeOption);
        })

        departmentSellKpi.showLoading();
        $.get("/department/kpi", function (data) {
            departmentSellKpi.hideLoading();
            var departmentName = [];
            var planSell = [];
            var actSell = [];
            var persent = [];
            var str = JSON.parse(data);

            for (i = 0; i < str.length; i++) {
                departmentName.push(str[i].departmentName);
                planSell.push(str[i].planSell);
                actSell.push(str[i].actSell);
                persent.push(str[i].persent);
            }
            var option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {type: 'cross'}
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data: ['计划额', '销售额', '完成率']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: departmentName
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '计划额',
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    },
                    {
                        type: 'value',
                        name: '销售额',
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    }
                ],
                series: [
                    {
                        name: '计划额',
                        type: 'bar',
                        data: planSell
                    },
                    {
                        name: '销售额',
                        type: 'bar',
                        data: actSell
                    },
                    {
                        name: '完成率',
                        type: 'line',
                        yAxisIndex: 1,
                        data: persent
                    }
                ]
            };
            departmentSellKpi.setOption(option);
        })

    },
    'customer-details': function charData() {
        var bigTypeChart = echarts.init(document.getElementById('charts-customer-big-type'));
        var smallTypeChart = echarts.init(document.getElementById('charts-customer-small-type'));
        var payTypeChart = echarts.init(document.getElementById('charts-customer-pay-type'));
        bigTypeChart.showLoading();
        smallTypeChart.showLoading();
        $.get("/customer/details", function (str) {
            bigTypeChart.hideLoading();
            smallTypeChart.hideLoading();
            var data = JSON.parse(str);
            var bigKeys = Object.keys(data.bigTypeSum);
            var smallKeys = Object.keys(data.smallTypeSum);
            var typeKeys = Object.keys(data.payType);
            var bigValues = [];
            var smallValues = [];
            var typeValues = [];
            for (var item in data.bigTypeSum) {
                var value = data.bigTypeSum[item];
                bigValues.push(value);
            }
            for (var item in data.smallTypeSum) {
                var value = data.smallTypeSum[item];
                smallValues.push(value);
            }
            for (var item in data.payType) {
                var value = data.payType[item];
                typeValues.push(value);
            }
            var smallTypeData = [];
            for (var i = 0; i < smallKeys.length; i++) {
                var temp = new Object();
                temp.name = smallKeys[i];
                temp.value = smallValues[i];
                smallTypeData.push(temp);
            }
            var payTypeData = [];
            for (var i = 0; i < typeKeys.length; i++) {
                var temp = new Object();
                temp.name = typeKeys[i];
                temp.value = typeValues[i];
                payTypeData.push(temp);
            }

            var bigOption = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                },
                legend: {
                    data: '销售量'
                },
                grid: {
                    left: '5%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: bigKeys
                },
                series: [
                    {
                        name: '销售量',
                        type: 'bar',
                        data: bigValues
                    }
                ]
            };
            bigTypeChart.setOption(bigOption);

            var smallOption = {
                grid: {
                    left: '5%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: smallKeys
                },
                label: {
                    normal: {
                        formatter: "{b} ({d}%)",
                        position: "insideTopRight"
                    }
                },
                labelLine: {
                    normal: {
                        smooth: .6
                    }
                },
                toolbox: {
                    show: !0,
                    feature: {
                        mark: {
                            show: !0
                        },
                        dataView: {
                            show: !0,
                            readOnly: !1
                        },
                        magicType: {
                            show: !0,
                            type: ["pie", "funnel"]
                        },
                        restore: {
                            show: !0
                        },
                        saveAsImage: {
                            show: !0
                        }
                    }
                },
                calculable: !0,
                series: [{
                    name: "商品小分类",
                    type: "pie",
                    roseType: "area",
                    label: {
                        normal: {
                            show: !0
                        },
                        emphasis: {
                            show: !0
                        }
                    },
                    lableLine: {
                        normal: {
                            show: !0
                        },
                        emphasis: {
                            show: !0
                        }
                    },
                    data: smallTypeData.sort(function (a, b) {
                        return a.value - b.value
                    })
                }]
            };
            smallTypeChart.setOption(smallOption);

            var typeOption = {
                grid: {
                    left: '5%',
                    right: '5%',
                    bottom: '5%',
                    containLabel: true
                },
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: typeKeys
                },
                label: {
                    normal: {
                        formatter: "{b} ({d}%)",
                        position: "insideTopRight"
                    }
                },
                labelLine: {
                    normal: {
                        smooth: .6
                    }
                },
                toolbox: {
                    show: !0,
                    feature: {
                        mark: {
                            show: !0
                        },
                        dataView: {
                            show: !0,
                            readOnly: !1
                        },
                        magicType: {
                            show: !0,
                            type: ["pie", "funnel"]
                        },
                        restore: {
                            show: !0
                        },
                        saveAsImage: {
                            show: !0
                        }
                    }
                },
                calculable: !0,
                series: [{
                    name: "应收账款",
                    type: "pie",
                    roseType: "area",
                    label: {
                        normal: {
                            show: !0
                        },
                        emphasis: {
                            show: !0
                        }
                    },
                    lableLine: {
                        normal: {
                            show: !0
                        },
                        emphasis: {
                            show: !0
                        }
                    },
                    data: payTypeData.sort(function (a, b) {
                        return a.value - b.value
                    })
                }]
            };
            payTypeChart.setOption(typeOption);
        })
    },
    'sell-net': function () {
        var geoChart = echarts.init(document.getElementById('charts-city-sells'));

        geoChart.showLoading();
        $.get("/area/details", function (jsonStr) {
            geoChart.hideLoading();
            var geoCoordMap = {
                "海门": [121.15, 31.89],
                "鄂尔多斯": [109.781327, 39.608266],
                "招远": [120.38, 37.35],
                "舟山": [122.207216, 29.985295],
                "齐齐哈尔": [123.97, 47.33],
                "盐城": [120.13, 33.38],
                "赤峰": [118.87, 42.28],
                "青岛": [120.33, 36.07],
                "乳山": [121.52, 36.89],
                "金昌": [102.188043, 38.520089],
                "泉州": [118.58, 24.93],
                "莱西": [120.53, 36.86],
                "日照": [119.46, 35.42],
                "胶南": [119.97, 35.88],
                "南通": [121.05, 32.08],
                "拉萨": [91.11, 29.97],
                "云浮": [112.02, 22.93],
                "梅州": [116.1, 24.55],
                "文登": [122.05, 37.2],
                "上海": [121.48, 31.22],
                "攀枝花": [101.718637, 26.582347],
                "威海": [122.1, 37.5],
                "承德": [117.93, 40.97],
                "厦门": [118.1, 24.46],
                "汕尾": [115.375279, 22.786211],
                "潮州": [116.63, 23.68],
                "丹东": [124.37, 40.13],
                "太仓": [121.1, 31.45],
                "曲靖": [103.79, 25.51],
                "烟台": [121.39, 37.52],
                "福州": [119.3, 26.08],
                "瓦房店": [121.979603, 39.627114],
                "即墨": [120.45, 36.38],
                "抚顺": [123.97, 41.97],
                "玉溪": [102.52, 24.35],
                "张家口": [114.87, 40.82],
                "阳泉": [113.57, 37.85],
                "莱州": [119.942327, 37.177017],
                "湖州": [120.1, 30.86],
                "汕头": [116.69, 23.39],
                "昆山": [120.95, 31.39],
                "宁波": [121.56, 29.86],
                "湛江": [110.359377, 21.270708],
                "揭阳": [116.35, 23.55],
                "荣成": [122.41, 37.16],
                "连云港": [119.16, 34.59],
                "葫芦岛": [120.836932, 40.711052],
                "常熟": [120.74, 31.64],
                "东莞": [113.75, 23.04],
                "河源": [114.68, 23.73],
                "淮安": [119.15, 33.5],
                "泰州": [119.9, 32.49],
                "南宁": [108.33, 22.84],
                "营口": [122.18, 40.65],
                "惠州": [114.4, 23.09],
                "江阴": [120.26, 31.91],
                "蓬莱": [120.75, 37.8],
                "韶关": [113.62, 24.84],
                "嘉峪关": [98.289152, 39.77313],
                "广州": [113.23, 23.16],
                "延安": [109.47, 36.6],
                "太原": [112.53, 37.87],
                "清远": [113.01, 23.7],
                "中山": [113.38, 22.52],
                "昆明": [102.73, 25.04],
                "寿光": [118.73, 36.86],
                "盘锦": [122.070714, 41.119997],
                "长治": [113.08, 36.18],
                "深圳": [114.07, 22.62],
                "珠海": [113.52, 22.3],
                "宿迁": [118.3, 33.96],
                "咸阳": [108.72, 34.36],
                "铜川": [109.11, 35.09],
                "平度": [119.97, 36.77],
                "佛山": [113.11, 23.05],
                "海口": [110.35, 20.02],
                "江门": [113.06, 22.61],
                "章丘": [117.53, 36.72],
                "肇庆": [112.44, 23.05],
                "大连": [121.62, 38.92],
                "临汾": [111.5, 36.08],
                "吴江": [120.63, 31.16],
                "石嘴山": [106.39, 39.04],
                "沈阳": [123.38, 41.8],
                "苏州": [120.62, 31.32],
                "茂名": [110.88, 21.68],
                "嘉兴": [120.76, 30.77],
                "长春": [125.35, 43.88],
                "胶州": [120.03336, 36.264622],
                "银川": [106.27, 38.47],
                "张家港": [120.555821, 31.875428],
                "三门峡": [111.19, 34.76],
                "锦州": [121.15, 41.13],
                "南昌": [115.89, 28.68],
                "柳州": [109.4, 24.33],
                "三亚": [109.511909, 18.252847],
                "自贡": [104.778442, 29.33903],
                "吉林": [126.57, 43.87],
                "阳江": [111.95, 21.85],
                "泸州": [105.39, 28.91],
                "西宁": [101.74, 36.56],
                "宜宾": [104.56, 29.77],
                "呼和浩特": [111.65, 40.82],
                "成都": [104.06, 30.67],
                "大同": [113.3, 40.12],
                "镇江": [119.44, 32.2],
                "桂林": [110.28, 25.29],
                "张家界": [110.479191, 29.117096],
                "宜兴": [119.82, 31.36],
                "北海": [109.12, 21.49],
                "西安": [108.95, 34.27],
                "金坛": [119.56, 31.74],
                "东营": [118.49, 37.46],
                "牡丹江": [129.58, 44.6],
                "遵义": [106.9, 27.7],
                "绍兴": [120.58, 30.01],
                "扬州": [119.42, 32.39],
                "常州": [119.95, 31.79],
                "潍坊": [119.1, 36.62],
                "重庆": [106.54, 29.59],
                "台州": [121.420757, 28.656386],
                "南京": [118.78, 32.04],
                "滨州": [118.03, 37.36],
                "贵阳": [106.71, 26.57],
                "无锡": [120.29, 31.59],
                "本溪": [123.73, 41.3],
                "克拉玛依": [84.77, 45.59],
                "渭南": [109.5, 34.52],
                "马鞍山": [118.48, 31.56],
                "宝鸡": [107.15, 34.38],
                "焦作": [113.21, 35.24],
                "句容": [119.16, 31.95],
                "北京": [116.46, 39.92],
                "徐州": [117.2, 34.26],
                "衡水": [115.72, 37.72],
                "包头": [110, 40.58],
                "绵阳": [104.73, 31.48],
                "乌鲁木齐": [87.68, 43.77],
                "枣庄": [117.57, 34.86],
                "杭州": [120.19, 30.26],
                "淄博": [118.05, 36.78],
                "鞍山": [122.85, 41.12],
                "溧阳": [119.48, 31.43],
                "库尔勒": [86.06, 41.68],
                "安阳": [114.35, 36.1],
                "开封": [114.35, 34.79],
                "济南": [117, 36.65],
                "德阳": [104.37, 31.13],
                "温州": [120.65, 28.01],
                "九江": [115.97, 29.71],
                "邯郸": [114.47, 36.6],
                "临安": [119.72, 30.23],
                "兰州": [103.73, 36.03],
                "沧州": [116.83, 38.33],
                "临沂": [118.35, 35.05],
                "南充": [106.110698, 30.837793],
                "天津": [117.2, 39.13],
                "富阳": [119.95, 30.07],
                "泰安": [117.13, 36.18],
                "诸暨": [120.23, 29.71],
                "郑州": [113.65, 34.76],
                "哈尔滨": [126.63, 45.75],
                "聊城": [115.97, 36.45],
                "芜湖": [118.38, 31.33],
                "唐山": [118.02, 39.63],
                "平顶山": [113.29, 33.75],
                "邢台": [114.48, 37.05],
                "德州": [116.29, 37.45],
                "济宁": [116.59, 35.38],
                "荆州": [112.239741, 30.335165],
                "宜昌": [111.3, 30.7],
                "义乌": [120.06, 29.32],
                "丽水": [119.92, 28.45],
                "洛阳": [112.44, 34.7],
                "秦皇岛": [119.57, 39.95],
                "株洲": [113.16, 27.83],
                "石家庄": [114.48, 38.03],
                "莱芜": [117.67, 36.19],
                "常德": [111.69, 29.05],
                "保定": [115.48, 38.85],
                "湘潭": [112.91, 27.87],
                "金华": [119.64, 29.12],
                "岳阳": [113.09, 29.37],
                "长沙": [113, 28.21],
                "衢州": [118.88, 28.97],
                "廊坊": [116.7, 39.53],
                "菏泽": [115.480656, 35.23375],
                "合肥": [117.27, 31.86],
                "武汉": [114.31, 30.52],
                "大庆": [125.03, 46.58]
            };
            var json = JSON.parse(jsonStr);
            var data = [];
            for (var item in json.citySells) {
                data.push({
                    name: item,
                    value: json.citySells[item]
                })
            }

            var convertData = function (data) {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    var geoCoord = geoCoordMap[data[i].name];
                    if (geoCoord) {
                        res.push({
                            name: data[i].name,
                            value: geoCoord.concat(data[i].value)
                        });
                    }
                }
                return res;
            };

            var convertedData = [
                convertData(data),
                convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6))
            ];


            var geoOption = {
                backgroundColor: '#ecf8ff',
                animation: true,
                animationDuration: 1000,
                animationEasing: 'cubicInOut',
                animationDurationUpdate: 1000,
                animationEasingUpdate: 'cubicInOut',
                title: [
                    {
                        text: '地区销售分布',
                        left: 'center',
                        textStyle: {
                            color: '#0e0e0e'
                        }
                    },
                    {
                        id: 'statistic',
                        right: 120,
                        top: 40,
                        width: 100,
                        textStyle: {
                            color: '#0e0e0e',
                            fontSize: 8
                        }
                    }
                ],
                toolbox: {
                    iconStyle: {
                        normal: {
                            borderColor: '#0e0e0e'
                        },
                        emphasis: {
                            borderColor: '#b1e4ff'
                        }
                    }
                },
                brush: {
                    outOfBrush: {
                        color: '#ecf8ff'
                    },
                    brushStyle: {
                        borderWidth: 2,
                        color: 'rgba(0,0,0,0.2)',
                        borderColor: 'rgba(0,0,0,0.5)',
                    },
                    seriesIndex: [0, 1],
                    throttleType: 'debounce',
                    throttleDelay: 300,
                    geoIndex: 0
                },
                geo: {
                    map: 'china',
                    left: '10',
                    right: '35%',
                    center: [117.98561551896913, 31.205000490896193],
                    zoom: 2.5,
                    label: {
                        emphasis: {
                            show: false
                        }
                    },
                    roam: true,
                    itemStyle: {
                        normal: {
                            areaColor: '#52bbc7',
                            borderColor: '#0e0e0e'
                        },
                        emphasis: {
                            areaColor: '#2a333d'
                        }
                    }
                },
                tooltip: {
                    trigger: 'item'
                },
                grid: {
                    right: 40,
                    top: 100,
                    bottom: 40,
                    width: '30%'
                },
                xAxis: {
                    type: 'value',
                    scale: true,
                    position: 'top',
                    boundaryGap: false,
                    splitLine: {show: false},
                    axisLine: {show: false},
                    axisTick: {show: false},
                    axisLabel: {margin: 2, textStyle: {color: '#9980da'}},
                },
                yAxis: {
                    type: 'category',
                    name: 'TOP 20',
                    nameGap: 16,
                    axisLine: {show: false, lineStyle: {color: '#414141'}},
                    axisTick: {show: false, lineStyle: {color: '#373737'}},
                    axisLabel: {interval: 0, textStyle: {color: '#0e0e0e'}},
                    data: []
                },
                series: [
                    {
                        name: 'route success rate',
                        type: 'scatter',
                        coordinateSystem: 'geo',
                        data: convertedData[0],
                        symbolSize: 8,
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#a82e2d'
                            }
                        }
                    },
                    {
                        name: 'Top 5',
                        type: 'effectScatter',
                        coordinateSystem: 'geo',
                        data: convertedData[1],
                        symbolSize: 8,
                        showEffectOn: 'emphasis',
                        rippleEffect: {
                            brushType: 'stroke'
                        },
                        hoverAnimation: true,
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: true
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#a82e2d',
                                shadowBlur: 10,
                                shadowColor: '#333'
                            }
                        },
                        zlevel: 1
                    },
                    {
                        id: 'bar',
                        zlevel: 2,
                        type: 'bar',
                        symbol: 'none',
                        itemStyle: {
                            normal: {
                                color: '#a82e2d'
                            }
                        },
                        data: []
                    }
                ]
            };


            geoChart.on('brushselected', renderBrushed);

            geoChart.setOption(geoOption);

            setTimeout(function () {
                geoChart.dispatchAction({
                    type: 'brush',
                    areas: [
                        {
                            geoIndex: 0,
                            brushType: 'polygon',
                            coordRange: [[119.72, 34.85], [119.68, 34.85], [119.5, 34.84], [119.19, 34.77], [118.76, 34.63], [118.6, 34.6], [118.46, 34.6], [118.33, 34.57], [118.05, 34.56], [117.6, 34.56], [117.41, 34.56], [117.25, 34.56], [117.11, 34.56], [117.02, 34.56], [117, 34.56], [116.94, 34.56], [116.94, 34.55], [116.9, 34.5], [116.88, 34.44], [116.88, 34.37], [116.88, 34.33], [116.88, 34.24], [116.92, 34.15], [116.98, 34.09], [117.05, 34.06], [117.19, 33.96], [117.29, 33.9], [117.43, 33.8], [117.49, 33.75], [117.54, 33.68], [117.6, 33.65], [117.62, 33.61], [117.64, 33.59], [117.68, 33.58], [117.7, 33.52], [117.74, 33.5], [117.74, 33.46], [117.8, 33.44], [117.82, 33.41], [117.86, 33.37], [117.9, 33.3], [117.9, 33.28], [117.9, 33.27], [118.09, 32.97], [118.21, 32.7], [118.29, 32.56], [118.31, 32.5], [118.35, 32.46], [118.35, 32.42], [118.35, 32.36], [118.35, 32.34], [118.37, 32.24], [118.37, 32.14], [118.37, 32.09], [118.44, 32.05], [118.46, 32.01], [118.54, 31.98], [118.6, 31.93], [118.68, 31.86], [118.72, 31.8], [118.74, 31.78], [118.76, 31.74], [118.78, 31.7], [118.82, 31.64], [118.82, 31.62], [118.86, 31.58], [118.86, 31.55], [118.88, 31.54], [118.88, 31.52], [118.9, 31.51], [118.91, 31.48], [118.93, 31.43], [118.95, 31.4], [118.97, 31.39], [118.97, 31.37], [118.97, 31.34], [118.97, 31.27], [118.97, 31.21], [118.97, 31.17], [118.97, 31.12], [118.97, 31.02], [118.97, 30.93], [118.97, 30.87], [118.97, 30.85], [118.95, 30.8], [118.95, 30.77], [118.95, 30.76], [118.93, 30.7], [118.91, 30.63], [118.91, 30.61], [118.91, 30.6], [118.9, 30.6], [118.88, 30.54], [118.88, 30.51], [118.86, 30.51], [118.86, 30.46], [118.72, 30.18], [118.68, 30.1], [118.66, 30.07], [118.62, 29.91], [118.56, 29.73], [118.52, 29.63], [118.48, 29.51], [118.44, 29.42], [118.44, 29.32], [118.43, 29.19], [118.43, 29.14], [118.43, 29.08], [118.44, 29.05], [118.46, 29.05], [118.6, 28.95], [118.64, 28.94], [119.07, 28.51], [119.25, 28.41], [119.36, 28.28], [119.46, 28.19], [119.54, 28.13], [119.66, 28.03], [119.78, 28], [119.87, 27.94], [120.03, 27.86], [120.17, 27.79], [120.23, 27.76], [120.3, 27.72], [120.42, 27.66], [120.52, 27.64], [120.58, 27.63], [120.64, 27.63], [120.77, 27.63], [120.89, 27.61], [120.97, 27.6], [121.07, 27.59], [121.15, 27.59], [121.28, 27.59], [121.38, 27.61], [121.56, 27.73], [121.73, 27.89], [122.03, 28.2], [122.3, 28.5], [122.46, 28.72], [122.5, 28.77], [122.54, 28.82], [122.56, 28.82], [122.58, 28.85], [122.6, 28.86], [122.61, 28.91], [122.71, 29.02], [122.73, 29.08], [122.93, 29.44], [122.99, 29.54], [123.03, 29.66], [123.05, 29.73], [123.16, 29.92], [123.24, 30.02], [123.28, 30.13], [123.32, 30.29], [123.36, 30.36], [123.36, 30.55], [123.36, 30.74], [123.36, 31.05], [123.36, 31.14], [123.36, 31.26], [123.38, 31.42], [123.46, 31.74], [123.48, 31.83], [123.48, 31.95], [123.46, 32.09], [123.34, 32.25], [123.22, 32.39], [123.12, 32.46], [123.07, 32.48], [123.05, 32.49], [122.97, 32.53], [122.91, 32.59], [122.83, 32.81], [122.77, 32.87], [122.71, 32.9], [122.56, 32.97], [122.38, 33.05], [122.3, 33.12], [122.26, 33.15], [122.22, 33.21], [122.22, 33.3], [122.22, 33.39], [122.18, 33.44], [122.07, 33.56], [121.99, 33.69], [121.89, 33.78], [121.69, 34.02], [121.66, 34.05], [121.64, 34.08]]
                        }
                    ]
                });
            }, 0);


            function renderBrushed(params) {
                var mainSeries = params.batch[0].selected[0];

                var selectedItems = [];
                var categoryData = [];
                var barData = [];
                var maxBar = 30;
                var sum = 0;
                var count = 0;

                for (var i = 0; i < mainSeries.dataIndex.length; i++) {
                    var rawIndex = mainSeries.dataIndex[i];
                    var dataItem = convertedData[0][rawIndex];
                    var pmValue = dataItem.value[2];

                    sum += pmValue;
                    count++;

                    selectedItems.push(dataItem);
                }

                selectedItems.sort(function (a, b) {
                    return a.value[2] - b.value[2];
                });

                for (var i = 0; i < Math.min(selectedItems.length, maxBar); i++) {
                    categoryData.push(selectedItems[i].name);
                    barData.push(selectedItems[i].value[2]);
                }

                this.setOption({
                    yAxis: {
                        data: categoryData
                    },
                    xAxis: {
                        axisLabel: {show: !!count}
                    },
                    title: {
                        id: 'statistic',
                        text: count ? '平均: ' + (sum / count).toFixed(4) : ''
                    },
                    series: {
                        id: 'bar',
                        data: barData
                    }
                });
            }
        })

        var predictChart = echarts.init(document.getElementById('charts-month-predict'));

        predictChart.showLoading();

        $.get("/month/predict", function (jsonStr) {
            predictChart.hideLoading();
            var data = [];
            var json = JSON.parse(jsonStr);
            for (var item in json) {
                var month = json[item].month;
                var value = json[item].sells;
                var temp = [];
                temp.push(month);
                temp.push(value);
                data.push(temp);
            }

            var myRegression = ecStat.regression('exponential', data);

            myRegression.points.sort(function (a, b) {
                return a[0] - b[0];
            });

            predictChart.setOption({
                title: {
                    left: 'center',
                    top: 21
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                xAxis: {
                    type: 'value',
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    },
                    splitNumber: 20
                },
                yAxis: {
                    type: 'value',
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    }
                },
                series: [{
                    name: 'scatter',
                    type: 'scatter',
                    label: {
                        emphasis: {
                            show: true
                        }
                    },
                    data: data
                }, {
                    name: 'line',
                    type: 'line',
                    showSymbol: false,
                    smooth: true,
                    data: myRegression.points,
                    markPoint: {
                        itemStyle: {
                            normal: {
                                color: 'transparent'
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'left',
                                formatter: myRegression.expression,
                                textStyle: {
                                    color: '#333',
                                    fontSize: 14
                                }
                            }
                        },
                        data: [{
                            coord: myRegression.points[myRegression.points.length - 1]
                        }]
                    }
                }]
            });
        })
    },
    'finance-kpi': function () {
        var radarChart = echarts.init(document.getElementById('finance-kpi-radar'));
        radarChart.showLoading();
        $.get('/finance/kpiData', function (data) {
            radarChart.hideLoading();
            var json = JSON.parse(data);
            var cashRate = json['现金比率'];
            var ciChanFuZhai = json['资产负债率'];
            var yinyezengzhang = json['营业利润增长率'];
            var quanyijinglv = json['权益净利率'];
            var yingyelirun = json['营业利润率'];
            var radarOption = {
                title: {
                    text: '基础雷达图'
                },
                tooltip: {},
                legend: {
                    data: ['预算分配（Allocated Budget）', '实际开销（Actual Spending）']
                },
                radar: {
                    // shape: 'circle',
                    indicator: [
                        {name: '现金比率（sales）', max: 300},
                        {name: '资产负债率（Administration）', max: 300},
                        {name: '营业利润增长率（Information Techology）', max: 1000},
                        {name: '权益净利率（Customer Support）', max: 300},
                        {name: '营业利润率（Development）', max: 300}
                    ]
                },
                series: [{
                    name: '关键财务指标',
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data: [
                        {
                            value: [cashRate, ciChanFuZhai, yinyezengzhang, quanyijinglv, yingyelirun],
                            name: '关键财务指标'
                        }
                    ]
                }]
            };
            radarChart.setOption(radarOption);
        })
    },
    'humanresouce-kpi': function () {
        var employeeNumChart = echarts.init(document.getElementById('employeeNums-chart'));
        var employeeFlowChart = echarts.init(document.getElementById('employeeFlow-chart'));
        $.get('/humanResource/kpi/employeeNum', function (data) {
            var json = JSON.parse(data);
            var companyNames = Object.keys(json);
            var values = [];
            for (var item in json) {
                values.push(json[item])
            }
            var option = {
                title: {
                    text: '各公司员工人数'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['公司人数']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: companyNames
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '人数',
                        type: 'bar',
                        data: values,
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    // build a color map as your need.
                                    var colorList = [
                                        '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                                    ];
                                    return colorList[params.dataIndex]
                                }
                            }
                        }
                    }
                ]
            };
            employeeNumChart.setOption(option);
        })
        $.get('/humanResource/kpi/employeeFlow', function (data) {
            var json = JSON.parse(data);
            var increase = json['increase'];
            var decrease = json['decrease'];
            var pureIncrease = json['pureIncrease'];
            var time = Object.keys(increase);
            var increaseData = [];
            var decreaseData = [];
            var pureIncreaseData = [];
            for (var item in increase) {
                increaseData.push(increase[item]);
            }
            for (var item in decrease) {
                decreaseData.push(decrease[item]);
            }
            for (var item in pureIncrease) {
                pureIncreaseData.push(pureIncrease[item]);
            }

            var option = {
                title: {},
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    feature: {
                        dataView: {
                            show: true,
                            readOnly: false
                        },
                        restore: {
                            show: true
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                grid: {
                    containLabel: true
                },
                legend: {
                    data: ['入职人数', '离职人数', '净增长人数']
                },
                xAxis: [{
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        formatter: '{value}月'
                    },
                    data: time
                }],
                yAxis: [{
                    type: 'value',
                    name: '增长人数',
                    interval: 1,
                    position: 'right',
                    axisLabel: {
                        formatter: '{value} 人'
                    }
                }, {
                    type: 'value',
                    name: '变动人数',
                    interval: 1,
                    position: 'left',
                    axisLabel: {
                        formatter: '{value} 人'
                    }
                }],
                series: [{
                    name: '入职人数',
                    type: 'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                        }
                    },
                    yAxisIndex: 1,
                    lineStyle: {
                        normal: {
                            width: 3,
                            shadowColor: 'rgba(0,0,0,0.4)',
                            shadowBlur: 10,
                            shadowOffsetY: 10
                        }
                    },
                    data: increaseData
                }, {
                    name: '离职人数',
                    type: 'line',
                    smooth: true,
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                        }
                    },
                    yAxisIndex: 1,
                    lineStyle: {
                        normal: {
                            width: 3,
                            color: '#74ff50',
                            shadowColor: 'rgba(0,0,0,0.4)',
                            shadowBlur: 10,
                            shadowOffsetY: 10
                        }
                    },
                    data: decreaseData
                },
                    {
                        name: '净增长人数',
                        type: 'bar',
                        yAxisIndex: 0,
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: pureIncreaseData
                    }]
            };
            employeeFlowChart.setOption(option);
        })
    },
    'humanresouce-base': function () {
        var sexBalance = echarts.init(document.getElementById('sexBalance'));
        var ageBalance = echarts.init(document.getElementById('ageBalance'));
        var eduBalance = echarts.init(document.getElementById('eduBalance'));
        var rankBalance = echarts.init(document.getElementById('rankBalance'));
        sexBalance.showLoading();
        $.get('/humanResource/base/sexBalance', function (data) {
            sexBalance.hideLoading();
            var json = JSON.parse(data);
            var sexNames = Object.keys(json);
            var values = [];
            for (var item in json) {
                values.push({name: item, value: json[item]})
            }
            var option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: sexNames
                },
                series: [
                    {
                        name: '性别结构',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: values
                    }
                ]
            };
            sexBalance.setOption(option);
        })
        ageBalance.showLoading();
        $.get('/humanResource/base/ageBalance', function (data) {
            ageBalance.hideLoading();
            var json = JSON.parse(data);
            var names = Object.keys(json);
            var values = [];
            for (var item in json) {
                values.push(json[item]);
            }
            var option = {
                title: {},
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: "{a} <br/>{b} : {c}人"
                },
                legend: {
                    data: ['2016年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01],
                    "axisLabel": {
                        "interval": 0,
                        formatter: '{value}人',
                    }
                },
                yAxis: {
                    type: 'category',
                    data: names
                },
                series: [{
                    name: '年龄构成',
                    type: 'bar',
                    data: values
                }]
            };
            ageBalance.setOption(option);
        })
        eduBalance.showLoading();
        $.get('/humanResource/base/educationBalance', function (data) {
            eduBalance.hideLoading();
            var json = JSON.parse(data);
            var names = Object.keys(json);
            var values = [];
            for (var item in json) {
                values.push(json[item]);
            }
            var option = {
                title: {},
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: "{a} <br/>{b} : {c}人"
                },
                legend: {
                    data: ['2016年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01],
                    "axisLabel": {
                        "interval": 0,
                        formatter: '{value}人',
                    }
                },
                yAxis: {
                    type: 'category',
                    data: names
                },
                series: [{
                    name: '学历构成',
                    type: 'bar',
                    data: values
                }]
            };
            eduBalance.setOption(option);
        })
        rankBalance.showLoading();
        $.get('/humanResource/base/rankBalance', function (data) {
            rankBalance.hideLoading();
            var json = JSON.parse(data);
            var names = Object.keys(json);
            var values = [];
            for (var item in json) {
                values.push(json[item]);
            }
            var option = {
                title: {},
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: "{a} <br/>{b} : {c}人"
                },
                legend: {
                    data: ['2016年']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01],
                    "axisLabel": {
                        "interval": 0,
                        formatter: '{value}人',
                    }
                },
                yAxis: {
                    type: 'category',
                    data: names
                },
                series: [{
                    name: '职称构成',
                    type: 'bar',
                    data: values
                }]
            };
            rankBalance.setOption(option);
        })
    }
}


// 风格切换

$('.tpl-skiner-toggle').on('click', function () {
    $('.tpl-skiner').toggleClass('active');
})

$('.tpl-skiner-content-bar').find('span').on('click', function () {
    $('body').attr('class', $(this).attr('data-color'))
    saveSelectColor.Color = $(this).attr('data-color');
    // 保存选择项
    storageSave(saveSelectColor);

})


// 侧边菜单开关


function autoLeftNav() {


    $('.tpl-header-switch-button').on('click', function () {
        if ($('.left-sidebar').is('.active')) {
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').removeClass('active');
            }
            $('.left-sidebar').removeClass('active');
        } else {

            $('.left-sidebar').addClass('active');
            if ($(window).width() > 1024) {
                $('.tpl-content-wrapper').addClass('active');
            }
        }
    })

    if ($(window).width() < 1024) {
        $('.left-sidebar').addClass('active');
    } else {
        $('.left-sidebar').removeClass('active');
    }
}


// 侧边菜单
$('.sidebar-nav-sub-title').on('click', function () {
    $(this).siblings('.sidebar-nav-sub').slideToggle(80)
        .end()
        .find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
})

function show(value) {
    console.log(value)
}