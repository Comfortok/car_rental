<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
<head>
    <jsp:include page="../admin/headerAdmin.jsp"/>
</head>
<body>
<br>
<div class="form-group">
    <form action="mainServlet" name="changeCarImage" method="post">
        <div class="card">
            <div class="card-header">
                <fmt:message key="car.image" bundle="${loc}"/>
            </div>
            <div class="card-body">
                <input name="image" type="text" placeholder="<fmt:message key="car.image.input" bundle="${loc}"/>">
                <input type="hidden" name="command" value="changeCarImage"/>
                <input type="hidden" name="carId" value="${requestScope.carId}"/>
                <input type="submit" class="btn btn-primary"
                       value="<fmt:message key="button.update" bundle="${loc}"/>"/>
            </div>
        </div>
    </form>
</div>
<c:choose>
    <c:when test="${not empty requestScope.errorEmpty}">
        <div class="alert alert-primary" role="alert">
            <fmt:message key="error.empty" bundle="${loc}"/>
        </div>
    </c:when>
</c:choose>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>