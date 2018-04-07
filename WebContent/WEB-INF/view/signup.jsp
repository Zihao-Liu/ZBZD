<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>注册</title>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">

</head>

<body>

	<form action="/zbzd/userController/signUp" method="post">
		用户名：<input type="text" name="username" placeholder="只允许字母和数字"/> 
		密码<input type="password" name="password" placeholder="只允许字母和数字"/> 
		昵称 <input type="text" name="nickname" placeholder="不能为空"/> 
		隐私<label><input type="radio" name="privacy" value="1" checked="checked">公开</label> 
		<label><input type="radio" name="privacy" value="0">隐藏</label> 
		性别 <label><input type="radio" name="gender" value="male" checked="checked">男</label> 
		<label><input type="radio" name="gender" value="female">女</label> 
		简介<input type="text" name="introduction" /> 
		<input type="submit" />
	</form>
	${error}
</body>

</html>