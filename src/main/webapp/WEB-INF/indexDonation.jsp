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
<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
</script>
<script>
	$(function() {
		$(".pass").click(function(){
			var that = this
			/* alert($(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html()); */
			$.post("passApply", {
				"donationId" : $(this).parent().prev().prev().prev()
						.prev().prev().prev().prev().prev().html()
			}, function(date) {
				if (date == "true") {
					$(that).attr("disabled", true);
					$(that).removeAttr("style");
					$(that).parent().prev().html("已通过");
					$(that).parent().prev().prev().prev().prev().html(getNowFormatDate());
					$(that).parent().next().children().attr("disabled", true);
					$(that).parent().next().children().removeAttr("style");
					$.ajax({
						type: 'post',
						//加上这句话
						xhrFields: {
						    withCredentials: true
						},
						crossDomain: true,
						url:"http://192.168.137.11:8089/integration-consumer-two/integral/update.do",
				        	data:{
				        		examineUserName:$(that).parent().prev().prev().prev().prev().prev().prev().prev().html(),
				        		userName:"admin",
				        		tokenString:window.localStorage.getItem("token"),
				        		upInteger:$(that).parent().prev().prev().prev().html(),
				        		channel:3
				        	},
				        	dataType: 'json',
				        	success: function(data) {
				        		if(data.errorCode == '0000'){
				        			alert("操作成功!");
				        		}
				        },
				        	error: function(data){
				        		alert(data.errorMsg);
				        	}
					});
				} else {
					alert(date);
				}
			}, "text");
		});
		
		$(".refuse").click(function(){
			var that = this
			/* alert($(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html()); */
			$.post("refuseApply", {
				"donationId" : $(this).parent().prev().prev().prev().prev()
						.prev().prev().prev().prev().prev().html()
			}, function(date) {
				if (date == "true") {
					$(that).attr("disabled", true);
					$(that).removeAttr("style");
					$(that).parent().prev().prev().html("已拒绝");
					$(that).parent().prev().prev().prev().prev().prev().html(getNowFormatDate());
					$(that).parent().prev().children().attr("disabled", true);
					$(that).parent().prev().children().removeAttr("style");
				} else {
					alert(date);
				}
			}, "text");
		});
	});
</script>
<script type="text/javascript">
	function queryDeviceRecords(pageNum) {
		window.location.href = "http://192.168.137.11:18000/backstageAdmin/adminIndexDonation?pageNum="
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
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>序号</th>
									<th>ID</th>
									<th>用户名</th>
									<th>捐赠物资类</th>
									<th>申请时间</th>
									<th>处理时间</th>
									<th>积分</th>
									<th>图片名</th>
									<th>状态</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${donationList}" var="donationList"
									varStatus="status">
									<tr>
										<td style="display: none">${donationList.ex1}</td>
										<td id="${status.index+1 }">${status.index+1 }</td>
										<td>${donationList.id}</td>
										<td>${donationList.username}</td>
										<td>${donationList.type}</td>
										<td>${donationList.apply_time}</td>
										<td>${donationList.credit_time}</td>
										<td>${donationList.ex1}</td>
										<td><img style="width: 50px; height: 30px;" alt="img" src="http://192.168.137.95/upload/${donationList.img_url}"/></td>
										<td>
                                				<c:if test="${donationList.status==2}">
   												已拒绝
											</c:if>
											<c:if test="${donationList.status==0}">
   												待审核
											</c:if>
											<c:if test="${donationList.status==1}">
   												已通过
											</c:if>
                                			</td>
                                			<td>
											<button class="pass"
												<c:if test="${donationList.status==0}">style="background-color: antiquewhite;"</c:if>
												<c:if test="${donationList.status==2 ||donationList.status==1}">disabled="disabled"</c:if>>通过</button>
										</td>
										<td>
											<button class="refuse"
												<c:if test="${donationList.status==0}">style="background-color: antiquewhite;"</c:if>
												<c:if test="${donationList.status==2 || donationList.status==1}">disabled="disabled"</c:if>>拒绝</button>
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