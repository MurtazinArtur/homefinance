<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="currency" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="account_type" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Изменить данные счета</title>
</head>
<body>
<div>
<form id="edit_form">
	<label>Название</label><br>
	<input type="text" id="account_name" value=""><br>
	<label>Остаток на счете</label><br>
	<input type="text" id="account_amount" value=""><br>
	<label>Валюта Счёта</label><br>
	<select name = "currencyModel" id="account_currencyModel" required>
		<currency:forEach var="currency" items="${currencies}">
			<option>${currency.name}</option>
		</currency:forEach>
	</select><br>
	<label>Тип счета</label><br>
	<select name = "accountType" id="account_accountType" required>
		<account_type:forEach var="account_type" items="${accountTypes}">
			<option>${account_type}</option>
		</account_type:forEach>
	</select><br>
	<input type="button" id='edit_account' data-dismiss="modal" value="Сохранить"><br>
</form>
</div>
<script>
insertEditValue()
</script>
<script>
	document.getElementById('edit_account').addEventListener('click', setEditValue);
    function setEditValue() {
        var account = JSON.parse(localStorage.getItem("storageName"));
        var changedNameValue = document.querySelector('#account_name').value;
		var changedAmountValue = document.querySelector('#account_amount').value;
		var changedCurrencyModelValue = document.querySelector('#account_currencyModel').value;
		var changedAccountTypeValue = document.querySelector('#account_accountType').value;

		if (account['name'] !== String(changedNameValue) || account['amount'] !== String(changedAmountValue)
				|| account['currencyModel'] !== String(changedCurrencyModelValue)
				|| account['accountType'] !== String(changedAccountTypeValue)) {
            account['name'] = String(changedNameValue);
			account['amount'] = String(changedAmountValue);
			account['currencyModel'] = String(changedCurrencyModelValue);
			account['accountType'] = String(changedAccountTypeValue);

            var result = JSON.stringify(account);
            $.ajax({
                type: 'post',
                url: "${contextPath}/accounts/update",
                contentType: "application/json",
				cache: false,
                data: result,
				success: function(data){
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

<script src="${contextPath}/resources/js/account_edit.js"></script>
</body>
</html>