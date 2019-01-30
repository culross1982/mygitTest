<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/qdscss/qDinTest.css" rel="stylesheet">
<style>
#input_mask span {
	text-align: left;
}
</style>

<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					DIN测试数据 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN测试数据进行操作</small>
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
				<form method="get" action="${pageContext.request.contextPath}/qds/dinTestWindows">
					<div class="form-group">
						<ul>
							<li class="daterange">
								<label class="col-md-3 col-sm-3 col-xs-6">选择时间</label>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<input class="form-control" name="range_date" type="text" id="range_date" placeholder="请选择测试时间段">
									<i class="fa fa-calendar"></i>
								</div>
							</li>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="testNo" type="text" class="form-control col-md-7 col-xs-12" value="${qProTest.testNo }" pattern="[0-9a-zA-Z]*" placeholder="请输入模块串号">
									</div>
								</div>
							</li>
							<li style="width: 200px">
								<div class="form-group">
									<label class="control-label col-md-5 col-sm-5 col-xs-6">测试人员</label>
									<div class="col-md-7 col-sm-7 col-xs-6">
										<input name="realname" type="text" class="form-control col-md-7 col-xs-12" value="${qProTest.realname }" placeholder="请输入人员"> 
									</div>
								</div>
							</li>

							<li style="width: 50px">
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
							<div class="modifyStatus">
								<form action="${pageContext.request.contextPath}/qds/addDinTestFromLog" method="post" enctype="multipart/form-data"
									onsubmit="return checkSuffix();" class="form-inline" style="margin:5px 0;">
									<div class="form-group">
										<input type="file" style="width: 250px;" name="attachment" id="attachment" />
									</div>
									<div class="form-group">
										<input type="submit" class="btn btn-success btn-sm" value="点击上传" id="fileButton" style="color: withe" />
									</div>
									<div class="form-group">
										<em>上传.log的DIN测试文件</em>
									</div>
								</form>
							</div>
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 20px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 60px;"
											aria-label="Last name: activate to sort column ascending">模块串号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">代码</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 50px;"
											aria-label="Last name: activate to sort column ascending">测试状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 50px;"
											aria-label="Last name: activate to sort column ascending">测试结果</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">测试设备<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 50px;"
											aria-label="Last name: activate to sort column ascending">测试人员</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 80px;"
											aria-label="Last name: activate to sort column ascending">测试时间 </th>	
										<th class="sorting " tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 60px;"
											aria-label="Last name: activate to sort column ascending">测试描述</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${qdsProductTestList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${t.id }</td>
											<td>${t.testNo}</td>
											<td>${t.testCode}</td>
											<td>${t.testStatus}</td>
											<td>${t.status}</td>
											<td>${t.testResult}</td>
											<td>${t.testEquipment}</td>
											<td>${t.realname}</td>
											<td><fm:formatDate value="${t.testTime}" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${t.discription}</td>
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
											<a href="${pageContext.request.contextPath}/qds/dinTestWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinTestWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinTestWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/qds/dinTestWindows?currentPage=${pageCount}" aria-controls="datatable-responsive"
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
<script src="${pageContext.request.contextPath }/statics/qdsjs/qDinTest.js"></script>