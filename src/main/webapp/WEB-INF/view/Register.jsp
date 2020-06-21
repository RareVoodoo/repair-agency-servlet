<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${sessionScope.currentLocale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.form.registration.title"/></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>


<div class="container-fluid">
    <nav class="navbar ">
        <a class="navbar-brand">Repair Service</a>
        <form class="form-inline">
            <a class="btn btn-primary mr-sm-2" href="?lang=en"><fmt:message key="lang.eng"/></a>
            <a class="btn btn-primary my-2 my-sm-0" href="?lang=ua"><fmt:message key="lang.ua"/></a>
        </form>
    </nav>

    <ul>
        <c:forEach items="${errors}" var="err" varStatus="idx">
            <li><fmt:message key= "${err}"/></li>

        </c:forEach>
    </ul>
    <form class="form-signin" action="/app/registerUser" method="post">
        <h2 class="form-signin-heading"><fmt:message key="label.form.registration.title"/></h2>


        <p>
            <label for="fullName" class="sr-only">Full Name</label>
            <input type="text" id="fullName" name="fullName" class="form-control"
                   placeholder="<fmt:message key="page.form.full.name"/>" required>
        </p>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder=
            <fmt:message key="page.form.username"/> required
                   autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control"
                   placeholder="<fmt:message key="page.form.password"/>" required>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message
                key="page.registration.form.submit"/></button>
        <input type="hidden" name="lang" value="en"/>

        <span class="text-center"><fmt:message key="page.form.already.registered.title"/></span>
        <span><a href="/app/login"><fmt:message key="page.form.login"/></a></span>
    </form>
</div>
</body>
</html>
