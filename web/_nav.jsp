<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anons
  Date: 6/27/16
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<nav class="navbar navbar-default ">--%>
    <div class="container cust-nav">
        <div class="col-md-10 col-md-push-1 nav-wrapper">
            <ul class="nav navbar-nav">
                <c:if test="${type=='Admin'}">
                    <li><a href="/visit?action=all">All History</a></li>
                </c:if>
                <li><a href="/visit?action=my">My History</a></li>
                <li><a href="/member?">Logout</a></li>
            </ul>
        </div>
    </div>
<%--</nav>--%>
