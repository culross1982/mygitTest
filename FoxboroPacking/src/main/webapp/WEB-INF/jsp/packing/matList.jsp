<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel" >
			<div class="x_title">
				<h2>
					包材库存清单 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对材料数据进行查询（添加、删除限管理员权限）操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content" style="height:40px">
				<form method="post" action="${pageContext.request.contextPath}/mat/materialsList?currentPage=1">
					<input type="hidden" name="pageIndex" value="1" />
					<ul>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">材料型号</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="materialsName" type="text" class="form-control col-md-7 col-xs-12" value="${requestScope.materialsName }">
								</div>
							</div>
						</li>
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
								<!-- 普通用户进行对此功能进行隐藏 -->
								<a href="addMaterialsCategoryWindow" class="btn btn-success btn-sm modifyStatus" style="margin:5px 0;">新增材料数据信息</a>
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">材料型号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">数量</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">材料分类</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">创建者</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">创建时间</th>
										<!-- <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">更新者</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 60px;"
											aria-label="Last name: activate to sort column ascending">更新时间</th> -->
										<!-- 普通用户进行对此功能进行隐藏 -->
										<th class="sorting modifyStatus" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 80px;"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="m" items="${mateList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${m.id }</td>
											<td>${m.materialsName }</td>
											<td>${m.materialsNum}</td>
											<td>${m.materialsCategoryName}</td>
											<td>${m.createdByUsername}</td>
											<td><fm:formatDate value="${m.creationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<%-- <td>${m.modifyByUsername}</td>
											<td><fmt:formatDate value="${m.modifyDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
											<!-- 普通用户进行对此功能进行隐藏 -->
											<td class="modifyStatus">
												<!-- 移除“修改”功能（修复bug） --> <%-- <a class="modifyMat operation" data-toggle="tooltip"
												href="${pageContext.request.contextPath}/mat/updateMaterialsWindow?id=${m.id }" data-placement="top"
												data-original-title="修改材料信息">修改</a>&nbsp;&nbsp;  --%> <a class="deleteMat operation btn btn-danger btn-xs" data-toggle="tooltip" mid=${m.id }
													mName=${m.materialsName } data-placement="top" data-original-title="删除材料信息" style="cursor: pointer"><i class="fa fa-trash-o"></i>删除</a>
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
											<a href="${pageContext.request.contextPath}/mat/materialsList" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${pageCount}" aria-controls="datatable-responsive"
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
<script src="${pageContext.request.contextPath }/statics/packingjs/matList.js"></script>