<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>


<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="header.title" bundle="${loc}"/></title>
<head>
    <jsp:include page="../common/header.jsp"/>
</head>
<body>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Car</th>
        <th scope="col">Start</th>
        <th scope="col">End</th>
        <th scope="col">Sum</th>
        <th scope="col">Status</th>
        <th scope="col">Status2</th>
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
                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    More...
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <input type="hidden" name="orderId" value="${elem.id}"/>
                    <c:set var="orderId" value="${elem.id}" scope="page" />
                    <input type="hidden" name="paymentSum" value="${elem.paymentSum}"/>
                    <c:set var="paymentSum" value="${elem.paymentSum}" scope="page" />
                    <c:choose>
                    <c:when test="${elem.status eq 'Confirmed. Waiting for payment'}">
                        <button type="button" data-toggle="modal" data-target=".bd-payment-modal-sm" class="btn btn-link">Pay</button><br>
                    </c:when>
                        <c:otherwise>
                            <button type="button" data-toggle="modal" data-target=".bd-payment-modal-sm" class="btn btn-link" disabled>Pay</button><br>
                        </c:otherwise>
                    </c:choose>
                    <button type="submit" name="command" value="orderDecline" class="btn btn-link">Decline</button>
                </div>
            </div>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<div class="modal fade bd-payment-modal-sm" tabindex="-1" role="dialog" aria-labelledby="paymentModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <form action="mainServlet" name="orderInfo" method="post">
            <div class="modal-header">
                <h5 class="modal-title" id="paymentModalLabel">TITLE</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>
                    Enter the sum: <input type="number" name="actualSum">
                </p>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="orderId" value="${pageScope.orderId}"/>
                <input type="hidden" name="paymentSum" value="${pageScope.paymentSum}"/>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" name="command" value="payAnOrder" class="btn btn-primary">Pay</button>
            </div>
            </form>
        </div>
    </div>
</div>

<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>