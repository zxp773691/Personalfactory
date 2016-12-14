
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
<script src="js/materialmanager.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	<section id="material_show">
		<nav id="nav_query">
			<label for="keyword">权限名称：</label><input type="text" name="keyword"
				id="keyword" " value="${requestScope.keyword}">
			<button type="button" id="btn_query" class="whiteButton">查询</button>
		</nav>
		<section>
			<table class="standardTable">
				<tbody>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>name</th>
						<th>rate</th>
						<th>type</th>
						<th>price</th>
					</tr>
					<c:forEach items="${requestScope.pageInfo.list}" var="material">
						<tr>
							<td><input type="checkbox" id="${material.id}"
								name="id_check"></td>
							<td>${material.name}</td>
							<td>${material.rate}</td>
							<c:if test="${material.type=='0'}">
							<td>原料</td>
							</c:if>
							<c:if test="${material.type=='1'}">
							<td>面粉</td>
							</c:if>
							<td>${material.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<nav id="nav_operation">
			<div id="div_operation">
				<button type="button" id="btn_updateMaterial" class="whiteButton">修改商品</button>
				<button type="button" id="btn_addMaterial" class="whiteButton">增加商品</button>
				<button type="button" id="btn_deleteMaterial" class="whiteButton">删除商品</button>
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
	<section id="material_add" hidden style="width: 500px; margin: 10px auto">
		<form action="" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: 0px">
				<legend hidden>新增</legend>
				<table class="noBorderTable">
				<tr id="table_row_id">
				<td><label for="id">id：</label></td>
				<td><input readonly type="text" name="id" id="id" value=""><br></td>
				<td><label id="labeltip_id"></label></td>
				</tr >
				<tr id="tr_name">
				<td><label for="name">商品名：</label></td>
				<td><input type="text" name="name" id="name" value=""></td>
				<td><label id="labeltip_name" style="color:red">请输入商品名</label></td>
				</tr>
				<tr id="tr_rate">
				<td><label for="rate">出粉率：</label></td>
				<td><input type="text" name="rate" id="rate" value=""><br></td>
				<td><label id="labeltip_rate"></label></td>
				</tr>
				<tr id="tr_type">
				<td><label for="type">类型：</label></td>
				<td><input type="text" name="type" id="type" value=""></td>
				<td><label id="labeltip_type"></label></td>
				</tr>
				<tr id="tr_state">
				<td><label for="state">状态：</label></td>
				<td><input type="text" name="state" id="state" value=""></td>
				<td><label id="labeltip_state"></label></td>
				</tr>	
				<tr id="tr_price">
				<td><label for="price">价格：</label></td>
				<td><input type="text" name="price" id="price" value=""></td>
				<td><label id="labeltip_price"></label></td>
				</tr>						
				</table>
				<div style="margin-top:10px;padding-left:80px">
					<button type="reset" class="whiteButton">重置</button>
					<button type="button" id="btn_addAndUpdate" class="whiteButton"></button>
					<button type="button" id="btn_return" class="whiteButton">返回</button>
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>