<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约捐献</title>
<link href='../../css/doDonate.css' rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/distpicker.data.js"></script>
<script type="text/javascript" src="../../js/distpicker.js"></script>
<script type="text/javascript" src="../../js/doDonate.js"></script>

<script type="text/javascript">
	function imgChange(e) {
		console.info(e.target.files[0]);//图片文件
		var dom = $("input[id^='myfile']")[0];
		console.info(dom.value);//这个是文件的路径 C:\fakepath\icon (5).png
		console.log(e.target.value);//这个也是文件的路径和上面的dom.value是一样的
		var reader = new FileReader();
		reader.onload = (function(file) {
			return function(e) {
				console.info(this.result); //这个就是base64的数据了
				var sss = $("#image");
				$("#image")[0].src = this.result;
			};
		})(e.target.files[0]);
		reader.readAsDataURL(e.target.files[0]);
	}
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<div id='logo'>
				<img src="../images/logo.jpg" width=100% height=100% />
			</div>
			<div id="nav">
				<ul>
					<li><a href="index.do">首页</a></li>
					<li class="active"><a href="doDonate.do">我要捐献</a></li>
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
		<div id="center">
			<div id="center-title">
				<span id="center-title-text">预约捐献</span>
			</div>
			<div id="center-content">
				<form action="/publishDonateSubmit.do" method="post"
					enctype="multipart/form-data" autocomplete="off">
					<div id="donate-title">
						<span class="spa">标题</span> <input type="text" id="title-content"
							name="title" /> <span id="msg-spa"></span>
					</div>
					<div id="donate-type">
						<span class="spa">类别</span> <select id="st" name="type"
							maxLength=30>
							<option value="衣物类" selected="selected">衣物类</option>
							<option value="书籍类">书籍类</option>
							<option value="其他">其他</option>
						</select>
					</div>
					<div id="donate-img">
						<span class="spa">照片</span> <a href="javascript:;"
							class="a-upload"> <input type="file" id="myfile"
							name="myfile" onchange="imgChange(event)" />点击这里上传图片
						</a>
					</div>
					<div id="main-img">
						<img src="" style="WIDTH: 135px; HEIGHT: 120px" id="image">
					</div>
					<div id="donate-num">
						<span class="spa">件数</span>
						<button id="subBtn" type="button" class="numBtn numFristBtn">-</button>
						<input type="text" id="number" name="number" value="5" />
						<button id="addBtn" type="button" class="numBtn">+</button>
						<span id="msg-spa3"></span> <span id="est-num">(预计您可获取50积分)</span>
					</div>
					<div id="donate-tel">
						<span class="spa">电话</span> <input type="text" id="telPhone"
							name="telPhone" value="${donate.telPhone }" /> <span
							id="msg-spa4"></span>
					</div>
					<div id="donate-address">

						<div id="distpicker-container">
							<div class="spa spa-address">地址</div>
							<div id="distpicker1">
								<select data-province="北京市" name="province"></select> <select
									data-city="北京市市辖区" name="city"></select> <select
									data-district="东城区" name="district"></select>
							</div>
							<input type="text" id="address" name="address"
								value="${donate.address }" /> <span id="msg-spa5"></span>
						</div>
					</div>
					<div id="donate-note">
						<span class="spa">备注</span> <input type="text" id="description"
							name="description" value="${donate.description }" />
					</div>
					<div id="donate-btn">
						<INPUT id=btnSubmit style="WIDTH: 62px" type=submit value=提交
							name=btnSubmit> <INPUT id="btnReset" style="WIDTH: 63px"
							type=reset value=重置 name=btnReset><br> <span
							id="msg-spa2"></span>
					</div>
				</form>
				
				<div id="donate-att">
					<br>
					<span class="spa">须知:</span>
					<p>1、为了回馈你的爱心，你将从捐献的每件衣物、每本书和其他类商品中分别获取10、8、5个积分。</p>
					<p>2、阳光的预约上门服务为免费服务，相关费用全额由阳光承担，您不需支付任何费用。如果你预约成功，快递小哥会尽快与你联系。</p>
					<p>3、阳光的初衷是通过环保的方式帮助需要帮助的人，所以我们对您的衣服没有任何要求，任何的衣物、包类、鞋类、床单、玩具都可以回收.</p>
				</div>
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
</body>
<script type="text/javascript">
	$("#distpicker1").distpicker();
</script>
</html>