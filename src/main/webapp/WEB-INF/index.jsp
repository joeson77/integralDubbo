<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-tw"
	class="zh-tw">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>环球信息网1</title>
<script src="../js/jquery-2.2.3.min.js"></script>
<script>
	$(function(){
		$("#updateBtn").click(function(){
			$.post(
				"../user/update.do",{
					"tokenString" : $("#tokenStr").val(),
					"userName" : $("#userName").val(),
					"userPwd" : $("#userPwd").val()
				},function(date){
					alert(date.flag)
				},"json"
			);
		});
		
		$("#getVerificationCode").click(function(){
			$.post(
				"../user/sendMsg.do",{
					"userTel" : $("#userTel").val()
				},function(date){
					alert(date.flag)
				},"json"
			);
		});
		
		$("#submitReg").click(function(){
			$.post(
				"../user/register.do",{
					"verificationCode" : $("#verificationCode").val()
				},function(date){
					alert(date.flag)
				},"json"
			);
		});
		
		$("#loginBtn").click(function(){
			$.post(
				"../user/login.do",{
					"loginUserName" : $("#loginUserName").val(),
					"loginUserPwd" : $("#loginUserPwd").val()
				},function(date){
					alert(date.flag);
					$("#tokenStr").val(date.token);
				},"json"
			);
		});
		
		$("#selectBtn").click(function(){
			$.post(
				"../user/lookToken.do",{
					"tokenString" : $("#tokenStr").val(),
				},function(date){
					alert(date)
				},"text"
			);
		});
	});
</script>
</head>
<body>
	<span>${string}</span>
	<br></br>
	<span>
		<c:forEach items="${stuList}" var="stuList">
		<tr>
			<td><span>${stuList.userName}</span></td>
			<td><span>${stuList.updateTime}</span></td>
			<td><span>${stuList.integral}</span></td>
			<td><span>${stuList.ex1}</span></td>
		</tr>
	</c:forEach>
	</span>
	<br></br><br></br>
	<div>
		<span>用户:</span><input id="userName" type="text"/><br></br>
		<span>密码:</span><input id="userPwd" type="text"/><br></br>
		<button id="updateBtn">确认修改</button>
	</div>
	<br></br><br></br>
	<div>
		<span>手机号:</span><input id="userTel" type="text"/><br></br>
		<span>验证码:</span><input id="verificationCode" type="text"/>
		<button id="getVerificationCode">获取验证码</button><br></br>
		<button id="submitReg">注册</button>
	</div>
	<br></br><br></br>
	<div>
		<span>用户:</span><input id="loginUserName" type="text"/><br></br>
		<span>密码:</span><input id="loginUserPwd" type="text"/><br></br>
		<span>token串为:</span><input id="tokenStr" type="text" value=""/><br></br>
		<button id="loginBtn">登录</button><br></br>
		<button id="selectBtn">查询</button>
	</div>
</body>
</html>
