<%--
  Created by IntelliJ IDEA.
  User: anons
  Date: 5/25/16
  Time: 9:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>register form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
    <style>
        .container{
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .form-login .form-control{
            position: relative;
            font-size: 16px;
            height: auto;
            padding: 5px;
            box-sizing: border-box;
        }
        .form-login input[type="password"]
        {
            margin-bottom: 10px;
        }
        .form-login input{
            margin-bottom: 10px;
        }
    </style>
    <script>
        function setUsername() {
            var fname = document.getElementById("fname").value;
            var lname = document.getElementById("lname").value;
            var uname = document.getElementById("uname");
            console.log('here');
            uname.value=fname.toLowerCase()+'.'+lname.toLowerCase();
            console.log(uname.value);
            console.log(uname);
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
                    <p> Unable to Register! Try again.</p>
                </div><!--footer-block End<!-->
            </c:if>
            <c:if test="${status=='success'}">
                <div class="error">
                    <p> Successfully Registered.</p>
                </div><!--footer-block End<!-->
            </c:if>
            <form class="form-login" method="post" action="/member">

                <input type="text" name="fname" id="fname" class="form-control" placeholder="First Name" required autofocus>
                <input type="text" name="mname" class="form-control" placeholder="Middle Name">
                <input type="text" name="lname" id="lname" class="form-control" placeholder="Last Name" required autofocus>
                <input type="text" name="user" id="uname" class="form-control" placeholder="User Name" disabled>
                <input type="password" name="pass" class="form-control" placeholder="Password" required autofocus onkeypress="setUsername();">
                <input type="text" name="add" class="form-control" placeholder="Address" >
                <input type="text" name="contact" class="form-control" placeholder="Contact" required autofocus>
                <input type="text" name="email" class="form-control" placeholder="Email">
                <select name="type" required class="form-control">
                    <option value="">--Select--</option>
                    <option value="Employee">Employee</option>
                    <option value="Student">Student</option>
                    <option value="Guest">Guest</option>
                    <option value="Admin">Admin</option>
                </select>
                <input type="hidden" name="operation" class="form-control" id="operation" value="store">

                <div class="footer-block">
                    <button type="submit" class="btn btn-lg btn-primary btn-block button" >
                        Register
                    </button>
                </div>
                <div class="footer-block">
                    <a href="/">
                        <button type="button" class="btn btn-lg btn-primary btn-block button">
                            Back
                        </button>
                    </a>
                </div><!--footer-block End<!-->

            </form>
        </div> <!--Container End><!-->
    </div>  <!--form-login End><!-->
</div>   <!--Wrapper End><!-->

</body>

</html>

