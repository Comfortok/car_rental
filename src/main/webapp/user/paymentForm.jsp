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
<br>
<div class="mx-auto">
    <div class="row">
        <div class="col-sm-6">
            <div class="card">
                <div class="card-header">
                    <fmt:message key="form.payment" bundle="${loc}"/>
                </div>
                <div class="card-body">
                    <form action="mainServlet" name="orderInfo" method="post">
                        <p>
                            <fmt:message key="form.payment.input" bundle="${loc}"/>
                            <input type="text" name="actualSum" pattern="[0-9]{4,}">
                        </p>
                        <input type="hidden" name="orderId" value="${requestScope.orderId}"/>
                        <input type="hidden" name="paymentSum" value="${requestScope.paymentSum}"/>
                        <button type="submit" name="command" value="orderPayment" class="btn btn-primary">
                            <fmt:message key="button.pay" bundle="${loc}"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:choose>
    <c:when test="${not empty requestScope.wrongSum}">
        <div class="alert alert-primary" role="alert">
            <fmt:message key="error.wrong.sum" bundle="${loc}"/>
        </div>
    </c:when>
</c:choose>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>