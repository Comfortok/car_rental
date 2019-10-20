<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
<jsp:useBean id="now" class="java.util.Date"/>

<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<head>
    <jsp:include page="common/header.jsp"/>
</head>
<body>

        <%--<div id="menu">
            <c:import url="common/menu.jsp" charEncoding="utf-8"/>
        </div>--%>
<%--        <custom:info-tag type="${userType}" email="${email}">
            <fmt:message key="infotag.access" bundle="${loc}"/>
        </custom:info-tag>--%>
        <div id="page-content">
            <h2><fmt:message key="user.mainpage.title" bundle="${loc}"/></h2>
            <p><fmt:message key="user.mainpage.test" bundle="${loc}"/></p>
            <form class="form-inline" action="mainServlet" method="post">
                <div class="container">
                    <input name="startDate" class="form-control mr-sm-1" type="date" required
                           min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"  />
                    <input name="endDate" class="form-control mr-sm-1" type="date" required
                           min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>" />
                    <input type="hidden" name="command" value="availableCars">
                    <button class="btn btn-outline-success my-1 my-sm-0" type="submit">Search</button>
                </div>
            </form>
<%--            <c:if test="${not empty flag}">
                <jsp:useBean id="userId" scope="request" type="com.epam.entity.User"/>
                <div class="msg"> <fmt:message key="user.neworders.message" bundle="${loc}"/></div>
                <input type="hidden" name="userId" value="${userId}"/>
            </c:if>--%>
            <%--<div id="back">
                <form name ="RentACar"  method="POST" action="mainServlet" >
                    <input type="hidden" name="command" value="cars"/>
                    <input type="submit" value="<fmt:message key="media.slogan" bundle="${loc}"/>"/>
                </form>
            </div>--%>
        </div>
<div class="footer">  <c:import url="common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>