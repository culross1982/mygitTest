<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					产品基础数据列表 <i class="fa fa-user"></i><small>${sessionScope.user.realname } --- 您可以对产品基础数据进行查询（添加、修改、删除限管理员权限）操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="${pageContext.request.contextPath}/pro/searchProList?currentPage=1">
					<input type="hidden" name="pageIndex" value="1" />
					<ul>
						<li>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12 text-right">产品型号</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="pwd" type="text" class="form-control col-md-7 col-xs-12" value="${pwd }">
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12 text-right">包装盒</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="box" type="text" class="form-control col-md-7 col-xs-12" value="${box }">
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12 text-right">衬垫</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="gasket" type="text" class="form-control col-md-7 col-xs-12" value="${gasket }">
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12 text-right" >海绵(变化)</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="spongiaOne" type="text" class="form-control col-md-7 col-xs-12" value="${spongiaOne }">
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12 text-right">海绵(固定)</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="spongiaTwo" type="text" class="form-control col-md-7 col-xs-12" value="${spongiaTwo }">
								</div>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">产品分类</label>
								<div class="col-md-6 col-sm-6 col-xs-12" >
									<c:set var="CategoryId" value="${requestScope.productCategoryId }" />
									<select name="productCategoryId" id="" class="form-control" style="font-size:13px;">
										<option value="0">--请选择--</option>
										<c:forEach var="c" items="${cateList}">
											<option value="${c.id}" <c:if test="${c.id==CategoryId}">selected</c:if>>${c.productCategoryName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</li>		
					</ul>
					<ul class="text-right" style="margin-top:-70px">
						<li>
							<button type="submit" class="btn btn-success">查询</button>
						</li>
					</ul>
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
							<div class="modifyStatus"><!-- 普通用户进行对此功能进行隐藏 -->
								<%-- <c:if test="${sessionScope.user.category==1 || sessionScope.user.category==2}"> --%>
								<a href="${pageContext.request.contextPath}/pro/addProWindow" class="btn btn-success btn-sm">新增产品包材基础数据信息</a>
								<form action="${pageContext.request.contextPath}/pro/addProductFromXls" method="post" enctype="multipart/form-data"
									onsubmit="return checkSuffix();" class="form-inline" style="margin:5px 0;">
									<div class="form-group">
										<input type="file" style="width: 250px;" name="file" id="file" />
									</div>
									<div class="form-group">
										<input type="submit" class="btn btn-success btn-sm" value="点击上传" id="fileButton" style="color: withe" />
									</div>
									<div class="form-group">
										<em>上传.xls、.xlsx的文件</em>
									</div>
								</form>
							</div>
			
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">产品型号/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">包装盒/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">衬垫/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">海绵(变化)/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">海绵(固定)/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">静电袋/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">隔档/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">备注</th>
										<!-- 不订购，屏蔽此字段 -->
										<!-- <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">静电铭牌/数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">静电气泡袋/数量</th> -->	
										<!-- 普通用户进行对此功能进行隐藏 -->
										<th class="sorting modifyStatus" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${ProList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${p.id }</td>
											<td>${p.pwd }/${p.pwdQuantity }</td>
											<td>${p.box }/${p.boxQuantity }</td>
											<td>${p.gasket }/${p.gasketQuantity }</td>
											<td>${p.spongiaOne }/${p.spongiaOneQuantity }</td>
											<td>${p.spongiaTwo }/${p.spongiaTwoQuantity }</td>
											<td>${p.esdBag }/${p.esdBagQuantity }</td>
											<td>${p.geDang }/${p.geDangQuantity }</td>
											<td>${p.remark }</td>
											<!-- 不订购，屏蔽此字段 -->
											<%-- <td>${p.esdTable }/${p.esdTableQuantity }</td>
											<td>${p.esdBubbleBag }/${p.esdBubbleBagQuantity }</td> --%>
											<!-- 普通用户进行对此功能进行隐藏 -->
											<td class="modifyStatus"><a class="modifyAppInfo operation btn btn-info btn-xs" data-toggle="tooltip" href="${pageContext.request.contextPath}/pro/updatePro?id=${p.id }"
													data-placement="top" data-original-title="修改产品包材信息"><i class="fa fa-pencil"></i>修改</a> &nbsp;&nbsp; <a class="deleteApp operation btn btn-danger btn-xs" data-toggle="tooltip"
													data-placement="top" style="cursor: pointer" pid=${p.id } pname=${p.pwd } data-original-title="删除产品包材信息"><i class="fa fa-trash-o"></i>删除</a></td>		
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
								<!-- <ul class="pagination"> -->
								<ul class="pager">
									<c:if test="${currentPage!=1}">
										<li class="paginate_button previous">
											<a href="${pageContext.request.contextPath}/pro/searchProList" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/pro/searchProList?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/pro/searchProList?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/pro/searchProList?currentPage=${pageCount}" aria-controls="datatable-responsive"
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
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/packingjs/proList.js"></script>