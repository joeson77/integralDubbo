<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>一个组后台管理系统</title>
<meta charset="UTF-8">
<title>后台登录</title>
<link href='css/login.css' rel="stylesheet" type="text/css" />
<script src="js/jquery-2.2.3.min.js"></script>
<script>
	$(function(){
		$("#loginBtn").click(function(){
			$.ajax({
				type: 'post',
				url:"http://192.168.137.11:8089/integration-consumer-two/user/login.do",
				//加上这句话
				xhrFields: {
				    withCredentials: true
				},
				crossDomain: true,
				data:{
					loginUserName: $("#userName").val(),
					loginUserPwd : $("#userPwd").val(),
					imgCode : $("#userCode").val()
		        	},
		        	dataType: 'json',
		    		success:function(data){
					if(data.errorCode == "0000"){
						window.localStorage.setItem("token",data.token);
						window.location.href='indexIntegral';
					}else{
						alert(data.errorMsg);
					}
				},
				error:function(data){
					alert(data);
				}
			});
		});
	});
</script>
<body>
	<div id="container">
		<div id="main">
			<div id="title">
				<span id="spa">登录</span>
			</div>
			<div id="content">
				<div id="userNameContainer">
					<input id="userName" type="text" placeholder="请输入用户名" />
				</div>
				<div id="userPwdContainer">
					<input id="userPwd" type="password" placeholder="请输入密码" />
				</div>
				<div id="userCodeContainer">
					<input id="userCode" type="text" placeholder="请输入验证码" />
					<div id="codeImgContainer">
						<img style="width: 100%" alt="验证码"
							src="http://192.168.137.11:8089/integration-consumer-two/user/valicode.do" />
					</div>
				</div>
				<div id="loginBtnContainer">
					<button id="loginBtn">登录</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>