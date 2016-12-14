<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link rel="stylesheet" type="text/css" href="css/_component.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/base.css" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/main.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<header>
		<h1>面粉厂生产管理系统</h1>
		<button class="greenButton" id="return_main">
			<span class="icon-home"></span>返回首页
		</button>
		<button class="redButton" id="return_login">
			<span class="icon-power-off"></span>退出登录
		</button>
	</header>
	<section>
		<nav id="menu" class="leftnav">
			<div class="leftnav-title">
				<strong><span class="icon-list"></span>菜单列表
				</strong>
			</div>
			<h6>
				&nbsp;<span class="icon-user"></span>功能菜单
			</h6>
			<ul>
				<c:forEach items="${requestScope.map}" var="permission">
					<li>
						<c:if test="${permission.value.type==0||permission.value.type==1}">
						<a href="${permission.value.url}&userid=${requestScope.user.id}" target="main_iframe">
							<span class="icon-caret-right"></span>&nbsp;${permission.value.name} 
						</a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<h6>
				&nbsp;<span class="icon-user"></span>系统管理
			</h6>
			<ul>
				<c:forEach items="${requestScope.map}" var="permission">
					<li>
						<c:if test="${permission.value.type==2}">
						<a href="${permission.value.url}&userid=${requestScope.user.id}" target="main_iframe">
							<span class="icon-caret-right"></span>&nbsp;${permission.value.name} 
						</a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</nav>
		<article>
			<iframe name="main_iframe"></iframe>
		</article>
		<input type="hidden" name="username" id="username" value="${requestScope.user.username}"> 
		<input type="hidden" name="password" id="password" value="${requestScope.user.password}">
	</section>
</body>
</html>