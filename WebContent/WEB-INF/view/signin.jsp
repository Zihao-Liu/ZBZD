<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>��¼</title>

<link href="<%=request.getContextPath()%>/css/signin.css"
	type="text/css" rel="stylesheet" media="all" />
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

</head>

<body>
	<div id='login'>
		<form action="/zbzd/userController/signIn" method="post">
			<div id="login_title">��¼</div>
			<div class="line">
				�û�����&nbsp;&nbsp; <input name="username" type="text"
					placeholder="�û���" />
			</div>
			<div class="line">
				���룺&nbsp;&nbsp; <input name="password" type="password"
					placeholder="����" />
			</div>
			<button id="log_submit" value="login" type="submit">��¼</button>
		</form>
		${error}

	</div>
</body>

</html>