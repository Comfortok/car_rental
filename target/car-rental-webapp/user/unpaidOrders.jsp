<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="custom" uri="/WEB-INF/custom.tld" %>


<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}" scope="session"/>
    <fmt:setBundle basename="title" var="loc"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="header.title" bundle="${loc}"/></title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon" />
    <style>
        table{
            border-collapse: collapse;
            width: 100%;
        }
        th,td{
            border: 2px solid green;
            padding: 15px;
        }

    </style>
</head>
<body>
<div class="page-wrapper">
    <div class="page-buffer">
        <custom:info-tag type="${sessionScope.userType}" email="${sessionScope.email}">
            <fmt:message key='infotag.access' bundle="${loc}"/>
        </custom:info-tag>
        <div id="page-content">
            <h2><fmt:message key="cars.title" bundle="${loc}"/></h2>
            <div id="orders">
                <ul>
                    <jsp:useBean id="orders" scope="request" type="java.util.List"/>
                    <table>
                        <c:forEach var="elem" items="${orders}">
                            <tr>
                                <td>
                                        <%--<input type="hidden" name="orderId" value="${elem.id}" />
                                        <input type="hidden" name="userId" value="${elem.user}" />
                                        <input type="hidden" name="carId" value="${elem.car}" />
                                        <c:out value="${elem.id}"/>
                                        <c:out value="${elem.user}"/>
                                        <c:out value="${elem.car}"/>--%>
                                    <input type="hidden" name="orderElem" value="${elem}"/>
                                    <c:out value="${elem}"/>;
                                        <input type="hidden" name="command" value="payAnOrder" />
                                        <input type="submit" value="PAY (do not push)" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>