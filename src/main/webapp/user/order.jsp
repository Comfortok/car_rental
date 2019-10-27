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
        <h5><c:out value="${param.carModel}"/></h5>
    </div>
    <div class="card-body">
        <h5 class="card-title"><c:out value="${param.startDate}"/> <c:out value="${param.endDate}"/></h5>
        <c:out value="${param.carId}"/>
        <p class="card-text">Fill the driver info</p>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
            Launch demo modal
        </button>
        <a href="${pageContext.request.contextPath}/mainServlet?command=orderCar" class="btn btn-primary">Order!</a>
    </div>
    <div class="card-footer text-muted">
        2 days ago
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <form action="mainServlet" name="orderInfo" method="post">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">
                    <c:out value="Fill the form"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                    <input type="hidden" name="carId" value="${param.carId}"/>
                    <input type="hidden" name="payment" value="${pageContext.request.getAttribute("payment")}"/>
                    <input type="hidden" name="startDate" value="${param.startDate}"/>
                    <input type="hidden" name="endDate" value="${param.endDate}"/>
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input class="form-control" id="name" name="name" type="text" placeholder="Name">
                    </div>

                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input class="form-control" id="surname" name="surname" type="text" placeholder="Surname">
                    </div>

                    <div class="form-group">
                        <label for="birthDate">Date of birth</label>
                        <input class="form-control" id="birthDate" type="date" placeholder="Date of birth"
                               name="birthDate" pattern="yyyy-MM-dd">
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input class="form-control" id="phone" type="tel" placeholder="Phone" name="phone">
                    </div>
                <br>
                        <c:out value="Passport Info"/>
                        <div class="form-group">
                            <label for="passport">Passport No</label>
                            <input class="form-control" id="passport" name="passport" type="text" placeholder="Passport No">
                        </div>

                        <div class="form-group">
                            <label for="passportIssue">Issue date</label>
                            <input class="form-control" id="passportIssue" type="date" placeholder="Issue date"
                                   name="passportIssue" pattern="yyyy-MM-dd">
                        </div>

                        <div class="form-group">
                            <label for="passportExpiry">Expiry date</label>
                            <input class="form-control" id="passportExpiry" type="date" placeholder="Expiry date"
                                   name="passportExpiry" pattern="yyyy-MM-dd">
                        </div>

                        <div class="form-group">
                            <label for="passportAuthority">Authority</label>
                            <input class="form-control" id="passportAuthority" name="passportAuthority"
                                   type="text" placeholder="Passport Authority">
                        </div>

                    <br>
                        <c:out value="Licence Info"/>
                        <div class="form-group">
                            <label for="licence">Licence No</label>
                            <input class="form-control" id="licence" name="licence" type="text" placeholder="Licence No">
                        </div>

                        <div class="form-group">
                            <label for="licenceIssue">Issue date</label>
                            <input class="form-control" id="licenceIssue" type="date" placeholder="Issue date"
                                   name="licenceIssue" pattern="yyyy-MM-dd">
                        </div>

                        <div class="form-group">
                            <label for="licenceExpiry">Expiry date</label>
                            <input class="form-control" id="licenceExpiry" type="date" placeholder="Expiry date"
                                   name="licenceExpiry" pattern="yyyy-MM-dd">
                        </div>

                        <div class="form-group">
                            <label for="licenceAuthority">Authority</label>
                            <input class="form-control" id="licenceAuthority" type="text" placeholder="Licence Authority"
                                   name="licenceAuthority">
                        </div>

                        <div class="form-group">
                            <label for="licenceCategory">Category</label>
                            <input class="form-control" id="licenceCategory" type="text" placeholder="Licence Category"
                                   name="licenceCategory">
                        </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <input type="hidden" name="command" value="orderCar" />
                <input type="submit" value="orderrrr" />
            </div>
            </form>
        </div>
    </div>
</div>

<div class="footer">  <c:import url="../common/footer.jsp" charEncoding="utf-8"/> </div>
</body>
</html>
