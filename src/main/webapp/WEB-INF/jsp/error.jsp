<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <jsp:useBean id="roleId" scope="session" type="java.lang.Long"/>
            <c:choose>
                <c:when test="${roleId eq 1}">
                    <jsp:include page="common/header.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="admin/headerAdmin.jsp"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <jsp:include page="common/header.jsp"/>
        </c:otherwise>
    </c:choose>
</head>
<body>
<br>
<h2><fmt:message key="page.error" bundle="${loc}"/></h2>
<c:choose>
    <c:when test="${not empty requestScope.errorFormat}">
        <div class="alert">
                <fmt:message key="page.error.format" bundle="${loc}"/>
        </div>
    </c:when>
</c:choose>
<div class="footer"><c:import url="common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>