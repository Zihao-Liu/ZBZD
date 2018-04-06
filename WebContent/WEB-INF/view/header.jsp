<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
	<div class="navigation">
		<ul>
			<li id="logo">ZBZD</li>
			<li class="but">首页</li>
			<li class="but">话题</li>
		</ul>



	</div>
	<div class="searchbar">
		<form action="" method="post" class="searchbar">
			<input type="text" name="searchbar" placeholder="搜索你感兴趣的内容" /> <input
				type="submit" value="搜索" />
		</form>
	</div>
	<div class="userinfo">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<form action="/zbzd/userController/toSignIn" method="get"
					class="signin">
					<input type="submit" value="登录">
				</form>
				<form action="/zbzd/userController/toSignUp" method="get"
					class="signup">
					<input type="submit" value="注册">
				</form>
			</c:when>
			<c:otherwise>
                ${user.username}
                <form action="/zbzd/userController/signOut" method="get"
					class="signout">
					<input type="submit" value="退出" />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>