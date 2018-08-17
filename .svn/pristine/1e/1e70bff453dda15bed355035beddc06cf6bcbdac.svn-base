<%@page import="com.test.entity.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Open heart Bootstarp Website Template | Home :: w3layouts</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700' rel='stylesheet' type='text/css'>
<!--//fonts-->
</head>
<body> 
<!--header-->
	<div class="banner-in">
		<div class="container">
		</div>
	</div>
	  <!--header-->
		<div class="header">
			<div class="container">			
				<div class="logo">
					<a href="index.html"><img src="images/logo.png" alt=" " /></a>
				</div>
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li  ><a href="index.do" class="scroll">首页</a></li>					
						<li><a href="weDo.do" class="scroll">我们的行动</a></li>
						<li><a href="404.html" class="scroll">关于我们</a></li>
						<li><a href="gallery.html" class="scroll">动态新闻</a></li>
						<li><a href="blog.html" class="scroll">联系我们</a></li>
						<li><a id="userInfo" onclick="loginJudge()" href="javascript:return false;" href="contact.html" class="scroll"><%
 									if (session.getAttribute("user") != null) {
 										Users user = (Users)session.getAttribute("user");
 										out.print("欢迎您:" + user.getUserName() + "&nbsp;&nbsp;&nbsp;&nbsp;积分:" + user.getUserIntegral());
 									} else
 										out.print("登录");
 									%></a></li>
					</ul>
					<!--script-->
				<script>
					$("span.menu").click(function(){
						$(".top-nav ul").slideToggle(500, function(){
						});
					});
			</script>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	<!---->
	<!--content-->
	<div class="content">
		<div class="container">
		<div class="page-found">
			<div class="page-not-found">
				<h3>404</h3>
			</div>
			<p>We are sorry. This was unexpected </p>
			 <a class="btn  btn-1c btn1 btn-1d" href="index.do">返回首页</a>
		</div>
		</div>
	</div>
	<!--footer-->
	<div class="footer">
		<div class="container">
			<span class="drop"> </span>
			<p>Clarity <span>38 mirus lorem 1230</span> Newyork, 2587-4577</p>
				<p class="footer-class">Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
				<ul class="social-icons">
					<li><a href="#"><span> </span> </a></li>
					<li class="twitter"><a href="#"><span> </span></a></li>
					<li class="gmail"><a href="#"><span> </span> </a></li>
					<li class="print"><a href="#"><span> </span> </a></li>
				</ul>
		</div>	
		</div>
</body>
<script type="text/javascript">
	function loginJudge(){
		if($("#userInfo").text().trim() == "登录"){
			window.location.href='http://www.baidu.com';
		}
	}
</script>
</html>