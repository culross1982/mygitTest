<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/qdscss/qDinHistory.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					DIN历史数据 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN历史数据进行操作</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<%-- <div class="x_content">
				<form method="post" action="${pageContext.request.contextPath}/pro/searchProList?currentPage=1">
					<input type="hidden" name="pageIndex" value="1" />
				</form>
			</div> --%>
			<!-- 时间选择 -->
			<div class="x_content">
				<form method="get" action="${pageContext.request.contextPath}/qds/dinHistoryWindows">
					<div class="form-group">
						<ul>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="moduleNo" type="text" class="form-control col-md-7 col-xs-12"  
										pattern="[0-9a-hj-np-zA-Z]*" placeholder="请输入模块串号">
									</div>
								</div>
							</li>
							<li>
								<button type="submit" class="btn btn-success">查询</button>
							</li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_content">
				<p class="text-muted font-13 m-b-30"></p>
				<div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row">
						<div class="col-sm-12">
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">工作令</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">模块串号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">装配状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试状态 </th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">检验状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">产品类别
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">操作	
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${qdsProductList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${p.id }</td>
											<td>${p.order}</td>
											<td>${p.moduleNo}</td>
											<td>
												<c:if test="${p.assyStatus==0}"><i class="glyphicon glyphicon-remove"></i></c:if>
												<c:if test="${p.assyStatus==1}"><i class="glyphicon glyphicon-ok"></i></c:if>
											</td>
											<td>
												<c:if test="${p.testStatus==0}"><i class="glyphicon glyphicon-remove"></i></c:if>
												<c:if test="${p.testStatus==1}"><i class="glyphicon glyphicon-ok"></i></c:if>
											</td>
											<td>
												<c:if test="${p.inspectionStatus==0}"><i class="glyphicon glyphicon-remove"></i></c:if>
												<c:if test="${p.inspectionStatus==1}"><i class="glyphicon glyphicon-ok"></i></c:if>				
											</td>
											<td>${p.qdsProCategoryName}</td>
											<td><a class="detailHistory operation modifyStatus btn btn-success btn-xs" data-toggle="tooltip" id=${p.id } data-placement="top"
													style="cursor: pointer"><i class="fa fa-table"></i>详情</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-5">
							<div class="dataTables_info" id="datatable-responsive_info" role="status" aria-live="polite">共${count }条记录 ${currentPage }/${pageCount }页</div>
						</div>
						<div class="col-sm-7">
							<div class="dataTables_paginate paging_simple_numbers" id="datatable-responsive_paginate">
								<ul class="pager">
									<c:if test="${currentPage!=1}">
										<li class="paginate_button previous">
											<a href="${pageContext.request.contextPath}/qds/dinHistoryWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinHistoryWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinHistoryWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/qds/dinHistoryWindows?currentPage=${pageCount}" aria-controls="datatable-responsive" data-dt-idx="7"
												tabindex="0">最后一页</a>
										</li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- detailHistory start -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body">

					<h5 id="myModalRealname"></h5>
					<input class="hide" id="myModalUsername" name="myModalUsername"></input>
					<!-- <form class="form-horizontal form-label-left input_mask" id="input_mask"> -->
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">

							<!-- <div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-3">模块串号</label>
									<div class="col-md-9">
										<input name="moduleNo" id="moduleNo" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">单板串号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="assyNoP" id="assyNoP" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">单板串号</label>
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
							</div> -->
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-3">测试代码</label>
									<div class="col-md-9">
										<input name="testCode" id="testCode" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试状态</label>
									<div class="col-md-9">
										<input name="testStatus" id="testStatus" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">状态</label>
									<div class="col-md-9">
										<input name="status" id="status" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试结果</label>
									<div class="col-md-9">
										<input name="testResult" id="testResult" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试人员</label>
									<div class="col-md-9">
										<input name="testBy" id="testBy" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试时间</label>
									<div class="col-md-9">
										<input name="testTime" id="testTime" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试设备</label>
									<div class="col-md-9">
										<input name="testEquipment" id="testEquipment" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">测试描述</label>
									<div class="col-md-9">
										<input name="testDiscription" id="testDiscription" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">不良代码</label>
									<div class="col-md-9">
										<input name="testErrorCode" id="testErrorCode" type="text" class="form-control" disabled="disabled">
									</div>
								</div>
							</div>
						</table>
					</div>
					<!-- </form> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default closeModal" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- detailHistory end -->
	
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/qdsjs/qDinHistory.js"></script>