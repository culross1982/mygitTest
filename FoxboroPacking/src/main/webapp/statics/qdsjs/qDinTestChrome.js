var qdsProCategoryId=1; //1表示产品分类为DIN

$(".daterange input").each(function() {
	var $this = $(this);
	$this.daterangepicker({
		locale : {
			"format" : "YYYY-MM-DD",// 显示格式
			"separator" : " ~ ",// 两个日期之间的分割线
			// 中文化
			"applyLabel" : "确定",
			"cancelLabel" : "取消",
			"fromLabel" : "开始",
			"toLabel" : "结束",
			"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
			"monthNames" : [ "一月", "二月", "三月", "四月", "五月", "六", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
			"firstDay" : 1
		},
		format: 'YYYY-MM-DD',//设置input显示日期格式
		separator : " ~ ",// 设置input显示两个日期之间的分割线
	}, function(start, end, label) {
		// 点击确定后的事件，下面是为了bootstrap validate得校验，
		// 若未使用，可忽视
		/*if ($this.parents("form.required-validate").length > 0) {
				var $form = $this.parents("form.required-validate");

				var name = $this.attr("name");
				if ($form.length > 0) {
					var data = $form.data('bootstrapValidator');
					data.updateStatus(name, 'NOT_VALIDATED', null)
					// Validate the field
					.validateField(name);
				}
			}*/
		// 设置最小宽度，否则显示不全
	}).css("min-width", "205px").next("i").click(function() {
		// 对日期的i标签增加click事件，使其在鼠标点击时可以拉出日期选择
		$(this).parent().find('input').click();
	});
});

/*弹出"新增装配数据"界面框*/
$(".insertError").click(function () {
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	//窗口载入数据
	$("#moduleNo").val(obj.attr("moduleNo"));
	$("#testDiscription").val(obj.attr("testDiscription"));
	$("#productTestId").val(obj.attr("id"));
	$("#remark").val("");
	$("#errorCodeId").val("14");
});

/*输入维修数据按“提交”按钮*/
$("#addErrorBtn").click(function(){
	var moduleNo=$("#moduleNo").val();
	var errorCodeId=$("#errorCodeId").val();
	var remark=$("#remark").val();
	var productTestId=$("#productTestId").val();
	//var qdsProCategoryId=$("#qdsProCategoryId").val();
	$.ajax({
		url:"addProductError.ajax",
		data:{"qdsProCategoryId":qdsProCategoryId,"moduleNo":moduleNo,"errorCodeId":errorCodeId,"remark":remark,"productTestId":productTestId},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("提交失败！");
			}else if(data.result=="success"){
				humane.log("提交成功！");
				window.setTimeout("window.location.href='dinTestWindows'",1500);//弹窗后延迟跳转网页
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
})

/*弹出"修改"界面框*/
$(".modifyError").click(function(){
	var obj=$(this);
	$('#modifyModal').modal();	//弹出窗口
	//窗口载入数据
	$("#moduleNo1").val(obj.attr("moduleNo"));
	$("#testDiscription1").val(obj.attr("testDiscription"));
	$("#errorCodeId1").val(obj.attr("errorCodeId"));
	$("#productTestId1").val(obj.attr("id"));
	$("#remark1").val(obj.attr("remark"));
})

/*修改界面按“修改”按钮*/
$("#modifyErrorBtn").click(function(){
	var moduleNo1=$("#moduleNo1").val();
	var errorCodeId1=$("#errorCodeId1").val();
	var productTestId1=$("#productTestId1").val();
	var remark1=$("#remark1").val();
	$.ajax({
		url:"modifyProductError.ajax",
		data:{"qdsProCategoryId":qdsProCategoryId,"moduleNo1":moduleNo1,"errorCodeId1":errorCodeId1,"remark1":remark1,"productTestId1":productTestId1},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("修改失败！");
			}else if(data.result=="success"){
				humane.log("修改成功！");
				window.setTimeout("window.location.href='dinTestWindows'",1500);//弹窗后延迟跳转网页
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
})
