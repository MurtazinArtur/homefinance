<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Добавить Валюту</title>
</head>
<body>
<form action="currencies/add_new_currency" id="add_currency">
    <label>Название</label>
    <input type="text" name="name" id="name" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование валюты:"
           class="name" required/>
    <label>Код</label>
    <input type="text" name="code" id="code" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите код валюты:"
           class="code" required/>
    <label>Символ</label>
    <input type="text" name="symbol" id="symbol" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите символ валюты:"
           class="symbol" required/>
    <input type="submit" value="Добавить валюту">
</form>
<script>
    document.getElementById('add_currency').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var currency = {};
        var formData = new FormData(event.target);

        formData.forEach(function (value, key) {
            currency[key] = value;
        });
        formData.close;
        var json = JSON.stringify(currency);
        $.ajax({
            type: 'post',
            url: "${contextPath}/currencies/save",
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