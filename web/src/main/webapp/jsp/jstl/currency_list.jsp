<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title >Список валют</title>
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

<h1><p align="center">Список Валют</p></h1>

<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col"><p align="center">Наименование</p></th>
        <th scope="col"><p align="center">Код</p></th>
        <th scope="col"><p align="center">Символ</p></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="currency" items="${currencies}">
        <tr><a href="#form1" class="popup"></a>
            <td class="tbl-item cl-0" cellnum='0'>
                <p align="center">${currency.name}</p></td>
            <td class="tbl-item cl-1" cellnum='1'>
                <p align="center">${currency.code}</p></td>
            <td class="tbl-item cl-2" cellnum='2'>
                <p align="center">${currency.symbol}</p></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

        <div style="float:left; width:150px; margin-right: 50px; margin-left:150px">
    <a href="#form1" class="bott">Создать</a></div>
<div style="float:left; width:150px; margin-right: 50px;">
    <a href="#" class="bott">Удалить</a></div>
<div style="float:left; width:150px; margin-right: 50px;">
    <a href="<c:url value="/" />" class="bott">Вернуться</a></div>

<script src="<c:url value="/resources/js/send_post.js" />"></script>
<script src="<c:url value="/resources/js/send_delete.js" />"></script>
<script src="<c:url value="/resources/js/serialize.js" />"></script>
<script src="<c:url value="/resources/js/table.js" />"></script>

</body>
</html>
