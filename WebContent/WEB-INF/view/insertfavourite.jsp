<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<title>�����ղؼ�</title>
<meta http-equiv="Content-Type" content="text/html charset=gb2312">

<link href="<%=request.getContextPath()%>/css/header.css"
    type="text/css" rel="stylesheet" media="all" charset="utf-8" />
</head>

<body>
    <%@include file="header.jsp"%>
    <div class="favouritebar">
        <form action="/zbzd/favouriteController/insertFavourite" method="post">
            <input type="text" name="name" placeholder="�ղؼб���" /> 
            <input type="submit" value="����" class="sub" />
        </form>
    </div>

</body>

</html>