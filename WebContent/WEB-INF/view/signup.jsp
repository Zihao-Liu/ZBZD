<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>ע��</title>

<meta http-equiv="Content-Type" content="text/html charset=gb2312">

</head>

<body>

	<form action="/zbzd/userController/signUp" method="post">
		�û�����<input type="text" name="username" placeholder="ֻ������ĸ������"/> 
		����<input type="password" name="password" placeholder="ֻ������ĸ������"/> 
		�ǳ� <input type="text" name="nickname" placeholder="����Ϊ��"/> 
		��˽<label><input type="radio" name="privacy" value="1" checked="checked">����</label> 
		<label><input type="radio" name="privacy" value="0">����</label> 
		�Ա� <label><input type="radio" name="gender" value="male" checked="checked">��</label> 
		<label><input type="radio" name="gender" value="female">Ů</label> 
		���<input type="text" name="introduction" /> 
		<input type="submit" />
	</form>
	${error}
</body>

</html>