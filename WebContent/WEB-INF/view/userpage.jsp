<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>用户信息</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/personal.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="personal">
		<p class="nickname">用户名: ${user.nickname}</p>
		<p class="gender">性别: ${user.gender}</p>
		<p class="like">获得赞数:${likeCount} &nbsp; 踩数:${dislikeCount}</p>
		<p class="introduction">
			个人简介:
			<c:if test="${empty user.introduction}">
                                    无个人简介     
            </c:if>
			${user.introduction}
		</p>
		<p><a href="/zbzd/messageController/toSendMessage?id=${user.id}">发送私信</a></p>
		<c:choose>
			<c:when test="${empty follow}">
				<p>
					<a href="/zbzd/userController/follow?id=${user.id}">关注</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="/zbzd/userController/deleteFollow?id=${user.id}">取消关注</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
	<hr />
	<c:if test="${user.privacy==0}">
		<div class="favourite">
			<p>他的收藏夹</p>
			<ul>
				<c:forEach var="favourite" items="${favourites}">
					<li class="onefavourite"><a
						href="/zbzd/favouriteController/toFavourite?id=${favourite.id}">${favourite.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<ul class="actbar">
			<li><a href="/zbzd/userController/userPage?act=que">他的问题</a></li>
			<li><a href="/zbzd/userController/userPage?act=ans">他的回答</a></li>
			<li><a href="/zbzd/userController/userPage?act=wat">他的关注</a></li>
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
							<p class="attachment">赞数:${answer.likeCount}&nbsp;
								踩数:${answer.dislikeCount}&nbsp;最后修改于：${answer.modifiedTime}</p>
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
	</c:if>
</body>

</html>