<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户行为分析</title>
<link rel="stylesheet" href="css/chart.css" type="text/css">
<link rel="stylesheet" href="css/indexChart.css" type="text/css">
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/echarts.common.min.js"></script>
</head>
<body>
<div id="main">
      <div id="content">
            <div id="achartsAjax"></div>
      </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('achartsAjax'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '本月物资捐赠一览图',        //主标题文本
            subtext: '',            //副标题文本
            x: 'center'                    //标题水平安放位置
        }
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url:'countChart.do',
        type:'post',
        dataType:'json',
        success:function(data){
            myChart.setOption({ //加载数据图表
                series: {
                    data: data,
                    name: '用户捐赠物资',                        //系列名称，如启用legend，该值将被legend.data索引相关
                    type: 'pie',                        //图表类型，必要参数！
                    radius: '55%',                        //半径
                    center: ['50%', '60%']            //圆心坐标
                },
                tooltip: {                         //提示框，鼠标悬浮交互时的信息提示
                    trigger: 'item',            //触发类型，默认数据触发，可选值有item和axis
                    formatter: "{a} <br/>{b} : {c} ({d}%)",    //鼠标指上时显示的数据  a（系列名称），b（类目值），c（数值）, d（占总体的百分比）
                    backgroundColor: 'rgba(0,0,0,0.7)'    //提示背景颜色，默认为透明度为0.7的黑色
                },
                legend: {                                //图例，每个图表最多仅有一个图例。
                    orient: 'vertical',                    //布局方式，默认为水平布局，可选值有horizontal(竖直)和vertical(水平)
                    x: 'left',                            //图例水平安放位置，默认为全图居中。可选值有left、right、center
                    data: ['衣物类','书籍类','其他']
                },
                toolbox: {                                //工具箱，每个图表最多仅有一个工具箱。
                    show: true,                            //显示策略，可选为：true（显示） | false（隐藏）
                    feature: {                            //启用功能
                        dataView: {                        //数据视图
                            show: true,
                            readOnly: false                //readOnly 默认数据视图为只读，可指定readOnly为false打开编辑功能
                        },
                        restore: {                        //还原，复位原始图表   右上角有还原图标
                           show: true
                       },
                        saveAsImage: {                    //保存图片（IE8-不支持），默认保存类型为png，可以改为jpeg
                            show: true,
                            type: 'jpeg',
                            title: '保存为图片'
                        }
                    }
                }
            });
        }
    });
});
</script>
</html>