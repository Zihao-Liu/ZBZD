<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>关注列表</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/followlist.css" type="text/css"
	rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="followlist">

		<h1>
			<c:choose>
				<c:when test="${not empty follower}">
				    粉丝列表
				</c:when>
				<c:otherwise>
				我关注的人
				</c:otherwise>
			</c:choose>
		</h1>
		<hr />
		<c:choose>
			<c:when test="${not empty follower}">
				<ul>
					<c:forEach var="follower" items="${followers}">
						<li class="onefollow"><a
							href="/zbzd/userController/userPage?id=${follower.followerId}">${follower.name}</a></li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<ul>
					<c:forEach var="following" items="${followings}">
						<li class="onefollow"><a
							href="/zbzd/userController/userPage?id=${following.followingId}">${following.name}</a></li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>

	</div>
</body>

</html>