<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List"/>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
<head>
    <jsp:include page="headerAdmin.jsp"/>
</head>
<body>
<div class="page">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="order.number" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.car" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.start" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.end" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.sum" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.status" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="order.driver" bundle="${loc}"/></th>
        </tr>
        </thead>
        <tbody>
        <div class="container">

                <c:forEach var="elem" items="${orders}">
                    <tr>
                        <th scope="row"><c:out value="${elem.id}"/></th>
                        <td><c:out value="${elem.car}"/></td>
                        <td><c:out value="${elem.startDate}"/></td>
                        <td><c:out value="${elem.endDate}"/></td>
                        <td><c:out value="${elem.paymentSum}"/></td>
                        <td><c:out value="${elem.status}"/></td>
                        <td><form name="orderInfo" action="mainServlet" method="post">
                                    <button type="submit" name="command" value="orderDetails" class="btn btn-link">
                                        <input type="hidden" name="driver" value="${elem.driver}"/>
                                        <input type="hidden" name="orderId" value="${elem.id}"/>
                                        <input type="hidden" name="statusId" value="${elem.status}"/>
                                        <fmt:message key="button.details" bundle="${loc}"/>
                                    </button>
                        </form>
                        </td>
                    </tr>
                </c:forEach>
        </div>
        </tbody>
    </table>
</div>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>