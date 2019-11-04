<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Добавить банк</title>
</head>
<body>
<form action="banks/add_new_bank" id="add_bank">
    <label>Название</label>
    <input type="text" name="name" id="name" value="" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование банка:"
           class="name" required/>
    <input type="submit" value="Добавить банк">
</form>
<script>
    document.getElementById('add_bank').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var bank = {};
        var formData = new FormData(event.target);

        formData.forEach(function(value, key){
            bank[key] = value;
        });
        formData.close;
        var json = JSON.stringify(bank);
        $.ajax({
            type: 'post',
            url: "${contextPath}/banks/save",
            contentType: "application/json",
            cache: false,
            data: json,
            success: function (html) {
                //window.location.reload();
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