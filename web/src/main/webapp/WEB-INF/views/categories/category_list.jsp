<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Список Категорий Транзакций</title>

    <script src="${contextPath}/resources/js/jquery-2.1.4.min.js"></script>
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
                <h4>Добавить Категорию</h4>
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

<h1><p align="center">Список Категорий Платежей</p></h1>

<table class="table table-hover" id="category_table">
    <thead>
    <tr>
        <th scope="col"><p align="center">Наименование</p></th>
        <th scope="col"><p align="center">Родительская категория</p></th>
    </tr>
    </thead>
    <tbody class="list" id="category_tbl">
    <c:forEach var="category" items="${categories}">
        <tr id="category_${category.id}">
            <td class="category_name" data-field="category_name" id="category_name">
                <p align="center">${category.name}</p></td>
            <td class="category_parentCategory" data-field="category_parentCategory" id="category_parentCategory">
                <p align="center">${category.parentCategory}</p></td>
            <td><a href="javascript:void(0);" data-href="${contextPath}/categories/category_edit" class="openPopup">
                <button type="button" class="btn btn-primary" id="edit_category"
                        name="${category.id}" onclick="getCategoryValue(this.name)">Edit
                </button>
            </a>
                <a type="button" class="btn btn-warning"
                   href="${contextPath}/categories/delete/${category.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <a href="javascript:void(0);" data-href="${contextPath}/categories/add_new_category" class="openPopup">
        <button class="btn btn-success" type="button"> Add</button>
    </a>
</div>

<script src="${contextPath}/resources/js/categories/category_filter.js"></script>
<script src="${contextPath}/resources/js/categories/category_edit.js"></script>
</body>
</html>