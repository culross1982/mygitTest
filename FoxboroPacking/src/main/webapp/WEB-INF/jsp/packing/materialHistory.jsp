<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/css/materialHistory.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12" >
		<div class="x_panel" >
			<div class="x_title">
				<h2>
					包材历史清单 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 查看包材历史使用数据</small>
				</h2>

				<div class="clearfix"></div>
			</div>
			<!-- 时间选择 -->
			<div class="x_content" style="height:30px">
				<form method="get" action="${pageContext.request.contextPath}/mat/materialHistoryWindows">
					<div class="form-group daterange ">
						<ul>
							<li>
								<label class="col-md-3 col-sm-3 col-xs-6">选择时间</label>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<input class="form-control" name="range_date" type="text" id="range_date">
									<i class="fa fa-calendar"></i>
								</div>
							</li>
							<li>
								<button type="submit" class="btn btn-success" >查询</button>
							</li>
							<!-- 将包材历史清单导入Excel中  #到服务器端# -->
							<!-- <li class="pull-right">
								<button type="button" class="btn btn-success pull-right" id="export" 
								data-toggle="tooltip" data-placement="top" data-original-title="D:\包材历史清单表\">表格导出</button>
							</li> -->
							<li class="pull-right">
								<a class="btn btn-success" href="${pageContext.request.contextPath}/mat/export">表格下载 </a>
							</li>
						</ul>
					</div>
				</form>
			</div>
			<div class="x_content"></div>
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
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 10px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">材料型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">材料分类</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 10px;"
											aria-label="Last name: activate to sort column ascending">数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">产品型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">分类</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">使用者</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 70px;"
											aria-label="Last name: activate to sort column ascending">操作时间
											<div class="pull-right">
												<a href="${pageContext.request.contextPath}/mat/dateAsc">
													<i class="fa fa-sort-alpha-asc date-asc"></i>
												</a>
												<a href="${pageContext.request.contextPath}/mat/materialHistoryWindows">
													<i class="fa fa-sort-alpha-desc date-desc"></i>
												</a>
											</div>
										</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 10px;"
											aria-label="Last name: activate to sort column ascending">状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 80px;"
											aria-label="Last name: activate to sort column ascending">备注</th>
										<th class="sorting modifyStatus" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 10px;"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="h" items="${historyList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${h.id }</td>
											<td>${h.materialsName}</td>
											<td>${h.materialsCategoryName}</td>
											<td>${h.materialsChangeNum}</td>
											<td>${h.pwd}</td>
											<td>${h.productCategoryName}</td>
											<td>${h.realname}</td>
											<td><fm:formatDate value="${h.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${h.materialStatusName}</td>
											<td>${h.remark}</td>
											<td class="modifyStatus">
												<c:if test="${h.materialStatusId!=1 }">  <!-- 状态为新建的历史记录无"撤销"选项 -->
													<a class="cancelHistory operation btn btn-warning btn-xs" data-toggle="tooltip" hid=${h.id } data-placement="top" data-original-title="撤销历史信息" style="cursor: pointer"><i class="fa fa-undo"></i>撤销</a>
												</c:if></td>
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
											<a href="${pageContext.request.contextPath}/mat/materialHistoryWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/mat/materialHistoryWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/mat/materialHistoryWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/mat/materialHistoryWindows?currentPage=${pageCount}" aria-controls="datatable-responsive"
												data-dt-idx="7" tabindex="0">最后一页</a>
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
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/packingjs/materialHistory.js"></script>
<!-- Datatables -->
<!-- sorting -->
<%-- <script src="${pageContext.request.contextPath }/statics/js/jquery.dataTables.min.js"></script> --%>