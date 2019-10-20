<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
    <fmt:setLocale value="${language}" scope="session"/>
    <fmt:setBundle basename="title" var="loc"/>
    <title><fmt:message key="header.title" bundle="${loc}"/></title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id="menu">
    <form action="login.jsp" >
        <input type="submit" value="LOG IN" />
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="submit" value="<fmt:message key="menu.main" bundle="${loc}"/> " />
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="ACCOUNT" />
        <input type="submit" value="<fmt:message key="menu.account" bundle="${loc}"/> " />
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="CARSREDIRECT" />
        <input type="submit" value="<fmt:message key="menu.choose" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="basket" />
        <input type="submit" value="<fmt:message key="menu.basket" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="paid" />
        <input type="submit" value="<fmt:message key="menu.paid.user" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="denied" />
        <input type="submit" value="<fmt:message key="menu.annul" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="CONTACTSREDIRECT" />
        <input type="submit" value="<fmt:message key="menu.contacts" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="LOGOUTREDIRECT" />
        <input type="submit" value="<fmt:message key="menu.logout" bundle="${loc}"/>"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="EN" />
        <input name= "language" type="submit" value="EN"/>
    </form>
    <form name ="menu" class="menu" method="POST" action="mainServlet" >
        <input type="hidden" name="command" value="RU" />
        <input name= "language" type="submit" value="RU"/>
    </form>
</div>
</body>
</html>