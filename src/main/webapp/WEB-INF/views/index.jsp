<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>爱·物资</title>
<link href='../../css/index.css' rel="stylesheet" type="text/css" />
<link href='../../css/swiper-4.3.3.min.css' rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/swiper-4.3.3.min.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<div id='logo'>
				<img src="../images/logo.jpg" width=100% height=100% />
			</div>
			<div id="nav">
				<ul>
					<li class="active"><a href="index.do">首页</a></li>
					<li><a href="doDonate.do">我要捐献</a></li>
					<li><a href="selectDonate.do">我的捐献</a></li>
					<li><a href="http://localhost:8080/#/index/0">积分系统</a>
					<li><a href="#">联系我们</a></li>
				</ul>
			</div>
			<div id="user">
				<c:if test="${username!=null }">
					${username }&nbsp;&nbsp;&nbsp;当前积分数 :&nbsp;${integral }
				</c:if>
				<c:if test="${username==null}">
					<a style="cursor: default;" href="http://localhost:8080/#/Login"
						class="scroll">登录</a>
				</c:if>
			</div>
		</div>
		<div id="banner">
			<div class="swiper-container" style="width: 100%; height: 100%">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<img src="../images/banner2.png" width="100%" height="100%" />
					</div>
					<div class="swiper-slide">
						<img src="../images/banner3.png" width="100%" height="100%" />
					</div>
					<div class="swiper-slide">
						<img src="../images/banner4.png" width="100%" height="100%" />
					</div>
				</div>
				<!-- 如果需要分页器 -->
				<div class="swiper-pagination"></div>
				<!-- 如果需要导航按钮 -->
				<!-- <div class="swiper-button-prev"></div>
			    <div class="swiper-button-next"></div> -->
			</div>
		</div>
		<div id="step">
			<img src="../images/step.png" width="100%" height="100%" />
		</div>

		<div id="center">
			<div id="title">
				<c:if test="${type==null }">
					最新捐献
				</c:if>
				<c:if test="${type=='衣物类'}">
					 闲置衣物		
				</c:if>
				<c:if test="${type=='书籍类'}">
					图书教材	
				</c:if>
				<c:if test="${type=='其他'}">
					 其他物品	
				</c:if>
				<span id="spa"></span>
			</div>
			<div id="menu" class="sidebar">
				<li style="margin-top:0;">
					<div class="nav-img"><img src="<%=basePath%>img/index.png"></div>
					<div class="nav-text"><a href="<%=basePath%>index.do" class="index"> 最新捐献</a></div>
				</li>
				<li>
					<div class="nav-img"><img src="<%=basePath%>img/makeup.png" /></div>
					<div class="nav-text"><a href="<%=basePath%>index.do?eyJhbGciOiJIUzI1NiJ9=衣物类" class="makeup"> 闲置衣物</a></div>
				</li>
				<li>
					 <div class="nav-img"><img src="<%=basePath%>img/book.png" /> </div>
					 <div class="nav-text"><a href="<%=basePath%>index.do?eyJhbGciOiJIUzI1NiJ9=书籍类" class="book">图书教材</a></div>
				</li>
				<li>
					<div class="nav-img"><img src="<%=basePath%>img/smallthing.png" /></div>
					<div class="nav-text"><a href="<%=basePath%>index.do?eyJhbGciOiJIUzI1NiJ9=其他" class="smallthing">其他物品</a></div>
				</li>
			</div>
			<div id="content">
				<c:forEach items='${donationList}' var='donation'>
					<div class="center-item">
						<div class="item-content">
							<div class="item-img">
								<img src='../../upload/${donation.imgUrl}' width="100%"
									height="95%" />
							</div>
							<div class="item-text">
								<div class="text-t">${donation.title}</div>
								<div class="text-b">
									<span class="donationName">${donation.username}</span> <span
										class="donationTime"><fmt:formatDate
											value="${donation.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
									</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="footer">
			<div id="footer-list">
				<ul>
					<li>首页 |</li>
					<li>联系我们 |</li>
					<li>关于我们 |</li>
					<li>问题反馈</li>
				</ul>
			</div>
			<p id="footer-p">
				&copy;2018-2019 iGoods 爱<span id="spa2">·</span>物资版权所有
				工信部备案：京ICP备05079127号-2
			</p>
		</div>
	</div>
	<script>
		var mySwiper = new Swiper('.swiper-container', {
			direction : 'horizontal',
			loop : true,
			autoplay : true,
			speed : 1000,

			// 如果需要分页器
			pagination : {
				el : '.swiper-pagination',
			},

			// 如果需要前进后退按钮
			navigation : {
				nextEl : '.swiper-button-next',
				prevEl : '.swiper-button-prev'
			},

			// 如果需要滚动条
			scrollbar : {
				el : '.swiper-scrollbar',
			},
		})
		function query(data){
			alert(data);
			$.post('queryAjax.do',{type:data},function(data){
				alert(data);
			})
		}
	</script>
</body>
</html>