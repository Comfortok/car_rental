<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="title" var="loc"/>
    <c:import url="common/header.jsp" charEncoding="utf-8"/>
</head>
<body>
<center>
    <div class="card-text-center">
        <div class="card-header">
            <fmt:message key="register.form" bundle="${loc}"/>
        </div>
        <div class="card-body">
            <form name="form-login" method="post" action="mainServlet">
                <div class="form-group">
                    <label for="email"><fmt:message key="login.email" bundle="${loc}"/></label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                           required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="login.password" bundle="${loc}"/></label>
                    <input type="password" class="form-control" id="password" name="password"
                           pattern="^[a-zA-Z0-9_-]{4,16}$" required>
                    <small id="passwordHelp" class="form-text text-muted">
						<fmt:message key="register.help" bundle="${loc}"/></small>
                </div>
                <div class="form-group">
                    <label for="password2"><fmt:message key="register.password.confirm" bundle="${loc}"/></label>
                    <input type="password" class="form-control" id="password2" name="passwordRepeat"
                           required>
                </div>
                <input type="hidden" name="command" value="register"/>
                <button type="submit" class="btn btn-primary">
					<fmt:message key="button.signup" bundle="${loc}"/></button>
            </form>
            <c:choose>
                <c:when test="${not empty requestScope.emailError}">
                    <div class="alert">
                        <small id="errorEmail" class="form-text text-muted"><c:out value="${requestScope.errorEmail}"/></small>
                    </div>
                </c:when>
                <c:when test="${not empty requestScope.validationError}">
                    <small id="errorValidation" class="form-text text-muted"><c:out value="${requestScope.validationError}"/></small>
                </c:when>
                <c:when test="${not empty requestScope.errorConfirm}">
                    <small id="errorConfirm" class="form-text text-muted"><c:out value="${requestScope.errorConfirm}"/></small>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</center>
<br>
<div class="footer"><c:import url="common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>