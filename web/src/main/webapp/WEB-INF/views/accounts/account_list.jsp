<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Список Счетов</title>

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
    </ul>
</nav>
<div class="mask-content"></div>

<div class="modal fade" id="modal_add">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4></h4>
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

<h1><p align="center">Список Счетов</p></h1>

<div class="form-group">
    <input type="text" class="form-control pull-right" id="search" placeholder="Поиск по таблице">
</div>

<div id="table" class="container">
    <table class="table table-hover" id="account_table">
        <thead>
        <tr>
            <th scope="col"><p align="center">Наименование</p></th>
            <th scope="col"><p align="center">Остаток на счёте</p></th>
            <th scope="col"><p align="center">Валюта счета</p></th>
            <th scope="col"><p align="center">Тип счета</p></th>
        </tr>
        </thead>
        <tbody class="list">
        <c:forEach var="account" items="${accounts}">
            <tr id="account_${account.id}">
                <td class="account_name" data-field="account_name" id="account_name">
                    <p align="center">${account.name}</p></td>
                <td class="account_amount" data-field="account_amount" id="account_amount">
                    <p align="center">${account.amount}</p></td>
                <td class="account_currencyModel" data-field="account_currencyModel" id="account_currencyModel">
                    <p align="center">${account.currencyModel}</p></td>
                <td class="account_accountType" data-field="account_accountType" id="account_accountType">
                    <p align="center">${account.accountType}</p></td>
                <td><a href="javascript:void(0);" data-href="${contextPath}/accounts/account_edit" class="openPopup">
                    <button type="button" class="btn btn-primary" id="edit_account"
                            name="${account.id}" onclick="getAccountValue(this.name)">Edit
                    </button>
                </a>
                    <a type="button" class="btn btn-warning"
                       href="${contextPath}/accounts/delete/${account.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="javascript:void(0);" data-href="${contextPath}/accounts/add_new_account" class="openPopup">
            <button class="btn btn-success" type="button"> Add</button>
        </a>
    </div>
</div>

<script src="${contextPath}/resources/js/accounts/account_filter.js"></script>
<script src="${contextPath}/resources/js/accounts/account_edit.js"></script>
</body>
</html>