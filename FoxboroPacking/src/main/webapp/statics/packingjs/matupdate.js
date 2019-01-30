$("#materialsName").blur(function(){
	var materialsName=$("#materialsName").val();
	$.ajax({
		url:"updateMaterialsNameCheck",
		data:"materialsName="+materialsName,
		type:"post",
		success:function(result){
			if(!result){
				humane.log("材料名称重复,请重新输入！");
				$("#materialsName").val("");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
			humane.log("发生错误");
		}
	});
});



