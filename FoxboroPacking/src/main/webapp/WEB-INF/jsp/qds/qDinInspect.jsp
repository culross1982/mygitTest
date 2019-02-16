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
					DIN检验 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN产品进行检验操作</small>
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
				<form method="get" action="${pageContext.request.contextPath}/qds/dinInspectWindows">
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
	
	<!-- DIN检验列表 -->
	<div class="col-md-8 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_content">
				<p class="text-muted font-13 m-b-30"></p>
				<div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row">
						<div class="col-sm-12">
							<div style="margin:5px 0;">
								<a class="btn btn-success btn-sm" id="dinInspect">DIN检验</a>
							</div>
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
											aria-label="Last name: activate to sort column ascending">检验状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">检验时间 </th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">检验者 </th>
										<!-- 普通用户进行对此功能进行隐藏 -->
										<th class="sorting modifyStatus" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">操作 </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${qdsProductList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${p.id }</td>
											<td>${p.order}</td>
											<td>${p.moduleNo}</td>
											<td>
												<c:if test="${p.inspectionStatus==0}"><span style="color:red;line-height:8px;">待检验</span></c:if>
												<c:if test="${p.inspectionStatus==1}"><span style="color:green;line-height:8px;">已检验</span></c:if>
											</td>
											<td><fm:formatDate value="${p.inspectionTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${p.inspectionRealName}</td>
											<td class="modifyStatus"><!-- 普通用户进行对此功能进行隐藏 -->
												<c:if test="${p.inspectionStatus==1}">
													<a class="cancelDinInspect operation btn btn-danger btn-xs" data-toggle="tooltip" id=${p.id } moduleNo=${p.moduleNo }
												   style="cursor: pointer"><i class="fa fa-rotate-left (alias)"></i>撤销</a>
												</c:if>
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
											<a href="${pageContext.request.contextPath}/qds/dinInspectWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinInspectWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinInspectWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/qds/dinInspectWindows?currentPage=${pageCount}" aria-controls="datatable-responsive" data-dt-idx="7"
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
	
	<!-- DIN今日检验工作令列表 -->
	<div class="col-md-4 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_content">
				<p class="text-muted font-13 m-b-30"></p>
				<div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row">
						<div class="col-sm-12">
							<div style="margin:5px 0;">
								<a class="btn btn-success btn-sm" style="color:black;background:white;border-color:black;cursor:default;">今日检验统计</a>
							</div>
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">工作令</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">数量</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${qdsInspectTotalList }" varStatus="status">
										<tr role="row" class="odd">
											<td>${t.order}</td>
											<td>${t.count}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Inspect数据 start -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">DIN检验</h4>
				</div>
				<div class="modal-body">

					<input class="hide" id="qdsProCategoryId" value="1"></input>	<!-- 给ajax提供产品分类 -->
					<!-- <form class="form-horizontal form-label-left input_mask" id="input_mask"> -->
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="checkModuleNo" id="checkModuleNo" type="text" class="form-control col-md-4 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">检验时间</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="checkInspectTime"  id="checkInspectTime" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" >
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">本次共检验条数</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="checkCount" id="checkCount" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" value="0">
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
					<button type="button" id="inspectBtn" class="btn btn-success" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						检验
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Inspect数据 end -->
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript">
	function getBrowser() {
		var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器   
		var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
		var isChrome = userAgent.indexOf("Chrome") > -1;
		if (isIE11) { //判断是否IE浏览器
			return "IE11";
		}else if (isChrome) {
			return "Chrome";
		}
	}

	$(document).ready(function() {
		var scriptNode = document.createElement("script");
		 if (getBrowser() == "IE11") {
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinInspect.js";
		} else if (getBrowser() == "Chrome") {
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinInspectChrome.js";
		} 
		$("body").append(scriptNode);
	})
</script>