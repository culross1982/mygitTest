var category=new Array("box","gasket","spongiaOne","spongiaTwo","esdBag","geDang","esdTable","esdBubbleBag","remark");
//根据分类表选择产品型号
$("#productCategoryId").change(function(){
	var id=$(this).val();
	if(id!="" && id!=0 && id!=null){
		$.ajax({
			url:"proUseMateOutCheck.json",
			data:{id:id},
			type:"post",
			dataType:"json",
			success:function(data){
				$("#pwd").html("");
				var options="<option value='0'>--请选择--</option>";
				for(var i=0;i<data.length;i++){
					options+="<option value=\""+data[i].id+"\">"+data[i].pwd+"</option>";
				}
				$("#pwd").html(options);
				//重新选择类型后删除页面产品信息
				for(var i=0;i<category.length;i++){
					$("#"+category[i]).val("");	//重置包材分类信息
					$("#"+category[i]+"Quantity").val(""); //重置包材总数量信息
					$("#"+category[i]+"QuantityMate").val("");	//库存量重置
					$("#"+category[i]+"QuantityMate").next().html("");	//备注重置
				}
				//重置数量
				$("#pwdQuantity").val("");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				humane.log("加载产品型号失败！");
			}
		});
	}else{
		$("#pwd").html("");
		var options="<option value='0'>--请选择--</option>";
	}	
});

//选择产品后显示产品信息,如仓库中无此型号包材则提示
$("#pwd").change(function(){
	var pwdId=$(this).val();
	//重置数量
	$("#pwdQuantity").val("");
	var pwdQuantity=$("#pwdQuantity").val();
	if(pwdId!=0 && pwdId!=null){
		$.ajax({
			url:"showProductInfo.json",
			data:"id="+pwdId,
			dataType:"json",
			type:"post",
			success:function(data){
				$("#box").val(data.box);
				$("#gasket").val(data.gasket);
				$("#spongiaOne").val(data.spongiaOne);
				$("#spongiaTwo").val(data.spongiaTwo);
				$("#esdBag").val(data.esdBag);
				$("#geDang").val(data.geDang);
				$("#esdTable").val(data.esdTable);
				$("#esdBubbleBag").val(data.esdBubbleBag);
				$("#remark").val(data.remark);
				$("#boxQuantity").val(data.boxQuantity*pwdQuantity);
				$("#gasketQuantity").val(data.gasketQuantity*pwdQuantity);
				$("#spongiaOneQuantity").val(data.spongiaOneQuantity*pwdQuantity);
				$("#spongiaTwoQuantity").val(data.spongiaTwoQuantity*pwdQuantity);
				$("#esdBagQuantity").val(data.esdBagQuantity*pwdQuantity);
				$("#geDangQuantity").val(data.geDangQuantity*pwdQuantity);
				$("#esdTableQuantity").val(data.esdTableQuantity*pwdQuantity);
				$("#esdBubbleBagQuantity").val(data.esdBubbleBagQuantity*pwdQuantity);
				//库存量
				if(data.boxMate==-999){
					$("#boxQuantityMate").next().html("库存中无此型号包材");
					$("#boxQuantityMate").val("");
				}else if(data.boxMate==-888){
					$("#boxQuantityMate").val("");
					$("#boxQuantityMate").next().html("");
				}else{
					$("#boxQuantityMate").next().html("");
					$("#boxQuantityMate").val(data.boxMate);
				}

				if(data.gasketMate==-999){
					$("#gasketQuantityMate").next().html("库存中无此型号包材");
					$("#gasketQuantityMate").val("");
				}else if(data.gasketMate==-888){
					$("#gasketQuantityMate").val("");
					$("#gasketQuantityMate").next().html("");
				}else{
					$("#gasketQuantityMate").next().html("");
					$("#gasketQuantityMate").val(data.gasketMate);
				}

				if(data.spongiaOneMate==-999){
					$("#spongiaOneQuantityMate").next().html("库存中无此型号包材");
					$("#spongiaOneQuantityMate").val("");
				}else if(data.spongiaOneMate==-888){
					$("#spongiaOneQuantityMate").val("");
					$("#spongiaOneQuantityMate").next().html("");
				}else{
					$("#spongiaOneQuantityMate").next().html("");
					$("#spongiaOneQuantityMate").val(data.spongiaOneMate);
				}

				if(data.spongiaTwoMate==-999){
					$("#spongiaTwoQuantityMate").next().html("库存中无此型号包材");
					$("#spongiaTwoQuantityMate").val("");
				}else if(data.spongiaTwoMate==-888){
					$("#spongiaTwoQuantityMate").val("");
					$("#spongiaTwoQuantityMate").next().html("");
				}else{
					$("#spongiaTwoQuantityMate").next().html("");
					$("#spongiaTwoQuantityMate").val(data.spongiaTwoMate);
				}

				if(data.esdBagMate==-999){
					$("#esdBagQuantityMate").next().html("库存中无此型号包材");
					$("#esdBagQuantityMate").val("");
				}else if(data.esdBagMate==-888){
					$("#esdBagQuantityMate").val("");
					$("#esdBagQuantityMate").next().html("");
				}else{
					$("#esdBagQuantityMate").next().html("");
					$("#esdBagQuantityMate").val(data.esdBagMate);
				}

				if(data.geDangMate==-999){
					$("#geDangQuantityMate").next().html("库存中无此型号包材");
					$("#geDangQuantityMate").val("");
				}else if(data.geDangMate==-888){
					$("#geDangQuantityMate").val("");
					$("#geDangQuantityMate").next().html("");
				}else{
					$("#geDangQuantityMate").next().html("");
					$("#geDangQuantityMate").val(data.geDangMate);
				}

				if(data.esdTableMate==-999){
					$("#esdTableQuantityMate").next().html("库存中无此型号包材");
					$("#esdTableQuantityMate").val("");
				}else if(data.esdTableMate==-888){
					$("#esdTableQuantityMate").val("");
					$("#esdTableQuantityMate").next().html("");
				}else{
					$("#esdTableQuantityMate").next().html("");
					$("#esdTableQuantityMate").val(data.esdTableMate);
				}

				if(data.esdBubbleBagMate==-999){
					$("#esdBubbleBagQuantityMate").next().html("库存中无此型号包材");
					$("#esdBubbleBagQuantityMate").val("");
				}else if(data.esdBubbleBagMate==-888){
					$("#esdBubbleBagQuantityMate").val("");
					$("#esdBubbleBagQuantityMate").next().html("");
				}else{
					$("#esdBubbleBagQuantityMate").next().html("");
					$("#esdBubbleBagQuantityMate").val(data.esdBubbleBagMate);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				humane.log("加载产品信息失败！");
			}
		});
	}
});

$("#pwdQuantity").focus(function(){
	$(this).val("");
});

//产品数量改变后改变材料数量（鼠标移除时动作，修复bug）
$("#pwdQuantity").mouseout(function(){
	var pwdQuantity=$(this).val();
	var pwdId=$("#pwd").val();
	if(pwdId !=0 && pwdId!=null){
		$.ajax({
			url:"showProductInfo.json",
			data:{id:pwdId},
			type:"post",
			dataType:"json",
			success:function(data){
				//包装盒数量：1个包装盒可能有多个产品，（包装盒数量/1个盒子所装的产品数*实际产品数）向下取整后+1，
				//如果实际产品数正好等于包装盒可以装的数量则不+1
				if(data.boxMate!=-999 && data.boxMate!=-888){
					$("#boxQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity)+1);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity)+1)>data.boxMate){
						$("#boxQuantityMate").next().html("库存数不足！");
					}else{
						$("#boxQuantityMate").next().html("");
					}
				}
				//衬垫：原理同包装盒
				if(data.gasketMate!=-999 && data.gasketMate!=-888){
					$("#gasketQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.gasketQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.gasketQuantity/data.pwdQuantity*pwdQuantity)+1);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.gasketQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.gasketQuantity/data.pwdQuantity*pwdQuantity)+1)>data.gasketMate){
						$("#gasketQuantityMate").next().html("库存数不足！");
					}else{
						$("#gasketQuantityMate").next().html("");
					}
				}
				//海绵（变化）根据产品数量计算得出：
				if(data.spongiaOneMate!=-999 && data.spongiaOneMate!=-888){
					$("#spongiaOneQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.spongiaOneQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.spongiaOneQuantity/data.pwdQuantity*pwdQuantity)+1);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.spongiaOneQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.spongiaOneQuantity/data.pwdQuantity*pwdQuantity)+1)>data.spongiaOneMate){
						$("#spongiaOneQuantityMate").next().html("库存数不足！");
					}else{
						$("#spongiaOneQuantityMate").next().html("");
					}
				}
				//海绵（固定）一个盒子所用数量固定：盒子数*每个盒子使用的海绵数
				if(data.spongiaTwoMate!=-999 && data.spongiaTwoMate!=-888){
					/*$("#spongiaTwoQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.spongiaTwoQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.spongiaTwoQuantity/data.pwdQuantity*pwdQuantity)+1);*/
					$("#spongiaTwoQuantity").val(pwdQuantity%data.pwdQuantity==0?
							(Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity))*data.spongiaTwoQuantity:
								(Math.floor(data.boxQuantity/data.pwdQuantity*pwdQuantity)+1)*data.spongiaTwoQuantity);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.spongiaTwoQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.spongiaTwoQuantity/data.pwdQuantity*pwdQuantity)+1)>data.spongiaTwoMate){
						$("#spongiaTwoQuantityMate").next().html("库存数不足！");
					}else{
						$("#spongiaTwoQuantityMate").next().html("");
					}
				}
				//静电袋数量：1个产品=1个静电袋
				if(data.esdBagMate!=-999 && data.esdBagMate!=-888){
					$("#esdBagQuantity").val(pwdQuantity);
					if(pwdQuantity>data.esdBagMate){
						$("#esdBagQuantityMate").next().html("库存数不足！");
					}else{
						$("#esdBagQuantityMate").next().html("");
					}
				}
				//格挡：原理同包装盒
				if(data.geDangMate!=-999 && data.geDangMate!=-888){
					$("#geDangQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.geDangQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.geDangQuantity/data.pwdQuantity*pwdQuantity)+1);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.geDangQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.geDangQuantity/data.pwdQuantity*pwdQuantity)+1)>data.geDangMate){
						$("#geDangQuantityMate").next().html("库存数不足！");
					}else{
						$("#geDangQuantityMate").next().html("");
					}
				}
				//静电铭牌数量：产品数+包装盒数量
				if(data.esdTableMate!=-999 && data.esdTableMate!=-888){
					var boxQuantity=$("#boxQuantity").val();
					$("#esdTableQuantity").val(Math.floor(pwdQuantity)+Math.floor(boxQuantity));
					if((Math.floor(pwdQuantity)+Math.floor(boxQuantity))>data.esdTableMate){
						$("#esdTableQuantityMate").next().html("库存数不足！");
					}else{
						$("#esdTableQuantityMate").next().html("");
					}
				}
				//防静电气泡袋：
				if(esdBubbleBagMate!=-999 && data.esdBubbleBagMate!=-888){
					$("#esdBubbleBagQuantity").val(pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.esdBubbleBagQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.esdBubbleBagQuantity/data.pwdQuantity*pwdQuantity)+1);
					if((pwdQuantity%data.pwdQuantity==0?
							Math.floor(data.esdBubbleBagQuantity/data.pwdQuantity*pwdQuantity):
								Math.floor(data.esdBubbleBagQuantity/data.pwdQuantity*pwdQuantity)+1)>data.esdBubbleBagMate){
						$("#esdBubbleBagQuantityMate").next().html("库存数不足！");
					}else{
						$("#esdBubbleBagQuantityMate").next().html("");
					}
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				humane.log("数量更新失败！");
			}
		});
	}
});

//产品使用包材 “使用” 按钮
$("#ProUseMateBtn").click(function(){
	var box=$("#box").val();
	var boxQuantity=$("#boxQuantity").val();
	var gasket=$("#gasket").val();
	var gasketQuantity=$("#gasketQuantity").val();
	var spongiaOne=$("#spongiaOne").val();
	var spongiaOneQuantity=$("#spongiaOneQuantity").val();
	var spongiaTwo=$("#spongiaTwo").val();
	var spongiaTwoQuantity=$("#spongiaTwoQuantity").val();
	var esdBag=$("#esdBag").val();
	var esdBagQuantity=$("#esdBagQuantity").val();
	var geDang=$("#geDang").val();
	var geDangQuantity=$("#geDangQuantity").val();
	var esdTable=$("#esdTable").val();
	var esdTableQuantity=$("#esdTableQuantity").val();
	var esdBubbleBag=$("#esdBubbleBag").val();
	var esdBubbleBagQuantity=$("#esdBubbleBagQuantity").val();
	var productCategoryId=$("#productCategoryId").val();
	var pwd=$("#pwd").val();
	var pwdQuantity=$("#pwdQuantity").val();
	$.ajax({
		type:"POST",
		url:"../mat/doUseMaterialsByPro.ajax",
		data:{"box":box,"boxQuantity":boxQuantity,"gasket":gasket,"gasketQuantity":gasketQuantity,"spongiaOne":spongiaOne,"spongiaOneQuantity":spongiaOneQuantity,"spongiaTwo":spongiaTwo,"spongiaTwoQuantity":spongiaTwoQuantity,"esdBag":esdBag,"esdBagQuantity":esdBagQuantity,"geDang":geDang,"geDangQuantity":geDangQuantity,"esdTable":esdTable,"esdTableQuantity":esdTableQuantity,"esdBubbleBag":esdBubbleBag,"esdBubbleBagQuantity":esdBubbleBagQuantity,"productCategoryId":productCategoryId,"pwd":pwd,"pwdQuantity":pwdQuantity},
		dataType:"json",
		success:function(result){
			if(result.UseMaterialsByProResult=="false"){
				humane.log("使用失败！");
			}else if(result.UseMaterialsByProResult=="noProductCategoryId"){
				humane.log("请选择产品分类和产品型号！");
			}else if(result.UseMaterialsByProResult=="noPwdQuantity"){
				humane.log("请填写正确的使用数量！");
			}else if(result.UseMaterialsByProResult=="success"){
				humane.log("使用成功！");
				window.setTimeout("jump()",1500);//弹窗后延迟跳转网页
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	})
})

function jump(){
	window.location.href="productUseMateWindow";
}