
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
<script src="js/usermanager.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	<section id="user_show">
		<nav id="nav_query">
			<label for="keyword">用户名称：</label><input type="text" name="keyword"
				id="keyword" " value="${requestScope.keyword}">
			<button type="button" id="btn_query" class="whiteButton">查询</button>
		</nav>
		<section>
			<table class="standardTable">
				<tbody>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>username</th>
						<th>userno</th>
						<th>realname</th>
						<th>state</th>
					</tr>
					<c:forEach items="${requestScope.pageInfo.list}" var="storeinfo">
						<tr>
							<td><input type="checkbox" id="${storeinfo.id}"
								name="id_check"></td>
							<td>${storeinfo.username}</td>
							<td>${storeinfo.userno}</td>
							<td>${storeinfo.realname}</td>
							<c:if test="${storeinfo.state=='0'}">
							<td>离职</td>
							</c:if>
							<c:if test="${storeinfo.state=='1'}">
							<td>正常</td>
							</c:if>
							<c:if test="${storeinfo.state=='2'}">
							<td>休假</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>
		<nav id="nav_operation">
			<div id="div_operation">
				<button type="button" id="btn_updateUser" class="whiteButton">修改用户</button>
				<button type="button" id="btn_addUser" class="whiteButton">增加用户</button>
				<button type="button" id="btn_deleteUser" class="whiteButton">用户删除</button>
				<button type="button" id="btn_updateRole" class="whiteButton">修改角色</button>
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
	<section id="user_add" hidden style="width: 500px; margin: 10px auto">
		<form action="" method="post" id="addAndUpdateForm">
			<fieldset id="" style="border: 0px">
				<legend hidden>新增</legend>
				<table class="noBorderTable">
				<tr id="table_row_id">
				<td><label for="id">id：</label></td>
				<td><input readonly type="text" name="id" id="id" value=""><br></td>
				<td><label id="labeltip_id"></label></td>
				</tr >
				<tr id="tr_username">
				<td><label for="userName">用户名：</label></td>
				<td><input type="text" name="userName" id="userName" value=""></td>
				<td><label id="labeltip_name" style="color:red">请输入用户名</label></td>
				</tr>
				<tr id="tr_password">
				<td><label for="password">密&nbsp;码：</label></td>
				<td><input type="text" name="password" id="password" value=""><br></td>
				<td><label id="labeltip_password"></label></td>
				</tr>
				<tr id="tr_userno">
				<td><label for="userNo">用户编号：</label></td>
				<td><input type="text" name="userNo" id="userNo" value=""></td>
				<td><label id="labeltip_userno"></label></td>
				</tr>
				<tr id="tr_realname">
				<td><label for="realName">真实姓名：</label></td>
				<td><input type="text" name="realName" id="realName" value=""></td>
				<td><label id="labeltip_realname"></label></td>
				</tr>
				<tr id="tr_telephone">
				<td><label for="telephone">电&nbsp;话：</label></td>
				<td><input type="text" name="telephone" id="telephone" value=""></td>
				<td><label id="labeltip_telephone"></label></td>
				</tr>
				<tr id="tr_idcard">
				<td><label for="idCard">身份证：</label></td>
				<td><input type="text" name="idCard" id="idCard" value=""></td>
				<td><label id="labeltip_idcard"></label></td>
				</tr>
				<tr id="tr_registertime">
				<td><label for="registerTime">注册时间：</label></td>
				<td><input type="text" name="registerTime" id="registerTime" value=""></td>
				<td><label id="labeltip_registertime"></label></td>
				</tr>
				<tr id="tr_state">
				<td><label for="state">用户状态：</label></td>
				<td><input type="text" name="state" id="state" value=""></td>
				<td><label id="labeltip_state"></label></td>
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