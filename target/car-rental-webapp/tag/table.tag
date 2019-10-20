<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="brand" required="true" %>
<%@ attribute name="model" required="true" %>
<%@ attribute name="carCategory" required="true" %>
<%@ attribute name="carPrice" required="true" %>

<center>
    <table>
        <tr>
            <td rowspan="3" colspan="5" align="center">
                <img style="background: url('img/cars/id/${id}.jpg'); width:330px; height:200px; border: 0px;
                        border-radius:20px; -webkit-border-radius:20px; -moz-border-radius:20px;">
                <td width="100px" align="center"><fmt:message key="carstable.number"/><td/>
                <td width="100px" align="center"><fmt:message key="carstable.brand"/><td/>
                <td width="100px" align="center"><fmt:message key="carstable.model"/><td/>
                <td width="100px" align="center"><fmt:message key="carstable.category"/><td/>
                <td width="100px" align="center"><fmt:message key="carstable.price"/><td/>
            </td>
        </tr>
        <td width="100px" align="center">${id}</td>
        <td width="100px" align="center">${brand}</td>
        <td width="100px" align="center">${model}</td>
        <td width="100px" align="center">${carCategory}</td>
        <td width="100px" align="center">${carPrice}</td>
    </table>
</center>