<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>个人主页</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/personal.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="personal">
		<p class="nickname">用户名: ${user.nickname}
		<p>
		<p class="gender">性别: ${user.gender}</p>
		<p class="introduction">
			个人简介:
			<c:if test="${empty user.introduction}">
                                    无个人简介     
            </c:if>
			${user.introduction}
		</p>
		<p class="privacy">
			<c:choose>
				<c:when test="${user.privacy==1}">
		                                当前隐私公开 <input type="button" value="隐藏"
						class="change" />
				</c:when>
				<c:otherwise>
		                               当前隐私隐藏  <input type="button" value="公开"
						class="change" />
				</c:otherwise>
			</c:choose>
			<input type="button" value="编辑个人资料" class="edit" />
		</p>
	</div>

	<ul class="actbar">
		<li><a href="/zbzd/userController/personal?act=que">我的问题</a></li>
		<li><a href="/zbzd/userController/personal?act=ans">我的回答</a></li>
	</ul>
	<hr />
	<c:choose>
		<c:when test="${not empty questions}">
			<div class="questionbar">
				<c:forEach var="question" items="${questions}">
					<div class="onequestion">
						<p>
							<a href="/zbzd/questionController/toQuestion?id=${question.id}">${question.title}</a>
						</p>
						<p class="content">${question.content}</p>
						<p class="attachment">发布于：${question.createTime}
							&nbsp;&nbsp;回答人数：${question.answerCount}</p>
					</div>
				</c:forEach>
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
			</div>
		</c:when>

		<c:when test="${not empty answers}">
			<div class="answerbar">
				<c:forEach var="answer" items="${answers}">
					<div class="oneanswer">
						<p class="content">
							<a
								href="/zbzd/questionController/toQuestion?id=${answer.questionId}">${answer.content}</a>
						</p>
						<p class="attachment">最后修改于：${answer.modifiedTime}</p>
					</div>
				</c:forEach>
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
			</div>
		</c:when>
	</c:choose>
</body>

</html>