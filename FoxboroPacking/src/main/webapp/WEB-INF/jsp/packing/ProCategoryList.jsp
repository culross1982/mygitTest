<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					产品分类列表管理 <i class="fa fa-user"></i><small> ${sessionScope.user.realname } --- 您可以对产品分类列表进行删除、增加操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content" style="height:40px">
				<form method="post">
					<input type="hidden" name="pageIndex" value="1" />
					<ul>
						<li style="width: 300px">
							<div>
								<label class="control-label col-md-3 col-sm-3 col-xs-12">产品分类</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="productCategoryName" type="text" id="productCategoryName" class="form-control col-md-7 col-xs-12">
								</div>
								<span style="color: red;" id="message"></span>
							</div>
						</li>
						<li>
							<button type="button" id="button" class="btn btn-success btn-sm">新增产品分类信息</button>
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
							<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
								<thead>
									<tr role="row" class="success">
										<th class="sorting_asc" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="First name: activate to sort column descending" aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">产品分类</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 80px;"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pcl" items="${ProCategoryList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${pcl.id }</td>
											<td>${pcl.productCategoryName }</td>
											<td><a class="deleteProCate operation btn btn-danger btn-xs" data-toggle="tooltip" data-placement="top" style="cursor: pointer" pid=${pcl.id }
													pname=${pcl.productCategoryName } data-original-title="删除产品分类信息"><i class="fa fa-trash-o"></i>删除</a></td>
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
								<ul class="pagination">
									<c:if test="${currentPage!=1}">
										<li class="paginate_button previous">
											<a href="${pageContext.request.contextPath}/pro/searchProCategoryListWindow" aria-controls="datatable-responsive" data-dt-idx="0"
												tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/pro/searchProCategoryListWindow?currentPage=${currentPage-1 }"
												aria-controls="datatable-responsive" data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/pro/searchProCategoryListWindow?currentPage=${currentPage+1 }"
												aria-controls="datatable-responsive" data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/pro/searchProCategoryListWindow?currentPage=${pageCount}" aria-controls="datatable-responsive"
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
<script src="${pageContext.request.contextPath }/statics/packingjs/proCategoryList.js"></script>
</html>