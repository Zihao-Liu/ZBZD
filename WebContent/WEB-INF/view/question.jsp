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
	<div class="questioncontent">
		<p>���Ի���:&nbsp;${tagName}</p>
		<h1>${question.title}</h1>
		<p>${question.content}</p>
		<c:choose>
		  <c:when test="${empty watch}">
		  <p class="watch"><a href="/zbzd/questionController/watchQuestion?id=${question.id}">��ע</a></p>
		  </c:when>
		  <c:otherwise>
		  <p class="watch"><a href="/zbzd/questionController/deleteWatch?id=${question.id}">ȡ����ע</a></p>
		  </c:otherwise>
		</c:choose>
		<p class="attachment">
			�����ڣ�${question.createTime} &nbsp;
			<c:choose>
				<c:when test="${1==question.isAnonymous}">
                                                     ��������
                <c:if test="${poster.id==sessionScope.user.id}">
						<input type="button" value="ȡ������" class="ano"
							onclick="window.location.href='/zbzd/questionController/editQuestionAnonymous?id=${question.id}'" />
					</c:if>
				</c:when>
				<c:otherwise>
					<a href="/zbzd/userController/userPage?id=${poster.id}">������:${poster.nickname}</a>&nbsp;
                <c:if test="${poster.id==sessionScope.user.id}">
						<input type="button" value="����" class="ano"
							onclick="window.location.href='/zbzd/questionController/editQuestionAnonymous?id=${question.id}'" />
					</c:if>
				</c:otherwise>
			</c:choose>
		</p>
	</div>

	<div class="myanswer">
		<h1>�ҵĻش�</h1>
		<c:choose>
			<c:when test="${empty myAnswer}">
				<div class="postanswer">
					<form action="/zbzd/answerController/postAnswer" method="post">
						<input type="hidden" name="questionId" value="${question.id}" />
						<input type="text" name="answercontent" class="answercontent" />
						<input type="checkbox" name="isAnonymous" value="yes"
							class="anano">�����ش� <input type="submit" value="�ش�"
							class="ans" />
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="oneanswer">
					<p class="content">${myAnswer.content}</p>
					<p class="attachment">
						����޸��ڣ�${myAnswer.modifiedTime} &nbsp;
						����:${myAnswer.likeCount}&nbsp; ����:${myAnswer.dislikeCount}
						<c:choose>
							<c:when test="${1==myAnswer.isAnonymous}">
	                                                                            �����ش� <input
									type="button" value="ȡ������" class="ano"
									onclick="window.location.href='/zbzd/answerController/editAnswerAnonymous?id=${myAnswer.id}'" />
							</c:when>
							<c:otherwise>
								<input type="button" value="����" class="ano"
									onclick="window.location.href='/zbzd/answerController/editAnswerAnonymous?id=${myAnswer.id}'" />
							</c:otherwise>
						</c:choose>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<br />
	<ul class="actbar">
		<li><a
			href="/zbzd/questionController/toQuestion?id=${question.id}&act=time">���»ش�</a></li>
		<li><a
			href="/zbzd/questionController/toQuestion?id=${question.id}&act=count">���Żش�</a></li>
	</ul>
	<hr />
	<div class="contentbar">
		<h1>�����ش�</h1>
		<c:forEach var="answer" items="${answers}">
			<div class="oneanswer">
				<p class="content">${answer.content}</p>
				<p class="attachment">
					����޸��ڣ�${answer.modifiedTime} &nbsp; ����:${answer.likeCount}&nbsp;
					����:${answer.dislikeCount}
					<c:choose>
						<c:when test="${1==answer.isAnonymous}">
                                                                                �����ش�
                            </c:when>
						<c:otherwise>
							<a href="">���ߣ�${answer.userId}</a>
						</c:otherwise>
					</c:choose>
				</p>

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



					<sql:query dataSource="${dataSource}" var="results2">
                            select *
                            from user_collect_answer
                            where user_id = ${sessionScope.user.id} and answer_id = ${answer.id}
                         </sql:query>


					<c:choose>
						<c:when test="${empty results2.rows}">
							<form action="/zbzd/answerController/collectAnswer" method="post">
								<select name="favouriteId" class="favouriteId">
									<c:forEach var="favourite" items="${favourites}">
										<option value="${favourite.id}">${favourite.name}</option>
									</c:forEach>
								</select> <input type="hidden" name="id" value="${answer.id}" /> <input
									type="hidden" name="questionId" value="${question.id }" /> <input
									type="submit" value="�ղ�" />
							</form>
						</c:when>
						<c:otherwise>
							<a
								href="/zbzd/answerController/deleteCollect?id=${answer.id}&qid=${question.id}">ȡ���ղ�</a>
						</c:otherwise>
					</c:choose>

				</c:if>


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