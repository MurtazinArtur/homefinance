<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title >                              Список валют</title>
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
    <li><a href="http://localhost:8080/web-1.0-SNAPSHOT/currencies">Виды Валют</a>
    <li><a href="http://localhost:8080/web-1.0-SNAPSHOT/accounts">Счета</a>
    <li><a href="http://localhost:8080/web-1.0-SNAPSHOT/banks">Банки</a>
    <li><a href="http://localhost:8080/web-1.0-SNAPSHOT/categories">Категории Платежей</a>
    <li><a href="http://localhost:8080/web-1.0-SNAPSHOT/transaction">Транзакции</a>
</ul>
</nav>
<div class="mask-content"></div>

<a href="#x" class="overlay" id="form1"></a>
<form class="modal" action="/currency_list.jsp" method="post">
    <input name="currency_name" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование валюты:"
           class="name" required/>
    <input name="currency_code" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите код валюты:"
           class="name" required/>
    <input name="currency_symbol" type="text" onkeyup="var yratext=/['0-9',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите символ валюты:"
           class="name" required/>
    <input name="submit" class="btn" type="submit" value="Отправить"/>
</form>
<h1>Список Валют</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Наименование</th>
        <th scope="col">Код</th>
        <th scope="col">Символ</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="currency" items="${currencies}">
        <tr>
            <td>${currency.name}</td>
            <td>${currency.code}</td>
            <td>${currency.symbol}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>Наименование: <%= request.getParameter("currency_name") %></p>
<p>:Код: <%= request.getParameter("currency_code") %></p>
<p>Символ: <%= request.getParameter("currency_symbol") %></p>
<div style="float:left; width:150px; margin-right: 50px; margin-left:150px">
    <a href="#form1" class="bott">Создать</a></div>
<div style="float:left; width:150px; margin-right: 50px;">
    <a href="#" class="bott">Удалить</a></div>
<div style="float:left; width:150px; margin-right: 50px;">
    <a href="http://localhost:8080/web-1.0-SNAPSHOT/" class="bott">Вернуться</a></div>
</body>
</html>
