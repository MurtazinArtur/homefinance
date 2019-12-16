<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <title>Список Пользователей</title>

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
                <h4>Добавить Пользователя</h4>
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

<h1><p align="center">Список Пользователей</p></h1>

<div class="container"></div>
<div id="table" class="container">
    <table class="table table-hover" id="user_table">
        <thead>
        <tr>
            <th scope="col"><p align="center">Имя пользоателя</p></th>
            <th scope="col"><p align="center">Пароль</p></th>
            <th scope="col"><p align="center">Тип пользователя</p></th>
        </tr>
        </thead>
        <tbody class="list" id="user_tbl">
        <c:forEach var="user" items="${users}">
            <tr id="user_${user.id}">
                <td class="user_name" data-field="user_name" id="user_name">
                    <p align="center">${user.user}</p></td>
                <td class="user_password" data-field="user_password" id="user_password">
                    <p align="center">${user.password}</p></td>
                <td class="user_userRole" data-field="user_userRole" id="user_userRole">
                    <p align="center">${user.userRole}</p></td>
                <td><a href="javascript:void(0);" data-href="${contextPath}/users/user_edit" class="openPopup">
                    <button type="button" class="btn btn-primary" id="edit_user"
                            name="${user.id}" onclick="getUserValue(this.name)">Edit
                    </button>
                </a>
                    <a type="button" class="btn btn-warning"
                       href="${contextPath}/users/delete/${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <a href="javascript:void(0);" data-href="${contextPath}/users/add_new_user" class="openPopup">
            <button class="btn btn-success" type="button"> Add</button>
        </a>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/modal.js"></script>
<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/users/user_filter.js"></script>
<script src="${contextPath}/resources/js/users/user_edit.js"></script>
</body>
</html>
