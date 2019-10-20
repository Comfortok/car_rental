<%@page language="java" contentType="text/html; charset=UTF-8" %>
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
<div class="page-wrapper">
    <div class="header">
        <c:import url="common/header.jsp" charEncoding="utf-8"/>
    </div>
    <div class="page-buffer">
        <center>
            <div id="login">
                <form name ="LanguageForm" method="POST" action="mainServlet" class="lang">
                    <input type="hidden" name="command" value="RU" />
                    <input type="hidden" name="userType" value="GUEST" />
                    <input name= "language" type="submit" value="RU"/>
                    <input type="hidden" name="command" value="EN" />
                    <input type="hidden" name="userType" value="GUEST" />
                    <input name = "language" type="submit" value="EN"/>
                </form>

                <form name='form-login' method="POST" action="mainServlet" >
                    <h1><fmt:message key="login.title" bundle="${loc}"/></h1> <br/>
                    <input type="hidden" name="command" value="login" />
                    <input type="hidden" name="userType" value="GUEST" />
                    <span class="fontawesome-user"></span>
                    <input type="text" id="user" name= "email" placeholder="<fmt:message key="user.account.email" bundle="${loc}" />" required>
                    <c:if test="${not empty errorLogin}"><div class="alert"><fmt:message key="active.restricted" bundle="${loc}"/></div></c:if>
                    <span class="fontawesome-lock"></span>
                    <input type="password" maxlength="16" name="password" id="pass" placeholder="<fmt:message key="login.password" bundle="${loc}"/>" required>
                    <c:if test="${not empty errorPassword}"><div class="alert"><fmt:message key="error.pass.message" bundle="${loc}"/></div></c:if>
                    <input type="submit" value="<fmt:message key="login.enter" bundle="${loc}"/>">
                    <div class='notice'><a href='mailto:123@gmail.com?Subject=<fmt:message key="login.forgot" bundle="${loc}"/>'
                                           target="_top"><fmt:message key="login.forgot" bundle="${loc}"/></a></div>
                </form>
                <form action="register.jsp">
                    <input type="submit" value="<fmt:message key="login.register" bundle="${loc}"/> " />
                </form>
            </div></center> </div>
</div>
<div class="footer">
    <c:import url="common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>