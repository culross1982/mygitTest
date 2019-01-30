<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					用户列表 <i class="fa fa-user"></i><small> ${sessionScope.user.username }- 您可以对用户进行删除操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
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
											aria-label="Last name: activate to sort column ascending">用户名</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">姓名</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 40px;"
											aria-label="Last name: activate to sort column ascending">用户权限</th>
										<th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 80px;"
											aria-label="Last name: activate to sort column ascending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="u" items="${usersList }" varStatus="status">
										<tr role="row" class="odd">
											<td tabindex="0" class="sorting_1">${u.id }</td>
											<td>${u.username }</td>
											<td>${u.realname }</td>
											<td><c:forEach items="${u.role}" var="role">
													${role.roleName}&nbsp;&nbsp;&nbsp;&nbsp;
												</c:forEach></td>
											<td><a class="deleteUesr operation btn btn-danger btn-xs" data-toggle="tooltip" data-placement="top" uid=${u.id } realname=${u.realname } username=${u.username }
													style="cursor: hand; cursor: pointer;" data-original-title="删除用户信息"><i class="fa fa-trash-o"></i>删除</a>&nbsp;&nbsp;&nbsp; <a class="modifyUesr operation btn btn-info btn-xs"
													data-toggle="tooltip" data-placement="top" uid=${u.id } realname=${u.realname } username=${u.username } style="cursor: hand; cursor: pointer;"
													data-original-title="修改用户权限"><i class="fa fa-pencil"></i>修改权限</a>
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
											<a href="${pageContext.request.contextPath}/config/usersManageWindows" aria-controls="datatable-responsive" data-dt-idx="0" tabindex="0">首页</a>
										</li>
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/config/usersManageWindows?currentPage=${currentPage-1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">上一页</a>
										</li>
									</c:if>
									<c:if test="${currentPage!=pageCount }">
										<li class="paginate_button ">
											<a href="${pageContext.request.contextPath}/config/usersManageWindows?currentPage=${currentPage+1 }" aria-controls="datatable-responsive"
												data-dt-idx="1" tabindex="0">下一页</a>
										</li>
										<li class="paginate_button next">
											<a href="${pageContext.request.contextPath}/config/usersManageWindows?currentPage=${pageCount}" aria-controls="datatable-responsive"
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
	<!-- 修改权限界面框 start -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改权限</h4>
				</div>
				<div class="modal-body">

					<h5 id="myModalRealname"></h5>
					<input class="hide" id="myModalUsername" name="myModalUsername"></input>
					<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
						cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
						<tbody>
							<tr role="row" >
										<td class="sorting_asc" tabindex="0" rowspan="1" colspan="1" style="width: 50px;vertical-align: middle;" >系统管理</td>
										<td class="sorting" tabindex="0" rowspan="1" colspan="1" style="width: 100px;">
											<div class="checkbox text-left">
												<label> 
													<input type="checkbox" id="admin" name="category" value="1">
													Admin
												</label>
											</div>
										</td>
									</tr>
							<tr role="row">
								<td class="sorting_asc" tabindex="0" rowspan="1" colspan="1" style="width: 50px; vertical-align: middle;">包材系统</td>
								<td class="sorting" tabindex="0" rowspan="1" colspan="1" style="width: 100px;">
									<div class="checkbox text-left">
										<label>
											<input type="checkbox" id="packingAdmin" name="category" value="2">
											包材管理员
										</label>
									</div>
									<div class="checkbox text-left">
										<label>
											<input type="checkbox" id="packingCommon" name="category" value="3">
											包材普通用户
										</label>
									</div>
								</td>
							</tr>
							<tr role="row">
								<td class="sorting_asc" tabindex="0" rowspan="1" colspan="1" style="width: 50px; vertical-align: middle;">QDS系统</td>
								<td class="sorting" tabindex="0" rowspan="1" colspan="1" style="width: 100px;">
									<div class="checkbox text-left">
										<label>
											<input type="checkbox" id="QDSAdmin" name="category" value="4">
											QDS管理员
										</label>
									</div>
									<div class="checkbox text-left">
										<label>
											<input type="checkbox" id="QDSInspect" name="category" value="5">
											QDS检验员
										</label>
									</div>
									<div class="checkbox text-left">
										<label>
											<input type="checkbox" id="QDScommon" name="category" value="6">
											QDS普通用户
										</label>
									</div>
								</td>
							</tr>
						</tbody>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						关闭
					</button>
					<button type="button" id="modifyUesrBtn" class="btn btn-primary" data-dismiss="modal">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改权限界面框 end -->
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/configjs/cUser.js"></script>
