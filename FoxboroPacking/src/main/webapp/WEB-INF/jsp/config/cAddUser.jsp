<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="login_wrapper">
	<div class="animate form login_form">
		<section class="login_content" style="padding: 0px">
			<div class="x_content">
				<form class="form-horizontal form-label-left" action="addUser" method="post" onsubmit="return check();">
					<h1>用户注册</h1>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">
							用户名
							<span class="required"></span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
							<input id="username" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="username"
								placeholder="字母或数字组成" pattern="[a-zA-Z0-9]*" required="required" type="text" style="margin-bottom: 0; width: 160px">
							<span style="padding: 9px"></span>
						</div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="realname">
							姓名
							<span class="required"></span>
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
							<input id="realname" class="form-control col-md-7 col-xs-12" name="realname" placeholder="汉字或英文字母" required="required" type="text"
								style="margin-bottom: 0; width: 160px">
							<span style="padding: 9px"></span>
						</div>
					</div>

					<!-- 注册时密码输入与验证 -->
					<!-- <div class="item form-group">
								<label for="password" class="control-label col-md-3">密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="password" type="password" name="password" class="form-control col-md-7 col-xs-12" required="required" placeholder="字母数字不小于6位"
										pattern="[a-zA-Z0-9]*" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<label for="repassword" class="control-label col-md-3 col-sm-3 col-xs-12">确认密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="repassword" type="password" name="repassword" class="form-control col-md-7 col-xs-12" required="required" placeholder="请再次输入密码"
										pattern="[a-zA-Z0-9]{6,}" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div> -->

					<!-- 验证图片功能 -->
					<%-- <div class="item form-group">
								<label for="code" class="control-label col-md-3 col-sm-3 col-xs-12">验证码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="code" type="text" name="text" class="form-control col-md-7 col-xs-12" required="required" pattern="[a-zA-Z]*"
										style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<img src="${pageContext.request.contextPath}/config/getVerify" id="codeImage" alt="点击更换验证码"></img>
								<a href="javascript:location.reload();" rel="nofollow">看不清，换一张</a>
							</div> --%>

					<!-- Authority config start -->
					<div class="item form-group">
						<div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-2 ">
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
									<tr role="row" >
										<td class="sorting_asc" tabindex="0" rowspan="1" colspan="1" style="width: 50px;vertical-align: middle;" >包材系统</td>
										<td class="sorting" tabindex="0" rowspan="1" colspan="1" style="width: 100px;">
											<div class="checkbox text-left">
												<label> 
													<input type="checkbox" id="packingAdmin" name="category" value="2">
													包材管理员
												</label>
											</div>
											<div class="checkbox text-left">
												<label>
													<input type="checkbox" id="packingCommon" name="category" value="3" >
													包材普通用户
												</label>
											</div>
										</td>
									</tr>
									<tr role="row" >
										<td class="sorting_asc" tabindex="0" rowspan="1" colspan="1" style="width: 50px;vertical-align: middle;">QDS系统</td>
										<td class="sorting" tabindex="0" rowspan="1" colspan="1" style="width: 100px;">
											<div class="checkbox text-left">
												<label>
													<input type="checkbox" id="QDSAdmin" name="category" value="4" >
													QDS管理员
												</label>
											</div>
											<div class="checkbox text-left">
												<label>
													<input type="checkbox" id="QDSInspect" name="category" value="5" >
													QDS检验员
												</label>
											</div>
											<div class="checkbox text-left">
												<label>
													<input type="checkbox" id="QDScommon" name="category" value="6" >
													QDS普通用户
												</label>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- Authority config end -->
					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-6 col-md-offset-3">
							<button id="send" type="submit" class="btn btn-success">注册</button>
							<button type="reset" class="btn btn-primary">重填</button>
						</div>
					</div>
				</form>
			</div>
		</section>
	</div>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/configjs/cAddUser.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/packingjs/register.js"></script> --%>