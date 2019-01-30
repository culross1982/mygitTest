/*系统管理*/
$("#admin").click(function(){
	if($(this).is(":checked")){
		$("#packingAdmin").attr("disabled",true);$("#packingAdmin").removeAttr("checked");
		$("#packingCommon").attr("disabled",true);$("#packingCommon").removeAttr("checked");
		$("#QDSAdmin").attr("disabled",true);$("#QDSAdmin").removeAttr("checked");
		$("#QDSInspect").attr("disabled",true);$("#QDSInspect").removeAttr("checked");
		$("#QDScommon").attr("disabled",true);$("#QDScommon").removeAttr("checked");
	}else{
		$("#packingAdmin").attr("disabled",false);
		$("#packingCommon").attr("disabled",false);
		$("#QDSAdmin").attr("disabled",false);
		$("#QDSInspect").attr("disabled",false);
		$("#QDScommon").attr("disabled",false);
	}
})

/*包材系统*/
$("#packingAdmin").click(function(){
	if($(this).is(":checked")){
		$("#packingCommon").attr("disabled",true);
		$("#admin").attr("disabled",true);
	}else{
		$("#packingCommon").attr("disabled",false);
		$("#admin").attr("disabled",false);
	}
})

$("#packingCommon").click(function(){
	if($(this).is(":checked")){
		$("#packingAdmin").attr("disabled",true);
		$("#admin").attr("disabled",true);
	}else{
		$("#packingAdmin").attr("disabled",false);
		$("#admin").attr("disabled",false);
	}
})

/*QDS系统*/
$("#QDSAdmin").click(function(){
	if($(this).is(":checked")){
		$("#QDSInspect").attr("disabled",true);
		$("#QDScommon").attr("disabled",true);
		$("#admin").attr("disabled",true);
	}else{
		$("#QDSInspect").attr("disabled",false);
		$("#QDScommon").attr("disabled",false);
		$("#admin").attr("disabled",false);
	}
})

$("#QDSInspect").click(function(){
	if($(this).is(":checked")){
		$("#QDSAdmin").attr("disabled",true);
		$("#QDScommon").attr("disabled",true);
		$("#admin").attr("disabled",true);
	}else{
		$("#QDSAdmin").attr("disabled",false);
		$("#QDScommon").attr("disabled",false);
		$("#admin").attr("disabled",false);
	}
})

$("#QDScommon").click(function(){
	if($(this).is(":checked")){
		$("#QDSInspect").attr("disabled",true);
		$("#QDSAdmin").attr("disabled",true);
	}else{
		$("#QDSInspect").attr("disabled",false)
		$("#QDSAdmin").attr("disabled",false)
	}
})

var usernameFlag=false;
var realnameFlag=false;
var passwordFlag=false;
var repasswordFlag=false;
var codeFlag=false;

//注册时用户名检查
$("#username").on("blur",function(){
	$.ajax({
		url:"checkUsername.json",
		data:"username="+$(this).val(),
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.username=="exist"){
				$("#username").next().attr("class","glyphicon glyphicon-remove-circle");
				humane.log("用户名已存在");
				usernameFlag=false;
			}else if(data.username=="empty"){
				$("#username").next().attr("class","glyphicon glyphicon-remove-circle");
				usernameFlag=false;
			}else if(data.username=="noexist"){
				$("#username").next().attr("class","glyphicon glyphicon-ok-circle");
				usernameFlag=true;
			}
		},
		error:function(){
			humane.log("发生错误");
		}
	});
});

//注册时姓名检查
$("#realname").on("blur",function(){
	$.ajax({
		url:"checkRealname.ajax",
		data:"realname="+$(this).val(),
		//dataType:"json",
		type:"post",
		success:function(result){
			if(!result){
				$("#realname").next().attr("class","glyphicon glyphicon-remove-circle");
				realnameFlag=false;
			}else{
				$("#realname").next().attr("class","glyphicon glyphicon-ok-circle");
				realnameFlag=true;
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

//注册时密码检查，（此功能已取消，由admin统一注册，系统默认生成初始密码）
/*$("#password").on("blur",function(){
		$.ajax({
			url:"checkpassword.ajax",
			data:"password="+$(this).val(),
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.password=="false"){
					$("#password").next().attr("class","glyphicon glyphicon-remove-circle");
					alert("不能小于6位数");
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
				alert("发生错误");
			}
		});
	});*/

//注册时重复密码检查，（此功能已取消，由admin统一注册，系统默认生成初始密码）
/*$("#repassword").on("blur",function(){
		var password=$("#password").val();
		$.ajax({
			url:"checkrepassword.ajax",
			data:{"repassword":$(this).val(),"password":password},	//data要用json格式
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.repassword=="false"){
					$("#repassword").next().attr("class","glyphicon glyphicon-remove-circle");
					alert("密码不一致");
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
				alert("发生错误");
			}
		});
	});*/

//注册时验证码检查，（此功能已取消）
/*$("#code").on("blur",function(){
		var code=$(this).val();
		$.ajax({
			url:"verifyCode.json",
			data:"code="+code,
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.codeResult=="false"){
					$("#code").next().attr("class","glyphicon glyphicon-remove-circle");
					alert("验证码不一致");
					codeFlag=false;
				}else if(data.codeResult=="empty"){
					$("#code").next().attr("class","glyphicon glyphicon-remove-circle");
					codeFlag=false;
				}else if(data.codeResult="true"){
					$("#code").next().attr("class","glyphicon glyphicon-ok-circle");
					codeFlag=true;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				alert("发生错误");
			}
		})
	})*/

//注册时验证各项是否为true
/*function check(){
	if(usernameFlag && realnameFlag && passwordFlag && repasswordFlag && codeFlag){
		return true;
	}else{
		return false;
	}
}*/

function check(){
	if(usernameFlag && realnameFlag){
		return true;
	}else{
		return false;
	}
}
