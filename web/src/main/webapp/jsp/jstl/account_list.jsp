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

<a href="#x" class="overlay" id="form1"></a>
<form class="modal" action="<c:url value="/bank_list" />" method="post">
    <input name="account_name" type="text" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование счета:"
           class="name" required/>
    <input name="amount" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите остаток на счете:"
           class="name" required/>
    <input name="currency_id" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование валюты счета:"
           class="name" required/>
    <input name="account_type" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите тип счета:"
           class="name" required/>
    <input name="submit" class="btn" type="submit" value="Отправить"/>
</form>
<h1><p align="center">Список Счетов</p></h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col"><p align="center">Наименование</p></th>
        <th scope="col"><p align="center">Остаток на счёте</p></th>
        <th scope="col"><p align="center">Валюта счета</p></th>
        <th scope="col"><p align="center">Тип счета</p></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td><p align="center">${account.name}</p></td>
            <td><p align="center">${account.amount}</p></td>
            <td><p align="center">${account.currencyModel.name}</p></td>
            <td><p align="center">${account.accountType}</p></td>
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
</body>
</html>
