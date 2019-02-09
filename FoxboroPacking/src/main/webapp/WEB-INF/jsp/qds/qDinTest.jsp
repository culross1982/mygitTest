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
										<input name="moduleNo" type="text" class="form-control col-md-7 col-xs-12" value="${qProTest.moduleNo }" pattern="[0-9a-zA-Z]*" placeholder="请输入模块串号">
									</div>
								</div>
							</li>
							<li style="width: 200px">
								<div class="form-group">
									<label class="control-label col-md-5 col-sm-5 col-xs-6">上传人员</label>
									<div class="col-md-7 col-sm-7 col-xs-6">
										<input name="realname" type="text" class="form-control col-md-7 col-xs-12" value="${qProTest.realname}" placeholder="请输入人员"> 
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
							<div id="uploadFailed">
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
											aria-label="Last name: activate to sort column ascending">代码</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">状态</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试结果</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试设备<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">上传人员</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试时间 </th>	
										<th class="sorting " tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">测试描述</th>
										<th class="sorting " tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">维修记录</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${qdsProductTestList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${t.id }</td>
											<td>${t.moduleNo}</td>
											<td>${t.testCode}</td>
											<td>${t.testStatus}</td>
											<td>${t.status}</td>
											<td>${t.testResult}</td>
											<td>${t.testEquipment}</td>
											<td>${t.realname}</td>
											<td><fm:formatDate value="${t.testTime}" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${t.testDiscription}</td>
											<td>
												<c:if test="${t.errorStatus==0}"></c:if>
												<c:if test="${t.errorStatus==1}">
													<a class="insertError operation btn btn-danger btn-xs" data-toggle="tooltip" id=${t.id } moduleNo=${t.moduleNo } testDiscription=${t.testDiscription }
											        data-placement="top" style="cursor: pointer"><i class="fa fa-pencil"></i>待输入</a>
											    </c:if>
											    <c:if test="${t.errorStatus==2}">
													<a class="modifyError operation btn btn-info btn-xs" data-toggle="tooltip" id=${t.id } moduleNo=${t.moduleNo } testDiscription=${t.testDiscription }
											        errorCodeId=${t.errorCodeId } remark=${t.remark } data-placement="top" style="cursor: pointer"><i class="fa fa-pencil"></i>修改</a>
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
	
	<!-- 输入维修数据 start -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">输入维修数据</h4>
				</div>
				<div class="modal-body">

					<input class="hide" id="qdsProCategoryId" value="1"></input>	<!-- 给ajax提供产品分类 -->
					<input class="hide" id="productTestId"></input>	<!-- 给ajax提供qdsProductTestId -->
					<!-- <form class="form-horizontal form-label-left input_mask" id="input_mask"> -->
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="moduleNo" id="moduleNo" type="text" class="form-control col-md-4 col-xs-12" disabled="disabled">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">测试描述</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="testDiscription" id="testDiscription" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">不良代码</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<select id="errorCodeId" name="errorCodeId" class="form-control col-md-7 col-xs-12"  style="font-size:8px;width:200px;">
										<option value="14">重新测试</option>
 											<c:forEach items="${qdsErrorCodeList }" var="e">
												<option value="${e.id }">${e.description}</option>
											</c:forEach>
									</select>
									<span class="control-label col-md-5 col-sm-5 col-xs-12"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">输入者</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="repairBy" id="repairBy" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" value="${sessionScope.user.realname}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">备    注</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="remark" id="remark" type="text" class="form-control col-md-7 col-xs-12">
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
					<button type="button" id="addErrorBtn" class="btn btn-success" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						提交
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 输入维修数据 end -->
	
	<!-- 修改维修数据 start -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModal" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改维修数据</h4>
				</div>
				<div class="modal-body">

					<input class="hide" id="qdsProCategoryId" value="1"></input>	<!-- 给ajax提供产品分类 -->
					<input class="hide" id="productTestId"></input>	<!-- 给ajax提供qdsProductTestId -->
					<!-- <form class="form-horizontal form-label-left input_mask" id="input_mask"> -->
					<div class="form-horizontal form-label-left input_mask" id="input_mask">
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">模块串号</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="moduleNo1" id="moduleNo1" type="text" class="form-control col-md-4 col-xs-12" disabled="disabled">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
								
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">测试描述</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="testDiscription1" id="testDiscription1" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled">
									<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">不良描述</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<select id="errorCodeId1" name="errorCodeId1" class="form-control col-md-7 col-xs-12"  style="font-size:8px;width:200px;">
										<option></option>
 											<c:forEach items="${qdsErrorCodeList }" var="e">
												<option value="${e.id }">${e.description}</option>
											</c:forEach>
									</select>
									<span class="control-label col-md-5 col-sm-5 col-xs-12"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">输入者</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="repairBy1" id="repairBy1" type="text" class="form-control col-md-7 col-xs-12" disabled="disabled" value="${sessionScope.user.realname}">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">备    注</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
									<input name="remark1" id="remark1" type="text" class="form-control col-md-7 col-xs-12">
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
					<button type="button" id="addErrorBtn" class="btn btn-success" >
						<span class="glyphicon glyphicon-floppy-disk" style="color:white" aria-hidden="true"></span>
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改维修数据 end -->
</div>
<%@include file="common/footer.jsp"%>
<%-- <script src="${pageContext.request.contextPath }/statics/qdsjs/qDinTest.js"></script> --%>
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
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinTest.js";
		} else if (getBrowser() == "Chrome") {
			scriptNode.src="${pageContext.request.contextPath }/statics/qdsjs/qDinTestChrome.js";
		} 
		$("body").append(scriptNode);
	})
</script>