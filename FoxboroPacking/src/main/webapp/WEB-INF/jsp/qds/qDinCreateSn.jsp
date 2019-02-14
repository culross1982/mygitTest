<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<!-- daterangepicker materialHistory-->
<link href="${pageContext.request.contextPath }/statics/qdscss/qDinCreate.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					DIN S/N生成 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对DIN S/N进行生成</small>
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
				<form method="get" action="${pageContext.request.contextPath}/qds/dinCreateSnWindows">
					<div class="form-group">
						<ul>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">工作令</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="order" type="text" class="form-control col-md-7 col-xs-12" pattern="[0-9a-zA-Z]*" placeholder="请输入工作令">
									</div>
								</div>
							</li>
							<li>
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">模块型号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="module" type="text" class="form-control col-md-7 col-xs-12" pattern="[0-9a-zA-Z]*" placeholder="请输入模块串号">
									</div>
								</div>
							</li>
							<li>
								<button type="submit" class="btn btn-success">查询</button>
							</li>
						</ul>
						<!-- <ul class="daterange">
							<li>
								<label class="col-md-3 col-sm-3 col-xs-6">选择时间</label>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<input class="form-control" name="range_date" type="text" id="range_date" placeholder="请选择时间段">
									<i class="fa fa-calendar"></i>
								</div>
							</li>
							
						</ul> -->
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
							<div class="modifyStatus" style="margin:5px 0;">
								<a class="btn btn-success btn-sm" id="createSn">DIN S/N生成</a>
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
											aria-label="Last name: activate to sort column ascending">模块型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">版本</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">串号起始位</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">串号结束位</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">创建人员</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">创建时间
										<th class="sorting modifyStatus" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" 
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="o" items="${qdsProductOrderList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${o.id }</td>
											<td>${o.order}</td>
											<td>${o.module}</td>
											<td>${o.ver}</td>
											<td>${o.pwdQuantity}</td>
											<td>${o.snStart}</td>
											<td>${o.snEnd}</td>
											<td>${o.realname}</td>
											<td><fm:formatDate value="${o.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><a class="deleteProductOrder operation modifyStatus btn btn-danger btn-xs" data-toggle="tooltip" id=${o.id } order=${o.order } data-placement="top"
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
											<a href="${pageContext.request.contextPath}/qds/dinCreateSnWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinCreateSnWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/qds/dinCreateSnWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/qds/dinCreateSnWindows?currentPage=${pageCount}" aria-controls="datatable-responsive" data-dt-idx="7"
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
	
	<!-- DIN S/N生成 start -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">DIN S/N生成</h4>
				</div>
				<div class="modal-body">
					<!-- 填写部分 start -->
					<!-- <form class="form-horizontal form-label-left input_mask" id="input_mask"> -->
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<div>
								<input class="hide" id="qdsProCategoryName" value="DIN"></input>
								<input class="hide" id="qdsProCategoryId" value="1"></input>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">工作令（<span class="red">★</span>）</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="order" id="order" type="text" class="form-control col-md-4 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">模块型号（<span class="red">★</span>）</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="module1" id="module1" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">版本（<span class="red">★</span>）</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="ver" id="ver" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入字母或数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">数量（<span class="red">★</span>）</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="pwdQuantity" id="pwdQuantity" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入数字">
									<span class="control-label col-md-5 col-sm-5 col-xs-12"></span>
								</div>
							</div>
						</table>
					</div>
					<!-- </form> -->
				</div>
				<!-- 填写部分 end -->
				
				<!-- 预览部分 start -->
				<div class="modal-body hide" id="reviewBody">
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"></label>
								<div class="col-md-9 col-sm-9 col-xs-12" id="review">
								<!-- 异步添加预览清单   statics/qdsjs/qDinCreateSnChrome.js -->
								</div>
							</div>
						</table>
					</div>
				</div>
				<!-- 预览部分 end -->
				
				<div class="modal-footer">
					<span id="message"></span>
					<button type="button" class="btn btn-default closeModal" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						关闭
					</button>
					<button type="button" id="createSnReviewBtn" class="btn btn-warning" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						预览
					</button>
					<button type="button" id="createSnBtn" class="btn btn-success hide" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						生成
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- DIN S/N生成 end -->
	
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
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinCreateSn.js";
		} else if (getBrowser() == "Chrome") {
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinCreateSnChrome.js";
		} 
		$("body").append(scriptNode);
	})
</script>