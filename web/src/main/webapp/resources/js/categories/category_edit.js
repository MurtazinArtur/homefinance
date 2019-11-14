function getCategoryValue(data) {
    var rows = document.querySelectorAll('#category_' + data);
    var editName = rows[0].cells[0].innerText;
    var editParentCategory = rows[0].cells[1].innerText;
    var category = {
        'id': data,
        'name': editName,
        'parentCategory': editParentCategory
    };
    localStorage.setItem("storageName", JSON.stringify(category));
}

function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var category = JSON.parse(editValue);
    document.querySelector('#category_name').value = category['name'];
    document.querySelector('#category_parentCategory').value = category['parentCategory'];
}

