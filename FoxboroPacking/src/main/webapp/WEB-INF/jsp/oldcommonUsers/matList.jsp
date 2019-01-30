<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					材料数据列表 <i class="fa fa-user"></i><small>${userSession.userName}
						${sessionScope.user.username }- 您可以对材料数据进行查询操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="${pageContext.request.contextPath}/mat/materialsList?currentPage=1">
					<input type="hidden" name="pageIndex" value="1" />
					<ul>
						<li>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">材料型号</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input name="materialsName" type="text"
										class="form-control col-md-7 col-xs-12" value="${requestScope.materialsName }">
								</div>
							</div></li>
						<li><button type="submit" class="btn btn-primary">查
								&nbsp;&nbsp;&nbsp;&nbsp;询</button>
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
				<div id="datatable-responsive_wrapper"
					class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row">
						<div class="col-sm-12">
							<table id="datatable-responsive"
								class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
								cellspacing="0" width="100%" role="grid"
								aria-describedby="datatable-responsive_info"
								style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting_asc" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="First name: activate to sort column descending"
											aria-sort="ascending">编号</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											材料型号</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											数量</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											材料分类</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											创建者</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											创建时间</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">
											更新者</th>
										<th class="sorting" tabindex="0"
											aria-controls="datatable-responsive" rowspan="1" colspan="1"
											style="width: 60px;"
											aria-label="Last name: activate to sort column ascending">
											更新时间</th>
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
											<td><fmt:formatDate value="${m.creationDate}" pattern="yyyy-MM-dd"/></td>
											<td>${m.modifyByUsername}</td>
											<td><fmt:formatDate value="${m.modifyDate}" pattern="yyyy-MM-dd"/></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-5">
							<div class="dataTables_info" id="datatable-responsive_info"
								role="status" aria-live="polite">共${count }条记录
								${currentPage }/${pageCount }页</div>
						</div>
						<div class="col-sm-7">
							<div class="dataTables_paginate paging_simple_numbers"
								id="datatable-responsive_paginate">
								<ul class="pagination">
									<c:if test="${currentPage!=1}">
										<li class="paginate_button previous"><a href="${pageContext.request.contextPath}/mat/materialsList"
											aria-controls="datatable-responsive" data-dt-idx="0"
											tabindex="0">首页</a></li>
										<li class="paginate_button "><a
											href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${currentPage-1 }"
											aria-controls="datatable-responsive" data-dt-idx="1"
											tabindex="0">上一页</a></li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button "><a
											href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${currentPage+1 }"
											aria-controls="datatable-responsive" data-dt-idx="1"
											tabindex="0">下一页</a></li>
										<li class="paginate_button next"><a
											href="${pageContext.request.contextPath}/mat/materialsList?currentPage=${pageCount}"
											aria-controls="datatable-responsive" data-dt-idx="7"
											tabindex="0">最后一页</a></li>
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
