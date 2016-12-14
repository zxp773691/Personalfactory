
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
<script src="js/jquery-1.8.0.js" type="text/javascript" charset="utf-8"></script>
<script src="layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="js/buymaterial.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	<section id="material_buy"  style="width: 500px; margin: 10px auto">
		<form action="store?param=doBuyMaterial" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: solid 1px #999 ">
				<legend >原料购买</legend>
				<table class="noBorderTable">
				<tr id="tr_name">
				<td><label for="name" class="label_middle">原料名称：</label></td>
				<td><select id="name" name="name">
				<c:forEach items="${requestScope.list}" var="material">
				<option value="${material.name}">${material.name}</option>
				</c:forEach>
				</select></td>
				</tr>
				<tr id="tr_customername">
				<td><label for="customername" class="label_middle">供货商：</label></td>
				<td><input type="text" name="customername" id="customername" value=""></td>
				</tr>
				<tr id="tr_date">
				<td><label for="date" class="label_middle">购买日期：</label></td>
				<td><input type="date" name="date" id="date" ></td>
				</tr>
				<tr id="tr_price">
				<td><label for="price" class="label_middle">购买金额(元/kg)：</label></td>
				<td><input type="text" name="price" id="price" value="${requestScope.list[0].price }"><br></td>
				</tr>
				<tr id="tr_weight">
				<td><label for="weight" class="label_middle">原料重量(kg)：</label></td>
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
<script >
$('#customername').on('click', function(){
	  layer.open({
		  type: 1,
		  btn:'确认',
		  area: ['600px', '360px'],
		  shadeClose: false, //点击遮罩关闭
		  content: '<div style="padding:20px;"><c:forEach items="${requestScope.clist}" var="customer"><input type="radio" name="radio" value="${customer.name}">${customer.name}</c:forEach><\/div>',
		  yes: function(index,layero){
			  let value=$('input:radio:checked').val();
			   $("#customername").val(value);
			  layer.close(index);
		  },
		  });
	  });
</script>
</html>