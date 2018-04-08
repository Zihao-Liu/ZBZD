<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>首页</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath() %>/css/header.css" type="text/css" rel="stylesheet" media="all" charset="utf-8" />
<link href="<%=request.getContextPath() %>/css/question.css" type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
    <%@include file="header.jsp" %>
    <div class="questioncontent">
        <p>来自话题:${tagName}</p>
        <h1>${question.title}</h1>
        <p>${question.content}</p>
        <input type="button" value="关注问题">
        <p class="attachment">发布于：${question.createTime} &nbsp;
        <c:choose>
            <c:when test="${1==question.isAnonymous}">
                                                     匿名发布
                <c:if test="${poster.id==sessionScope.user.id}">
                    <input type="button" value="取消匿名" class="ano"/>
                </c:if>
            </c:when>
            <c:otherwise>
               <a href="#">提问人:${poster.username}</a>&nbsp;
                <c:if test="${poster.id==sessionScope.user.id}">
                    <input type="button" value="匿名" class="ano"/>
                </c:if>          
            </c:otherwise>
        </c:choose>
        
        </p>
    </div>

</body>

</html>