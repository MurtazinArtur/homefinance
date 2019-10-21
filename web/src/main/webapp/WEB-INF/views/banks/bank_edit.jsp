<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Изменить данные банка</title>
</head>
<body>
<form action="banks/bank_edit" method="POST">
	<label>Название</label>
	<input type="text" name="name">
	<input type="submit" value="Сохранить">
</form>
</body>
</html>