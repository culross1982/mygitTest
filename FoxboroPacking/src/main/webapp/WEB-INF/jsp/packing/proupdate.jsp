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
					修改产品包材信息 <i class="fa fa-user"></i><small>${sessionScope.user.realname } --- 产品型号不可重复，打（★）为必填项。</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="clearfix"></div>
				<div id="add">
					<form class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/pro/doUpdatePro"
						method="post">
						<div id="proLeft">
							<input name="id" type="hidden" value="${product.id}"/>
							<input name="pwd" type="hidden" value="${product.pwd}"/>
							<div class="item ">
								<label for="pwdShow">产品型号：</label> <input id="pwdShow" name="pwdShow"
								 type="text" value="${product.pwd}" disabled="disabled">
							</div>

							<div class="item ">
								<label for="box">包装盒（<span class="red">★</span>）： </label> <input id="box" name="box"
									required="required" type="text" value="${product.box }">
							</div>

							<div class="item ">
								<label for="gasket">衬垫：</label> <input id="gasket" name="gasket"
									type="text" value="${product.gasket }">
							</div>

							<div class="item ">
								<label for="spongiaOne">海绵（变化）： </label> <input id="spongiaOne"
									name="spongiaOne" type="text" value="${product.spongiaOne }">
							</div>

							<div class="item ">
								<label for="spongiaTwo">海绵（固定）：</label> <input id="spongiaTwo"
									type="text" name="spongiaTwo" value="${product.spongiaTwo }"/>
							</div>

							<div class="item ">
								<label for="esdBag">静电袋： </label> <input id="esdBag" type="text"
									name="esdBag" value="${product.esdBag }"/>
							</div>

							<div class="item ">
								<label for="geDang">隔档：</label> <input id="geDang" type="text"
									name="geDang" value="${product.geDang }"/>
							</div>

							<div class="item ">
								<label for="esdTable">静电铭牌：</label> <input id="esdTable"
									type="text" name="esdTable" value="${product.esdTable }"/>
							</div>

							<div class="item ">
								<label for="esdBubbleBag">防静电气泡袋： </label> <input
									id="esdBubbleBag" type="text" name="esdBubbleBag" value="${product.esdBubbleBag }"/>
							</div>

							<div class="item ">
								<label for="remark">备注： </label> <input id="remark" type="text"
									name="remark" value="${product.remark }"/>
							</div>

							<div class="item " style="margin: 5px">
								<label for="select">产品分类： </label> <select 
									id="productCategoryId" name="productCategoryId" style="height: 27px">
									<option value="${product.productCategoryId }">-- ${product.productCategoryName } --</option>
									<c:forEach items="${cateList }" var="cate">
										<option value="${cate.id }">${cate.productCategoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div id="proRight">
							<div class="item ">
								<label for="pwdQuantity">产品型号数量（<span class="red">★</span>）：</label> <input type="text" required="required"
									name="pwdQuantity" size="4" id="pwdQuantity" value="${product.pwdQuantity }"/>
							</div>
							<div class="item ">
								<label for="boxQuantity">包装盒数量（<span class="red">★</span>）：</label> <input type="text" required="required"
									name="boxQuantity" size="4" id="boxQuantity" value="${product.boxQuantity }"/>
							</div>
							<div class="item ">
								<label for="gasketQuantity">衬垫数量：</label> <input type="text"
									name="gasketQuantity" size="4" id="gasketQuantity" value="${product.gasketQuantity }"/>
							</div>
							<div class="item ">
								<label for="spongiaOneQuantity">海绵数量（变化）：</label> <input type="text"
									name="spongiaOneQuantity" size="4" id="spongiaOneQuantity" value="${product.spongiaOneQuantity }"/>
							</div>
							<div class="item ">
								<label for="spongiaTwoQuantity">海绵数量（固定）：</label> <input type="text"
									name="spongiaTwoQuantity" size="4" id="spongiaTwoQuantity" value="${product.spongiaTwoQuantity }"/>
							</div>
							<div class="item ">
								<label for="esdBagQuantity">静电袋数量：</label> <input type="text"
									name="esdBagQuantity" size="4" id="esdBagQuantity" value="${product.esdBagQuantity }"/>
							</div>
							<div class="item ">
								<label for="geDangQuantity">隔档数量：</label> <input type="text"
									name="geDangQuantity" size="4" id="geDangQuantity" value="${product.geDangQuantity }"/>
							</div>
							<div class="item ">
								<label for="esdTableQuantity">静电铭牌数量：</label> <input type="text"
									name="esdTableQuantity" size="4" id="esdTableQuantity" value="${product.esdTableQuantity }"/>
							</div>
							<div class="item ">
								<label for="esdBubbleBagQuantity">防静电气泡袋数量：</label> <input type="text"
									name="esdBubbleBagQuantity" size="4" id="esdBubbleBagQuantity" value="${product.esdBubbleBagQuantity }"/>
							</div>
						</div>
						<div class="form-group" >
							<div class="col-md-6 col-md-offset-3" style="margin-top:20px">
								<button id="send" type="submit" class="btn btn-success">修改</button>
								<button type="button" class="btn btn-primary" id="back" onclick="window.location.href='${pageContext.request.contextPath}/pro/searchProList?currentPage=1'">返回</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/packingjs/proupdata.js"></script>