<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/qdscss/qDinAssy.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					DIN装配数据 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN装配数据进行操作</small>
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
				<form method="get" action="${pageContext.request.contextPath}/qds/dinAssyWindows">
					<div class="form-group">
						<ul>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="moduleNo" type="text" class="form-control col-md-7 col-xs-12" value="${qProAssy.moduleNo }" 
										pattern="[0-9a-zA-Z]*" placeholder="请输入模块串号">
									</div>
								</div>
							</li>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">单板串号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="assyNo" type="text" class="form-control col-md-7 col-xs-12" value="${qProAssy.assyNo }" 
										pattern="[0-9a-zA-Z]*" placeholder="请输入单板串号">
									</div>
								</div>
							</li>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">装配人员</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<input name="realname" type="text" class="form-control col-md-7 col-xs-12" value="${qProAssy.realname }" placeholder="请输入人员">
									</div>
								</div>
							</li>
						</ul>
						<ul class="daterange">
							<li>
								<label class="col-md-3 col-sm-3 col-xs-6">选择时间</label>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<input class="form-control" name="range_date" type="text" id="range_date" placeholder="请选择时间段">
									<i class="fa fa-calendar"></i>
								</div>
							</li>
							<li>
								<button type="submit" class="btn btn-success">查询</button>
							</li>
							<!-- 屏蔽导出功能 -->
							<!-- <li class="pull-right">
								<button type="button" class="btn btn-success pull-right" id="export" 
								data-toggle="tooltip" data-placement="top" data-original-title="D:\\">表格导出</button>
							</li> -->
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
							<div style="margin:5px 0;">
								<a class="btn btn-success btn-sm" id="addDinAssy">新增装配数据</a>
							</div>
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">模块串号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">单板串号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">装配人员</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">装配时间
											<div class="pull-right">
												<a href="${pageContext.request.contextPath}/qds/dinDateAsc">
													<i class="fa fa-sort-alpha-asc date-asc"></i>
												</a>
												<a href="${pageContext.request.contextPath}/qds/dinAssyWindows">
													<i class="fa fa-sort-alpha-desc date-desc"></i>
												</a>
											</div>
										</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">修改人员</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">修改时间
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="a" items="${qdsProductAssyList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${a.id }</td>
											<td>${a.moduleNo}</td>
											<td>${a.assyNo}</td>
											<td>${a.realname}</td>
											<td><fm:formatDate value="${a.assyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>${a.modifyName}</td>
											<td><fm:formatDate value="${a.assyModifyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><a class="modifyDinAssy operation btn btn-info btn-xs" data-toggle="tooltip" id=${a.id } realname=${a.realname } moduleNo=${a.moduleNo } assyNo=${a.assyNo } 
											       assyTime=<fm:formatDate value="${a.assyTime}" pattern="yyyy-MM-dd HH:mm:ss" /> data-placement="top" 
											       style="cursor: pointer"><i class="fa fa-pencil"></i>修改</a>&nbsp;&nbsp;
												<a class="deleteDinAssy operation btn btn-danger btn-xs" data-toggle="tooltip" id=${a.id } assyNo=${a.assyNo } moduleNo=${a.moduleNo }
												   style="cursor: pointer"><i class="fa fa-trash-o"></i>删除</a></td>
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
											<a href="${pageContext.request.contextPath}/qds/dinAssyWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinAssyWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinAssyWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/qds/dinAssyWindows?currentPage=${pageCount}" aria-controls="datatable-responsive" data-dt-idx="7"
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
	
	<!-- 新增装配数据 start -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增装配数据</h4>
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
									<input name="moduleNo" id="moduleNo" type="text" class="form-control col-md-4 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">单板串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="assyNoP" id="assyNoP" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">单板串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="assyNoM" id="assyNoM" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">装配时间</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="assyTime"  id="assyTime" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" >
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">装配人员</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="realname" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" value="${sessionScope.user.realname}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">本次共上传条数</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="count" id="count" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" value="0">
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
					<button type="button" id="addAssyDataBtn" class="btn btn-success" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						新增
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增装配数据 end -->
	
	<!-- modify装配数据 start -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改装配数据</h4>
				</div>
				<div class="modal-body">

					<input class="hide" id="qdsProCategoryId" value="1"></input>	<!-- 给ajax提供产品分类 -->
					<form class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<input name="idModify" id="idModify"  type="hidden">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="moduleNoModify" id="moduleNoModify" type="text" class="form-control col-md-4 col-xs-12" disabled="disabled">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">单板串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="assyNoModify" id="assyNoModify" type="text" class="form-control col-md-7 col-xs-12">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">装配时间</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="assyTimeModify"  id="assyTimeModify" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" >
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">装配人员</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="realnameModify" id="realnameModify" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled">
								</div>
							</div>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default closeModal" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						关闭
					</button>
					<button type="button" id="modifyDinAssyBtn" class="btn btn-success" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- modify装配数据 end -->
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
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinAssy.js";
		} else if (getBrowser() == "Chrome") {
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinAssyChrome.js";
		} 
		$("body").append(scriptNode);
	})
</script>
<%-- <script src="${pageContext.request.contextPath }/statics/qdsjs/qDinAssy.js"></script> --%>
<!-- Datatables -->
<!-- sorting -->
<%-- <script src="${pageContext.request.contextPath }/statics/js/jquery.dataTables.min.js"></script> --%>