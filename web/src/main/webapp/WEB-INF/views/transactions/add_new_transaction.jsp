<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="currency" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="account" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bank" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Добавить Операцию</title>

    <link href="${contextPath}/resources/css/daterangepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/chosen.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/chosen.jquery.min.js"></script>
    <script src="${contextPath}/resources/js/chosen.proto.js"></script>
    <script src="${contextPath}/resources/js/moment.min.js"></script>
    <script src="${contextPath}/resources/js/daterangepicker.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>
<body>

<form action="transactions/add_new_transaction" id="add_transaction">
    <label>Дата операции</label><br>
    <input type="text" name="date" id="transaction_date" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите дату операции:"
           class="transaction_date" required/><br>
    <label>Место проведения операции</label><br>
    <input type="text" name="source" id="transaction_source" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите место проведения операции:"
           class="transaction_source" required/><br>
    <label>Категория операции</label><br>
    <select data-placeholder="Выберите категорию(ии)" name="category" id="transaction_category" required multiple="multiple">
        <option value="0"></option>
        <category:forEach var="category" items="${categories}">
            <option>${category.name}</option>
        </category:forEach>
    </select><br>
    <label>Сумма операции</label><br>
    <input type="text" name="amount" id="transaction_amount" onkeyup="var yratext=/[,':']/;
    if(yratext.test(this.value)) this.value=''" placeholder="Введите сумму операции:"
           class="transaction_amount" required/><br>
    <label>Валюта операции</label><br>
    <select name="currency" id="transaction_currency" required>
        <option value="0"></option>
        <currency:forEach var="currency" items="${currencies}">
            <option>${currency.name}</option>
        </currency:forEach>
    </select><br>
    <label>Счет выполнения операции</label><br>
    <select name="account" id="transaction_account" required>
        <option value="0"></option>
        <account:forEach var="account" items="${accounts}">
            <option>${account.name}</option>
        </account:forEach>
    </select><br>
    <label>Банк проведения операции</label><br>
    <select name="bank" id="transaction_bank" required>
        <option value="0"></option>
        <bank:forEach var="bank" items="${banks}">
            <option>${bank.name}</option>
        </bank:forEach>
    </select><br>
    <input name="submit" type="submit" value="Отправить"/>
</form>
<script>
    $(function () {
        $('#transaction_date').daterangepicker({
            singleDatePicker: true,
            locale: {
                format: 'YYYY-MM-DD'
            }
        });
    });
</script>
<script>
    document.getElementById('add_transaction').addEventListener('submit', submitForm);

    function submitForm(event) {
        // Отменяем стандартное поведение браузера с отправкой формы
        event.preventDefault();
        var transaction = {};
        var category = [];
        var formData = new FormData(event.target);

        formData.forEach(function (value, key) {
            if(key ==="category"){
                category.push(value);
            }else{
                transaction[key] = value;
            }
        });
        transaction["category"] = category;
        formData.close;
        var json = JSON.stringify(transaction);
        $.ajax({
            type: 'post',
            url: "${contextPath}/transactions/save",
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
<script type="text/javascript">
    $(function(){
        $("#transaction_category").chosen({width: "22%"});
    });
</script>
</body>
</html>