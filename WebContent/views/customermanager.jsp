
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
<script src="js/customermanager.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	<section id="customer_show">
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
						<th>customerno</th>
						<th>name</th>
						<th>telephone</th>
						<th>remark</th>
					</tr>
					<c:forEach items="${requestScope.pageInfo.list}" var="customer">
						<tr>
							<td><input type="checkbox" id="${customer.id}"
								name="id_check"></td>
							<td>${customer.customerno}</td>
							<td>${customer.name}</td>
							<td>${customer.telephone}</td>
							<td>${customer.remark}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<nav id="nav_operation">
			<div id="div_operation">
				<button type="button" id="btn_updateCustomer" class="whiteButton">修改客户</button>
				<button type="button" id="btn_addCustomer" class="whiteButton">增加客户</button>
				<button type="button" id="btn_deleteCustomer" class="whiteButton">删除客户</button>
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
	<section id="customer_add" hidden style="width: 500px; margin: 10px auto">
		<form action="" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: 0px">
				<legend hidden>新增</legend>
				<table class="noBorderTable">
				<tr id="table_row_id">
				<td><label for="id">id：</label></td>
				<td><input readonly type="text" name="id" id="id" value=""><br></td>
				<td><label id="labeltip_id"></label></td>
				</tr >
				<tr id="tr_customerno">
				<td><label for="customerno">客户编号：</label></td>
				<td><input type="text" name="customerno" id="customerno" value=""></td>
				<td><label id="labeltip_customerno" style="color:red"></label></td>
				</tr>
				<tr id="tr_name">
				<td><label for="name">客户名：</label></td>
				<td><input type="text" name="name" id="name" value=""></td>
				<td><label id="labeltip_name" style="color:red">请输入客户名</label></td>
				</tr>
				<tr id="tr_address">
				<td><label for="address">地址：</label></td>
				<td><input type="text" name="address" id="address" value=""></td>
				<td><label id="labeltip_address" style="color:red"></label></td>
				</tr>
				<tr id="tr_telephone">
				<td><label for="telephone">电话：</label></td>
				<td><input type="text" name="telephone" id="telephone" value=""><br></td>
				<td><label id="labeltip_telephone"></label></td>
				</tr>
				<tr id="tr_idcard">
				<td><label for="idcard">身份证：</label></td>
				<td><input type="text" name="idcard" id="idcard" value=""></td>
				<td><label id="labeltip_idcard" style="color:red"></label></td>
				</tr>
				<tr id="tr_registertime">
				<td><label for="registertime">注册时间：</label></td>
				<td><input type="text" name="registertime" id="registertime" value=""></td>
				<td><label id="labeltip_registertime" style="color:red"></label></td>
				</tr>
				<tr id="tr_state">
				<td><label for="state">状态：</label></td>
				<td><input type="text" name="state" id="state" value=""></td>
				<td><label id="labeltip_state" style="color:red"></label></td>
				</tr>
				<tr id="tr_remark">
				<td><label for="remark">备注：</label></td>
				<td><input type="text" name="remark" id="remark" value=""></td>
				<td><label id="labeltip_remark"></label></td>
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