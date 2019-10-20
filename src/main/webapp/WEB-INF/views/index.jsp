<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Text</title>
    <link href="${contextPath}/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
</head>
<body style="margin: 30px">

<h1>${pasisveikinimas}</h1>

<form:form method="POST" modelAttribute="modelForma" enctype="multipart/form-data">

        <form:textarea path="textas1" rows="3" cols="40" id="tes" placeholder="tekstas 1......"/>

        <form:textarea path="textas2" rows="3" cols="40" id="tes2" placeholder="tekstas 2......"/>
<br>
        <form:input path="file" type="file" name="file"/>
<br>

    <div class="form-group" style="margin-top: 10px">
            <button class="btn btn-success" type="submit">Submit</button>
    </div>

</form:form>

</div>

<div class="container">
    <c:if test="${not empty pirmasRez || not empty antrasRez || not empty uploudas}">
    <table class="table table-striped table-dark">
        <tr>
            <th scope="col">Rezultatas</th>
        </tr>
        <c:if test="${not empty pirmasRez}">
            <tr><td>${pirmas}</td></tr>
            <c:forEach items="${pirmasRez}" var="pirmasRez">
                <tr><td>${pirmasRez}</td></tr>
            </c:forEach>
        </c:if>
        <c:if test="${not empty antrasRez}">
            <tr><td>${antras}</td></tr>
            <c:forEach items="${antrasRez}" var="antrasRez">
                <tr><td>${antrasRez}</td></tr>
            </c:forEach>
        </c:if>
        <c:if test="${not empty uploudas}">
            <c:forEach items="${uploudas}" var="upl">
                <tr><td>${upl}</td></tr>
            </c:forEach>
        </c:if>
    </table>

        <button class="btn btn-dark btn-group" type="button" onclick="window.location.href='download/fileCurrent'" >Atsisusti Faila</button>
        <button class="btn btn-info btn-group" type="button" onclick="window.location.href='view'" >View</button>
    </c:if>



</div>

</body>
</html>