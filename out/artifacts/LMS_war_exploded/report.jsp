<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anons
  Date: 6/27/16
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
    <style>
        .cust-nav{
            margin-top: 0px;
        }
        .container{
            width: 50%;
        }
        .cust-nav li{
            margin-right: 0.8em;
            margin-left: 0.9em;
        }
    </style>
</head>
<body>
<div class="wrapper">

    <jsp:include page="_nav.jsp"/>
    <div class="row">
        <div class="clock"><span id='ct'></span></div>
        <div class="container col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Visit Date</th>
                        <th>Visit time</th>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Type</th>
                        <th>Purpose</th>
                    </tr>
                    <c:forEach var="report" items="${history}">
                        <tr>
                            <td><c:out value="${report.getVisitId()}"/></td>
                            <td><c:out value="${report.getVisitDate()}"/></td>
                            <td><c:out value="${report.getVisitTime()}"/></td>
                            <td><c:out value="${report.getName()}"/></td>
                            <td><c:out value="${report.getContact()}"/></td>
                            <td><c:out value="${report.getType()}"/></td>
                            <td><c:out value="${report.getVisitPurpose()}"/></td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div> <!--Container End><!-->
    </div>  <!--form-login End><!-->
</div>
</body>
</html>
