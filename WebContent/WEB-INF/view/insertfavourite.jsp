<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>创建收藏夹</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
    type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
    <%@include file="header.jsp"%>
    <div class="favouritebar">
        <form action="/zbzd/favouriteController/insertFavourite" method="post">
            <input type="text" name="name" placeholder="收藏夹标题" /> 
            <input type="submit" value="创建" class="sub" />
        </form>
    </div>

</body>

</html>