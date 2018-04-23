<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>�û���Ϣ</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/personal.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="personal">
		<p class="nickname">�û���: ${user.nickname}</p>
		<p class="gender">�Ա�: ${user.gender}</p>
		<p class="like">�������:${likeCount} &nbsp; ����:${dislikeCount}</p>
		<p class="introduction">
			���˼��:
			<c:if test="${empty user.introduction}">
                                    �޸��˼��     
            </c:if>
			${user.introduction}
		</p>
		<p><a href="/zbzd/messageController/toSendMessage?id=${user.id}">����˽��</a></p>
		<c:choose>
			<c:when test="${empty follow}">
				<p>
					<a href="/zbzd/userController/follow?id=${user.id}">��ע</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<a href="/zbzd/userController/deleteFollow?id=${user.id}">ȡ����ע</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
	<hr />
	<c:if test="${user.privacy==0}">
		<div class="favourite">
			<p>�����ղؼ�</p>
			<ul>
				<c:forEach var="favourite" items="${favourites}">
					<li class="onefavourite"><a
						href="/zbzd/favouriteController/toFavourite?id=${favourite.id}">${favourite.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<ul class="actbar">
			<li><a href="/zbzd/userController/userPage?act=que">��������</a></li>
			<li><a href="/zbzd/userController/userPage?act=ans">���Ļش�</a></li>
			<li><a href="/zbzd/userController/userPage?act=wat">���Ĺ�ע</a></li>
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
							<p class="attachment">�����ڣ�${question.createTime}
								&nbsp;&nbsp;�ش�������${question.answerCount}</p>
						</div>
					</c:forEach>
					<div class="pagetool">
						<c:if test="${curPage>1}">
							<a
								href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage-1}">��һҳ&nbsp;</a>
						</c:if>
						��ǰҳ��${curPage} &nbsp;��ҳ����${totalPage}
						<c:if test="${curPage<totalPage}">
							<a
								href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage+1}">&nbsp;��һҳ</a>
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
							<p class="attachment">����:${answer.likeCount}&nbsp;
								����:${answer.dislikeCount}&nbsp;����޸��ڣ�${answer.modifiedTime}</p>
						</div>
					</c:forEach>
					<div class="pagetool">
						<c:if test="${curPage>1}">
							<a
								href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage-1}">��һҳ&nbsp;</a>
						</c:if>
						��ǰҳ��${curPage} &nbsp;��ҳ����${totalPage}
						<c:if test="${curPage<totalPage}">
							<a
								href="/zbzd/tagController/questionBelongTag?id=${tag.id}&curPage=${curPage+1}">&nbsp;��һҳ</a>
						</c:if>
					</div>
				</div>
			</c:when>
		</c:choose>
	</c:if>
</body>

</html>