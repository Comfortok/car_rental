<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/custom.tld" %>


<!DOCTYPE html>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="header.title" bundle="${loc}"/></title>
    <head>
        <jsp:include page="../common/header.jsp"/>
    </head>
    <body>
    <center>
    <div class="card-deck">
                    <jsp:useBean id="availableCars" scope="request" type="java.util.List"/>
                    <c:forEach var="elem" items="${availableCars}">
                        <div class="mx-auto">
                        <div class="card text-white bg-dark mb-3 border-primary mb-3">
                            <input type="hidden" name="carModel" value="${elem.model}" />
                            <div class="card-header">
                                <h5><c:out value="${elem.model}"/></h5>
                            </div>
                            <img class="card-img-top" src="${pageContext.request.contextPath}/img/cars/id/1.jpg"
                                 width="239px" height="180px" alt="Card image cap">
                            <div class="card-body">
                                <input type="hidden" name="carCat" value="${elem.category}" />
                                <input type="hidden" name="carId" value="${elem.id}" />
                                <input type="hidden" name="carPrice" value="${elem.price}" />
                                <h5 class="card-title"><c:out value="${elem.category}"/></h5>
                                <p class="card-text">
                                    <c:out value="${elem.isAvailable}"/>
                                </p>
                                <p class="card-text">
                                    <c:out value="${elem.id}"/>
                                </p>
                                <p class="card-text">
                                    <c:out value="${elem.price}"/><fmt:message key="cars.currency" bundle="${loc}"/>
                                </p>

                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <a href="${pageContext.request.contextPath}/mainServlet?command=order"
                                           class="btn btn-primary">Order</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary">Order</a>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                        </div>
                        </div>
                    </c:forEach>
    </div>
    </center>
        <div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
    </body>
</html>