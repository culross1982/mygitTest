/*弹出"生成S/N"界面框*/
$("#createSn").click(function () {
	var flagOrder=false;
	var flagModule=false;
	var flagVer=false;
	var flagPwdQuantity=false;
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	resetModal();	//调用重置窗口
});

//重置窗口
function resetModal(){
	setTimeout(function(){$("#order").val("");	
	$("#module1").val("");
	$("#ver").val("");
	$("#pwdQuantity").val("");
	$("#order").next().html("");
	$("#module1").next().html("");
	$("#ver").next().html("");
	$("#pwdQuantity").next().html("");
	$("#order").focus();
	$("#review").children().remove();
	$("#message").html("")},500);
}

/*工作令验证*/
$("#order").change(function(){
	$("#review").children().remove();
	$("#createSnReviewBtn").removeClass("hide");
	$("#createSnBtn").addClass("hide");
	flagOrder=false;
	var obj=$(this);
	$.ajax({
		url:'addOrder.ajax',
		data:{'order':obj.val()},
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("工作令不可为空！");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败！");
			}else if(data.result=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				flagOrder=true;
			}else if(data.result=="exsit"){
				obj.next().css("color","red");
				obj.next().html("工作令已存在！");
			}else if(data.result=="lengthError"){
				obj.next().css("color","red");
				obj.next().html("长度错误（12位）！");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误！");
		}
	})
})

/*模块型号验证*/
$("#module1").change(function(){
	$("#createSnReviewBtn").removeClass("hide");
	$("#createSnBtn").addClass("hide");
	$("#review").children().remove();
	$("#ver").val("");
	$("#ver").next().html("");
	flagModule=false;
	var obj=$(this);
	var qdsProCategoryName=$("#qdsProCategoryName").val();
	$.ajax({
		url:"checkModule.ajax",
		data:{"module":obj.val(),"qdsProCategoryName":qdsProCategoryName},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("模块型号不可为空！");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败！");
			}else if(data.result=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				flagModule=true;
			}else if(data.result=="noexsit"){
				obj.next().css("color","red");
				obj.next().html("无此模块型号！");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误！");
		}
	})
})
/*$("#module1").change(function(){
	var obj=$(this);
	if(obj.val().trim().length==0){
		obj.next().css("color","red");
		obj.next().html("模块型号不可为空！");
	}else{
		obj.next().css("color","#3DCD58");
		obj.next().html("pass");
		flagModule=true;
	}
})*/

/*版本号验证*/
$("#ver").change(function(){
	$("#createSnReviewBtn").removeClass("hide");
	$("#createSnBtn").addClass("hide");
	$("#review").children().remove();
	flagVer=false;
	var obj=$(this);
	var module1=$("#module1").val();
	var qdsProCategoryName=$("#qdsProCategoryName").val();
	$.ajax({
		url:"checkVer.ajax",
		data:{"ver":obj.val(),"module":module1,"qdsProCategoryName":qdsProCategoryName},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="empty"){
				obj.next().css("color","red");
				obj.next().html("版本不可为空！");
			}else if(data.result=="failed"){
				obj.next().css("color","red");
				obj.next().html("操作失败！");
			}else if(data.result=="success"){
				obj.next().css("color","#3DCD58");
				obj.next().html("pass");
				flagVer=true;
			}else if(data.result=="noexsit"){
				obj.next().css("color","red");
				obj.next().html("错误！请查看基础数据");
			}else if(data.result="lengthError"){
				obj.next().css("color","red");
				obj.next().html("长度错误（2位）！");
			}
		},
		error:function(){
			obj.next().css("color","red");
			obj.next().html("ajax错误！");
		}
	})
})

/*数量验证*/
$("#pwdQuantity").change(function(){
	$("#createSnReviewBtn").removeClass("hide");
	$("#createSnBtn").addClass("hide");
	$("#review").children().remove();
	flagPwdQuantity=false;
	var re=/^[1-9][0-9]*$/;
	var obj=$(this);
	if(obj.val().trim().length==0){
		obj.next().css("color","red");
		obj.next().html("数量不可为空！");
	}else if(!re.test(obj.val())){
		obj.next().css("color","red");
		obj.next().html("必须为数字且不能为0！");
	}else{
		obj.next().css("color","#3DCD58");
		obj.next().html("pass");
		flagPwdQuantity=true;
	}
})

/*S/N生成预览*/
$("#createSnReviewBtn").click(function(){
	var order=$("#order").val();
	var module1=$("#module1").val();
	var pwdQuantity=$("#pwdQuantity").val();
	var qdsProCategoryName=$("#qdsProCategoryName").val();
	var moduleNoResult="";
	if(flagOrder==true && flagModule==true && flagVer==true && flagPwdQuantity==true){
		$.ajax({
			url:"createSnReview.ajax",
			data:{"module":module1,"pwdQuantity":pwdQuantity,"order":order,"qdsProCategoryName":qdsProCategoryName},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result=="failed"){
					$("#message").css("color","red");
					$("#message").html("预览失败！");
				}else{
					$("#message").css("color","green");
					$("#message").html("预览成功！");
					$("#reviewBody").removeClass("hide");
					$("#createSnReviewBtn").addClass("hide");
					$("#createSnBtn").removeClass("hide");
					var json=eval(data.result);
					for(var i=0;i<json.length;i++){
						moduleNoResult=moduleNoResult+"<span>"+json[i].moduleNo+"</span></br>";
					}
					$("#review").append(moduleNoResult);
				}
			},
			error:function(){
				$("#message").css("color","red");
				$("#message").html("ajax错误！");
			}
		})
	}
})

/*S/N生成按钮*/
$("#createSnBtn").click(function(){
	var reviewList=new Object();
	for(var i=0;i<$("#review").find("span").length;i++){
		reviewList[i]=$("#review").find("span").eq(i).html();
	}
	var order=$("#order").val();
	var module1=$("#module1").val();
	var ver=$("#ver").val();
	var pwdQuantity=$("#pwdQuantity").val();
	var qdsProCategoryId=$("#qdsProCategoryId").val();
	$.ajax({
		url:"createSn.ajax",
		data:{"reviewList":JSON.stringify(reviewList),"order":order,"module1":module1,"ver":ver,"pwdQuantity":pwdQuantity,"qdsProCategoryId":qdsProCategoryId},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="orderFailed"){
				humane.log("生成工作令数据错误");
			}else if(data.result=="productFailed"){
				humane.log("生成产品数据错误");
			}else if(data.result="success"){
				humane.log("生成成功！");
				window.setTimeout("window.location.href='dinCreateSnWindows'",1500);//弹窗后延迟跳转网页
			}else if(daat.result="empty"){
				humane.log("生成数据不存在！");
			}
		},
		error:function(){
			humane.log("ajax发生错误");
		}
	})
})

/*删除按钮*/
$(".deleteProductOrder").click(function(){
	var obj=$(this);
	var id=obj.attr("id");
	var order=obj.attr("order");
	var qdsProCategoryId=1;	//1表示产品分类为DIN
	if(confirm("你确定要删除【"+order+"】的数据信息吗？")){
		$.ajax({
			url:"deleteProductOrder.ajax",
			data:{"id":id,"qdsProCategoryId":qdsProCategoryId},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result=="orderFailed"){
					humane.log("删除工作令数据失败");
				}else if(data.result=="productFailed"){
					humane.log("删除产品数据失败");
				}else if(data.result=="success"){
					humane.log("删除成功！");
					obj.parents("tr").remove();
				}else if(data.result=="empty"){
					humane.log("删除数据不存在！");
				}else if(data.result=="isExsitAssyStatus"){
					humane.log("此工作令的装配数据已存在，不可删除！");
				}
			},
			error:function(){
				humane.log("ajax发生错误");
			}
		})
	}
})