<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Изменить данные категории</title>
</head>
<body>
<div>
<form id="edit_form">
	<label>Название</label><br>
	<input type="text" id="category_name" value=""><br>
	<label>Родительская категория</label><br>
	<select name = "category_parentCategory" id="category_parentCategory" required>
		<option value="0"></option>
		<c:forEach var="parentCategory" items="${nameCategories}">
			<option>${parentCategory}</option>
		</c:forEach>
	</select><br>
	<input type="button" id='edit_category' data-dismiss="modal" value="Сохранить"><br>
</form>
</div>
<script>
insertEditValue()
</script>
<script>
	document.getElementById('edit_category').addEventListener('click', setEditValue);
    function setEditValue() {
        var category = JSON.parse(localStorage.getItem("storageName"));
        var changedNameValue = document.querySelector('#category_name').value;
		var changedParentCategoryValue = document.querySelector('#category_parentCategory').value;

		if (category['name'] !== String(changedNameValue) ||
				category['parentCategory'] !== String(changedParentCategoryValue)){
			category['name'] = String(changedNameValue);
			category['parentCategory'] = String(changedParentCategoryValue);
            var result = JSON.stringify(category);
            $.ajax({
                type: 'post',
                url: "${contextPath}/categories/update",
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

<script src="${contextPath}/resources/js/category_edit.js"></script>
</body>
</html>