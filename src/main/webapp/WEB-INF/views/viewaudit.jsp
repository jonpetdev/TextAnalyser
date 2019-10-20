
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>View Audit File</title>
    <link href="${contextPath}/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
</head>
<body style="margin: 30px">

    <div class="container">
        <table class="table table-striped table-light">
            <tr>
                <th>Audito įrašai <button style="float: right" class="btn btn-dark" type="button" onclick="window.location.href='download/auditas'" >Atsisusti Faila</button></th>
            </tr>
        <c:forEach items="${content}" var="contentas" >

            <tr><td>${contentas}</td></tr>

        </c:forEach>
        </table>

    </div>
</body>
</html>
