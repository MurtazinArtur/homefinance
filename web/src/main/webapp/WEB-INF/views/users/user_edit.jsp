<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="userRoles" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Изменить данные валюты</title>
</head>
<body>
<div>
    <form id="edit_form">
        <label>Имя Пользователя</label><br>
        <input type="text" id="user_name" value=""><br>
        <label>Пароль</label><br>
        <input type="text" id="user_password" value=""><br>
        <label>Тип Пользователя</label><br>
        <select name="userRole" id="user_userRole" required>
            <userRoles:forEach var="userRole" items="${userRoles}">
                <option>${userRole}</option>
            </userRoles:forEach>
        </select><br>
        <input type="button" id='edit_user' data-dismiss="modal" value="Сохранить"><br>
    </form>
</div>
<script>
    insertEditValue()
</script>
<script>
    document.getElementById('edit_user').addEventListener('click', setEditValue);

    function setEditValue() {
        var user = JSON.parse(localStorage.getItem("storageName"));
        var changedUserValue = document.querySelector('#user_name').value;
        var changedPasswordValue = document.querySelector('#user_password').value;
        var changedUserRoleValue = document.querySelector('#user_userRole').value;

        if (user['user'] !== String(changedUserValue) || user['password'] !== String(changedPasswordValue)
            || user['userRole'] !== String(changedUserRoleValue)) {
            user['user'] = String(changedUserValue);
            user['password'] = String(changedPasswordValue);
            user['userRole'] = String(changedUserRoleValue);

            var result = JSON.stringify(user);
            $.ajax({
                type: 'post',
                url: "${contextPath}/users/update",
                contentType: "application/json",
                cache: false,
                data: result,
                success: function (data) {
                    window.location.reload();
                },
                error: function () {
                    console.log("Запрос завершен с ошибкой!!!");
                    console.log("Status ajax responce: " + $.ajax.status +
                        "StatusText: " + $.ajax.statusText + "ajax responce: " + $.ajax.response);
                }
            });
            return false;
        } else {
            alert("Вы не внесли изменений!");
        }
    }
</script>

<script src="${contextPath}/resources/js/users/user_edit.js"></script>
</body>
</html>