<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<label>Название</label><br>
	<input type="text" id="currency_name" value=""><br>
	<label>Код</label><br>
	<input type="text" id="currency_code" value=""><br>
	<label>Символ</label><br>
	<input type="text" id='currency_symbol' value=""><br>
	<input type="button" id='edit_currency' data-dismiss="modal" value="Сохранить"><br>
</form>
</div>
<script>
insertEditValue()
</script>
<script>
	document.getElementById('edit_currency').addEventListener('click', setEditValue);
    function setEditValue() {
        var currency = JSON.parse(localStorage.getItem("storageName"));
        var changedNameValue = document.querySelector('#currency_name').value;
		var changedCodeValue = document.querySelector('#currency_code').value;
		var changedSymbolValue = document.querySelector('#currency_symbol').value;

		if (currency['name'] !== String(changedNameValue) || currency['code'] !== String(changedCodeValue)
				|| currency['symbol'] !== String(changedSymbolValue)) {
            currency['name'] = String(changedNameValue);
            currency['code'] = String(changedCodeValue);
            currency['symbol'] = String(changedSymbolValue);

            var result = JSON.stringify(currency);
            $.ajax({
                type: 'post',
                url: "${contextPath}/currencies/update",
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

<script src="${contextPath}/resources/js/currencies/currency_edit.js"></script>
</body>
</html>