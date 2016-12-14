
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
<script src="js/processmaterial.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<section id="record_add"  style="width: 500px; margin: 10px auto">
		<form action="record?param=doProcess" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: 0px">
				<legend hidden>新增</legend>
				<table class="noBorderTable">
				<tr id="tr_processtime">
				<td><label for="processtime" class="label_middle"> 加工时间：</label></td>
				<td><input type="text" name="processtime" id="processtime" value=""></td>
				</tr>
				<tr id="tr_mlname">
				<td><label for="mlname" class="label_middle">原料名称：</label></td>
				<td><input type="text" name="mlname" id="mlname" value=""><br></td>
				</tr>
				<tr id="tr_frname">
				<td><label for="frname" class="label_middle">面粉名称：</label></td>
				<td><input type="text" name="frname" id="frname" value=""></td>
				</tr>
				<tr id="tr_mlweight">
				<td><label for="mlweight" class="label_middle">原料重量：</label></td>
				<td><input type="text" name="mlweight" id="mlweight" value=""></td>
				</tr>	
				<tr id="tr_frweight">
				<td><label for="frweight" class="label_middle">面粉重量：</label></td>
				<td><input readonly type="text" name="frweight" id="frweight" value=""></td>
				</tr>						
				</table>
				<div style="margin-top:10px;padding-left:80px">
					<button type="reset" class="whiteButton">重置</button>
					<button type="submit" id="btn_addAndUpdate" class="whiteButton">确定</button>
				</div>
			</fieldset>
		</form>
	</section>
</body>
</html>