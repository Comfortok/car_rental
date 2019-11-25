<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <jsp:include page="headerAdmin.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
</head>
<body>
<div class="form-group">
    <form action="mainServlet" name="addCarInfo" method="post">
        <input type="hidden" name="command" value="addCar"/>
        <div class="form-header">
            <h5 class="form-title">
                <fmt:message key="form.fill" bundle="${loc}"/>
            </h5>
        </div>
        <div class="form-body">
            <c:choose>
                <c:when test="${not empty requestScope.errorEmpty}">
                    <div class="alert alert-primary" role="alert">
                        <small id="errorValidation" class="form-text text-muted"><c:out
                                value="${requestScope.errorEmpty}"/></small>
                    </div>
                </c:when>
            </c:choose>
            <div class="form-group">
                <label for="regNumber"><fmt:message key="car.number" bundle="${loc}"/></label>
                <input class="form-control" id="regNumber" name="regNumber" type="text" placeholder="123ABC09"
                       pattern="^[0-9]{3}(?:[A-Z]{3})?[0-9]{1,2}$" required
                       title="<fmt:message key="car.number.format" bundle="${loc}"/>">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="brand"><fmt:message key="car.brand" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="brand" name="brand" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="brandList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${brandList}">
                        <option value="${elem}"><c:out value="${elem}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="model"><fmt:message key="car.model" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="model" name="model" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="modelList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${modelList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="color"><fmt:message key="car.color" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="color" name="color" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="colorList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${colorList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="category">
                        <fmt:message key="car.category" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="category" name="category" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="categoryList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${categoryList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="transmission">
                        <fmt:message key="car.transmission" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="transmission" name="transmission" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="transmissionList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${transmissionList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="body"><fmt:message key="car.body" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="body" name="body" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="bodyList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${bodyList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="engine"><fmt:message key="car.engine" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="engine" name="engine" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <jsp:useBean id="engineList" scope="request" type="java.util.Set"/>
                    <c:forEach var="elem" items="${engineList}">
                        <option value="${elem}"><c:out value="${elem}"/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="airConditioner">
                        <fmt:message key="car.ac" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="airConditioner" name="airConditioner" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <option value="Yes"><fmt:message key="form.yes" bundle="${loc}"/></option>
                    <option value="No"><fmt:message key="form.no" bundle="${loc}"/></option>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="available">
                        <fmt:message key="car.available" bundle="${loc}"/></label>
                </div>
                <select class="custom-select" id="available" name="available" required>
                    <option selected disabled value=""><fmt:message key="form.choose" bundle="${loc}"/></option>
                    <option value="Yes"><fmt:message key="form.yes" bundle="${loc}"/></option>
                    <option value="No"><fmt:message key="form.no" bundle="${loc}"/></option>
                </select>
            </div>
            <div class="form-group">
                <label for="engineVolume"><fmt:message key="car.engine.volume" bundle="${loc}"/></label>
                <input class="form-control" id="engineVolume" name="engineVolume" type="number" placeholder="1.8"
                       min="0.5" max="10" step="0.1" title="<fmt:message key="car.engine.volume.format"
                       bundle="${loc}"/>" required>
            </div>
            <div class="form-group">
                <label for="baggage"><fmt:message key="car.baggage" bundle="${loc}"/></label>
                <input class="form-control" id="baggage" name="baggage" placeholder="3"
                       title="<fmt:message key="car.baggage.format" bundle="${loc}"/>"
                       type="number" min="1" max="10" step="1" required>
            </div>
            <div class="form-group">
                <label for="seat"><fmt:message key="car.seat" bundle="${loc}"/></label>
                <input class="form-control" id="seat" name="seat" type="number" min="2" max="20" step="1"
                       title="<fmt:message key="car.seat.format" bundle="${loc}"/>" placeholder="4" required>
            </div>
            <div class="form-group">
                <label for="fuelConsumption"><fmt:message key="car.fuel.consumption" bundle="${loc}"/></label>
                <input class="form-control" id="fuelConsumption" name="fuelConsumption" type="number" required
                       min="1" max="50" step="0.5" placeholder="8.5"
                       title="<fmt:message key="car.fuel.consumption.format" bundle="${loc}"/>">
            </div>
            <div class="form-group">
                <label for="door"><fmt:message key="car.door" bundle="${loc}"/></label>
                <input class="form-control" id="door" name="door" type="number" min="2" placeholder="4"
                       max="5" step="1" title="<fmt:message key="car.door.format" bundle="${loc}"/>" required>
            </div>
            <div class="form-group">
                <label for="year"><fmt:message key="car.year" bundle="${loc}"/></label>
                <input class="form-control" id="year" name="year" type="number" min="1900" placeholder="2019"
                       step="1" max="2020" title="<fmt:message key="car.year.format" bundle="${loc}"/>" required>
            </div>
            <div class="form-group">
                <label for="mileage"><fmt:message key="car.mileage" bundle="${loc}"/></label>
                <input class="form-control" id="mileage" name="mileage" type="number" min="1" placeholder="100"
                       step="1" title="<fmt:message key="car.mileage.format" bundle="${loc}"/>" required>
            </div>
            <div class="form-group">
                <label for="carImage"><fmt:message key="car.image" bundle="${loc}"/></label>
                <input class="form-control" id="carImage" name="image" type="text"
                       placeholder="<fmt:message key="image.folder" bundle="${loc}"/>">
            </div>
        </div>
        <div class="modal-footer">
            <input type="submit" class="btn btn-primary"
                   value="<fmt:message key="button.add" bundle="${loc}"/>"/>
        </div>
    </form>
</div>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>