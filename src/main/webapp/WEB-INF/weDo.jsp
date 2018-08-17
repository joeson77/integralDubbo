<%@page import="com.test.entity.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>我们的行动</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<script src="layer/layer.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<link href="css/weDo.css" rel="stylesheet" type="text/css" media="all" />
<link href="layer/theme/default/layer.css" rel="stylesheet" type="text/css" media="all" />		
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
			<!-- <h6>首页 / <span>我们的行动</span></h6> -->
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
						<li><a href="index.do" class="scroll">首页 </a></li>
						<li class="active" ><a href="weDo.do" class="scroll">我们的行动  </a></li>
						<li><a href="about.html" class="scroll">关于我们</a></li>
						<li><a href="gallery.html" class="scroll">动态新闻</a></li>
						<li><a href="blog.html" class="scroll">联系我们</a></li>
						<li><a href="javascript:return false;" onclick="loginJudge()" id="userInfo" class="scroll">
									<%
 									if (session.getAttribute("user") != null) {
 										Users user = (Users)session.getAttribute("user");
 										out.print("欢迎您:" + user.getUserName() + "&nbsp;&nbsp;&nbsp;&nbsp;");
 									} else
 										out.print("登录");
 									%>
							</a>
							<a style="cursor: default;" href="javascript:return false;" id="userIntegralInfo" class="scroll">
									<%
 									if (session.getAttribute("user") != null) {
 										Users user = (Users)session.getAttribute("user");
 										out.print(",积分:" + user.getUserIntegral());
 									} 
 									%>
							</a>
						</li>
						<li style="display: none"><span id="tokenString">
									<%
 									if (session.getAttribute("token") != null) {
 										String token = (String)session.getAttribute("token");
 										out.print(token);
 									}
 									%>
						</span></li>
						<li style="display: none"><span id="userNameString">
									<%
 									if (session.getAttribute("user") != null) {
 										Users user = (Users)session.getAttribute("user");
 										out.print(user.getUserName());
 									}
 									%>
						</span></li>
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
	<div class="center">
		<div class='center-content'>
			<c:forEach items='${listActive}' var='publicBenefit' >
				<div class='center-content-item'>
					<div class="content-item">
						<div class='content-img' onclick='fun(${publicBenefit.activeId},this)'><img style="width:100%;height: 100%;" alt="img" src="http://192.168.137.11:18000/backstageAdmin/images/${publicBenefit.ex1}"/></div>
						<div class='content-text'>
							<div class="progressBar">
	            				<div class="progressBar_Track"></div>
	         				</div>
	          				<p class='progressBar-text' style="text-align:center;">
	          					${publicBenefit.havingIntegral}/${publicBenefit.activeNeedIntegral}
	          				</p>
						</div>
					</div>
				</div>
			</c:forEach>
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
	<div id='details' style="display:none">
		<div style="display: none"><span id="displayActiveId"></span></div>
		<div style='width:50%;height:200px;margin:0 auto;'><img style="width: 100%;height: 100%;" id="activeImg" alt="img" src=""></div>
		<div style='width:90%;height:50px;line-height:25px;margin:0 auto;'>
			<span style='font-size:18px;color:orange;font-weight:bold;'>活动名称：</span><p id='activeName' style=''></p>
		</div>
		<div style='width:90%;height:70px;margin:0 auto;'>
			<span style='font-size:18px;color:orange;font-weight:bold;'>活动描述：</span><p id='activeDesc' style=''></p>
		</div>
		<div style='width:90%;height:50px;margin:0 auto;'>
			<span style='font-size:18px;color:orange;font-weight:bold;'>活动状态：</span><p id='activeStatue' style=''></p>
		</div>
		<div style='width:90%;height:50px;margin:0 auto;'>
			<span style='font-size:18px;color:orange;font-weight:bold;'>开始时间：</span><p id='activeStartTime' style=''></p>
		</div>
		<div style='width:90%;height:100px;margin:0 auto;'>
			<span style='font-size:18px;color:orange;font-weight:bold;'>结束时间：</span><p id='activeEndTime' style=''></p>
		</div>
		<div style='width:90%;height:45px;line-height:40px;margin:0 auto;text-align:center'>
			<button style='border:1px solid orange;background:white;color:orange' id='donationBtn'>我要捐献</button>
		</div>
	</div>
	<div id="te" style="display:none">
		<div style='width:100%;height:40px;line-height:30px;margin-top:10px;text-align:center'>
			<input type='text' id='donationNum' placeholder='请输入捐赠积分数'/><br>
		</div>
		<div style='width:100%;height:30px;text-align:center'>
			<button style='border:1px solid orange;background:white;color:orange' id='yesDonation' onclick='funn()'>确认捐献</button>
		</div>
		
	</div>
</body>
<script type="text/javascript">
var indexSmall;
var indexBig;
var that;

$(document).ready(function (e) {
   
   $('.content-text').each(function(){
   		var text=$(this).children("p.progressBar-text").text();
   		var arr=text.split('/');
   		var haveNum=parseInt(arr[0]);
   		var needNum=parseInt(arr[1]);
   	    var mWidth = haveNum / needNum * $(this).children(".progressBar").width() + "px";
 	    $(this).find(".progressBar_Track").css("width", mWidth);
   });

});
function fun(id,obj){
	that = obj;
	console.log(id);
	$.post("getDetails.do",{'activeId':id}, function(data) {
		  console.log(data);
		  var publicBenefit=data.publicBenefit;
		  $("#activeName").html(publicBenefit.activeName);
		  $("#activeImg").attr("src","http://192.168.137.11:18000/backstageAdmin/images/" + publicBenefit.ex1);
		  $("#activeDesc").html(publicBenefit.ex2);
		  $("#activeStartTime").html(publicBenefit.startTime);
		  $("#activeEndTime").html(publicBenefit.endTime);
		  $("#displayActiveId").html(id);
		  if(publicBenefit.activeStatus == 0){
			  $("#activeStatue").html("努力完成中");
			  $("#donationBtn").removeAttr("disabled");
			  $("#donationBtn").css("color", "orange");
			  $("#donationBtn").css("border", "1px solid orange");
		  }else if(publicBenefit.activeStatus == 1){
			  $("#activeStatue").html("待开启");
			  $("#donationBtn").attr("disabled", true);
			  $("#donationBtn").css("color", "gray");
			  $("#donationBtn").css("border", "1px solid gray");
		  }else if(publicBenefit.activeStatus == 2){
			  $("#activeStatue").html("已过期");
			  $("#donationBtn").attr("disabled", true);
			  $("#donationBtn").css("color", "gray");
			  $("#donationBtn").css("border", "1px solid gray");
		  }else{
			  $("#activeStatue").html("已完成");
			  $("#donationBtn").attr("disabled", true);
			  $("#donationBtn").css("color", "gray");
			  $("#donationBtn").css("border", "1px solid gray");
		  }
		  indexBig = layer.open({
			  type: 1,
			  area: ['700px', '650px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: $("#details")
		  });
	});
}
function fun2(){
	var n=$(".num").val;
	alert(n);
}
$("#donationBtn").click(function(){
	indexSmall = layer.open({
		 type: 1,
		  area: ['300px', '150px'],
		  fixed: false, //不固定
		  content:$("#te")
		});
});
function funn(){
	var myreg=/^(0|[1-9][0-9]*)$/;
	var num=$("#donationNum").val();
	if(!myreg.test(num)){
		layer.msg('请输入正确格式的数字', function(){
			
		});
		return false;
	}
	if(num==0){
		layer.alert('捐赠数量需要大于0哦', {icon: 6});
		return false;
	}
	var token= $("#tokenString").html().trim();
	var userName = $("#userNameString").html().trim();
	var text=$(that).next().children().next().html();
	var arr=text.split('/');
	var haveNum=parseInt(arr[0]);
	var needNum=parseInt(arr[1]);
	var userIntegralInfo = $("#userIntegralInfo").text();
	var selUserIntegral=userIntegralInfo.split(':');
	var selUserIntegralInfo = selUserIntegral[0];
	var userIntegral = parseInt(selUserIntegral[1]);
	if($("#userInfo").text().trim() != "登录"){
		if(userIntegral >= num){
			$.post(
          		'updateHaving.do',{
          			num:num,
          			displayActiveId:$("#displayActiveId").text()
          		},function(data){
          			if(data.flag == "true"){
          				$.ajax({
          					type: 'post',
          					crossDomain: true,
          					url:"http://192.168.137.11:8080/integration-consumer-two/integral/update.do",
          			        	data:{
          			        		userName:userName,
          			        		tokenString:token,
          			        		upInteger:-num,
          			        		channel:4
          			        	},
          			        	dataType: 'json',
          			        	success: function(data) {
          			          	if(data.errorCode == '0000'){
          			          		$("#tokenString").html(data.token);
          			          		layer.close(indexSmall);
        		          				layer.close(indexBig);
        		          				$(that).next().children().next().html((haveNum + parseInt(num)) + "/" +needNum);
        		          				$("#userIntegralInfo").html(selUserIntegralInfo + ":" + (userIntegral - num));
          			          	}else{
          			          		alert("请您先登录!");
          			          		layer.close(indexSmall);
          			          		layer.close(indexBig);
          			          	}
          			        },
          			        	error: function(data){
          			        		alert("捐献失败!");
          			        	}
          				});
          			}else if(data.errorCode == "3333"){
          				alert("捐献失败!");
          			}else {
          				alert("捐献失败!");
          			}
          		},'json');
		}else{
			alert("您的积分不足!");
		}
	}else{
		alert("请先登录!");
		layer.close(indexSmall);
  		layer.close(indexBig);
	}
	
	 
	
}
</script>
<script type="text/javascript">
	function loginJudge(){
		if($("#userInfo").text().trim() == "登录"){
			window.location.href='http://192.168.137.11:8080/#/Login';
		}
	}
</script>
</html>