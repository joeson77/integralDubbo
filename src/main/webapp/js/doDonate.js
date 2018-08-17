$(function(){
	$("#title-content").blur(function(){
		var title=$("#title-content").val().trim();
		if(title==''){
			$("#msg-spa").html("标题不能为空");
		}else{
			$("#msg-spa").html("");
		}
		
	});
	$("#telPhone").blur(function(){
		var tel=$("#telPhone").val().trim();
        var myreg=/^1[34578][0-9]{9}$/;
        var flag=myreg.test(tel);
		if(tel==''){
			$("#msg-spa4").html("请填写联系方式");
		}else if(!flag){
			$("#msg-spa4").html("请填写正确的手机号码");
		}else{
			$("#msg-spa4").html("");
		}
		
	});
	$("#address").blur(function(){
		var address=$("#address").val().trim();
		if(address==''){
			$("#msg-spa5").html("请填写详细地址");
		}else{
			$("#msg-spa5").html("");
		}
		
	});
	$("#number").on("change",function(){
		var number=$("#number").val().trim();
        var myreg=/^(0|[1-9][0-9]*)$/;
        var flag=myreg.test(number);
		if(number==''){
			$("#msg-spa3").html("请填写捐赠的数量");
			$("#est-num").html("");
		}else if(!flag){
			$("#msg-spa3").html("请填写正确的数量");
			$("#est-num").html("");
		}else if(number<5){
			$("#msg-spa3").html("数量不能少于5哦");
			$("#est-num").html("");
		}else{
			$("#msg-spa3").html("");
			var type=$("#st").val();
			var numberStr=number;
			estimate(numberStr,type);
			
		}
		
	});
	$("#st").change(function(){
		var type=$("#st").val();
		var numberStr=$("#number").val().trim();
		if(numberStr!=''&&/^(0|[1-9][0-9]*)$/.test(numberStr)&&numberStr>=5){
			estimate(numberStr,type);
		}
		
	});
	$("#subBtn").click(function(){
		var number=$("#number").val().trim();
		if(number!=''&&/^(0|[1-9][0-9]*)$/.test(number)&&number>5){
			$("#number").val(parseInt(number)-1);
			var type=$("#st").val();
			var numberStr=$("#number").val();
			estimate(numberStr,type);
		}else if(number!=''&&/^(0|[1-9][0-9]*)$/.test(number)&&number<=5){
			$("#msg-spa3").html("数量不能少于5哦");
		}
	});

	$("#addBtn").click(function(){
		var number=$("#number").val().trim();
		if(number!=''&&/^(0|[1-9][0-9]*)$/.test(number)){
			$("#number").val(parseInt(number)+1);
			var type=$("#st").val();
			var numberStr=$("#number").val();
			estimate(numberStr,type);
			if(parseInt(number)+1==5||parseInt(number)+1>5){
				$("#msg-spa3").html("");
			}
		}
	});
	$("#btnSubmit").click(function(){
		var title=$("#title-content").val().trim();
		var msg=$("#msg-spa").html();
		var tel=$("#telPhone").val().trim();
		var msg2=$("#msg-spa4").html();
		var address=$("#address").val().trim();
		var msg3=$("#msg-spa5").html();
		var msg4=$("#msg-spa3").html();
		
		var number=$("#number").val().trim();
		if(number!=''&&/^(0|[1-9][0-9]*)$/.test(number)&&number==5){
			 $("#msg-spa3").html("");
		 }
		var picture=$("#image").attr("src").trim();
		if(picture==""){
			$("#msg-spa2").html("请上传照片");
			return false;
		}
		if(title!=''&&tel!=''&&address!=''&&msg==''&&msg2==''&&msg3==''&&msg4==''){
			$("#btnSubmit").css("disabled","disabled");
			$("#msg-spa2").html("");
		}else{
			$("#msg-spa2").html("请按要求填写信息");
			return false;
		}
		
	});
	$("#btnReset").click(function(){
		$("#est-num").html("(预计您可获取50积分)");
	});
	
});
function estimate(numberStr,type){
	$.post('getScore.do',{number:numberStr,type:type},function(data){
		$("#est-num").html("(预计您可赚取"+data.score+"积分)");
	});
}
