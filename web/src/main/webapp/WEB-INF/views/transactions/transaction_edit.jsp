<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="currency" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="category" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="account" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bank" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Изменить данные операций</title>

    <link href="${contextPath}/resources/css/daterangepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/chosen.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/chosen.jquery.min.js"></script>
    <script src="${contextPath}/resources/js/chosen.proto.js"></script>
    <script src="${contextPath}/resources/js/moment.min.js"></script>
    <script src="${contextPath}/resources/js/daterangepicker.js"></script>

</head>
<body>
<div>
    <form id="edit_form">
        <label>Дата операции</label><br>
        <input type="text" id="transaction_date" value=""><br>
        <label>Место проведения операции</label><br>
        <input type="text" id="transaction_source" value=""><br>
        <label>Категория операции</label><br>
        <select name="transaction_category" id="transaction_category" required multiple="multiple">
            <category:forEach var="category" items="${categories}">
                <option>${category.name}</option>
            </category:forEach>
        </select><br>
        <label>Сумма операции</label><br>
        <input type="text" id="transaction_amount" value=""><br>
        <label>Валюта операции</label><br>
        <select name="transaction_currency" id="transaction_currency" required>
            <currency:forEach var="currency" items="${currencies}">
                <option>${currency.name}</option>
            </currency:forEach>
        </select><br>
        <label>Счет выполнения операции</label><br>
        <select name="transaction_account" id="transaction_account" required>
            <account:forEach var="account" items="${accounts}">
                <option>${account.name}</option>
            </account:forEach>
        </select><br>
        <label>Банк проведения операции</label><br>
        <select name="transaction_bank" id="transaction_bank" required>
            <bank:forEach var="bank" items="${banks}">
                <option>${bank.name}</option>
            </bank:forEach>
        </select><br>
        <input type="button" id='edit_transaction' data-dismiss="modal" value="Сохранить"><br>
    </form>
</div>
<script>
    insertEditValue()
</script>
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
<script type="text/javascript">
    $(function(){
        $("#transaction_category").chosen({width: "22%"});
    });
</script>
<script>
    document.getElementById('edit_transaction').addEventListener('click', setEditValue);

    function setEditValue() {
        var transaction = JSON.parse(localStorage.getItem("storageName"));
        var changedAmountValue = document.querySelector('#transaction_amount').value;
        var changedDateValue = document.querySelector('#transaction_date').value;
        var changedSourceValue = document.querySelector('#transaction_source').value;
        var changedCategoryValue = document.querySelector('#transaction_category').value;
        var changedBankValue = document.querySelector('#transaction_bank').value;
        var changedAccountValue = document.querySelector('#transaction_account').value;
        var changedCurrencyValue = document.querySelector('#transaction_currency').value;

        if (transaction['amount'] !== String(changedAmountValue) || transaction['date'] !== String(changedDateValue)
            || transaction['source'] !== String(changedSourceValue)
            || transaction['category'] !== String(changedCategoryValue)
            || transaction['bank'] !== String(changedBankValue) || transaction['account'] !== String(changedAccountValue)
            || transaction['currency'] !== String(changedCurrencyValue)) {

            transaction['amount'] = String(changedAmountValue);
            transaction['date'] = String(changedDateValue);
            transaction['source'] = String(changedSourceValue);
            transaction['category'] = String(changedCategoryValue);
            transaction['bank'] = String(changedBankValue);
            transaction['account'] = String(changedAccountValue);
            transaction['currency'] = String(changedCurrencyValue);

            var result = JSON.stringify(transaction);
            $.ajax({
                type: 'post',
                url: "${contextPath}/transactions/update",
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

<script src="${contextPath}/resources/js/transactions/transaction_edit.js"></script>
</body>
</html>