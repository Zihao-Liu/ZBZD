<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>登录</title>

<link href="<%=request.getContextPath()%>/css/signin.css"
	type="text/css" rel="stylesheet" media="all" />
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

</head>

<body>
	<div id='login'>
		<form action="/zbzd/userController/signIn" method="post">
			<div id="login_title">登录</div>
			<div class="line">
				用户名：&nbsp;&nbsp; <input name="username" type="text"
					placeholder="用户名" />
			</div>
			<div class="line">
				密码：&nbsp;&nbsp; <input name="password" type="password"
					placeholder="密码" />
			</div>
			<button id="log_submit" value="login" type="submit">登录</button>
		</form>
		${error}

	</div>
</body>

</html>