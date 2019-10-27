<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/custom.tld" %>


<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="header.title" bundle="${loc}"/></title>
<head>
    <jsp:include page="../admin/header_admin.jsp"/>
</head>
<body>
<div class="modal-body">
    <ul class="list-group">
        <li class="list-group-item">
            <c:out value="${requestScope.driverName}"/>
            <c:out value="${requestScope.driverSurname}"/>
            <c:out value="${requestScope.driverBirth}"/>
            <c:out value="${requestScope.driverPhone}"/>
            <c:out value="${requestScope.passportNumber}"/>
            <c:out value="${requestScope.passportIssue}"/>
            <c:out value="${requestScope.passportExpiry}"/>
            <c:out value="${requestScope.passportAuthority}"/>
            <c:out value="${requestScope.licenceNumber}"/>
            <c:out value="${requestScope.licenceIssue}"/>
            <c:out value="${requestScope.licenceExpiry}"/>
            <c:out value="${requestScope.licenceAuthority}"/>
            <c:out value="${requestScope.licenceCategory}"/>
        </li>
    </ul>
</div>


<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>