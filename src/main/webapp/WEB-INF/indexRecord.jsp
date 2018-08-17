<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>积分项目后台管理</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	function queryDeviceRecords(pageNum) {
		window.location.href = "http://192.168.137.11:18000/backstageAdmin/adminIndexRecord?pageNum="
				+ pageNum;
	}
</script>
</head>
<body>
	<div></div>
	<div class="row" id="deviceRecordList" style="margin-right:0">
		<div class="col-lg-12" style="height: 15px;">
			<div class="panel">
				<div class="panel-heading bk-bg-primary">
					<div class="panel-actions">
						<a href="table.html#" class="btn-setting"><i
							class="fa fa-rotate-right"></i></a> <a href="table.html#"
							class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
					</div>
				</div>
				<div class="panel-body" style="height: 900px;">
					<div class="table-responsive">
						<form action="adminIndexRecord" method="post">
							<table>
								<tr>
									<td style="width: 55%"><span>用户名:</span> <input
										id="userNameInput" type="text" name="userNameInput" value="${userNameReturn}" /></td>
									<td style="width: 55%"><span>添加时间:</span> <input
										id="updateTimeInput" type="date" name="updateTimeInput" value="${updateTimeReturn}" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="submit" value="查询"></td>
								</tr>
							</table>
						</form>
						<br></br>
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>序号</th>
									<th>积分记录ID</th>
									<th>用户名</th>
									<th>添加时间</th>
									<th>修改积分数</th>
									<th>积分渠道</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${recordList}" var="recordList"
									varStatus="status">
									<tr>
										<td id="${status.index+1 }">${status.index+1 }</td>
										<td>${recordList.id}</td>
										<td>${recordList.userName}</td>
										<td>${recordList.updateTime}</td>
										<td>${recordList.integral}</td>
										<td>
                                				<c:if test="${recordList.ex1==1}">
   												签到获取积分
											</c:if>
											<c:if test="${recordList.ex1==2}">
   												阅读获取积分
											</c:if>
											<c:if test="${recordList.ex1==3}">
   												商城消费积分
											</c:if>
                                			</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<!-- 分页 -->
						<div class="message">
							共<i class="blue">${pagehelper.total}</i>条记录，当前显示第&nbsp;<i
								class="blue">${pagehelper.pageNum}/${pagehelper.pages}</i>&nbsp;页
						</div>
						<div style="text-align: center;">
							<ul class="pagination">
								<c:if test="${!pagehelper.isFirstPage}">
									<li><a
										href="javascript:queryDeviceRecords(${pagehelper.firstPage});">首页</a></li>
									<li><a
										href="javascript:queryDeviceRecords(${pagehelper.prePage});">上一页</a></li>
								</c:if>
								<c:forEach items="${pagehelper.navigatepageNums}"
									var="navigatepageNum">

									<c:if test="${navigatepageNum==pagehelper.pageNum}">
										<li class="active"><a
											href="javascript:queryAllDevices(${navigatepageNum});">${navigatepageNum}</a></li>
									</c:if>
									<c:if test="${navigatepageNum!=pagehelper.pageNum}">
										<li><a
											href="javascript:queryDeviceRecords(${navigatepageNum});">${navigatepageNum}</a></li>
									</c:if>
								</c:forEach>
								<c:if test="${!pagehelper.isLastPage}">
									<li><a
										href="javascript:queryDeviceRecords(${pagehelper.nextPage});">下一页</a></li>
									<li><a
										href="javascript:queryDeviceRecords(${pagehelper.lastPage});">最后一页</a></li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>