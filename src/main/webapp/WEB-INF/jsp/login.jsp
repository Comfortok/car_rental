<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="title" var="loc"/>
    <c:import url="common/header.jsp" charEncoding="utf-8"/>
</head>
<body>
<center>
    <div class="card-text-center">
        <div class="card-header">
            <fmt:message key="login.form" bundle="${loc}"/>
        </div>
        <div class="card-body">
            <form name="form-login" method="post" action="mainServlet">
                <div class="form-group">
                    <label for="exampleInputEmail1"><fmt:message key="login.email" bundle="${loc}"/></label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1"><fmt:message key="login.password" bundle="${loc}"/></label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
                </div>
                <c:choose>
                    <c:when test="${not empty requestScope.errorEmpty}">
                        <div class="alert alert-primary" role="alert">
                            <fmt:message key="error.empty" bundle="${loc}"/>
                        </div>
                    </c:when>
                    <c:when test="${not empty requestScope.errorLoginData}">
                        <div class="alert alert-primary" role="alert">
                            <fmt:message key="error.login" bundle="${loc}"/>
                        </div>
                    </c:when>
                </c:choose>
                <input type="hidden" name="command" value="login"/>
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="button.signin" bundle="${loc}"/></button>
                <div class='notice'><a
                        href='mailto:123@gmail.com?Subject=<fmt:message key="login.forgot" bundle="${loc}"/>'
                        target="_top"><fmt:message key="login.forgot" bundle="${loc}"/></a></div>
            </form>
        </div>
    </div>
</center>
<br><br><br><br>
<div class="footer">
    <c:import url="common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>