/*弹出"新增装配数据"界面框*/
$("#addDinDatabase").click(function () {
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	resetModal();	//调用重置窗口
});

//重置窗口
function resetModal(){
	setTimeout(function(){$("#module1").val("");	
	$("#ver").val("");
	$("#part").val("");
	$("#category").val("");
	$("#module1").next().html("");
	$("#ver").next().html("");
	$("#part").next().html("");
	$("#category").next().html("");
	$("#module1").focus();
	$("#ver").removeAttr("disabled");},500);
}

/*模块型号验证*/
$("#module1").change(function(){
	var obj=$(this);
	$("#ver").removeAttr("disabled");
	$("#ver").next().html("");
	$.ajax({
		url:'dinAddModule.ajax',
		data:{'module':obj.val()},
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("模块型号不可为空");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败");
			}else if(data.result=="success"){
				$("#ver").val("");
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
			}else{
				obj.next().css("color","#f0ad4e");
				obj.next().html("Warning!模块已存在");
				$("#ver").val(data.result);
				$("#ver").attr("disabled","disabled");
				$("#ver").next().css("color","#3DCD58");
				$("#ver").next().html("pass");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误");
		}
	})
})

/*版本验证*/
$("#ver").change(function(){
	var obj=$(this);
	if(obj.val().length!=2){
		obj.next().css("color","red");
		obj.next().html("版本长度为2位");
	}else{
		obj.next().css("color","#3DCD58");
		obj.next().html("pass");
	}
})

/*单板型号验证*/
$("#part").change(function(){
	var obj=$(this);
	$.ajax({
		url:'dinAddModule.ajax',
		data:{'part':obj.val()},
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("单板型号不可为空");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败");
			}else if(data.result=="exsit"){
				obj.next().css("color","red");
				obj.next().html("单板型号已存在！");
			}else if(data.result=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误");
		}
	})
})

/*按“新增”按钮*/
$("#addModuleDatabaseBtn").click(function(){
	if($("#module1").next().html()=="pass" || $("#module1").next().html()=="Warning!模块已存在" && $("#ver").next().html()=="pass" && $("#part").next().html()=="pass"){
		var module1=$("#module1").val();
		var ver=$("#ver").val();
		var part=$("#part").val();
		var category=$("#category").val();
		$.ajax({
			url:"addDatabase.ajax",
			data:{"module":module1,"ver":ver,"part":part,"category":category},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result=="failed"){
					humane.log("添加失败！");
				}else if(data.result=="success"){
					humane.log("添加成功！");
					window.location.href="dinDatabaseWindows";
				}
			},
			error:function(){
				humane.log("ajax错误！");
			}
		})
	}
})

/*“删除”按钮*/
$(".deleteDatabase").click(function(){
	var obj=$(this);
	var id=obj.attr("id");
	$.ajax({
		url:"delDatabase.ajax",
		data:{"id":id},
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("删除失败！");
			}else if(data.result=="success"){
				obj.parents("tr").remove();
				humane.log("删除成功！");
			}else if(data.result=="noexist"){
				humane.log("删除序号不存在！");
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
})