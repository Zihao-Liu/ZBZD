<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
	<div class="navigation">
		<ul>
			<li id="logo">ZBZD</li>
			<li class="but">��ҳ</li>
			<li class="but">����</li>
		</ul>



	</div>
	<div class="searchbar">
		<form action="" method="post" class="searchbar">
			<input type="text" name="searchbar" placeholder="���������Ȥ������" /> <input
				type="submit" value="����" />
		</form>
	</div>
	<div class="userinfo">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<form action="/zbzd/userController/toSignIn" method="get"
					class="signin">
					<input type="submit" value="��¼">
				</form>
				<form action="/zbzd/userController/toSignUp" method="get"
					class="signup">
					<input type="submit" value="ע��">
				</form>
			</c:when>
			<c:otherwise>
                ${user.username}
                <form action="/zbzd/userController/signOut" method="get"
					class="signout">
					<input type="submit" value="�˳�" />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>