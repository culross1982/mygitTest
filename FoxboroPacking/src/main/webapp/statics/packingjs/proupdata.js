$("#pwd").blur(function(){
	var pwd=$("#pwd").val();
	$.ajax({
		url:"updataPwdCheck",
		data:"pwd="+pwd,
		type:"post",
		success:function(result){
			if(!result){
				humane.log("产品型号重复,请重新输入！");
				$("#pwd").val("");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	});
});



