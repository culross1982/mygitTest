<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<link href="${pageContext.request.contextPath }/statics/packingcss/matOrder.css" rel="stylesheet">
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					包材订购 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对包材进行订购操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content ">
				<div class="x_title" >
					<form method="post">
						<input type="hidden" name="pageIndex" value="1" />
						<ul >
							<li>
								<div class="form-group" >
									<label class="control-label col-md-3 col-sm-3 col-xs-12">产品型号</label>
									<div class="col-md-9 col-sm-9 col-xs-12">
										<input name="pwd" id="pwd" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入产品型号">
									</div>
								</div>
							</li>
							<li style="width: 230px; margin-right: -50px">
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12 text-right">数量</label>
									<div class="col-md-6 col-sm-6 col-xs-12">
										<input name="pwdQuantity" id="pwdQuantity" type="text" class="form-control col-md-7 col-xs-12" placeholder="请输入数量">
									</div>
								</div>
							</li>
							<li style="width: 230px; margin-right: -50px">
								<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12 text-right">余量</label>
									<div class="col-md-4 col-sm-4 col-xs-12">
										<input name="margin" id="margin" type="text" class="form-control col-md-7 col-xs-12" placeholder="0">
									</div>
									<label class="control-label col-md-1 col-sm-1 col-xs-2 text-right" style="margin-left: -10px">%</label>
								</div>
							</li>
						</ul>
						<ul style="margin: -50px 0 0 650px;">
							<li>
								<button type="button" class="btn btn-success" id="saveBtn">单笔添加</button>
							</li>
						</ul>
					</form>
				</div>
				<form id="uploadForm" method="post" enctype="multipart/form-data" onsubmit="return checkSuffix();" class="form-inline" style="margin: 5px 0;">
					<div class="form-group">
						<input type="file" style="width: 250px;" name="file" id="file" />
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-success btn-sm" value="批量添加" id="fileButton" style="color: withe" />
					</div>
					<div class="form-group" style="line-height:0px">
						<em >上传.xls、.xlsx的文件</em>
						<span id="message" style="margin-left: 100px; color: red"></span>
						<span id="massageLable" class="hidden" style="color: red;">添加失败</span>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 包材订购材料清单 -->
	<div class="col-md-5 col-sm-12 col-xs-12">
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
										<!-- <th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th> -->
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">材料型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">材料类别</th>
										<!-- <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">余量</th> -->
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 30px;"
											aria-label="Last name: activate to sort column ascending">数量</th>
									</tr>
								</thead>
								<tbody id="materials">
									<!-- packingjs/matOrder.js -->
								</tbody>
							</table>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- 包材订购产品清单 -->
	<div class="col-md-5 col-sm-12 col-xs-12">
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
										<!-- <th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th> -->
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">产品型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">数量</th>
										<!-- <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">创建者</th> -->
										<!-- <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">创建时间</th> -->
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">余量</th>
									</tr>
								</thead>
								<tbody id="product">
									<!-- packingjs/matOrder.js -->
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-2 col-sm-12 col-xs-12">
		<!-- 订购包材表格保存到SQL并导出到  #服务器端# -->
		<!-- <ul>	
			<li>
				<button type="button" class="btn btn-success" id="exportOrder" data-toggle="tooltip" data-placement="top">表格导出</button>
			</li>
		</ul> -->
		<ul id="export" class="hide">
			<li>
				<a class="btn btn-success" href="${pageContext.request.contextPath}/mat/saveAndExportToExcelClient">表格下载 </a>
			</li>
		</ul>
	</div>
</div>
<%@include file="common/footer.jsp"%>
<%-- <script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script> --%>
<script src="${pageContext.request.contextPath }/statics/packingjs/matOrder.js"></script>