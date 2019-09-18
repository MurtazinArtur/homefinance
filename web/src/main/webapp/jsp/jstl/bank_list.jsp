<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Список Банков</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/buttons.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/modal_form.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
</head>
<body>
<input type="checkbox" id="nav-toggle" hidden>
<nav class="nav">
    <label for="nav-toggle" class="nav-toggle" onclick=""></label>
    <h2 class="logo">
        <a href="//dbmast.ru/">DBmast.ru</a>
    </h2>
    <ul>
        <li><a href="<c:url value="/currency_list" />">Виды Валют</a>
        <li><a href="<c:url value="/account_list" />">Счета</a>
        <li><a href="<c:url value="/bank_list" />">Банки</a>
        <li><a href="<c:url value="/category_list" />">Категории Платежей</a>
        <li><a href="<c:url value="/transaction_list" />">Транзакции</a>
    </ul>
</nav>
<div class="mask-content"></div>

<a href="#x" class="overlay" id="form_post"></a>
<form class="modal" id="posting_form" tabindex="-1" aria-hidden="true" name="posting_form"
      action="<c:url value="/bank_list" />" method="post">
    <input id="bank_name" name="bank_name" type="text"/>
    <input class="btn" type="button" id="send" name="submit" value="Отправить"/>
</form>

<h1><p align="center">Список Банков</p></h1>
<table class="table table-hover" id="bank_table">
    <thead>
    <tr>
        <th scope="col"><p align="center">Наименование</p></th>
    </tr>
    </thead>
    <tbody id="data">
    <c:forEach var="bank" items="${banks}">
        <tr>
            <td data-field="bank_name"><p align="center">${bank.name}</p></td>
            <td width=150px>
                <a href="#" class="button" id="delete_row" name="submit" onclick="deleteRow(${bank.id})">Удалить</a>
                <a href="#form_update" class="button" id="update_row">Изменить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<a href="#x" class="overlay" id="form_update"></a>
<form class="modal" id="updeting_form" tabindex="-1" aria-hidden="true" name="updeting_form"
      action="<c:url value="/bank_list" />" method="post">
    <input id="bank_name2" name="bank_name" type="text" aria-valuetext="${bank.name}" value="${bank.name}"/>
    <input class="btn" type="button" id="update" name="submit" onclick="updateRow(${bank.id})" value="Отправить"/>
</form>

<div style="float:left; width:150px; margin-right: 50px; margin-left:150px">
    <a href="#form_post" class="bott">Создать</a></div>
<div style="float:left; width:150px; margin-right: 50px;">
    <a href="<c:url value="/" />" class="bott">Вернуться</a></div>

<script src="<c:url value="/resources/js/send_post.js" />"></script>
<script src="<c:url value="/resources/js/send_delete.js" />"></script>
<script src="<c:url value="/resources/js/serialize.js" />"></script>
</body>
</html>
