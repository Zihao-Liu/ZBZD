<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>发送私信</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="questionbar">
		<form action="/zbzd/messageController/sendMessage" method="post">
			<p>接收人：${receiver.nickname}</p>
			<input type="text" name="content" placeholder="私信内容" /> <input
				type="hidden" name="id" value="${receiver.id}" /> <input
				type="submit" value="发送私信" class="sub" />
		</form>
	</div>

</body>

</html>