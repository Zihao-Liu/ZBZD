<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>��ҳ</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath() %>/css/header.css" type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath() %>/css/question.css" type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
    <%@include file="header.jsp" %>
    <div class="questioncontent">
        <p>���Ի���:${tagName}</p>
        <h1>${question.title}</h1>
        <p>${question.content}</p>
        <input type="button" value="��ע����">
        <p class="attachment">�����ڣ�${question.createTime} &nbsp;
        <c:choose>
            <c:when test="${1==question.isAnonymous}">
                                                     ��������
                <c:if test="${poster.id==sessionScope.user.id}">
                    <input type="button" value="ȡ������" class="ano"/>
                </c:if>
            </c:when>
            <c:otherwise>
               <a href="#">������:${poster.username}</a>&nbsp;
                <c:if test="${poster.id==sessionScope.user.id}">
                    <input type="button" value="����" class="ano"/>
                </c:if>          
            </c:otherwise>
        </c:choose>
        
        </p>
    </div>

</body>

</html>