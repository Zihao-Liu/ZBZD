<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>������ҳ</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/personal.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="personal">
		<p class="nickname">�û���: ${user.nickname}
		<p>
		<p class="gender">�Ա�: ${user.gender}</p>
		<p class="introduction">
			���˼��:
			<c:if test="${empty user.introduction}">
                                    �޸��˼��     
            </c:if>
			${user.introduction}
		</p>
		<p class="privacy">
			<c:choose>
				<c:when test="${user.privacy==1}">
		                                ��ǰ��˽���� <input type="button" value="����"
						class="change" />
				</c:when>
				<c:otherwise>
		                               ��ǰ��˽����  <input type="button" value="����"
						class="change" />
				</c:otherwise>
			</c:choose>
			<input type="button" value="�༭��������" class="edit" />
		</p>
	</div>

	<ul class="actbar">
		<li><a href="/zbzd/userController/personal?act=que">�ҵ�����</a></li>
		<li><a href="/zbzd/userController/personal?act=ans">�ҵĻش�</a></li>
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
						<p class="attachment">����޸��ڣ�${answer.modifiedTime}</p>
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
</body>

</html>