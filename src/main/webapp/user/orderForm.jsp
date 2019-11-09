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
    <jsp:include page="../common/header.jsp"/>
</head>
<body>
<jsp:useBean id="now" class="java.util.Date"/>
<div class="card text-center">
    <div class="card-header">
        <h5><c:out value="${param.model}"/></h5>
    </div>
    <div class="card-body">
        <h5 class="card-title"><c:out value="${param.startDate} - "/> <c:out value="${param.endDate}"/></h5>
        <p class="card-text"><fmt:message key="form.driver" bundle="${loc}"/></p>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
            <fmt:message key="button.open" bundle="${loc}"/>
        </button>
    </div>
    <div class="card-footer text-muted">
        <fmt:message key="form.order.extra" bundle="${loc}"/>
    </div>
</div>
<c:choose>
    <c:when test="${not empty requestScope.errorEmpty}">
        <div class="alert alert-primary" role="alert">
            <fmt:message key="error.empty" bundle="${loc}"/>
        </div>
    </c:when>
    <c:when test="${not empty requestScope.errorValidation}">
        <div class="alert alert-primary" role="alert">
            <fmt:message key="error.validation" bundle="${loc}"/>
        </div>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>
<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <form action="mainServlet" name="orderInfo" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">
                        <fmt:message key="form.fill" bundle="${loc}"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="carId" value="${param.carId}"/>
                    <input type="hidden" name="carPrice" value="${param.carPrice}"/>
                    <input type="hidden" name="model" value="${param.model}"/>
                    <input type="hidden" name="payment" value="${requestScope.payment}"/>
                    <input type="hidden" name="startDate" value="${param.startDate}"/>
                    <input type="hidden" name="endDate" value="${param.endDate}"/>
                    <div class="form-group">
                        <label for="name"><fmt:message key="driver.name" bundle="${loc}"/></label>
                        <input class="form-control" id="name" name="driverName" type="text"
                               pattern="^[A-ZА-Я]{1}[a-zа-яё]{1,25}">
                    </div>
                    <div class="form-group">
                        <label for="surname"><fmt:message key="driver.surname" bundle="${loc}"/></label>
                        <input class="form-control" id="surname" name="driverSurname" type="text"
                               pattern="^[A-ZА-Я]{1}[a-zа-яё]{1,25}">
                    </div>
                    <div class="form-group">
                        <label for="birthDate"><fmt:message key="driver.birth" bundle="${loc}"/></label>
                        <input class="form-control" id="birthDate" type="date"
                               name="driverBirth" pattern="yyyy-MM-dd">
                    </div>
                    <div class="form-group">
                        <label for="phone"><fmt:message key="driver.phone" bundle="${loc}"/></label>
                        <input class="form-control" id="phone" type="tel" name="driverPhone"
                               placeholder="77071234567" pattern="7[0-9]{10}">
                    </div>
                    <br>
                    <fmt:message key="passport.info" bundle="${loc}"/>
                    <div class="form-group">
                        <label for="passport"><fmt:message key="passport.number" bundle="${loc}"/></label>
                        <input class="form-control" id="passport" name="passportNumber" type="text"
                               placeholder="AB1234567" pattern="^([A-Z0-9]{2})[0-9]{7}">
                    </div>
                    <div class="form-group">
                        <label for="passportIssue"><fmt:message key="passport.issue" bundle="${loc}"/></label>
                        <input class="form-control" id="passportIssue" type="date"
                               name="passportIssue" pattern="yyyy-MM-dd">
                    </div>
                    <div class="form-group">
                        <label for="passportExpiry"><fmt:message key="passport.expiry" bundle="${loc}"/></label>
                        <input class="form-control" id="passportExpiry" type="date"
                               name="passportExpiry" pattern="yyyy-MM-dd">
                    </div>
                    <div class="form-group">
                        <label for="passportAuthority"><fmt:message key="passport.authority" bundle="${loc}"/></label>
                        <input class="form-control" id="passportAuthority" name="passportAuthority"
                               type="text" pattern="^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ\s]*$">
                    </div>
                    <br>
                    <fmt:message key="licence.info" bundle="${loc}"/>
                    <div class="form-group">
                        <label for="licence"><fmt:message key="licence.number" bundle="${loc}"/></label>
                        <input class="form-control" id="licence" name="licenceNumber" type="text"
                               placeholder="AB1234567" pattern="^([A-Z0-9]{2})[0-9]{7}">
                    </div>
                    <div class="form-group">
                        <label for="licenceIssue"><fmt:message key="licence.issue" bundle="${loc}"/></label>
                        <input class="form-control" id="licenceIssue" type="date"
                               name="licenceIssue" pattern="yyyy-MM-dd">
                    </div>
                    <div class="form-group">
                        <label for="licenceExpiry"><fmt:message key="licence.expiry" bundle="${loc}"/></label>
                        <input class="form-control" id="licenceExpiry" type="date"
                               name="licenceExpiry" pattern="yyyy-MM-dd">
                    </div>
                    <div class="form-group">
                        <label for="licenceAuthority"><fmt:message key="licence.authority" bundle="${loc}"/></label>
                        <input class="form-control" id="licenceAuthority" type="text"
                               name="licenceAuthority" pattern="^[a-zA-Zа-яА-ЯёЁ][a-zA-Zа-яА-ЯёЁ\s]*$">
                    </div>
                    <div class="form-group">
                        <label for="licenceCategory"><fmt:message key="licence.category" bundle="${loc}"/></label>
                        <input class="form-control" id="licenceCategory" type="text"
                               name="licenceCategory" pattern="^([A-Z]{1,10})$">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="button.close" bundle="${loc}"/></button>
                    <input type="hidden" name="command" value="orderCar"/>
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="button.submit" bundle="${loc}"/>"/>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>