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
    <jsp:include page="../admin/header_admin.jsp"/>
</head>
<body>

<div class="page">

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Car</th>
        <th scope="col">Start</th>
        <th scope="col">End</th>
        <th scope="col">Sum</th>
        <th scope="col">Status</th>
        <th scope="col">Driver</th>
    </tr>
    </thead>
    <tbody>

    <div class="container">
        <form name="orderInfo" action="mainServlet" method="post">
    <c:forEach var="elem" items="${orders}">

        <tr>
            <th scope="row"><c:out value="${elem.id}"/></th>
            <td><c:out value="${elem.car}"/></td>
            <td><c:out value="${elem.startDate}"/></td>
            <td><c:out value="${elem.endDate}"/></td>
            <td><c:out value="${elem.paymentSum}"/></td>
            <td><c:out value="${elem.status}"/></td>
            <td>
            <input type="hidden" name="driver" value="${elem.driver}"/>
            <input type="hidden" name="orderId" value="${elem.id}"/>
        <%--    <input type="hidden" name="command" value="orderDetails" />--%>

                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        More...
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <button type="submit" name="command" value="orderDetails" class="btn btn-link">Details</button><br>
                        <button type="submit" name="command" value="orderConfirm" class="btn btn-link">Confirm</button><br>
                        <button type="submit" name="command" value="orderDecline" class="btn btn-link">Decline</button>
                    </div>
                </div>

            <%--<button type="submit" class="btn btn-secondary btn-sm">Details</button>
                FOOOOOORM
                <a class="nav-link" href="${pageContext.request.contextPath}/mainServlet?command=orderConfirm">
                    Confirm</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/mainServlet?command=orderDecline">
                    Decline</a>--%>
            </td>
        </tr>
    </c:forEach>
        </form>
    </div>

    </tbody>
</table>
</div>

<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>