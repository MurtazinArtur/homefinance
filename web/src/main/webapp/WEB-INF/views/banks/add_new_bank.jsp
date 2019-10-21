<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Добавить банк</title>
</head>
<body>
<form action="banks/add_new_bank" method="POST">
    <label>Название</label>
    <input type="text" name="name">
    <input type="submit" value="Добавить банк">
</form>
</body>
</html>