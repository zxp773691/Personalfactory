<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
		<link rel="stylesheet" type="text/css" href="css/base.css"/>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<link rel="stylesheet" type="text/css" href="css/_component.css"/>

</head>
<body>
		<section>
			<h1>用户登录</h1>
			<h6 id="message">${message}</h6>
			<form action="lar?param=login" method="post">
			<fieldset id="">
				<legend hidden>登录</legend>
				</label><input type="text" name="userName" id="userName" placeholder="用户名"><br>
				<div id="div_nothing"></div>
				</label><input type="password" name="password" id="password" placeholder="密码"><br>
				<div id="div_nothing"></div>
				<nav>
					<button type="submit" id="login" class="blueButton">登陆</button>
				</nav>
			</fieldset>
			</form>
		</section>
</body>
</html>