//材料名称重复性判断
$("#materialsName").blur(function(){
	var materialsName=$("#materialsName").val();
	$.ajax({
		url:"addMaterialsNameCheck",
		data:"materialsName="+materialsName,
		type:"post",
		success:function(result){
			if(!result){
				humane.log("材料名称重复,请重新输入！");
				$("#materialsName").val("");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	});
});

//新增材料  保存
$("#matadd").click(function(){
	var materialsName=$("#materialsName").val();
	var materialsNum=$("#materialsNum").val();
	var materialsCategoryNameId=$("#materialsCategoryNameId").val();
	$.ajax({
		type:"POST",
		url:"addMaterialsMT.ajax",
		data:{"materialsName":materialsName,"materialsNum":materialsNum,"materialsCategoryNameId":materialsCategoryNameId},
		dataType:"json",
		success:function(result){
			if(result.addResult=="exsit"){
				humane.log("材料已存在！");
			}else if(result.addResult=="false"){
				humane.log("保存失败！");
			}else if(result.addResult=="success"){
				humane.log("保存成功！");
				$("#materialsName").val("");
				$("#materialsNum").val(0);
				$("#materialsCategoryNameId").val(0);
			}else if(result.addResult=="categoryfalse"){
				humane.log("请选择材料分类！");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	})
})


