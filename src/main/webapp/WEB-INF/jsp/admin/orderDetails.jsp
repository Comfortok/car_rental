<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <jsp:include page="headerAdmin.jsp"/>
</head>
<body>
<br>
<form action="mainServlet" name="orderDetailsInfo" method="post">
    <div class="btn-group" role="group" aria-label="Third group">
        <button type="submit" class="btn btn-secondary" name="command" value="showAllOrders">
            <fmt:message key="button.back" bundle="${loc}"/>
        </button>
        <input type="hidden" name="orderId" value="${requestScope.orderId}">
        <jsp:useBean id="statusId" scope="request" type="java.lang.String"/>
        <c:choose>
            <c:when test="${statusId eq 'Formed. Waiting for confirmation'}">
                <div class="btn-group" role="group" aria-label="First group">
                    <button type="submit" name="command" value="orderConfirm" class="btn btn-success">
                        <fmt:message key="button.confirm" bundle="${loc}"/>
                    </button>
                </div>
                <div class="btn-group" role="group" aria-label="Second group">
                    <button type="submit" name="command" value="orderDecline" class="btn btn-danger">
                        <fmt:message key="button.decline" bundle="${loc}"/>
                    </button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="btn-group" role="group" aria-label="Third group">
                    <button type="submit" name="command" value="orderArchive" class="btn btn-warning">
                        <fmt:message key="button.archive" bundle="${loc}"/>
                    </button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</form>
<center>
    <div class="card-group">
        <div class="card text-dark bg-light" style="text-align: left; width: 18rem">
            <div class="card-header">
                <fmt:message key="order.driver" bundle="${loc}"/>
            </div>
            <div class="card-body">
                <p class="card-text">
                    <fmt:message key="driver.name" bundle="${loc}"/>
                    <c:out value=": ${requestScope.driverName}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="driver.surname" bundle="${loc}"/>
                    <c:out value=": ${requestScope.driverSurname}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="driver.birth" bundle="${loc}"/>
                    <c:out value=": ${requestScope.driverBirth}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="driver.phone" bundle="${loc}"/>
                    <c:out value=": ${requestScope.driverPhone}"/>
                </p><br><br>
            </div>
        </div>
        <div class="card text-dark bg-light" style="text-align: left; width: 18rem">
            <div class="card-header">
                <fmt:message key="passport.info" bundle="${loc}"/>
            </div>
            <div class="card-body">
                <p class="card-text">
                    <fmt:message key="passport.number" bundle="${loc}"/>
                    <c:out value=": ${requestScope.passportNumber}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="passport.issue" bundle="${loc}"/>
                    <c:out value=": ${requestScope.passportIssue}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="passport.expiry" bundle="${loc}"/>
                    <c:out value=": ${requestScope.passportExpiry}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="passport.authority" bundle="${loc}"/>
                    <c:out value=": ${requestScope.passportAuthority}"/>
                </p><br>
            </div>
        </div>
        <div class="card text-dark bg-light" style="text-align: left; width: 18rem">
            <div class="card-header">
                <fmt:message key="licence.info" bundle="${loc}"/>
            </div>
            <div class="card-body">
                <p class="card-text">
                    <fmt:message key="licence.number" bundle="${loc}"/>
                    <c:out value=": ${requestScope.licenceNumber}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="licence.issue" bundle="${loc}"/>
                    <c:out value=": ${requestScope.licenceIssue}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="licence.expiry" bundle="${loc}"/>
                    <c:out value=": ${requestScope.licenceExpiry}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="licence.authority" bundle="${loc}"/>
                    <c:out value=": ${requestScope.licenceAuthority}"/>
                </p>
                <p class="card-text">
                    <fmt:message key="licence.category" bundle="${loc}"/>
                    <c:out value=": ${requestScope.licenceCategory}"/>
                </p>
            </div>
        </div>
    </div>
</center>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>