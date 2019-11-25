<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="title" var="loc"/>
<jsp:useBean id="categoryList" scope="request" type="java.util.List"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
<head>
    <jsp:include page="headerAdmin.jsp"/>
</head>
<body>
<br>
<div class="page">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="category.number" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="category.name" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="category.price" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="form.payment.input" bundle="${loc}"/></th>
            <th scope="col"><fmt:message key="category.action" bundle="${loc}"/></th>
        </tr>
        </thead>
        <tbody>
        <div class="container">
            <c:forEach var="elem" items="${categoryList}">
                <form name="priceChange" action="mainServlet" method="post">
                <tr>
                    <th scope="row"><c:out value="${elem.id}"/></th>
                    <td><c:out value="${elem.name}"/></td>
                    <td><c:out value="${elem.pricePerDay}"/></td>
                    <td><input type="number" name="price" pattern="[0-9]{4,7}" min="100"
                               placeholder="<fmt:message key="input.price" bundle="${loc}"/>"
                               title="Price must be in KZT (e.g. 10000)"></td>
                    <td>
                        <button type="submit" name="command" value="changeCategoryPrice" class="btn btn-primary">
                            <input type="hidden" name="categoryId" value="${elem.id}"/>
                            <fmt:message key="button.update" bundle="${loc}"/>
                        </button>
                    </td>
                </tr>
                </form>
            </c:forEach>
        </div>
        </tbody>
    </table>
</div>
<c:choose>
    <c:when test="${not empty requestScope.errorEmpty}">
        <div class="alert alert-primary" role="alert">
            <fmt:message key="error.empty" bundle="${loc}"/>
        </div>
    </c:when>
</c:choose>
<div class="footer"><c:import url="../common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>