<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="userRoles" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Добавить Валюту</title>
</head>
<body>
<form action="users/add_new_user" id="add_user">
    <label>Пользователь</label><br>
    <input type="text" name="user" id="user" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите имя пользователя:"
           class="user" required/><br>
    <label>Пароль</label><br>
    <input type="text" name="password" id="password" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите Пароль:"
           class="password" required/><br>
    <c:if test="${authUser != null}">
    <label>Тип пользователя</label><br>
    <select name = "userRole" id="userRole" required>
        <option value="0"></option>
        <userRoles:forEach var="userRole" items="${userRoles}">
            <option>${userRole}</option>
        </userRoles:forEach>
    </select><br>
    </c:if>
    <input name="submit" type="submit" value="Отправить"/>
</form>
<script>
    document.getElementById('add_user').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var user = {};
        var formData = new FormData(event.target);

        formData.forEach(function (value, key) {
            user[key] = value;
        });
        formData.close;
        var json = JSON.stringify(user);
        $.ajax({
            type: 'POST',
            url: "${contextPath}/users/save",
            contentType: "application/json",
            cache: false,
            data: json,
            success: function (html) {
                window.location.reload();
                alert("Пользователь успешно создан");
            },
            error: function () {
                // Запрос не получилось отправить
                console.error("Ошибка сохранения в базу");
                alert("Ошибка сохранения в базу");
            }
        });
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>