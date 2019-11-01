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
	<label>Название</label>
	<input type="text" id="bank_name" value="">
	<input type="button" id='edit_bank' data-dismiss="modal" value="Сохранить">
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
                type: 'post',
                url: "${contextPath}/banks/update",
                dataType: 'json',
                contentType: "application/json",
				cache: false,
                data: result,
				success: function(data){
                    window.location.reload();
                	console.log("Всё ништяк братаны");
				},
				error: function () {
					console.log("Это пиздец какой-то");
                    window.location.reload();
					console.log($.ajax.status, $.ajax.statusText);
					console.log($.ajax.response);
				}
            });
            return false;
        } else {
            alert("Вы не внесли изменений!");
        }
    }
</script>

<script src="${contextPath}/resources/js/bank_edit.js"></script>
</body>
</html>