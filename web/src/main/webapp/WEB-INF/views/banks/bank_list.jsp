<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Список Банков</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/buttons.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/menu.css" rel="stylesheet">
</head>
<body>
<input type="checkbox" id="nav-toggle" hidden>
<nav class="nav">
    <label for="nav-toggle" class="nav-toggle" onclick=""></label>
    <h2 class="logo">
        <a href="//dbmast.ru/">DBmast.ru</a>
    </h2>
    <ul>
        <li><a href="${contextPath}/currencies/">Виды Валют</a>
        <li><a href="${contextPath}/accounts/">Счета</a>
        <li><a href="${contextPath}/banks/">Банки</a>
        <li><a href="${contextPath}/categories/">Категории Платежей</a>
        <li><a href="${contextPath}/transactions/">Транзакции</a>
    </ul>
</nav>
<div class="mask-content"></div>

<a href="#x" class="overlay" id="form_post"></a>
<form class="modal" id="posting_form" tabindex="-1" aria-hidden="true" name="posting_form"
      action="${contextPath}/banks" method="post">
    <input id="bank_name" name="bank_name" type="text"/>
    <input class="btn" type="button" id="send" name="submit" value="Отправить"/>
</form>

<h1><p align="center">Список Банков</p></h1>
<div class="container">
    <table class="table table-hover" id="bank_table">
        <thead>
        <tr>
            <th scope="col"><p align="center">Наименование</p></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="bank" items="${banks}">
            <tr>
                <td data-field="bank_name"><p align="center">${bank.name}</p></td>
                <td><a type="button" class="btn btn-primary"
                       href="/update?id=${bank.id}">Edit</a>
                    <a type="button" class="btn btn-warning"
                       href="${contextPath}/banks/delete/${bank.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="${contextPath}/banks/add_new_bank">Add</a>
    </div>
</div>

<script src="${contextPath}/resources/js/send_post.js"></script>
<script src="${contextPath}/resources/js/send_delete.js"></script>
<script src="${contextPath}/resources/js/serialize.js"></script>
</body>
</html>