//修改密码时旧密码检查
$("#oldPwd").on("blur",function(){
	$.ajax({
		url:"checkOldPwd.ajax",
		data:"oldPwd="+$(this).val(),
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.oldPwd=="false"){
				$("#oldPwd").next().attr("class","glyphicon glyphicon-remove-circle");
				humane.log("旧密码不正确！");
				oldPwd=false;
			}else if(data.oldPwd=="empty"){
				$("#oldPwd").next().attr("class","glyphicon glyphicon-remove-circle");
				humane.log("旧密码不能为空！");
				oldPwd=false;
			}else if(data.oldPwd=="true"){
				$("#oldPwd").next().attr("class","glyphicon glyphicon-ok-circle");
				oldPwd=true;
			}
		},
		error:function(){
			humane.log("发生错误");
		}
	});
});

//修改密码时密码检查
$("#password").on("blur",function(){
	$.ajax({
		url:"checkpassword.ajax",
		data:"password="+$(this).val(),
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.password=="false"){
				$("#password").next().attr("class","glyphicon glyphicon-remove-circle");
				humane.log("不能小于6位数");
				passwordFlag=false;
			}else if(data.password=="empty"){
				$("#password").next().attr("class","glyphicon glyphicon-remove-circle");
				passwordFlag=false;
			}else if(data.password=="true"){
				$("#password").next().attr("class","glyphicon glyphicon-ok-circle");
				passwordFlag=true;
			}
		},
		error:function(){
			humane.log("发生错误");
		}
	});
});

//修改密码时重复密码检查
$("#repassword").on("blur",function(){
	var password=$("#password").val();
	$.ajax({
		url:"checkrepassword.ajax",
		data:{"repassword":$(this).val(),"password":password},	//data要用json格式
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.repassword=="false"){
				$("#repassword").next().attr("class","glyphicon glyphicon-remove-circle");
				humane.log("密码不一致");
				repasswordFlag=false;
			}else if(data.repassword=="empty"){
				$("#repassword").next().attr("class","glyphicon glyphicon-remove-circle");
				repasswordFlag=false;
			}else if(data.repassword=="true"){
				$("#repassword").next().attr("class","glyphicon glyphicon-ok-circle");
				repasswordFlag=true;
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

//更改时验证各项是否为true
function check(){
	if(oldPwd && passwordFlag && repasswordFlag){
		return true;
	}else{
		return false;
	}
}