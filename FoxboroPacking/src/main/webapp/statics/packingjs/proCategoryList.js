/*使用ajax新增产品分类*/
$("#button").click(function(){
	var productCategoryName=$("#productCategoryName").val();
	$.ajax({
		url:"addProCatetory.ajax",
		data:"productCategoryName="+productCategoryName,
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.addresult=="empty"){
				$("#message").html("请输入分类");
			}else if(result.addresult=="exsit"){
				$("#message").html("分类已存在");
			}else if(result.addresult=="true"){
				$("#message").html("");
				window.location.href = "ProCategoryListWindow";
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

/*根据id删除产品分类*/
$(".deleteProCate").click(function(){
	var obj=$(this);
	if(confirm("你确定要删除产品分类【"+obj.attr("pname")+"】吗？")){
		$.ajax({
			url:"delProCategory.ajax",
			type:"get",
			data:"id="+obj.attr("pid"),
			dataType:"json",
			success:function(result){
				if(result.delResult=="exsit"){
					humane.log("此分类下还有产品，无法删除！");
				}else if(result.delResult=="true"){
					humane.log("删除成功！");
					obj.parents("tr").remove();
				}else if(result.delResult=="empty"){
					humane.log("入参错误，无法删除！")
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				humane.log("发生错误");
			}
		})
	}
})