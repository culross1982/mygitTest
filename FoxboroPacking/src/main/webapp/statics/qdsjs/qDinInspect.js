var qdsProCategoryId=1; //1位DIN

/*弹出"检验"界面框*/
$("#dinInspect").click(function () {
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	resetModal();	//调用重置窗口
});

//重置窗口
function resetModal(){
	setTimeout(function(){$("#checkModuleNo").val("");
	$("#checkModuleNo").next().html("");
	$("#checkModuleNo").focus();},500);
}

//检验界面的“模块串号”验证
$("#checkModuleNo").bind("change input",function(){
	var objResult="false";	//初始化返回结果为false
	var obj=$(this);
	var checkModuleNo=$("#checkModuleNo").val();
	$.ajax({
		url:"inspectCheckModuleNo.ajax",
		data:{"moduleNo":checkModuleNo,"qdsProCategoryId":qdsProCategoryId},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("模块串号不可为空！");
			}else if(data.result=="lengthError"){
				obj.next().css("color","red");
				obj.next().html("长度错误（19位）！");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败");
			}else if(data.result=="noExistTest"){
				obj.next().css("color","red");
				obj.next().html("测试数据不存在！");
			}else if(data.result=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				doInspect();
			}else if(data.result="isExistInspect"){
				obj.next().css("color","red");
				obj.next().html("此产品已检验过！");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误");
		}
	})
})

//点击“检验”按钮
$("#inspectBtn").click(function(){
	doInspect();
})

//提交检验
function doInspect(){
	var checkModuleNo=$("#checkModuleNo").val();
	$.ajax({
		url:"addInspect.ajax",
		data:{"checkModuleNo":checkModuleNo,"qdsProCategoryId":qdsProCategoryId},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="success"){
				humane.log("检验成功！");
				$("#checkCount").val($("#checkCount").val()*1+1);
				resetModal();	//重置弹窗
			}else if(data.reusult=="failed"){
				humane.log("检验失败！");
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
}

/*关闭按钮*/
$(".closeModal").click(function(){
	window.location.href="dinInspectWindows";
})

//点击“撤销”按钮
$(".cancelDinInspect").click(function(){
	var obj=$(this);
	var id=obj.attr("id");
	var moduleNo=obj.attr("moduleNo");
	$.ajax({
		url:"cancelInspect.ajax",
		data:{"id":id,"qdsProCategoryId":qdsProCategoryId,"moduleNo":moduleNo},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="success"){
				humane.log("撤销成功！");
				obj.parents("tr").children("td").eq(3).html("待检验");
				obj.parents("tr").children("td").eq(3).css("color","red");
				obj.parents("tr").children("td").eq(4).html("");
				obj.parents("tr").children("td").eq(5).html("");
				obj.parents("tr").children("td").eq(6).html("");
			}else if(data.reusult=="failed"){
				humane.log("撤销失败！");
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
})

/*动态显示时间*/
function time() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var week = date.getDay();
	week = "星期" + "日一二三四五六".charAt(week);
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	var temp = [ month, day, hour, minute, second ];
	for ( var i in temp) {
		temp[i] = temp[i] < 10 ? "0" + temp[i] : temp[i];
	}
	var currentTime = year + "-" + temp[0] + "-" + temp[1] + " " + temp[2] + ":" + temp[3] + ":" + temp[4] + " ";
	document.getElementById("checkInspectTime").value = currentTime;
}
setInterval("time()", 1000);