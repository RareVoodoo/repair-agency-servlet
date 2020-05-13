<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Page</title>
</head>

<body>
<center><h2>User's Home</h2></center>
Welcome <%=request.getAttribute("userName") %>

<form action="/user/newRequest" method="post">

    Address:<input type="text" name="address"/><br/><br/>
    phone number:<input type="password" name="phoneNumber"/><br/><br/>
    Description:<input type="text" name="description"/><br/><br/>

    <br/><br/>
    <input type="submit" value="submit"/>

</form>

<table>
    <tr>
        <th>name</th>
        <th>desc</th>
        <th>contact<th>
    </tr>

    <c:forEach items="${request}" var="req" varStatus="idx">
        <tr>
            <td >${req.description}/></td>
            <td>${req.cancellationReason}</td>
            <td>${req.usdPrice}</td>
            <td>
                <form action="<c:url value="user/delete"/>" >
                    <input type="hidden" name="id" value="${req.id}"/>
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>


</table>

<div style="text-align: right"><a href="<c:url value="/logout"/>">Logout</a></div>

</body>
</html>