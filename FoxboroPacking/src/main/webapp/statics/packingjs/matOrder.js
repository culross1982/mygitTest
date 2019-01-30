var count=0;

//单笔添加订购包材
$("#saveBtn").click(function(){
	var nameList=$(".materialsName").val();
	var pwd=$("#pwd").val().toUpperCase();
	var pwdQuantity=$("#pwdQuantity").val();
	var objRegExp=new RegExp("[1-9]*[1-9][0-9]*");
	var margin=$("#margin").val();
	if(objRegExp.test(pwdQuantity)==true){
		addOrder(pwd,pwdQuantity,margin);	//调用添加方法
	}else{
		humane.log("“数量”栏格式错误！");
	}
})

/*批量添加订购包材*/
$("#fileButton").click(function(){
	if(checkSuffix()!=false){	//调用方法判断文件格式是否正确
		var form=new FormData($("#uploadForm")[0]);
		$.ajax({
			url:"addMaterialsOrderFromFile.ajax",
			data:form,
			type:"post",
			dataType:"json",
			contentType: false,  // 告诉jQuery不要去设置Content-Type请求头
			processData: false,	 //必须false才会避开jQuery对 formdata 的默认处理   
			success:function(data){
				if(data.result=="failed"){
					humane.log("添加失败！文件内容格式有误");
				}else if(data.result=="noexsit"){
					humane.log("添加失败！不存在对应的产品基础数据！");
				}else if(data.result=="empty"){
					humane.log("文件中无内容！添加失败！");
				}else if(data.result!=null){
					//文件正确，可以进行添加操作
					var json=eval(data.result);
					for(var i=0;i<json.length;i++){		//循环调用添加方法
						addOrder(json[i].pwd,json[i].pwdQuantity,json[i].margin);
					}
				}	
			},
			error:function(){
				humane.log("ajax系统错误！");
			}
		})
	}
})

//添加订购包材方法
function addOrder(pwd,pwdQuantity,margin){
	var proStr="";
	var matStr="";
	$.ajax({
		url:"addMaterialsOrder.ajax",
		data:{"pwd":pwd,"pwdQuantity":pwdQuantity,"margin":margin},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="empty"){
				humane.log("无输入信息！");
			}else if(data.result=="noexsit"){
				humane.log("不存在【"+pwd+"】对应的产品基础数据！");
				$("#message").append(pwd+",");
				$("#massageLable").removeClass("hidden");
			}else if(data.result!=null){
				$("#export").removeClass("hide");
				humane.log("【"+pwd+"】添加成功！");
				//产品列表异步显示
				$("#pwd").val("");
				$("#pwdQuantity").val("");
				$("#margin").val("");
				proStr=proStr+"<tr role='row' >";
				proStr=proStr+"<td>"+pwd+"</td>";
				proStr=proStr+"<td>"+pwdQuantity+"</td>";
				proStr=proStr+"<td>"+(Math.round(margin*100)/100)+"%</td>";
				proStr=proStr+"</tr>";
				$("#product").append(proStr);
				//材料列表异步显示
				var json=eval(data.result);
				count=json.length;
				for(var i=0;i<json.length;i++){
					matStr=matStr+"<tr role='row'>";
					matStr=matStr+"<td class='materialsName'>"+json[i].materialsName+"</td>";
					matStr=matStr+"<td>"+json[i].materialsCategoryName+"</td>";
					matStr=matStr+"<td class='materialsNum'>"+json[i].materialsNum+"</td>";
					matStr=matStr+"</tr>";		
				}
				$("#materials").html(matStr);
			}
		},
		error:function(){
			humane.log("ajax错误！");
		}
	})
}

//订购包材表格保存到SQL并导出到  #服务器#
/*$("#exportOrder").click(function(){
	$.ajax({
		url:"saveAndExportToExcel.ajax",
		data:{},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("表格导出失败！");
			}else if(data.result=="success"){
				humane.log("表格导出成功！");
			}
		},
		errro:function(){
			humane.log("表格导出失败！ajax系统错误");
		}
	})
})*/

//判断上传的文件是否为excel
function checkSuffix(){
	var name=$("#file").val();
	var suffix=(name.substr(name.lastIndexOf("."))).toLowerCase();
	if(suffix!=".xls" && suffix!=".xlsx"){
		humane.log("您上传的文件类型不符合（.xls或者.xlsx）")
		return false;
	}
}



//余量栏按钮
/*for(var j=0;j<count;j++){
	$("body").on("click","#num-jia"+j+"",function(){
		$("#input-num"+j+"").val($("#input-num"+j+"").val()*1+1);
	})

	$("body").on("click","#num-jian"+j+"",function(){
		if($("#input-num"+j+"").val()<=0){
			$("#input-num"+j+"").val(0);
		}else{
			$("#input-num"+j+"").val($("#input-num"+j+"").val()*1-1);
		}
		
	})
}*/


