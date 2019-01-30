$(".deleteUesr").on("click",function(){
	var obj=$(this);
	if(confirm("确定要删除【"+obj.attr("realname")+"】用户吗？")){
		$.ajax({
			url:"delUser.json",
			data:{id:obj.attr("uid")},
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