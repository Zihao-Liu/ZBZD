<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
	<div class="navigation">
		<ul>
			<li id="logo">ZBZD</li>
			<li class="fun"><a href="/zbzd">首页</a></li>
			<li class="fun"><a href="/zbzd/tagController/topTag">话题</a></li>
		</ul>



	</div>
	<div class="searchbar">
		<form action="" method="post" class="searchbar">
			<input type="text" name="search" placeholder="搜索你感兴趣的内容" /> <input
				type="submit" value="搜索" class="searchbutton" />
		</form>
		<input type="button" value="提问" class="question"
			onclick="window.location.href='/zbzd/questionController/toPostQuestion'" />
	</div>
	<div class="userinfo">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<form action="/zbzd/userController/toSignIn" method="get"
					class="signin">
					<input type="submit" value="登录" class="s">
				</form>
				<form action="/zbzd/userController/toSignUp" method="get"
					class="signup">
					<input type="submit" value="注册" class="s">
				</form>
			</c:when>
			<c:otherwise>
				<div class="user">${user.username}
					<div class="usermenu">
						<a href="/zbzd/userController/personal?curPage=1&pageSize=5">个人</a>
						<a href="/zbzd/userController/signOut">退出</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>