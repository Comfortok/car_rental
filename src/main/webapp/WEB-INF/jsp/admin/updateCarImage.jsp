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
<div class="form-group">
    <form action="imageServlet" method="post" enctype="multipart/form-data">
        <div class="card">
            <div class="card-header">
                <fmt:message key="car.image" bundle="${loc}"/>
            </div>
            <div class="card-body">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><fmt:message key="input.upload" bundle="${loc}"/></span>
                    </div>
                    <div class="custom-file">
                        <input type="file" name="image" class="custom-file-input" id="image" required>
                        <label class="custom-file-label" for="image">
                            <fmt:message key="car.image.choose" bundle="${loc}"/></label>

                    </div>
                </div>
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