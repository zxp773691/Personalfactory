
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>库存管理页面</title>
<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/usermanager.css" />
<link rel="stylesheet" type="text/css" href="css/_table.css" />
<link rel="stylesheet" type="text/css" href="css/_component.css" />
<script src="js/manager_page.js" type="text/javascript" charset="utf-8"></script>
<script src="js/vouchermakesure.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<input hidden id="userid" name="userid" value="${requestScope.bususerid}">
	<section id="voucher_show">
		<nav id="nav_query">
			<label for="keyword">顾客姓名：</label><input type="text" name="keyword"
				id="keyword" " value="${requestScope.keyword}">
			<button type="button" id="btn_query" class="whiteButton">查询</button>
		</nav>
		<section>
			<table class="standardTable">
				<tbody>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>voucherno</th>
						<th>occurtime</th>
						<th>totalprice</th>
						<th>weight</th>
					</tr>
					<c:forEach items="${requestScope.pageInfo.list}" var="voucher">
						<tr>
							<td><input type="checkbox" id="${voucher.id}"
								name="id_check"></td>
							<td>${voucher.voucherno}</td>
							<td>${voucher.occurtime}</td>
							<td>${voucher.totalprice}</td>
							<td>${voucher.weight}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<nav id="nav_operation">
			<div id="div_operation">
				<button type="button" id="btn_makesureVoucher" class="whiteButton">凭证确认</button>
				<button type="button" id="btn_lookVoucher" class="whiteButton">查看凭证</button>
			</div>
			<div id="div_page">
				当前页数：
				<h5 id="currentPage">${requestScope.pageInfo.pageNum}</h5>
				&nbsp;总页数：
				<h5 id="totalPage">${requestScope.pageInfo.pages }</h5>
				&nbsp;&nbsp;
				<button type="button" id="firstPage" class="whiteButton">首页</button>
				<button type="button" id="prePage" class="whiteButton">上一页</button>
				&nbsp; <select id="jumpPage">
					<c:forEach begin="1" end="${requestScope.pageInfo.pages}"
						var="page">
						<c:if test="${requestScope.pageInfo.pageNum==page}">
							<option value="${page}" selected>${page}</option>
						</c:if>
						<c:if test="${requestScope.pageInfo.pageNum!=page}">
							<option value="${page}">${page}</option>
						</c:if>
					</c:forEach>
				</select> &nbsp; <input type="text" id="input_page" style="height:10px"
					value="${requestScope.pageInfo.pageNum}">
				<button type="button" id="btn_jumpPage" class="whiteButton">GO</button>
				&nbsp;
				<button type="button" id="nextPage" class="whiteButton">下一页</button>
				<button type="button" id="lastPage" class="whiteButton">末页</button>
			</div>
		</nav>
	</section>
	<section id="voucher_add" hidden style="width: 500px; margin: 10px auto">
		<form action="" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: 0px">
				<legend hidden>新增</legend>
				<table class="noBorderTable">
				<tr id="table_row_id">
				<td><label for="id">id：</label></td>
				<td><input readonly type="text" name="id" id="id" value=""><br></td>
				<td><label id="labeltip_id"></label></td>
				</tr >
				<tr id="tr_voucherno">
				<td><label for="voucherno">凭证编号：</label></td>
				<td><input readonly type="text" name="voucherno" id="voucherno" value=""></td>
				</tr>
				<tr id="tr_type">
				<td><label for="type">凭证类型：</label></td>
				<td><input readonly type="text" name="type" id="type" value=""><br></td>
				<td><label id="labeltip_type"></label></td>
				</tr>
				<tr id="tr_occurtime">
				<td><label readonly for="occurtime">凭证时间：</label></td>
				<td><input type="text" name="occurtime" id="occurtime" value=""></td>
				</tr>
				<tr id="tr_weight">
				<td><label for="weight">商品重量：</label></td>
				<td><input readonly type="text" name="weight" id="weight" value=""><br></td>
				<td><label id="labeltip_weight"></label></td>
				</tr>		
				<tr id="tr_totalprice">
				<td><label for="totalprice">商品价格：</label></td>
				<td><input readonly type="text" name="totalprice" id="totalprice" value=""><br></td>
				<td><label id="labeltip_totalprice"></label></td>
				</tr>
				<tr id="tr_materialname">
				<td><label for="materialid">原料名称：</label></td>
				<td><input readonly type="text" name="materialname" id="materialname" value=""><br></td>
				<td><label id="labeltip_materialname"></label></td>
				</tr>
				<tr id="tr_customername">
				<td><label for="customername">顾客名称：</label></td>
				<td><input readonly type="text" name="customername" id="customername" value=""><br></td>
				<td><label id="labeltip_customername"></label></td>
				</tr>
				<tr id="tr_bususername">
				<td><label for="bususername">业务人员：</label></td>
				<td><input readonly type="text" name="bususername" id="bususername" value=""><br></td>
				<td><label id="labeltip_bususername"></label></td>
				</tr>
				<tr id="tr_storeusername">
				<td><label for="storeusername">仓库管理：</label></td>
				<td><input readonly type="text" name="storeusername" id="storeusername" value=""><br></td>
				<td><label id="labeltip_storeusername"></label></td>
				</tr>					
				</table>
				<div style="margin-top:10px;padding-left:80px">
					<button type="button" id="btn_return" class="whiteButton">返回</button>
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>