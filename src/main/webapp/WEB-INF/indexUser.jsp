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

<script>
	$(function() {
		$(".update").click(function(){
			$.post(
				"selectUserInfoById",{
					"userId":$(this).parent().prev().prev().prev().prev().prev().prev().prev().html()
				},function(date){
					$("#retUserId").html(date.id)
					$("#retUserName").html(date.userName)
					$("#retUserPwd").val(date.userPwd)
					$("#retUserTel").val(date.userTel)
					$("#retUserIntegral").val(date.userIntegral)
				},"json"
			);
			$("#background").show();
			$("#updateDiv").show();
		}); 
		
		$("#background").click(function(){
			$("#background").hide();
			$("#updateDiv").hide();
		});
		
		$(".blocking").click(function(){
			var that = null;
			that = this;
			/* alert($(this).parent().prev().prev().prev().prev().prev().prev().html()); */
			$.post(
				"updateUserState",{
					"userId" : $(this).parent().prev().prev().prev().prev().prev().html(),
					"updateUserState" : 1
				},function(date){
					if(date == "true"){
						$(that).attr("disabled",true);
						$(that).removeAttr("style");
						$(that).parent().prev().html("账号封号");
						$(that).next().removeAttr("disabled");
						$(that).next().attr("style","background-color: antiquewhite");
					}else{
						alert("修改状态失败");
					}
				},"text"
			);
		});
		
		$(".unblocking").click(function(){
			var that = null;
			that = this;
			/* alert($(this).parent().prev().prev().prev().prev().prev().prev().html()); */
			$.post(
				"updateUserState",{
					"userId" : $(this).parent().prev().prev().prev().prev().prev().html(),
					"updateUserState" : 0
				},function(date){
					if(date == "true"){
						$(that).attr("disabled",true);
						$(that).removeAttr("style");
						$(that).parent().prev().html("账号正常");
						$(that).prev().removeAttr("disabled");
						$(that).prev().attr("style","background-color: antiquewhite");
					}else{
						alert("修改状态失败");
					}
				},"text"
			);
		});
		
		$("#update").click(function(){
			$.post(
				"updateUserInfo",{
					"retUserId" : $("#retUserId").html(),
					"retUserName" : $("#retUserName").val(),
					"retUserPwd" : $("#retUserPwd").val(),
					"retUserTel" : $("#retUserTel").val(),
					"retUserIntegral" : $("#retUserIntegral").val()
				},function(date){
					if(date == "true"){
						location.reload()
					}else{
						alert(date)
					}
				},'text'
			);
		});
		
		$(".delete").click(function(){
			/* alert($(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().html()); */
			$.post(
				"deleteUserById",{
					"userId":$(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().html()
				},function(date){
					if(date == "true"){
						location.reload()
					}else{
						alert(date)
					}
				},"text"
			);
		});
	});
</script>
<script type="text/javascript">
	function queryDeviceRecords(pageNum) {
		window.location.href = "http://192.168.137.11:18000/backstageAdmin/adminIndexUser?pageNum="
				+ pageNum;
	}
</script>
</head>
<body>
	<div id="background" style="position:fixed;background: black;width: 100%;height: 100%;z-index: 1;opacity: 0.3;display: none;"></div>
	<div id="updateDiv" style="border-radius: 15px;border:3px solid #FFDAB9;position: absolute;background: white;width: 50%;height: 60%;top:20%;left:25%; z-index: 10;display: none;">
		<h2 style="text-align: center">用户信息修改</h2><br>
		<table style="width: 100%;height: 75%;font-size: 20px">
			<tr>
				<td style="text-align: right"><span>用户ID:</span></td>
				<td style="text-align: center"><span id="retUserId"></span></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>用户账号:</span></td>
				<td style="text-align: center"><span id="retUserName"></span></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>用户密码:</span></td>
				<td style="text-align: center"><input id="retUserPwd" type="text"/></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>用户手机号:</span></td>
				<td style="text-align: center"><input id="retUserTel" type="text"/></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>用户积分:</span></td>
				<td style="text-align: center"><input id="retUserIntegral" type="text"/></td>
			</tr>
			<tr></tr>
			<tr>
				<td style="text-align: right"><button>取消</button></td>
				<td style="text-align: center"><button id="update">提交</button></td>
			</tr>
		</table>
	</div>
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
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
                        <thead>
                        <tr>
							<th>序号</th>                        
                   		    <th>用户ID</th>
                            <th>用户账号</th>
                            <th>用户手机号</th>
                            <th>用户积分</th>
                            <th>用户状态</th>
                            <th>封号/解封</th>
                            <th>操作</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                       
                        <c:forEach items="${userList}" var="userList" varStatus="status">
                            <tr>
                                <td id="${status.index+1 }">${status.index+1 }</td>
                                <td>${userList.id}</td>
                                <td>${userList.userName}</td>
                                <td>${userList.userTel}</td>
                                <td>${userList.userIntegral}</td>
                                <td>
                                		<c:if test="${userList.userState==0}">
   										账号正常
									</c:if>
									<c:if test="${userList.userState==1}">
   										账号封号
									</c:if>
                                </td>
                                <c:if test="${userList.userState==0}">
                                		<td>
                                			<button class = "blocking" style="background-color: antiquewhite;">封号</button>
                                			<button class = "unblocking" disabled="disabled">解封</button>
                                		</td>
								</c:if>
								<c:if test="${userList.userState==1}">
                                		<td>
                                			<button class = "blocking" disabled="disabled">封号</button>
                                			<button class = "unblocking" style="background-color: antiquewhite;">解封</button>
                                		</td>
								</c:if>
                                <td><button class="update">修改</button></td>
                                <td><button class="delete">删除</button></td>
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