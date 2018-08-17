<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>捐赠记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='../../css/index.css' rel="stylesheet" type="text/css" />
<link href="../../css/page.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css"
	type="text/css" />
<link href='../../css/swiper-4.3.3.min.css' rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/swiper-4.3.3.min.js"></script>
<script type="text/javascript" src="../../js/page.js"></script>
<script type="text/javascript"
	src="../../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript"
	src="/js/plugins/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#beginDate,#endDate").click(function() {
			WdatePicker();
		});
		$("#query").click(function() {
			$("#currentPage").val(1);
			$("#searchForm").submit();
		});

		$("#page").initPage('${Result.totalCount}', '${Result.currentPage}',
				function(mm) {
					$("#currentPage").val(mm);
					$("#searchForm").submit();
				});
	});
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
					<li><a href="doDonate.do">我要捐献</a></li>
					<li class="active"><a href="selectDonate.do">我的捐献</a></li>
					<li><a href="http://localhost:8080/#/index/0">积分系统</a>
					<li><a href="#">联系我们</a></li>
				</ul>
			</div>
			<div id="user">
				<c:if test="${username!=null }">
					${username }&nbsp;&nbsp;&nbsp;当前积分数 :&nbsp;${integral }
				</c:if>
				<c:if test="${username==null}">
					<a style="cursor: default;" href="http://localhost:8080/#/Login" class="scroll">登录</a>
				</c:if>
			</div>
		</div>
		<div id="item">输入查询条件</div>
		<div style="width: 100%; text-align: center">
			<form id="searchForm" class="form-inline" method="post"
				action="/query.do">
				<input type="hidden" id="currentPage" name="currentPage" value="" />
				<div class="form-group">
					<label>审核状态</label> <select id="st" class="form-control"
						name="status">
						<option value="-1">全部</option>
						<option value="0">申请中</option>
						<option value="1">审核通过</option>
						<option value="2">审核拒绝</option>
					</select>
					<script type="text/javascript">
						$("[name=status] option[value='${(qo.status)}']").attr(
								"selected", "selected");
					</script>
				</div>
				<div class="form-group">
					<label>捐赠类别</label> <select id="st2" class="form-control"
						name="type">
						<option value="全部" selected="selected">全部</option>
						<option value="衣物类">衣物类</option>
						<option value="书籍类">书籍类</option>
						<option value="其他">其他</option>
					</select>
					<script type="text/javascript">
						$("[name=type] option[value='${(qo.type)}']").attr(
								"selected", "selected");
					</script>
				</div>
				<div class="form-group">
					<label>申请时间</label> <input class="form-control" type="text"
						name="beginDate" id="beginDate" value="${qo.beginDate}" />到 <input
						class="form-control" type="text" name="endDate" id="endDate"
						value='<fmt:formatDate value="${qo.endDate }" pattern="yyyy-MM-dd" />' '/>
				</div>
				<div class="form-group">
					<label>捐赠标题</label> <input id="donate-title" class="form-control"
						type="text" name="title" value="${qo.title}" autocomplete="off" />
				</div>
				<div class="form-group">
					<button id="query" class="btn btn-success">
						<i class="icon-search"></i> 查询
					</button>
					<button id="reset" type="button" class="btn btn-success">
						<i class="icon-clear"></i> 清空
					</button>
				</div>
			</form>
		</div>

		<div id="title">
			我的捐赠<span id="spa"></span>
		</div>

		<div id="center">
			<c:forEach items='${Result.result}' var='donation'>
				<div class="center-item">
					<div class="item-content">
						<div class="item-img">
							<img src='../../upload/${donation.imgUrl}' width="100%"
								height="95%" />
						</div>
						<div class="item-text">
							<div class="text-t">${donation.title}</div>
							<div class="text-b">
								<span class="status">${donation.donateStatusDispaly } </span> <span
									class="donationTime"><fmt:formatDate
										value="${donation.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="page-bar" style="text-align: center;">
			<div id="page-container">
				<ul class="page" maxshowpageitem="5" pagelistcount="8" id="page"></ul>
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
</body>
<script type="text/javascript">
	$("#reset").click(function() {
		$("#donate-title").val("");
		$("#beginDate").val("");
		$("#endDate").val("");
		$("#st").val("-1");
		$("#st2").val("全部");
	});
</script>
</html>