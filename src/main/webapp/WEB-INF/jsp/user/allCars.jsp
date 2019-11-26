<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="availableCars" scope="request" type="java.util.List"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <jsp:useBean id="roleId" scope="session" type="java.lang.Long"/>
            <c:choose>
                <c:when test="${roleId eq 1}">
                    <jsp:include page="../common/header.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="../admin/headerAdmin.jsp"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <jsp:include page="../common/header.jsp"/>
        </c:otherwise>
    </c:choose>
</head>
<body>
<br>
<c:choose>
    <c:when test="${not empty requestScope.errorFormat}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
            <fmt:message key="page.error.format" bundle="${loc}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:when>
</c:choose>
<center>
    <div class="card-group">
        <c:forEach var="elem" items="${availableCars}">
            <div class="mx-auto">
            <div class="card-col">
                <div class="card text-white bg-dark mb-3 border-primary mb-3">
                    <form name="order" method="post" action="mainServlet" class="order">
                        <input type="hidden" name="model" value="${elem.model}"/>
                        <div class="card-header">
                            <h5><c:out value="${elem.model}"/></h5>
                        </div>
                        <img class="card-img-top" src="${elem.imageName}"
                             alt="Card image cap">
                        <div class="card-body">
                            <input type="hidden" name="startDate" value="${requestScope.startDate}"/>
                            <input type="hidden" name="endDate" value="${requestScope.endDate}"/>
                            <input type="hidden" name="period" value="${requestScope.period}"/>
                            <input type="hidden" name="carCat" value="${elem.category}"/>
                            <input type="hidden" name="carId" value="${elem.id}"/>
                            <input type="hidden" name="carPrice" value="${elem.price}"/>
                            <input type="hidden" name="mileage" value="${elem.mileage}"/>
                            <input type="hidden" name="regNumber" value="${elem.registeredNumber}"/>
                            <h5 class="card-title"><c:out value="${elem.category}"/></h5>
                            <div class="content" style="text-align: left">
                                <p class="card-text">
                                    <fmt:message key="car.transmission" bundle="${loc}"/>
                                    <c:out value=": ${elem.transmission}"/>
                                </p>
                                <p class="card-text">
                                    <fmt:message key="car.engine" bundle="${loc}"/>
                                    <c:out value=": ${elem.engineType}"/>
                                </p>
                                <p class="card-text">
                                    <fmt:message key="car.year" bundle="${loc}"/>
                                    <c:out value=": ${elem.productionYear}"/>
                                </p>
                                <p class="card-text">
                                    <fmt:message key="car.price" bundle="${loc}"/>
                                    <c:out value=": ${elem.price} "/><fmt:message key="price.currency" bundle="${loc}"/>
                                </p>
                            </div>
                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    <c:choose>
                                        <c:when test="${roleId eq 1}">
                                            <c:if test="${not empty param.startDate}">
                                                <input type="hidden" name="command" value="orderForm"/>
                                                <br>
                                                <input type="submit" class="btn btn-primary"
                                                       value="<fmt:message key="button.order" bundle="${loc}"/>"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <br/>
                                            <div class="dropdown">
                                                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <fmt:message key="button.update" bundle="${loc}"/>
                                                </button>
                                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                    <button type="submit" name="command" value="updateCarImageForm"
                                                            class="btn btn-link">
                                                        <fmt:message key="button.image.change" bundle="${loc}"/></button><br/>
                                                    <button type="submit" name="command" value="updateCarForm"
                                                            class="btn btn-link">
                                                        <fmt:message key="button.update.car" bundle="${loc}"/></button><br/>
                                                    <button type="submit" name="command" value="showCategoryPrice"
                                                            class="btn btn-link">
                                                        <fmt:message key="button.update.price" bundle="${loc}"/></button>
                                                </div>
                                            </div>
                                            <br>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <br>
                                    <a href="${pageContext.request.contextPath}/mainServlet?command=showSignInPage"
                                       class="btn btn-primary"><fmt:message key="button.signin" bundle="${loc}"/></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </div>
            </div>
        </c:forEach>
    </div>
</center>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>