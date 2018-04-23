<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>����</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath()%>/css/question.css"
	type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="favouritecontent">
		<h1>${favourite.name}</h1>
		<p class="attachment">�����ڣ�${favourite.createTime}</p>
		<p>
			<a
				href="/zbzd/favouriteController/deleteFavourite?id=${favourite.id}">ɾ���ղؼ�</a>
		</p>
	</div>

	<hr />
	<div class="contentbar">
		<h1>�ղػش�</h1>
		<c:forEach var="answer" items="${answers}">
			<div class="oneanswer">
				<p class="content">${answer.content}</p>
				<p class="attachment">
					����޸��ڣ�${answer.modifiedTime} &nbsp; ����:${answer.likeCount}&nbsp;
					����:${answer.dislikeCount}

					<c:if test="${not empty sessionScope.user}">
						<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
							url="jdbc:mysql://localhost:3306/zbzd?useUnicode=true&characterEncoding=utf8&useSSL=false"
							user="root" password="" />
						<sql:query dataSource="${dataSource}" var="results">
                            select is_like
                            from user_response_answer
                            where user_id = ${sessionScope.user.id} and answer_id = ${answer.id}
                         </sql:query>
						<c:forEach var="result" items="${results.rows}">
							<c:set var="like" value="${result}" />
						</c:forEach>
						<c:choose>
							<c:when test="${empty results.rows}">
								<a
									href="/zbzd/answerController/responseAnswer?act=1&id=${answer.id}">ϲ��</a>
								<a
									href="/zbzd/answerController/responseAnswer?act=0&id=${answer.id}">��ϲ��</a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${like.is_like==1}">&nbsp;��ѡ����ϲ��</c:when>
									<c:otherwise>&nbsp;��ѡ���˲�ϲ��</c:otherwise>
								</c:choose>
								<a href="/zbzd/answerController/deleteResponse?id=${answer.id}">ȡ��</a>
							</c:otherwise>
						</c:choose>
						<a href="/zbzd/answerController/deleteCollect?id=${answer.id}">ȡ���ղ�</a>
					</c:if>

					<c:choose>
						<c:when test="${1==answer.isAnonymous}">
                                                                                �����ش�
                            </c:when>
						<c:otherwise>
							<a href="">���ߣ�${answer.userId}</a>
						</c:otherwise>
					</c:choose>
				</p>
			</div>
		</c:forEach>
		<div class="pagetool">
			<c:if test="${curPage>1}">
				<a
					href="/zbzd/questionController/toQuestion?id=${question.id}&curPage=${curPage-1}">��һҳ&nbsp;</a>
			</c:if>
			��ǰҳ��${curPage} &nbsp;��ҳ����${totalPage}
			<c:if test="${curPage<totalPage}">
				<a
					href="/zbzd/questionController/toQuestion?id=${question.id}&curPage=${curPage+1}">&nbsp;��һҳ</a>
			</c:if>
		</div>
	</div>


</body>

</html>