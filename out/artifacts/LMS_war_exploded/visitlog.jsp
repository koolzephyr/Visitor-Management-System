<%--
  Created by IntelliJ IDEA.
  User: anons
  Date: 6/27/16
  Time: 4:03 PM
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
        .clock{
            text-align: center;
            margin-top: 110px;
            font-size: 21px;
            color: #0084b4;
        }
        .container{
            margin-top: 10px;
        }
        .cust-nav{
            margin-top: 0px;
            width: 30%;
        }
        .cust-nav ul{
            list-style-type: none;
        }
    </style>
    <%--<style>
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
    </style>--%>
    <script type="text/javascript">
        function display_c(){
            var refresh=1000; // Refresh rate in milli seconds
            mytime=setTimeout('display_ct()',refresh);
        }

        function display_ct() {
            var strcount;
            var x = new Date();
            document.getElementById('ct').innerHTML = x;
            tt=display_c();
        }
    </script>
</head>
<body onload=display_ct();>

<div class="wrapper">
    <jsp:include page="_nav.jsp"/>
    <div class="row">
        <div class="clock"><span id='ct'></span></div>
        <div class="container col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
            <div class="title">
                Visit Log
            </div>

            <c:if test="${status=='error'}">
                <div class="error">
                    <p> Unable to save log! Try again.</p>
                </div><!--footer-block End<!-->
            </c:if>
            <c:if test="${status=='success'}">
                <div class="error">
                    <p> Successfully Logged.</p>
                </div><!--footer-block End<!-->
            </c:if>
            <form class="form-login" method="post" action="/visit">

                <%--<label class="label-info" for="comment">
                    Visit Reason
                </label>--%>

                <textarea rows="5" cols="20" class="form-control" id="comment" name="comment" placeholder="Enter Reason here...." required></textarea>

                <div class="footer-block">
                    <c:choose>
                        <c:when test="${logged=='log'}">
                            <td>
                                <button type="submit" class="btn btn-lg btn-error btn-block button"  disabled>
                                    Record
                                </button>
                                <%--style="background-color: #d43f3a;"--%>
                                <%--style="background-color: #d43f3a;"--%>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <button type="submit" class="btn btn-lg btn-primary btn-block button">
                                    Record
                                </button>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div> <!--Container End><!-->
    </div>  <!--form-login End><!-->
</div>   <!--Wrapper End><!-->

<%--<form action="/visit" method="post">

    <c:if test="${status=='error'}">
        <div class="error">
            <p> Unable to save log! Try again.</p>
        </div><!--footer-block End<!-->
    </c:if>
    <c:if test="${status=='success'}">
        <div class="error">
            <p> Successfully Logged.</p>
        </div><!--footer-block End<!-->
    </c:if>

    <table>
        <tr>
            <td>
                Visit Reason:
            </td>
            <td>
                <textarea rows="5" cols="20" name="comment" placeholder="Enter Here....." required></textarea>
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <c:choose>
                <c:when test="${logged=='log'}">
                    <td>
                        <input type="submit" value="Record" style="background-color: #d43f3a;"disabled>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <input type="submit" value="Record" style="background-color: #5bc0de;">
                    </td>
                </c:otherwise>
            </c:choose>

        </tr>
    </table>
</form>--%>

</body>
</html>
