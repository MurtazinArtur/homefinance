<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Список Валют</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/modal.js"></script>
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
        <li><a href="${contextPath}/users/">Пользователи</a>
    </ul>
</nav>
<div class="mask-content"></div>

<div class="modal fade" id="modal_add">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Добавить Валюту</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button class="btn-danger" type="button" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<h1><p align="center">Список Валют</p></h1>

<div class="form-group">
    <input type="text" class="form-control pull-right" id="search" placeholder="Поиск по таблице">
</div>

<div id="table" class="container">
    <table class="table table-hover" id="currency_table">
        <thead>
        <tr>
            <th scope="col"><p align="center">Наименование</p></th>
            <th scope="col"><p align="center">Код</p></th>
            <th scope="col"><p align="center">Символ</p></th>
        </tr>
        </thead>
        <tbody class="list" id="currency_tbl">
        <c:forEach var="currency" items="${currencies}">
            <tr id="currency_${currency.id}">
                <td class="currency_name" data-field="currency_name" id="currency_name">
                    <p align="center">${currency.name}</p></td>
                <td class="currency_code" data-field="currency_code" id="currency_code">
                    <p align="center">${currency.code}</p></td>
                <td class="currency_symbol" data-field="currency_symbol" id="currency_symbol">
                    <p align="center">${currency.symbol}</p></td>
                <td><a href="javascript:void(0);" data-href="${contextPath}/currencies/currency_edit" class="openPopup">
                    <button type="button" class="btn btn-primary" id="edit_currency"
                            name="${currency.id}" onclick="getCurrencyValue(this.name)">Edit
                    </button>
                </a>
                    <a type="button" class="btn btn-warning"
                       href="${contextPath}/currencies/delete/${currency.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="javascript:void(0);" data-href="${contextPath}/currencies/add_new_currency" class="openPopup">
            <button class="btn btn-success" type="button"> Add</button>
        </a>
    </div>
</div>

<script src="${contextPath}/resources/js/currencies/currency_filter.js"></script>
<script src="${contextPath}/resources/js/currencies/currency_edit.js"></script>
</body>
</html>
