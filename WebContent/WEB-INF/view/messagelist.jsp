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
<link href="<%=request.getContextPath()%>/css/message.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="messagelist">
		<ul class="actbar">
			<li><a href="/zbzd/messageController/toMessage?act=send">发件箱</a></li>
			<li><a href="/zbzd/messageController/toMessage?act=recv">收件箱</a></li>
		</ul>
		<hr />

		<c:forEach var="message" items="${messages}">
			<div class="onemessage">
				<c:if test="${message.isRead==0 and message.senderId!=user.id}">
					<p>新信息</p>
				</c:if>
				<p>信息：${message.content}</p>
				<p>发送人：${message.name}</p>
				<p>发送时间:${message.createTime}</p>
				<p>
					<a href="/zbzd/messageController/deleteMessage?id=${message.id}">删除</a>
				</p>
			</div>
		</c:forEach>
	</div>

	<div class="pagetool">
		<c:if test="${curPage>1}">
			<a
				href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage-1}">上一页&nbsp;</a>
		</c:if>
		当前页：${curPage} &nbsp;总页数：${totalPage}
		<c:if test="${curPage<totalPage}">
			<a
				href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage+1}">&nbsp;下一页</a>
		</c:if>
	</div>
</body>

</html>