
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
<link rel="stylesheet" type="text/css" href="css/buymaterial.css" />

</head>
<body>
	<section id="flour_sell"  style="width: 500px; margin: 10px auto">
		<form action="store?param=doSellFlour" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: solid 1px #999 ">
				<legend >面粉出售</legend>
				<table class="noBorderTable">
				<tr id="tr_name">
				<td><label for="name" class="label_middle">面粉名称：</label></td>
				<td><select id="name" name="name">
				<c:forEach items="${requestScope.list}" var="material">
				<c:if test="${material.type==1}">
				<option value="${material.name}">${material.name}</option>
				</c:if>
				</c:forEach>
				</select></td>
				</tr>
				<tr id="tr_customername">
				<td><label for="customername" class="label_middle">顾客姓名：</label></td>
				<td><input type="text" name="customername" id="customername" value=""></td>
				</tr>
				<tr id="tr_date">
				<td><label for="date" class="label_middle">出售日期：</label></td>
				<td><input type="date" name="date" id="date" ></td>
				</tr>
				<tr id="tr_price">
				<td><label for="price" class="label_middle">出售金额：</label></td>
				<td><input type="text" name="price" id="price" value=""><br></td>
				</tr>
				<tr id="tr_weight">
				<td><label for="weight" class="label_middle">面粉重量：</label></td>
				<td><input type="text" name="weight" id="weight" value=""></td>
				</tr>
									
				</table>
				<div style="margin-top:10px;padding-left:80px">
					<button type="reset" class="whiteButton">重置</button>
					<button type="submit" id="btn_butmaterial" class="whiteButton">确定</button>
				</div>
				<input hidden id="userid" name="userid" value="${requestScope.bususerid}">	
			</fieldset>
		</form>
	</section>
</body>
</html>