<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="title" var="loc"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><fmt:message key="header.title" bundle="${loc}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a href="#" class="navbar-brand">
        <img src="${pageContext.request.contextPath}/img/logo.png" width="30px" height="30px" alt="logo">
    </a>
    <button type="button" class="btn btn-link" data-toggle="modal" data-target=".bd-example-modal-sm">
        <fmt:message key="header.language" bundle="${loc}"/>
    </button>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="navitem">
                <a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">
                    <fmt:message key="header.home" bundle="${loc}"/>
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="navitem">
                <a class="nav-link" href="${pageContext.request.contextPath}/mainServlet?command=allCars">
                    <fmt:message key="header.park" bundle="${loc}"/></a>
            </li>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li class="navitem">
                        <a class="nav-link" href="${pageContext.request.contextPath}/mainServlet?command=logout">
                            <fmt:message key="header.logout" bundle="${loc}"/></a>
                    </li>
                    <li class="navitem">
                        <a class="nav-link" href="${pageContext.request.contextPath}/mainServlet?command=userOrders">
                            <fmt:message key="header.user.orders" bundle="${loc}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="navitem">
                        <a class="nav-link" href="login.jsp"><fmt:message key="header.signin" bundle="${loc}"/></a>
                    </li>
                    <li class="navitem">
                        <a class="nav-link" href="register.jsp"><fmt:message key="header.signup" bundle="${loc}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="header.language" bundle="${loc}"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container" align="center">
                    <table>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/mainServlet?command=RU"
                                   class="rounded mx-auto d-block">
                                    <img src="${pageContext.request.contextPath}/img/russia-flag-icon.png" width="72"
                                         height="72" alt="rf">
                                </a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/mainServlet?command=EN"
                                   class="rounded mx-auto d-block">
                                    <img src="${pageContext.request.contextPath}/img/uk-flag-icon.png" width="72"
                                         height="72" alt="uk">
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>