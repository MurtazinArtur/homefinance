<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="currency" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="account_type" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <title>Добавить Счёт</title>
</head>
<body>

<form action="accounts/add_new_account" id="add_account">
    <label>Название Счёта</label><br>
    <input type="text" name="name" id="name" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите наименование счета:"
           class="name" required/><br>
    <label>Остаток на счёте</label><br>
    <input type="text" name="amount" id="amount" onkeyup="var yratext=/['а-я','a-z',':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите остаток на счете:"
           class="amount" required/><br>
    <label>Валюта Счёта</label><br>
    <select name = "currencyModel" id="currencyModel" required>
        <option value="0"></option>
        <currency:forEach var="currency" items="${currencies}">
            <option>${currency.name}</option>
        </currency:forEach>
    </select><br>
    <label>Тип счета</label><br>
    <select name = "accountType" id="accountType" required>
        <option value="null"></option>
        <account_type:forEach var="account_type" items="${accountTypes}">
            <option>${account_type}</option>
        </account_type:forEach>
    </select><br>
    <input name="submit" type="submit" value="Отправить"/>
</form>

<script>
    document.getElementById('add_account').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var account = {};
        var formData = new FormData(event.target);

        formData.forEach(function (value, key) {
            account[key] = value;
        });
        formData.close;
        var json = JSON.stringify(account);
        $.ajax({
            type: 'post',
            url: "${contextPath}/accounts/save",
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