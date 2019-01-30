$(".deleteMat").click(function(){
	var obj=$(this);
	var id=obj.attr("mid");
	var mName=obj.attr("mName");
	if(confirm("你确定要删除材料【"+mName+"】的数据信息吗？")){
		$.ajax({
			url:"doDelMaterials.json",
			data:{"id":id,"mName":mName},
			dataType:"json",
			type:"GET",
			success:function(result){
				if(result.delresult=="notexsit"){
					humane.log("要删除的材料【"+mName+"】不存在！");
				}else if(result.delresult=="exist"){
					humane.log("要删除的材料【"+mName+"】已有使用记录，不予删除！");
				}else if(result.delresult=="false"){
					humane.log("删除材料【"+mName+"】失败！");
				}else if(result.delresult=="true"){
					humane.log("删除材料【"+mName+"】成功");
					obj.parents("tr").remove();
				}
			},
			error:function(){
				humane.log("发生错误！");
			}
		});
	}
});