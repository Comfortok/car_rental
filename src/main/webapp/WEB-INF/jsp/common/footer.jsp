<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setBundle basename="title" var="loc"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon"/>
</head>
<body>
<fmt:message key="footer.text" bundle="${loc}"/>
<br>
<tag:today/>
<div><a href="mailto:admin@email.com">admin@email.com</a></div>
</body>
</html>