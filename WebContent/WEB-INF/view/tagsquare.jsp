<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>话题广场</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/tagsquare.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="tagsquare">
		<h1>话题广场</h1>
		<ul>
			<c:forEach var="tag" items="${tags}">
				<li class="toptag"><input type="button" value="${tag.name}"
					class="but"
					onclick="window.location.href='/zbzd/tagController/subTag?id=${tag.id}'" /></li>
			</c:forEach>
		</ul>

		<div class="subsquare">
			<hr />
			<h1>子话题</h1>
			<ul>
				<c:if test="${not empty tag }">
					<li class="subtag"><input type="button" value="${tag.name}"
						class="but"
						onclick="window.location.href='/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=1'" /></li>
				</c:if>
				<c:if test="${not empty subtags}">
					<c:forEach var="subtag" items="${subtags}">
						<li class="subtag"><input type="button"
							value="${subtag.name}" class="but"
							onclick="window.location.href='/zbzd/tagController/questionBelongTag?id=${subtag.id}&curPage=1'" /></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
</body>

</html>