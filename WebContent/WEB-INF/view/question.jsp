<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>问题</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/question.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="questioncontent">
		<p>来自话题:&nbsp;${tagName}</p>
		<h1>${question.title}</h1>
		<p>${question.content}</p>
		<input type="button" value="关注问题">
		<p class="attachment">
			发布于：${question.createTime} &nbsp;
			<c:choose>
				<c:when test="${1==question.isAnonymous}">
                                                     匿名发布
                <c:if test="${poster.id==sessionScope.user.id}">
						<input type="button" value="取消匿名" class="ano"
							onclick="window.location.href='/zbzd/questionController/editQuestionAnonymous?id=${question.id}'" />
					</c:if>
				</c:when>
				<c:otherwise>
					<a href="#">提问人:${poster.nickname}</a>&nbsp;
                <c:if test="${poster.id==sessionScope.user.id}">
						<input type="button" value="匿名" class="ano"
							onclick="window.location.href='/zbzd/questionController/editQuestionAnonymous?id=${question.id}'" />
					</c:if>
				</c:otherwise>
			</c:choose>
		</p>
	</div>

	<div class="myanswer">
		<h1>我的回答</h1>
		<c:choose>
			<c:when test="${empty myAnswer}">
				<div class="postanswer">
					<form action="/zbzd/answerController/postAnswer" method="post">
						<input type="hidden" name="questionId" value="${question.id}" />
						<input type="text" name="answercontent" class="answercontent" />
						<input type="checkbox" name="isAnonymous" value="yes"
							class="anano">匿名回答 <input type="submit" value="回答"
							class="ans" />
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="oneanswer">
					<p class="content">${myAnswer.content}</p>
					<p class="attachment">
						最后修改于：${myAnswer.modifiedTime}
						<c:choose>
							<c:when test="${1==answer.isAnonymous}">
	                                                                            匿名回答 <input
									type="button" value="取消匿名" class="ano"
									onclick="window.location.href=''" />
							</c:when>
							<c:otherwise>
								<input type="button" value="匿名" class="ano"
									onclick="window.location.href=''" />
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<br />
	<hr />
	<div class="contentbar">
		<h1>其他回答</h1>
		<c:forEach var="answer" items="${answers}">
			<div class="oneanswer">
				<p class="content">${answer.content}</p>
				<p class="attachment">
					最后修改于：${answer.modifiedTime}
					<c:choose>
						<c:when test="${1==answer.isAnonymous}">
	                                                                            匿名回答
	                        </c:when>
						<c:otherwise>
							<a href="">作者：${answer.userId}</a>
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</c:forEach>
		<div class="pagetool">
			<c:if test="${curPage>1}">
				<a
					href="/zbzd/questionController/toQuestion?id=${question.id}&curPage=${curPage-1}">上一页&nbsp;</a>
			</c:if>
			当前页：${curPage} &nbsp;总页数：${totalPage}
			<c:if test="${curPage<totalPage}">
				<a
					href="/zbzd/questionController/toQuestion?id=${question.id}&curPage=${curPage+1}">&nbsp;下一页</a>
			</c:if>
		</div>
	</div>


</body>

</html>