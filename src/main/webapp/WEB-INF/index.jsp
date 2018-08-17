<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>一个组后台管理系统</title>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
<link rel="stylesheet" href="css/b.tabs.css" type="text/css">
<style type="text/css">
div.menuSideBar {
	
}

div.menuSideBar li.nav-header {
	font-size: 14px;
	padding: 3px 15px;
}

div.menuSideBar .nav-list>li>a, div.menuSideBar .dropdown-menu li a {
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	-ms-border-radius: 0px;
	border-radius: 0px;
}
.content{
    position: absolute;
    top: 30px;
    left: 30px;
}

html{height: 100%}
body{margin: 0;height: 100%;
    background: #fff;}
canvas{display: block;width: 100%;height: 100%;}
.body_content{
    position: absolute;
    top:30%;
    left: 20%;
    height: 20%;
    background: palevioletred;
    width: 20%;
}
.time{
    position: absolute;
    right: 30px;
    top: 30px;
}
</style>
</head>

<body >
<canvas id="canvas"></canvas>
<div id="show" class="time"></div>
	<div class="content">
		<div class="container">
			<h3 class="page-header"></h3>
			<div class="">
				<div class="row-fluid">
					<div class="col-md-2" style="padding-left: 0px;">
						<div class="well menuSideBar" style="padding: 8px 0px;">
							<ul class="nav nav-list" id="menuSideBar">
								<li class="nav-header">导航菜单</li>
								<li class="nav-divider"></li>
								<li mid="tab1" funurl="http://192.168.137.11:18000/backstageAdmin/adminIndexUser"><a tabindex="-1"
									href="javascript:void(0);">用户管理</a></li>
								<li mid="tab2" funurl="http://192.168.137.11:18000/backstageAdmin/adminIndexRecord"><a tabindex="-1"
									href="javascript:void(0);">积分详情</a></li>
								<li mid="tab3" funurl="http://192.168.137.11:18000/backstageAdmin/adminIndexPublicBenefit"><a tabindex="-1"
									href="javascript:void(0);">公益管理</a></li>
								<li mid="tab4" funurl="http://192.168.137.11:18000/backstageAdmin/adminIndexDonation"><a tabindex="-1"
									href="javascript:void(0);">捐献审核</a></li>
								<li mid="tab5" funurl="http://192.168.137.11:18000/backstageAdmin/adminIndexChart"><a tabindex="-1"
									href="javascript:void(0);">用户行为分析</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-10" id="mainFrameTabs" style="padding: 0px;height:1000px;">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active noclose"><a
								href="#bTabs_navTabsMainPage" data-toggle="tab">首页</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane active" id="bTabs_navTabsMainPage">
								<div class="page-header">
									<h2
										style="font-size: 31.5px; text-align: center; font-weight: normal;">欢迎使用"一个组"后台管理系统</h2>
								</div>
								<div
									style="text-align: center; font-size: 20px; line-height: 20px;">
									<br>
									<br> Welcome to use !
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/b.tabs.js"></script>
	<script type="text/javascript" src="js/demo1.js"></script>
	<script type="text/javascript" src="js/index.js"></script>

</body>
</html>