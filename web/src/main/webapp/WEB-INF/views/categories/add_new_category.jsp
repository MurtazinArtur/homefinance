<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Добавить Категорию</title>
</head>
<body>
<form action="categories/add_new_category" id="add_category">
    <label>Название</label><br>
    <input type="text" name="name" id="name" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование категории:"
           class="name" required/><br>
    <label>Родительская категория</label><br>
    <select name="parentCategory" id="parentCategory" required>
        <option value="0"></option>
        <c:forEach var="parentCategory" items="${nameCategories}">
            <option>${parentCategory}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Добавить категорию"><br>
</form>
<script>
    document.getElementById('add_category').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var category = {};
        var formData = new FormData(event.target);

        formData.forEach(function (value, key) {
            category[key] = value;
        });
        formData.close;
        var json = JSON.stringify(category);
        $.ajax({
            type: 'post',
            url: "${contextPath}/categories/save",
            contentType: "application/json",
            cache: false,
            data: json,
            success: function (html) {
                window.location.reload();
            },
            error: function () {
                // Запрос не получилось отправить
                console.error("Ошибка сохранения в базу");
            }
        });
    }
</script>
</body>
</html>