<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/custom.tld" %>

<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="header.title" bundle="${loc}"/></title>
<head>
    <jsp:include page="../common/header.jsp"/>
</head>
<body>
<jsp:useBean id="now" class="java.util.Date"/>


<div class="card text-center">
    <div class="card-header">
        ${sessionScope.carModel}
    </div>
    <div class="card-body">
        <input type="date" name="pickUpDate" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" required  max="2020-12-31"
               min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
        <input type="date" name="dropOffDate" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" required  max="2020-12-31"
               min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
    <div class="card-footer text-muted">
        2 days ago
    </div>
</div>


<%--<div class="page-wrapper">

    <div class="page-buffer">
        <div class="header">
            <c:import url='../common/header.jsp' charEncoding="utf-8"/>
        </div>
        <div id="menu">
            <c:import url="../common/menu.jsp" charEncoding="utf-8"/>
        </div>
        <custom:info-tag type="${sessionScope.userType}" email="${sessionScope.email}">
            <fmt:message key='infotag.access' bundle="${loc}"/>
        </custom:info-tag>
        <div id="page-content">
            <h2><fmt:message key="order.title" bundle="${loc}"/></h2>
            <c:if test="${not empty cpError}"><div class="alert"><fmt:message key="wrong.input.data" bundle="${loc}"/></div></c:if>

            <b> <fmt:message key="order.name" bundle="${loc}"/> </b> ${param.carModel} <br/>
            <b> <fmt:message key="order.price" bundle="${loc}"/> </b> ${param.carPrice} <fmt:message key="cars.currency" bundle="${loc}"/>
            <br/>
            <img src="${carImage}"/> <br/>
            <form name ="order"  method="POST" action="../mainServlet">
                <input type="hidden" name="command" value="orderCar" />
                <input type="hidden" name="carId" value="${param.carId}" />
                <input type="hidden" name="carPrice" value="${param.carPrice}" />
                <c:out value="${param.carId}"/>
                <p><fmt:message key="order.date" bundle="${loc}"/></p>
                <input type="date" name="pickUpDate" value="" required  max="2029-12-31" min="2019-09-10"/>
                <div class="notice"><fmt:message key="order.minmax" bundle="${loc}"/></div>
                <input  type="number"  min="1" max="30" required name="period" value="" placeholder="" />
                <input type="submit" value="<fmt:message key="cars.order" bundle="${loc}"/>" />
            </form>
        </div>
    </div>
</div>--%>

<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>
