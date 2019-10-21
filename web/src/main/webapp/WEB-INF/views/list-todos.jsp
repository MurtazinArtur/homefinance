<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Список Банков</title>
	<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/menu.css" rel="stylesheet">
</head>
<body>
<input type="checkbox" id="nav-toggle" hidden>
<nav class="nav">
	<label for="nav-toggle" class="nav-toggle" onclick=""></label>
	<h2 class="logo">
		<a href="//dbmast.ru/">DBmast.ru</a>
	</h2>
	<ul>
		<li><a href="${contextPath}/currencies/findAll">Виды Валют</a>
		<li><a href="${contextPath}/accounts/findAll">Счета</a>
		<li><a href="${contextPath}/banks/findAll">Банки</a>
		<li><a href="${contextPath}/categories/findAll">Категории Платежей</a>
		<li><a href="${contextPath}/transactions/findAll">Транзакции</a>
	</ul>
</nav>
<div class="mask-content"></div>
<div class="container">
		<table class="table table-hover" id="bank_table">
			<thead>
			<tr>
				<th scope="col"><p align="center">Наименование</p></th>
			</tr>
			</thead>

		<tbody>
			<c:forEach var="bank" items="${banks}">
				<tr>
					<td data-field="bank_name"><p align="center">${bank.name}</p></td>
					<td><a type="button" class="btn btn-primary"
						href="/update-todo?id=${todo.id}">Edit</a> <a type="button"
						class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a type="button" class="btn btn-success" href="/add-todo">Add</a>
	</div>
</div>
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap/bootstrap-datepicker.js"></script>

</body>
</html>