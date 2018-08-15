<%--
  Created by IntelliJ IDEA.
  User: anons
  Date: 5/25/16
  Time: 8:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>login form</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
    <script>
        function setLoginOperation() {
            document.getElementById("operation").value = "login";
        }
        function setRegisterOperation() {
            document.getElementById("operation").value = "register";
        }
    </script>
</head>
<body>
<div class="wrapper">
  <div class="row">
    <div class="container col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
      <div class="title">
        L S T
      </div>
      <c:if test="${status=='error'}">
        <div class="error">
          <p> Username/Password not matched! Try again.</p>
        </div><!--footer-block End<!-->
      </c:if>
        <c:if test="${status=='success'}">
            <div class="error">
                <p> Successfully Logined.</p>
            </div><!--footer-block End<!-->
        </c:if>
      <form class="form-login" method="post" action="/member">

        <input type="text" name="user" class="form-control" placeholder="User Id" >
        <input type="password" name="pass" class="form-control" placeholder="Password" >
        <input type="hidden" name="operation" class="form-control" id="operation">

        <button type="submit" class="btn btn-lg btn-primary btn-block button" onclick="setLoginOperation()">
          Login
        </button>
        <div class="footer-block">
            <button type="submit" class="btn btn-lg btn-primary btn-block button" onclick="setRegisterOperation()">
                Register
            </button>
        </div><!--footer-block End<!-->
      </form>
    </div> <!--Container End><!-->
  </div>  <!--form-login End><!-->
</div>   <!--Wrapper End><!-->

</body>

</html>
