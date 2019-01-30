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
#proLeft span{color: red}
#proLeft input{background-color:#f0f0f0}
</style>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					产品使用包材 <i class="fa fa-user"></i><small>
						${sessionScope.user.username }-请填写产品分类》产品型号》数量 </small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="clearfix"></div>
				<div id="add">
					<form class="form-horizontal form-label-left" style="width:900px" action="${pageContext.request.contextPath}/mat/doUseMaterialsByPro"
						method="post">
						<div id="proLeft">
							<div class="item ">
								<label for="box">包装盒（*）： </label> <input id="box" name="box"
									readonly="readonly" type="text"/>
							</div>

							<div class="item ">
								<label for="gasket">衬垫：</label> <input id="gasket" name="gasket"
									type="text" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="spongiaOne">海绵1： </label> <input id="spongiaOne"
									name="spongiaOne" type="text" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="spongiaTwo">海绵2：</label> <input id="spongiaTwo"
									type="text" name="spongiaTwo" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="esdBag">静电袋： </label> <input id="esdBag" type="text"
									name="esdBag" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="geDang">隔档：</label> <input id="geDang" type="text"
									name="geDang" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="esdTable">静电铭牌：</label> <input id="esdTable"
									type="text" name="esdTable" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="esdBubbleBag">防静电气泡袋： </label> <input
									id="esdBubbleBag" type="text" name="esdBubbleBag" readonly="readonly"/>
							</div>

							<div class="item ">
								<label for="remark">备注： </label> <input id="remark" type="text"
									name="remark" readonly="readonly"/>
							</div>
							
							<div class="item " style="margin: 5px">
								<label for="select">产品分类（*）： </label> <select
									id="productCategoryId" name="productCategoryId" style="height: 25px;width:172px">
									<option value="0">-- 请选择 --</option>
									<c:forEach items="${cateList }" var="cate">
										<option value="${cate.id }">${cate.productCategoryName}</option>
									</c:forEach>
								</select>
							</div>
							
							<div class="item " style="margin: 5px">
								<label for="select">产品型号（*）： </label> 
								<select id="pwd" name="pwd" style="height: 25px;width:172px">
								</select>
							</div>
							
							<div class="item ">
								<label for="pwdQuantity">数量（*）：</label> <input type="text" placeholder="请输入数量"
									name="pwdQuantity" size="4" id="pwdQuantity" required="required" style="height: 25px;width:172px;background-color: white" />
							</div>
						</div>

						<div id="proLeft" style="margin: 0 10px 0 20px;">
							<div class="item ">
								<label for="boxQuantity">使用包装盒总数量：</label> <input type="text"
									name="boxQuantity" size="4" id="boxQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="gasketQuantity">使用衬垫总数量：</label> <input type="text"
									name="gasketQuantity" size="4" id="gasketQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="spongiaOneQuantity">使用海绵总数量1：</label> <input type="text"
									name="spongiaOneQuantity" size="4" id="spongiaOneQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="spongiaTwoQuantity">使用海绵总数量2：</label> <input type="text"
									name="spongiaTwoQuantity" size="4" id="spongiaTwoQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="esdBagQuantity">使用静电袋总数量：</label> <input type="text"
									name="esdBagQuantity" size="4" id="esdBagQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="geDangQuantity">使用隔档总数量：</label> <input type="text"
									name="geDangQuantity" size="4" id="geDangQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="esdTableQuantity">使用静电铭牌总数量：</label> <input type="text"
									name="esdTableQuantity" size="4" id="esdTableQuantity" readonly="readonly"/>
							</div>
							<div class="item ">
								<label for="esdBubbleBagQuantity">使用防静电气泡袋总数量：</label> <input type="text"
									name="esdBubbleBagQuantity" size="4" id="esdBubbleBagQuantity" readonly="readonly"/>
							</div>
						</div>
						
						<div align="left" id="proLeft">
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="boxQuantityMate" size="4" id="boxQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="gasketQuantityMate" size="4" id="gasketQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="spongiaOneQuantityMate" size="4" id="spongiaOneQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="spongiaTwoQuantityMate" size="4" id="spongiaTwoQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="esdBagQuantityMate" size="4" id="esdBagQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="geDangQuantityMate" size="4" id="geDangQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="esdTableQuantityMate" size="4" id="esdTableQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
							<div class="item ">
								<label >库存量：</label><input type="text"
									name="esdBubbleBagQuantityMate" size="4" id="esdBubbleBagQuantityMate" readonly="readonly"/>
								<span></span>
							</div>
						</div>
				
						<div class="form-group" style="width:700px;">
							<div class="col-md-6 col-md-offset-3" style="margin-top:20px">
								<button id="send" type="submit" class="btn btn-success">使用</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/proUseMateOut.js"></script>