<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
		<c:choose>
		  <c:when test="${empty watch}">
		  <p class="watch"><a href="/zbzd/questionController/watchQuestion?id=${question.id}">关注</a></p>
		  </c:when>
		  <c:otherwise>
		  <p class="watch"><a href="/zbzd/questionController/deleteWatch?id=${question.id}">取消关注</a></p>
		  </c:otherwise>
		</c:choose>
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
					<a href="/zbzd/userController/userPage?id=${poster.id}">提问人:${poster.nickname}</a>&nbsp;
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
						最后修改于：${myAnswer.modifiedTime} &nbsp;
						赞数:${myAnswer.likeCount}&nbsp; 踩数:${myAnswer.dislikeCount}
						<c:choose>
							<c:when test="${1==myAnswer.isAnonymous}">
	                                                                            匿名回答 <input
									type="button" value="取消匿名" class="ano"
									onclick="window.location.href='/zbzd/answerController/editAnswerAnonymous?id=${myAnswer.id}'" />
							</c:when>
							<c:otherwise>
								<input type="button" value="匿名" class="ano"
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
			href="/zbzd/questionController/toQuestion?id=${question.id}&act=time">最新回答</a></li>
		<li><a
			href="/zbzd/questionController/toQuestion?id=${question.id}&act=count">热门回答</a></li>
	</ul>
	<hr />
	<div class="contentbar">
		<h1>其他回答</h1>
		<c:forEach var="answer" items="${answers}">
			<div class="oneanswer">
				<p class="content">${answer.content}</p>
				<p class="attachment">
					最后修改于：${answer.modifiedTime} &nbsp; 赞数:${answer.likeCount}&nbsp;
					踩数:${answer.dislikeCount}
					<c:choose>
						<c:when test="${1==answer.isAnonymous}">
                                                                                匿名回答
                            </c:when>
						<c:otherwise>
							<a href="">作者：${answer.userId}</a>
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
								href="/zbzd/answerController/responseAnswer?act=1&id=${answer.id}">喜欢</a>
							<a
								href="/zbzd/answerController/responseAnswer?act=0&id=${answer.id}">不喜欢</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${like.is_like==1}">&nbsp;您选择了喜欢</c:when>
								<c:otherwise>&nbsp;您选择了不喜欢</c:otherwise>
							</c:choose>
							<a href="/zbzd/answerController/deleteResponse?id=${answer.id}">取消</a>
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
									type="submit" value="收藏" />
							</form>
						</c:when>
						<c:otherwise>
							<a
								href="/zbzd/answerController/deleteCollect?id=${answer.id}&qid=${question.id}">取消收藏</a>
						</c:otherwise>
					</c:choose>

				</c:if>


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