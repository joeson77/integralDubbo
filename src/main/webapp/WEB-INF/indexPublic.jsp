<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分项目后台管理</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/indexPublic.css" rel="stylesheet" />
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script>
	$(function() {
		$(".shutDownActive").click(
				function() {
					var that = this
					/* alert($(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html()); */
					$.post("shutDownActive", {
						"activeId" : $(this).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.html()
					}, function(date) {
						if (date == "true") {
							$(that).attr("disabled", true);
							$(that).removeAttr("style");
							$(that).parent().prev().html("待开启");
							$(that).parent().next().children().removeAttr(
									"disabled");
							$(that).parent().next().children().attr("style",
									"background-color: antiquewhite");
						} else {
							alert(date);
						}
					}, "text");
				});

		$(".startActive").click(
				function() {
					var that = this
					/* alert($(this).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().html()); */
					$.post("startActive", {
						"activeId" : $(this).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.prev().html()
					}, function(date) {
						if (date == "true") {
							$(that).attr("disabled", true);
							$(that).removeAttr("style");
							$(that).parent().prev().prev().html("已开启");
							$(that).parent().prev().children().removeAttr(
									"disabled");
							$(that).parent().prev().children().attr("style",
									"background-color: antiquewhite");
						} else {
							alert(date);
						}
					}, "text");
				});

		$("#cancel").click(function() {
			$("#background").hide();
			$("#updateDiv").hide();
		});

		$("#createNewActive").click(function() {
			$("#background").show();
			$("#updateDiv").show();
		});

		$("#create").click(function() {
			$.post("createActive", {
				"activeName" : $("#activeName").val(),
				"startTime" : $("#startTime").val(),
				"endTime" : $("#endTime").val(),
				"activeType" : $("#activeType").val(),
				"activeNeedIntegral" : $("#activeNeedIntegral").val(),
				"activeImg" : $("#activeImg").val(),
				"activeDescribe" : $("#activeDescribe").val()
			}, function(date) {
				if(date == "true"){
					location.reload();
				}else{
					alert(date);
				}
			}, "text");
		});
	});
</script>
<script type="text/javascript">
	function queryDeviceRecords(pageNum) {
		window.location.href = "http://192.168.137.11:18000/backstageAdmin/adminIndexPublicBenefit?pageNum="
				+ pageNum;
	}
	
	function imgChange(e) {
	    console.info(e.target.files[0]);//图片文件
	    var dom =$("input[id^='imgTest']")[0];
	    console.info(dom.value);//这个是文件的路径 C:\fakepath\icon (5).png
	    console.log(e.target.value);//这个也是文件的路径和上面的dom.value是一样的
	    var reader = new FileReader();
	    reader.onload = (function (file) {
	        return function (e) {
	           console.info(this.result); //这个就是base64的数据了
	            var sss=$("#showImage");
	            $("#showImage")[0].src=this.result;
	            $("#activeImg").val(this.result);
	        };
	    })(e.target.files[0]);
	    reader.readAsDataURL(e.target.files[0]);
	}
</script>
</head>
<body>
	<div id="background"
		style="position: fixed; background: black; width: 100%; height: 100%; z-index: 1; opacity: 0.3; display: none;"></div>
	<div id="updateDiv"
		style=" border: 3px solid #FFDAB9; position: absolute; background: white; width: 60%; height: 85%; top: 20%; left: 25%; z-index: 10; display: none;">
		<h2 style="text-align: center">开启新公益项目</h2>
		<br>
		<div id="addContent">
			<div class="itemContainer">
				<div class="addItem">公益项目名称:</div>
				<div class="itemContent"><input id="activeName"></input></div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益开始时间:</div>
				<div class="itemContent"><input type="date"	id="startTime"></input></div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益结束时间:</div>
				<div class="itemContent"><input id="endTime" type="date" /></div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益类型:</div>
				<div class="itemContent">
					<select id="activeType">
						<option value="100">慈善</option>
						<option value="101">社会救援</option>
						<option value="102">援建希望小学</option>
					</select>
				</div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益所需积分:</div>
				<div class="itemContent"><input id="activeNeedIntegral"	type="text" /></div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益描述:</div>
				<div class="itemContent"><input id="activeDescribe"	type="text" /></div>
			</div>
			<div class="itemContainer">
				<div class="addItem">公益图片:</div>
				<div class="itemContent">
					<a href="javascript:;"class="a-upload"> 
					<input  id="imgTest"
					type="file" onchange="imgChange(event)" accept=".jpg"/>
					点击这里上传照片
					</a>
				<input style="display: none" id="activeImg" type="text"/>
				</div>
			</div>
			<div class="imgContainer">
				<img style="margin-left: 38%;width:70px;height: 50px" src="" id="showImage" alt=""/>
			</div>
			<div class="btnContainer">
				<button style="background-color: antiquewhite;" id="create">提交</button>
				<button style="background-color: antiquewhite;margin-left:20px;" id="cancel">取消</button>
			</div>
		</div>
		<!-- <table style="width: 100%; height: 80%; font-size: 20px">
			<tr>
				<td style="text-align: right"><span>公益项目名称:</span></td>
				
					<td style="text-align: center"><input id="activeName"></input></td>
				
			</tr>
			<tr>
				<td style="text-align: right"><span>公益开始时间:</span></td>
				<td style="text-align: center"><input type="date"
					id="startTime"></input></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>公益结束时间:</span></td>
				<td style="text-align: center"><input id="endTime" type="date" /></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>公益类型:</span></td>
				<td style="text-align: center"><select id="activeType">
						<option value="100">慈善</option>
						<option value="101">社会救援</option>
						<option value="102">援建希望小学</option>
				</select></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>公益所需积分:</span></td>
				<td style="text-align: center"><input id="activeNeedIntegral"
					type="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>公益描述:</span></td>
				<td style="text-align: center"><input id="activeDescribe"
					type="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right"><span>公益图片:</span></td>
			</tr>
			<tr>
				<td colspan="2"><input style="margin-left: 20%" id="imgTest"
					type="file" onchange="imgChange(event)" accept=".jpg"/></td>
				<td style="display: none"><input id="activeImg" type="text"/></td>
			</tr>
			<tr><td colspan="2"><img style="margin-left: 20%;width:50px;height: 30px" src="" id="showImage" alt=""></td></tr>
			<tr></tr>
			<tr>
				<td style="text-align: right"><button
						style="background-color: antiquewhite;" id="create">提交</button></td>
				<td style="text-align: center"><button
						style="background-color: antiquewhite;" id="cancel">取消</button></td>
			</tr>
			<tr></tr>
			<tr></tr>
		</table> -->
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
				<button id="createNewActive"
					style="margin-left: 20px; background-color: antiquewhite;">开启新公益</button>
				<div class="panel-body" style="height: 900px;">
					<div class="table-responsive">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th>序号</th>
									<th>简图</th>
									<th>公益项目名称</th>
									<th>项目起始时间</th>
									<th>项目结束时间</th>
									<th>公益项目类型</th>
									<th>所需积分</th>
									<th>已有积分</th>
									<th>公益项目状态</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${activeList}" var="activeList"
									varStatus="status">
									<tr>
										<td id="${status.index+1 }">${status.index+1 }</td>
										<td style="display: none">${activeList.activeId}</td>
										<td><img style="width: 50px; height: 30px;" alt="img"
											src="images/${activeList.ex1}"></td>
										<td>${activeList.activeName}</td>
										<td>${activeList.startTime}</td>
										<td>${activeList.endTime}</td>
										<td><c:if test="${activeList.activeType==100}">
   										慈善捐赠
									</c:if> <c:if test="${activeList.activeType==101}">
   										社会援助
									</c:if> <c:if test="${activeList.activeType==102}">
   										建造希望小学
									</c:if></td>
										<td>${activeList.activeNeedIntegral}</td>
										<td>${activeList.havingIntegral}</td>
										<td>
											<c:if test="${activeList.activeStatus==0}">
   												努力完成中
											</c:if>
											<c:if test="${activeList.activeStatus==1}">
   												待开启
											</c:if>
											<c:if test="${activeList.activeStatus==2}">
   												已过期
											</c:if>
											<c:if test="${activeList.activeStatus==3}">
   												已完成
											</c:if>
										</td>
										<td>
											<button class="shutDownActive"
												<c:if test="${activeList.activeStatus==0}">style="background-color: antiquewhite;"</c:if>
												<c:if test="${activeList.activeStatus==1 || activeList.activeStatus==2}">disabled="disabled"</c:if>>关闭</button>
										</td>
										<td>
											<button class="startActive"
												<c:if test="${activeList.activeStatus==1}">style="background-color: antiquewhite;"</c:if>
												<c:if test="${activeList.activeStatus==0 || activeList.activeStatus==2}">disabled="disabled"</c:if>>开启</button>
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