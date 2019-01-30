/*删除用户*/
$(".deleteUesr").on("click",function(){
	var obj=$(this);
	if(confirm("确定要删除【"+obj.attr("realname")+"】用户吗？")){
		$.ajax({
			url:"delUser.json",
			data:{id:obj.attr("uid"),username:obj.attr("username")},
			dataType:"json",
			type:"post",
			success:function(result){
				if(result.delresult=="notexsit"){
					humane.log("用户不存在，无法删除！");
				}else if(result.delresult=="false"){
					humane.log("删除【"+obj.attr("realname")+"】用户失败！");
				}else if(result.delresult=="true"){
					humane.log("删除成功！");
					obj.parents("tr").remove();
				}else if(result.delresult=="exsit"){
					humane.log("\"包材历史清单\"中存在用户【"+obj.attr("realname")+"】，用户删除失败！");
				}
			},
			error:function(){
				humane.log("发生错误");
			}
		});
	}
});

/*修改权限界面框*/
$(".modifyUesr").click(function () {
	var obj=$(this);
    $('#myModal').modal();
    $("#myModalRealname").html("您所要修改的用户："+obj.attr("realname"));
    $("#myModalUsername").val(obj.attr("username"));
 });

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

/*修改权限界面框 点击"修改"进行操作*/
$("#modifyUesrBtn").click(function(){
	var myModalUsername=$("#myModalUsername").val();
	var obj=document.getElementsByName("category");
	var check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
	$.ajax({
		url:"modifyUesrFunction.ajax",
		data:{username:myModalUsername,category:check_val},
		dataType: 'json',
		traditional: true,	/*传递数组需要用traditional: true*/
		type:"post",
		success:function(result){
			if(result.modifyResult=="failed"){
				humane.log("对不起，修改失败！");
			}else if(result.modifyResult=="success"){
				humane.log("修改成功！");
				window.setTimeout("jump()",1500);//弹窗后延迟跳转网页
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			/*alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);*/
			humane.log("发生错误");
		}
	})
})

function jump(){
	window.location.href="usersManageWindows";
}
