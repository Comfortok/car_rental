<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <jsp:include page="../common/header.jsp"/>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="order.number" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.car" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.start" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.end" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.sum" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.status" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="order.extra" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="elem" items="${orders}">
        <tr>
            <th scope="row"><c:out value="${elem.id}"/></th>
            <td><c:out value="${elem.car}"/></td>
            <td><c:out value="${elem.startDate}"/></td>
            <td><c:out value="${elem.endDate}"/></td>
            <td><c:out value="${elem.paymentSum}"/></td>
            <td><c:out value="${elem.status}"/></td>
            <td>
                <div class="dropdown">
                    <form action="mainServlet" name="orderInfo" method="post">
                        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="button.more" bundle="${loc}"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <input type="hidden" name="orderId" value="${elem.id}"/>
                            <input type="hidden" name="paymentSum" value="${elem.paymentSum}"/>
                            <c:choose>
                                <c:when test="${elem.status eq 'Confirmed. Waiting for payment'}">
                                    <button type="submit" class="btn btn-link" name="command" value="orderPayForm">
                                        <fmt:message key="button.pay" bundle="${loc}"/>
                                    </button>
                                    <br>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" disabled>
                                        <fmt:message key="button.pay" bundle="${loc}"/>
                                    </button>
                                    <br>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${elem.status eq 'Formed. Waiting for confirmation'}">
                                    <button type="submit" name="command" value="orderDecline" class="btn btn-link">
                                        <fmt:message key="button.decline" bundle="${loc}"/>
                                    </button>
                                    <br>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" name="command" value="orderDecline" class="btn btn-link"
                                            disabled>
                                        <fmt:message key="button.decline" bundle="${loc}"/>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>