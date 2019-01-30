//显示所选材料的数量
$("#id").change(function(){
	var id=$("#id").val();
	$.ajax({
		url:"warehouseOutCheck.ajax",
		data:"id="+id,
		type:"post",
		success:function(data){
			$("#materialsNum").val(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	});
});

$("#outNum").on("focus",function(){
	$(this).val("");
});

//材料 “出库” 按钮
$("#warehouseOutBtn").click(function(){
	var id=$("#id").val();
	var outNum=$("#outNum").val();
	var remark=$("#remark").val();
	$.ajax({
		type:"POST",
		url:"warehouseOut.ajax",
		data:{"id":id,"outNum":outNum,"remark":remark},
		dataType:"json",
		success:function(result){
			if(result.outResult=="noId"){
				humane.log("请选择材料型号！");
			}else if(result.outResult=="false"){
				humane.log("出库失败！");
			}else if(result.outResult=="noexsit"){
				humane.log("请填写出库数量！");
			}else if(result.outResult=="notEnough"){
				humane.log("库存不足，无法满足出库需求！");
			}else if(result.outResult=="success"){
				humane.log("出库成功！");
				$("#id").val(0);
				$("#materialsNum").val(0);
				$("#outNum").val(0);
				$("#remark").val("");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	})
})





