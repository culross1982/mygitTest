
$(".deleteApp").click(function(){
	var obj=$(this);
	if(confirm("你确定要删除产品【"+obj.attr("pname")+"】的基础数据信息吗？")){
		$.ajax({
			url:"doDelPro.json",
			data:{id:obj.attr("pid"),pwd:obj.attr("pname")},
			dataType:"json",
			type:"GET",
			success:function(result){
				if(result.delresult=="notexist"){
					humane.log("产品【"+obj.attr("pname")+"】不存在！");
				}else if(result.delresult=="false"){
					humane.log("产品【"+obj.attr("pname")+"】删除失败！");
				}else if(result.delresult=="exist"){
					humane.log("产品【"+obj.attr("pname")+"】有使用记录，删除失败！");
				}else if(result.delresult=="true"){
					humane.log("删除成功！");
					obj.parents("tr").remove();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				/*alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);*/
				humane.log("发生错误");
			}
		});
	}
});

//判断上传的文件是否为excel
function checkSuffix(){
	var name=$("#file").val();
	var suffix=(name.substr(name.lastIndexOf("."))).toLowerCase();
	if(suffix!=".xls" && suffix!=".xlsx"){
		humane.log("您上传的文件类型不符合（.xls或者.xlsx）")
		return false;
	}
}