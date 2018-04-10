<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>����</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="questionbar">
		<form action="/zbzd/questionController/postQuestion" method="post">
			<input type="text" name="title" placeholder="�������" /> <input
				type="text" name="content" placeholder="��������" /> <select name="tag">
				<option value="0">ѡ����</option>
				<c:forEach var="tag" items="${tags}">
					<option value="${tag.id}">${tag.name}</option>
				</c:forEach>
			</select> <input type="checkbox" name="isAnonymous" value="yes" class="ano">��������
			<input type="submit" value="�ύ����" class="sub" />
		</form>
	</div>

</body>

</html>