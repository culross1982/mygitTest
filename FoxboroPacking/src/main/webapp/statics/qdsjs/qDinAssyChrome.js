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

//将包材历史清单导入Excel中
/*$("#export").click(function(){
		$.ajax({
			url:"export.ajax",
			dataType:"json",
			type:"post",
			success:function(result){
				if(result.result=="false"){
					humane.log("导出失败！");
				}else if(result.result=="success"){
					humane.log("导出成功！");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				humane.log("发生错误");
			}
		})
	})*/


/*弹出"新增装配数据"界面框*/
$("#addDinAssy").click(function () {
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	resetModal();	//调用重置窗口
});

//重置窗口
function resetModal(){
	setTimeout(function(){$("#partNo").val("");	
	$("#assyNoP").val("");
	$("#assyNoM").val("");
	$("#partNo").next().html("");
	$("#assyNoP").next().html("");
	$("#assyNoM").next().html("");
	$("#partNo").focus();},500);
}

/*新增界面的卡件串号*/
$("#partNo").change(function(){
	var obj=$(this);
	var result=partNoCheck(obj);
	if(result=="true"){		//返回结果为ture则光标指向下一栏
		$("#assyNoP").focus();
	}
})

/*修改界面的卡件串号*/
$("#partNoModify").change(function(){
	var obj=$(this);
	partNoCheck(obj);
})

/*卡件串号检查通用方法*/
function partNoCheck(obj){
	var objResult=new Object;	//定义返回结果
	var partNo=obj.val();
	$.ajax({
		url:'dinAddPartNo.ajax',
		data:{'partNo':partNo},
		dataType:'json',
		type:'post',
		async:false,
		success:function(result){
			if(result.data=="empty"){
				obj.next().css("color","red");
				obj.next().html("卡件串号不可为空");
			}else if(result.data=="lengthError"){
				obj.next().css("color","red");
				obj.next().html("长度错误（19位）");
			}else if(result.data=="exsit"){
				obj.next().css("color","red");
				obj.next().html("串号已存在，请重输");
			}else if(result.data=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败");
			}else if(result.data=="incorrect"){
				obj.next().css("color","red");
				obj.next().html("串号不能使用'I'或'O'");
			}else if(result.data=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				objResult="true";
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误");
		}
	})
	return objResult;
}

/*新增界面的部件串号*/
$("#assyNoP").change(function(){
	var obj=$(this);
	if($("#assyNoM").val()==obj.val()){
		obj.next().css("color","red");
		obj.next().html("串号重复，请重输");
	}else{
		$.ajax({
			url:'dinAddAssyNo.ajax',
			data:{'assyNo':obj.val()},
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.data=="empty"){
					obj.next().css("color","red");
					obj.next().html("部件串号不可为空");
				}else if(result.data=="lengthError"){
					obj.next().css("color","red");
					obj.next().html("长度错误（12位）");
				}else if(result.data=="exsit"){
					obj.next().css("color","red");
					obj.next().html("串号已存在，请重输");
				}else if(result.data=="failed"){
					obj.next().css("color","red");
					obj.next().html("操作失败");
				}else if(result.data=="success"){
					obj.next().css("color","#3DCD58");
					obj.next().html("pass");
					$("#assyNoM").focus();
				}
			},
			error:function(){
				obj.next().css("color","red");
				obj.next().html("ajax错误");
			}
		})
	}
})

$("#assyNoM").change(function(){
	var obj=$(this);
	if(obj.val()==$("#assyNoP").val()){
		obj.next().css("color","red");
		obj.next().html("串号重复，请重输");
	}else{
		$.ajax({
			url:'dinAddAssyNo.ajax',
			data:{'assyNo':obj.val()},
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.data=="empty"){
					obj.next().css("color","red");
					obj.next().html("部件串号不可为空");
				}else if(result.data=="lengthError"){
					obj.next().css("color","red");
					obj.next().html("长度错误（12位）");
				}else if(result.data=="exsit"){
					obj.next().css("color","red");
					obj.next().html("串号已存在，请重输");
				}else if(result.data=="failed"){
					obj.next().css("color","red");
					obj.next().html("操作失败");
				}else if(result.data=="success"){
					obj.next().css("color","#3DCD58");
					obj.next().html("pass");
					doAdd();
				}
			},
			error:function(){
				obj.next().css("color","red");
				obj.next().html("ajax错误");
			}
		})
	}

})

/*修改界面的部件串号*/
$("#assyNoModify").change(function(){
	var obj=$(this);
	$.ajax({
		url:'dinAddAssyNo.ajax',
		data:{'assyNo':obj.val()},
		dataType:'json',
		type:'post',
		success:function(result){
			if(result.data=="empty"){
				obj.next().css("color","red");
				obj.next().html("部件串号不可为空");
			}else if(result.data=="lengthError"){
				obj.next().css("color","red");
				obj.next().html("长度错误（12位）");
			}else if(result.data=="exsit"){
				obj.next().css("color","red");
				obj.next().html("串号已存在，请重输");
			}else if(result.data=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败");
			}else if(result.data=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				$("#assyNoM").focus();
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误");
		}
	})
})

/*单点新增按钮*/
$("#addAssyDataBtn").click(function(){
	doAdd();
})

/*提交新增*/
function doAdd(){
	var assy=new Object;
	assy.partNo=$("#partNo").val();
	assy.assyNoP=$("#assyNoP").val();
	assy.assyNoM=$("#assyNoM").val();
	if($("#assyNoM").next().html()=="pass" && $("#assyNoP").next().html()=="pass" && $("#partNo").next().html()=="pass"){
		$.ajax({
			url:"dinDoAddAssy.ajax",
			data:{"assy":JSON.stringify(assy)},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result=="success"){
					humane.log("新增成功！");
					$("#count").val($("#count").val()*1+1);
					resetModal();	//重置弹窗
				}else if(data.reusult=="failed"){
					humane.log("新增失败！");
				}else if(data.result=="error"){
					humane.log("系统错误！");
				}
			},
			error:function(){
				humane.log("ajax错误！");
			}
		})
	}
}

/*删除装配数据*/
$(".deleteDinAssy").click(function(){
	var obj=$(this);
	var assyNo=obj.attr("assyNo");
	var id=obj.attr("id");
	if(confirm("你确定要删除【"+assyNo+"】的数据信息吗？")){
		$.ajax({
			url:"delAssyDataById.ajax",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result=="success"){
					humane.log("删除成功！");
					obj.parents("tr").remove();
				}else if(data.reusult=="failed"){
					humane.log("删除失败！");
				}else if(data.result=="error"){
					humane.log("系统错误！");
				}
			},
			error:function(){
				humane.log("ajax错误！");
			}
		})
	}
})

/*关闭按钮*/
$(".closeModal").click(function(){
	window.location.href="dinAssyWindows";
})

/*弹出"modify装配数据"界面框*/
$(".modifyDinAssy").click(function () {
	var obj=$(this);
	$('#modifyModal').modal();	//弹出窗口
	//窗口载入数据
	$("#partNoModify").val(obj.attr("partNo"));
	$("#assyNoModify").val(obj.attr("assyNo"));
	$("#assyTimeModify").val(obj.attr("assyTime"));
	$("#realnameModify").val(obj.attr("realname"));
	$("#idModify").val(obj.attr("id"));
});

/*点击"修改"按钮进行修改*/
$("#modifyDinAssyBtn").click(function(){
	var assyModify=new Object();
	assyModify.partNoModify=$("#partNoModify").val();
	assyModify.assyNoModify=$("#assyNoModify").val();
	assyModify.id=$("#idModify").val();
	$.ajax({
		url:"modifyProductAssy.ajax",
		data:{"assyModify":JSON.stringify(assyModify)},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="success"){
				humane.log("修改成功！");
				window.setTimeout("window.location.href='dinAssyWindows'",1500);//弹窗后延迟跳转网页
			}else if(data.reusult=="failed"){
				humane.log("修改失败！");
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
	document.getElementById("assyTime").value = currentTime;
}
setInterval("time()", 1000);
