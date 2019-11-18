<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Изменить данные банка</title>
</head>
<body>
<div>
<form id="edit_form">
	<label>Название</label><br>
	<input type="text" id="bank_name" value=""><br>
	<input type="button" id='edit_bank' data-dismiss="modal" value="Сохранить"><br>
</form>
</div>
<script>
insertEditValue()
</script>
<script>
	document.getElementById('edit_bank').addEventListener('click', setEditValue);
    function setEditValue() {
        var bank = JSON.parse(localStorage.getItem("storageName"));
        var changedValue = document.querySelector('#bank_name').value;
        if (bank['name'] !== String(changedValue)) {
            bank['name'] = String(changedValue);
            var result = JSON.stringify(bank);
            $.ajax({
				type: 'POST',
                url: '${contextPath}/banks/update',
                contentType: 'application/json',
				cache: false,
                data: result,
				success: function(data){
					window.location.reload();
				},
				error: function (xhr, str) {
                	alert( " || " + xhr);
					console.log("Запрос завершен с ошибкой!!!");
					console.log("Status ajax responce: " + $.ajax.status + " " +
							"StatusText: " + $.ajax.statusText + " " + "ajax responce: " + $.ajax.response);
				}
            });
            return false;
        } else {
            alert("Вы не внесли изменений!");
        }
    }
</script>

<script src="${contextPath}/resources/js/banks/bank_edit.js"></script>
</body>
</html>