<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="category" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Список Операций</title>

    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/buttons.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/menu.css" rel="stylesheet">

</head>
<body>
<input type="checkbox" id="nav-toggle" hidden>
<nav class="nav">
    <label for="nav-toggle" class="nav-toggle" onclick=""></label>
    <h2 class="logo">
        <a href="${contextPath}/hello/">Главная</a>
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

<nav class="navbar navbar-dark bg-dark" style="margin-bottom: 0px">
    <div class="container" style="margin: 5px; padding: 3px; width: 100%">
        <c:if test="${authUser.authorities != null}">
            <form method="get" class="form-inline">
                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control pull-right" id="search"
                               style="margin-left: 30px" placeholder="Поиск по таблице">
                    </div>
                    <div class="col">
                        <label id="user_label_login" style="margin-left: 1050px">
                            Вы авторизовались как ${authUser.username}
                        </label>
                        <label>
                            <a class="nav-link" href="/logout">Выйти</a>
                        </label>
                    </div>
                </div>
            </form>
        </c:if>
    </div>
</nav>

<h1><p align="center">Список Операций</p></h1>

<div id="table" class="container">
    <table class="table table-hover" id="transaction_table">
        <thead>
        <tr>
            <th scope="col"><p align="center">Дата</p></th>
            <th scope="col"><p align="center">Место проведения операции</p></th>
            <th scope="col"><p align="center">Категория транзакции</p></th>
            <th scope="col"><p align="center">Сумма</p></th>
            <th scope="col"><p align="center">Наименование валюты</p></th>
            <th scope="col"><p align="center">Наименование счета</p></th>
            <th scope="col"><p align="center">Наименование банка</p></th>
        </tr>
        </thead>
        <tbody class="list">
        <c:forEach var="transaction" items="${transactions}">
            <tr id="transaction_${transaction.id}">
                <td class="transaction_date" data-field="transaction_date" id="transaction_date">
                    <p align="center">${transaction.date}</p></td>
                <td class="transaction_source" data-field="transaction_source" id="transaction_source">
                    <p align="center">${transaction.source}</p></td>
                <td class="transaction_category" data-field="transaction_category" id="transaction_category">
                    <category:forEach var="category" items="${transaction.category}">
                        <p align="center">${category.name}</p>
                    </category:forEach></td>
                <td class="transaction_amount" data-field="transaction_amount" id="transaction_amount">
                    <p align="center">${transaction.amount}</p></td>
                <td class="transaction_currency" data-field="transaction_currency" id="transaction_currency">
                    <p align="center">${transaction.currency.name}</p></td>
                <td class="transaction_account" data-field="transaction_account" id="transaction_account">
                    <p align="center">${transaction.account.name}</p></td>
                <td class="transaction_bank" data-field="transaction_bank" id="transaction_bank">
                    <p align="center">${transaction.bank.name}</p></td>
                <td><a href="javascript:void(0);" data-href="${contextPath}/transactions/transaction_edit"
                       class="openPopup">
                    <button type="button" class="btn btn-primary" id="edit_transaction"
                            name="${transaction.id}" onclick="getTransactionValue(this.name)">Edit
                    </button>
                </a>
                    <a type="button" class="btn btn-warning"
                       href="${contextPath}/transactions/delete/${transaction.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="javascript:void(0);" data-href="${contextPath}/transactions/add_new_transaction" class="openPopup">
            <button class="btn btn-success" type="button"> Add</button>
        </a>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/modal.js"></script>
<script src="${contextPath}/resources/js/transactions/transaction_filter.js"></script>
<script src="${contextPath}/resources/js/transactions/transaction_edit.js"></script>
</body>
</html>