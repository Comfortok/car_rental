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
<div class="form-group">
    <form action="mainServlet" name="updateCarInfo" method="post">
        <div class="form-header">
            <h5 class="form-title">
                <fmt:message key="form.fill" bundle="${loc}"/></h5>
        </div>
        <div class="form-body">
            <input type="hidden" name="carId" value="${requestScope.carId}"/>
            <input type="hidden" name="mileage" value="${requestScope.mileage}"/>
            <input type="hidden" name="regNumber" value="${requestScope.regNumber}"/>
            <div class="form-group">
                <label for="carNumber"><fmt:message key="car.number" bundle="${loc}"/></label>
                <input class="form-control" id="carNumber" name="carNumber" type="text"
                       title="<fmt:message key="car.number.format" bundle="${loc}"/>"
                       placeholder="${requestScope.regNumber}" pattern="^[0-9]{3}(?:[A-Z]{3})?[0-9]{1,2}$">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="available">
                        <fmt:message key="car.available" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="available" name="available">
                    <option disabled><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <option value="Yes"><fmt:message key="form.yes" bundle="${loc}"/></option>
                    <option selected value="No"><fmt:message key="form.no" bundle="${loc}"/></option>
                </select>
            </div>
            <div class="form-group">
                <label for="carMileage"><fmt:message key="car.mileage" bundle="${loc}"/></label>
                <input class="form-control" id="carMileage" name="carMileage" type="number"
                placeholder="${requestScope.mileage}">
            </div>
        </div>
        <div class="modal-footer">
            <input type="hidden" name="command" value="updateCar"/>
            <input type="submit" class="btn btn-primary"
                   value="<fmt:message key="button.update" bundle="${loc}"/>"/>
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