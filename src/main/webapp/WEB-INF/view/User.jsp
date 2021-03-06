<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${sessionScope.currentLocale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><fmt:message key="page.user.cabinet"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>

<div>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">Repair Service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#"><fmt:message key="page.admin.request.table"/><span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="dropdown02" href="#" data-toggle="dropdown"
                           aria-haspopup="true"
                           aria-expanded="false"><fmt:message key="lang.label"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown02">
                            <a class="dropdown-item" href="?lang=en"><fmt:message key="lang.eng"/></a>
                            <a class="dropdown-item" href="?lang=ua"><fmt:message key="lang.ua"/></a>
                        </div>
                    <li class="nav-item dropdown">
                </ul>
                <a class="btn btn-primary btn-sm"
                   role="button"
                   href="/app/logout">
                    <fmt:message key="page.auth.logout"/></a>

            </form>

        </div>
    </nav>
</div>


<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow">
</div>


<main role="main" class="container">
    <div class="starter-template">
        <div>
        <a id="newRequest" class="btn btn-success btn-lg text-white" data-toggle="modal"
        data-target="#newRequestModal">
        <fmt:message key="page.new.request"/>
        </a>

        </div>

        <ul>
            <c:forEach items="${errors}" var="err" varStatus="idx">
                <li><fmt:message key= "${err}"/></li>
            </c:forEach>
        </ul>


        <h2><fmt:message key="page.user.my.executed.requests"/></h2>
        <div class="table-responsive">
            <table class="table">
                <thead class=" text-primary">
                <th scope="col">Id</th>
                <th scope="col"><fmt:message key="label.user.description"/></th>
                <th scope="col"><fmt:message key="label.user.repair.price"/></th>
                <th scope="col"><fmt:message key="label.admin.cancellation.reason"/></th>
                <th scope="col"><fmt:message key="label.user.add.comment"/></th>
                </thead>
                <tbody>
                <c:forEach items="${request}" var="req" varStatus="idx">
                    <tr>
                        <td>${req.id}</td>
                        <td>${req.description}</td>
                        <c:choose>
                            <c:when test="${language == 'en'}">
                                <td>${req.usdPrice}</td>
                            </c:when>
                            <c:when test="${language == 'ua'}">
                                <td>${req.uahPrice}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${req.usdPrice}</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${req.cancellationReason}</td>
                        <td><a id="addComment" class="btn btn-success btn-sm text-white" data-toggle="modal"
                               data-id="${req.id}"
                               data-target="#addCommentModal">
                            <fmt:message key="label.user.add.comment"/>
                        </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>


<!-- Modal -->
<div class="modal fade" id="addCommentModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalScrollableTitle"><fmt:message
                        key="label.user.comment"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="user/commentRequest" method="post">
                <div class="modal-body">
                    <input type="hidden" value="" id="requestId" name="requestId"/>
                    <input type="text" id="price" name="comment" class="form-control"
                           placeholder="<fmt:message
                        key="label.user.comment"/>" required
                           autofocus>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="label.button.cancel"/></button>
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.button.accept"/></button>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="newRequestModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalScrollableTitle2" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalScrollableTitle2"><fmt:message
                        key="page.new.request"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="user/createRequest" method="post">
                <div class="modal-body">
                    <p>
                    <input type="text" name="description" class="form-control"
                           placeholder="<fmt:message
                        key="label.user.description"/>" required
                           autofocus>
                    </p>

                    <p>
                    <input type="text" name="address" class="form-control"
                           placeholder="<fmt:message
                        key="page.user.address"/>" required
                           autofocus>
                    </p>

                    <p>
                    <input type="text" name="phoneNumber" class="form-control"
                           placeholder="<fmt:message
                        key="page.user.phone.number"/>" required
                           autofocus>

                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="label.button.cancel"/></button>
                    <button type="submit" class="btn btn-primary"><fmt:message key="label.button.accept"/></button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).on("click", "#addComment", function () {
        var id = $(this).data('id');
        $(".modal-body #requestId").val(id);
    });
</script>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>



</body>
</html>