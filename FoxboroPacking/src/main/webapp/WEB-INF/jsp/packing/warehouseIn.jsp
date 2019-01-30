<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					包材入库 <i class="fa fa-user"></i><small>
						 ${sessionScope.user.realname } --- 请选择材料型号并填写入库数量，打（★）为必填项。 </small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="clearfix"></div>
				<div id="add">
					<form class="form-horizontal form-label-left" action="#" method="post">
						<div id="proLeft">
							<div class="item " style="margin: 5px">
								<label for="select">材料型号（<span class="red">★</span>）： </label> 
								<select id="id" name="id" style="height: 27px">
									<c:set var="oldId" value="${sessionScope.oldId }"/>
									<option value="0">-- 请选择 --</option>
									<c:forEach items="${materials }" var="mate">
										<option value="${mate.id }" >${mate.materialsName}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="item ">
								<label for="materialsNum">库存数量： </label> <input id="materialsNum" name="materialsNum"
									disabled="disabled" type="text" value="0">
							</div>
							
							<div class="item ">
								<label for="inNum">入库数量（<span class="red">★</span>）： </label> <input id="inNum" name="inNum"
									required="required" type="text" value="请填写数量" pattern="[1-9]*[1-9][0-9]*">
							</div>
							
						</div>

						<div class="form-group" >
							<div class="col-md-6 col-md-offset-3" style="margin-top:20px">
								<button id="warehouseInBtn" type="button" class="btn btn-success">入库</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/packingjs/warehouseIn.js"></script>