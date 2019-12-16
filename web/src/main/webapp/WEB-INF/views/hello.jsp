<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="user" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="error" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/menu.css">
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

    <title>HomeFinance</title>
</head>
<body>

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
            <form th:action="@{/logout}" method="get" class="form-inline">
                <div class="row justify-content-end">
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
        <c:if test="${authUser.authorities == null}">
            <form th:action="@{/hello}" method="post" class="form-inline">
                <div>
                    <error:if test="${error == true}">
                        <label style="margin-right: 2px; margin-left: 920px" id="error_login">
                            Error authentication. Not Valid username or password
                            <a class="nav-link" href="/hello">Повторить попытку</a>
                        </label>
                    </error:if>
                    <error:if test="${error != true}">
                        <label style="margin-right: 2px; margin-left: 950px" id="user_label_logout">Вы не
                            авторизованы</label>
                    </error:if>
                    <div style="margin-top: 5px">
                        <input type="text" class="form-control input-small" name="username"
                               style="margin-right: 2px; margin-left: 850px" placeholder="Имя пользователя">
                        <input type="password" class="form-control" name="password" style="margin-right: 2px"
                               placeholder="Пароль">
                        <button type="submit" id="login" name="submit" class="btn btn-primary"
                                style="margin-right: 15px">Войти
                        </button>
                    </div>
                    <a href="javascript:void(0);" data-href="${contextPath}/users/add_new_user" class="openPopup">
                        <label style="color: black; margin-left: 980px">Регистрация</label>
                    </a>
                </div>
            </form>
        </c:if>
    </div>
</nav>

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
<div>
    <img src="${contextPath}/resources/images/main.jpg" width="100%">
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/js/modal.js"></script>
<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>

</body>
</html>