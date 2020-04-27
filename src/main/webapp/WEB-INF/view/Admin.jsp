<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Admin Page</title>
</head>

<body>
<center><h2>Admin's Home</h2></center>

Welcome
<%= request.getAttribute("userName") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/logout">Logout</a></div>
</body>
</html>