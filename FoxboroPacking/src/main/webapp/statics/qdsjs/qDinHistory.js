/*点击“详情”按钮*/
$(".detailHistory").click(function(){
	var obj=$(this);
	$('#addModal').modal();	//弹出窗口
	$.ajax({
		url:"detailHistory.ajax",
		data:{"id":obj.attr("id")},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("显示错误");
			}else{
				alert(data.result);
				
			/*<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3">模块串号</label>
					<div class="col-md-9">
						<input name="moduleNo" id="moduleNo" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">单板串号P</label>
					<div class="col-md-9 col-sm-9 col-xs-12">
						<input name="assyNoP" id="assyNoP" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">单板串号M</label>
					<div class="col-md-9 col-sm-9 col-xs-12">
						<input name="assyNoM" id="assyNoM" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">装配时间</label>
					<div class="col-md-9">
						<input name="assyTime" id="assyTime" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">装配人员</label>
					<div class="col-md-9">
						<input name="assyBy" id="assyBy" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">装配修改时间</label>
					<div class="col-md-9">
						<input name="assyModifyTime" id="assyModifyTime" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">装配修改人员</label>
					<div class="col-md-9">
						<input name="assyModifyBy" id="assyModifyBy" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">检验人员</label>
					<div class="col-md-9">
						<input name="inspectionBy" id="inspectionBy" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">检验时间</label>
					<div class="col-md-9">
						<input name="inspectionTime" id="inspectionTime" type="text" class="form-control" disabled="disabled">
					</div>
				</div>
			</div>*/
			}
		},
		error:function(){
			humane.log("ajax发生错误");
		}
	})
	resetModal();	//调用重置窗口
})

//重置窗口
function resetModal(){
	setTimeout(function(){$("#moduleNo").val("");	
	$("#assyNoP").val("");
	$("#assyNoM").val("");
	$("#assyTime").val("");
	$("#assyBy").val("");
	$("#assyModifyTime").val("");
	$("#assyModifyBy").val("");
	$("#testCode").val("");
	$("#testStatus").val("");
	$("#status").val("");
	$("#testResult").val("");
	$("#testBy").val("");
	$("#testTime").val("");
	$("#testEquipment").val("");
	$("#testDiscription").val("");
	$("#testErrorCode").val("");
	$("#inspectionBy").val("");
	$("#inspectionTime").val("");
	},500);
}