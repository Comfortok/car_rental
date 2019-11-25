<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date"/>

<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
<head>
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <jsp:useBean id="roleId" scope="session" type="java.lang.Long"/>
            <c:choose>
                <c:when test="${roleId eq 1}">
                    <jsp:include page="WEB-INF/jsp/common/header.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="WEB-INF/jsp/admin/headerAdmin.jsp"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <jsp:include page="WEB-INF/jsp/common/header.jsp"/>
        </c:otherwise>
    </c:choose>
</head>
<body>
<div id="page-content">
    <div class="row">
        <div class="col-sm-4">
            <div class="card-body">
                <h2><fmt:message key="home.title" bundle="${loc}"/></h2>
                <p><fmt:message key="home.text.1" bundle="${loc}"/></p>
                <p><fmt:message key="home.text.2" bundle="${loc}"/></p>
                <p><fmt:message key="home.text.3" bundle="${loc}"/></p>
                <p><fmt:message key="home.text.4" bundle="${loc}"/></p>
                <p><fmt:message key="home.text.5" bundle="${loc}"/></p>
            </div>
        </div>
        <div class="col-sm-6">
            <br>
            <div class="card border-success mb-3" style="max-width: 21rem;">
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="form.dates" bundle="${loc}"/></h5>
                    <form class="form-inline" action="mainServlet" method="post">
                        <div class="container">
                            <input name="startDate" id="startDate" class="form-control mr-sm-1" type="date" required
                                   min="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>"/>
                            <br>
                            <input name="endDate" id="endDate" class="form-control mr-sm-1" type="date" required
                                   min="${param.startDate}" pattern="yyyy-MM-dd"/>
                            <br>
                            <br>
                            <input type="hidden" name="command" value="availableCars">
                            <button class="btn btn-outline-success my-1 my-sm-0" type="submit">
                                <fmt:message key="button.search" bundle="${loc}"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="JavaScript">
    document.getElementById("startDate").onchange = function () {
        var input = document.getElementById("endDate");
        input.min = this.value;
    }
</script>
<br><br><br><br><br><br><br>
<div class="footer"><c:import url="WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>