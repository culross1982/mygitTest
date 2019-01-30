$("#pwd").blur(function(){
	var pwd=$("#pwd").val();
	$.ajax({
		url:"addPwdCheck",
		data:"pwd="+pwd,
		type:"post",
		success:function(result){
			if(!result){
				humane.log("产品型号重复,请重新输入！");
				$("#pwd").val("");
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

/*验证产品分类是否填写*/
function check(){
	var productCategoryId=$("#productCategoryId").val();
	if(productCategoryId==0){
		humane.log("新增失败！请选择产品分类");
		return false;
	}
}
