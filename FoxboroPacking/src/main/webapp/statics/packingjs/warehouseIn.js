//显示所选材料的数量
$("#id").change(function(){
	var id=$("#id").val();
	$.ajax({
		url:"warehouseInCheck.ajax",
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

$("#inNum").on("focus",function(){
	$(this).val("");
});

//材料 入库 按钮
$("#warehouseInBtn").click(function(){
	var id=$("#id").val();
	var inNum=$("#inNum").val();
	$.ajax({
		type:"POST",
		url:"warehouseIn.ajax",
		data:{"id":id,"inNum":inNum},
		dataType:"json",
		success:function(result){
			if(result.inResult=="noId"){
				humane.log("请选择材料型号！");
			}else if(result.inResult=="false"){
				humane.log("入库失败！");
			}else if(result.inResult=="noexsit"){
				humane.log("请填写入库数量！");
			}else if(result.inResult=="success"){
				humane.log("入库成功！");
				$("#id").val(0);
				$("#materialsNum").val(0);
				$("#inNum").val(0);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	})
})



