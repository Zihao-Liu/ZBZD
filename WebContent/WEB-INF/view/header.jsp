<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
	<div class="navigation">
		<ul>
			<li id="logo">ZBZD</li>
			<li class="fun"><a href="/zbzd">��ҳ</a></li>
			<li class="fun"><a href="/zbzd/tagController/topTag">����</a></li>
		</ul>



	</div>
	<div class="searchbar">
		<form action="" method="post" class="searchbar">
			<input type="text" name="search" placeholder="���������Ȥ������" /> <input
				type="submit" value="����" class="searchbutton" />
		</form>
		<input type="button" value="����" class="question"
			onclick="window.location.href='/zbzd/questionController/toPostQuestion'" />
	</div>
	<div class="userinfo">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<form action="/zbzd/userController/toSignIn" method="get"
					class="signin">
					<input type="submit" value="��¼" class="s">
				</form>
				<form action="/zbzd/userController/toSignUp" method="get"
					class="signup">
					<input type="submit" value="ע��" class="s">
				</form>
			</c:when>
			<c:otherwise>
				<div class="user">${user.username}
					<div class="usermenu">
						<a href="/zbzd/userController/personal?curPage=1&pageSize=5">����</a>
						<a href="/zbzd/userController/signOut">�˳�</a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>