<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/qdscss/qDinHistoryDetail.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					DIN数据详情 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN数据详情进行查看</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			
		</div>
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_content">
				<div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
					<div class="row">
						<div class="col-sm-12">
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">工作令</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${order}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">模块串号</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProduct.moduleNo}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">单板串号</label>
										<div class="col-md-6 col-sm-9 col-xs-12">
											<input type="text" class="form-control" value="${assyNoP}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">单板串号</label>
										<div class="col-md-6 col-sm-9 col-xs-12">
											<input type="text" class="form-control" value="${assyNoM}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">装配人员</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductAssy.assyRealName}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">装配时间</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="<fm:formatDate value="${qdsProductAssy.assyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled">
										</div>
									</div>
									
<%-- 									<div class="form-group">
										<label class="control-label col-md-4">装配修改时间</label>
										<div class="col-md-6">${qdsProductAssy.assyModifyTime}
											<input type="text" class="form-control" value="<fm:formatDate value="${qdsProductAssy.assyModifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">装配修改人员</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductAssy.assyModifyRealName}" disabled="disabled">
										</div>
									</div> --%>
									
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">测试代码</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.testCode}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">测试状态</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.testStatus}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">状态</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.status}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">测试结果</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.testResult}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">测试设备</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.testEquipment}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">上传人员</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.realname}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">测试时间</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="<fm:formatDate value="${qdsProductTest.testTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-4">测试描述</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTest.testDiscription}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">不良记录</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProductTestFail.errorStatus}" disabled="disabled">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4">检验人员</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="${qdsProduct.inspectionRealName}" disabled="disabled">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-4">检验时间</label>
										<div class="col-md-6">
											<input type="text" class="form-control" value="<fm:formatDate value="${qdsProduct.inspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled="disabled">
										</div>
									</div>
								</div>
							</table>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/qdsjs/qDinHistory.js"></script>