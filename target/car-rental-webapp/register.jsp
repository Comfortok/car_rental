<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
	<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
	<fmt:setBundle basename="title" var="loc"/>
	<title><fmt:message key="header.title" bundle="${loc}"/></title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
</head>
<body>
<div class="header">
	<c:import url="common/header.jsp" charEncoding="utf-8"/>
</div>
<center>
	<div id="login">
		<h1><fmt:message key="register.title" bundle="${loc}"/></h1>
		<form name ="RegisterForm" method="POST" action="mainServlet">
			<input type="hidden" name="command" value="register" />
			<input type="hidden" name="userType" value="GUEST" />
			<div class="titling"> <fmt:message key="register.email" bundle="${loc}"/>: <h7>*</h7></div>
			<input type="email" name="email" value=""required>
			<div class="titling"> <fmt:message key="register.passoword" bundle="${loc}"/>: <h7>*</h7></div>
			<div class="warning"> <fmt:message key="register.warn.pass" bundle="${loc}"/> </div>
			<input type="password" name="password" pattern="^[a-z0-9_-]{6,16}$" value=""  required id='pas1'>
			<div class="titling"> <fmt:message key="register.repeat_password" bundle="${loc}"/>: <h7>*</h7></div>
			<input type="password" name="passwordRepeat" value=""  required id='pas2'>
			<c:if test="${not empty errorPassword}"><div class="alert"><fmt:message key="error.double.pass" bundle="${loc}"/></div></c:if>
			<br/>
			<input type="submit" value="<fmt:message key="register.button" bundle="${loc}"/>">
			<br/>
		</form>
		<br/>
		<form name="back" action="login.jsp">
			<input type="submit" value="<fmt:message key="error.back.button" bundle="${loc}"/> " />
		</form>
	</div>
</center>
<div class="footer"><c:import url="common/footer.jsp" charEncoding="utf-8"/></div>
</body>
</html>