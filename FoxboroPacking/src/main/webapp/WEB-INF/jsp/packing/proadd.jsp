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
					新增产品包材信息 <i class="fa fa-user"></i><small>
						${sessionScope.user.realname }---产品型号不可重复，打（*）为必填项。 </small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="clearfix"></div>
				<div id="add">
					<form class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/pro/addPro"
						method="post" onsubmit="return check();">
						<div id="proLeft">
							<div class="item ">
								<label for="pwd">产品型号（*）：</label> <input id="pwd" name="pwd"
									required="required" type="text" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="box">包装盒（*）： </label> <input id="box" name="box"
									required="required" type="text" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="gasket">衬垫：</label> <input id="gasket" name="gasket"
									type="text" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="spongiaOne">海绵（固定）： </label> <input id="spongiaOne"
									name="spongiaOne" type="text" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="spongiaTwo">海绵（变化）：</label> <input id="spongiaTwo"
									type="text" name="spongiaTwo" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="esdBag">静电袋： </label> <input id="esdBag" type="text"
									name="esdBag" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="geDang">隔档：</label> <input id="geDang" type="text"
									name="geDang" pattern="[a-zA-Z0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="esdTable">静电铭牌：</label> <input id="esdTable"
									type="text" name="esdTable" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="esdBubbleBag">气泡袋： </label> <input
									id="esdBubbleBag" type="text" name="esdBubbleBag" pattern="[a-zA-Z0-9]{1,}"/>
							</div>

							<div class="item ">
								<label for="remark">备注： </label> <input id="remark" type="text"
									name="remark" />
							</div>

							<div class="item " style="margin: 5px">
								<label for="select">产品分类（*）： </label> <select
									id="productCategoryId" name="productCategoryId" style="height: 27px">
									<option value="0">-- 请选择 --</option>
									<c:forEach items="${cateList }" var="cate">
										<option value="${cate.id }">${cate.productCategoryName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div id="proRight">
							<div class="item ">
								<label for="pwdQuantity">产品型号数量（*）：</label> <input type="text"
									name="pwdQuantity" size="4" id="pwdQuantity" required="required" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="boxQuantity">包装盒数量（*）：</label> <input type="text"
									name="boxQuantity" size="4" id="boxQuantity" required="required" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="gasketQuantity">衬垫数量：</label> <input type="text"
									name="gasketQuantity" size="4" id="gasketQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="spongiaOneQuantity">海绵数量（固定）：</label> <input type="text"
									name="spongiaOneQuantity" size="4" id="spongiaOneQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="spongiaTwoQuantity">海绵数量（变化）：</label> <input type="text"
									name="spongiaTwoQuantity" size="4" id="spongiaTwoQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="esdBagQuantity">静电袋数量：</label> <input type="text"
									name="esdBagQuantity" size="4" id="esdBagQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="geDangQuantity">隔档数量：</label> <input type="text"
									name="geDangQuantity" size="4" id="geDangQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="esdTableQuantity">静电铭牌数量：</label> <input type="text"
									name="esdTableQuantity" size="4" id="esdTableQuantity" pattern="[0-9]{1,}"/>
							</div>
							<div class="item ">
								<label for="esdBubbleBagQuantity">气泡袋数量：</label> <input type="text"
									name="esdBubbleBagQuantity" size="4" id="esdBubbleBagQuantity" pattern="[0-9]{1,}"/>
							</div>
						</div>
				
						<div class="form-group" >
							<div class="col-md-6 col-md-offset-3" style="margin-top:20px">
								<button id="send" type="submit" class="btn btn-success">保存</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/packingjs/proadd.js"></script>