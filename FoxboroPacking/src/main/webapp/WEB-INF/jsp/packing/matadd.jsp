<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<style type="text/css">
#add {
	width: 600px;
	text-align: right;
}

input {
	margin: 5px;
}

#proLeft {
	float: left
}
</style>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					新增材料信息 <i class="fa fa-user"></i><small> ${sessionScope.user.realname }---请输入材料型号（不可重复）和数量，打（★）为必填项。 </small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="clearfix"></div>
				<div id="add">
					<form class="form-horizontal form-label-left" action="#" method="post">
						<div id="proLeft">
							<div class="item ">
								<label for="materialsName">材料型号（<span class="red">★</span>）：</label> <input id="materialsName" name="materialsName"
									required="required" type="text">
							</div>

							<div class="item ">
								<label for="materialsNum">数量（<span class="red">★</span>）： </label> <input id="materialsNum" name="materialsNum"
									required="required" type="text" value="0">
							</div>

							<div class="item " style="margin: 5px">
								<label for="select">材料分类（<span class="red">★</span>）： </label> <select
									id="materialsCategoryNameId" name="materialsCategoryNameId" style="height: 27px">
									<option value="0">-- 请选择 --</option>
									<c:forEach items="${MaterialsCategoryList }" var="mateCate">
										<option value="${mateCate.id }">${mateCate.materialsCategoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group" >
							<div class="col-md-6 col-md-offset-3" style="margin-top:20px">
								<button id="matadd" type="button" class="btn btn-success">保存</button>
								<button type="button" class="btn btn-primary" id="back" onclick="window.location.href='${pageContext.request.contextPath}/mat/materialsList?currentPage=1'">返回</button>
								<br /> <br /> 
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/packingjs/matadd.js"></script>