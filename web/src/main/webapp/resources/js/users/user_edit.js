function getUserValue(data) {
    var rows = document.querySelectorAll('#user_' + data);
    var editUser = rows[0].cells[0].innerText;
    var editPassword = rows[0].cells[1].innerText;
    var editUserRole = rows[0].cells[2].innerText;
    var user = {
        'id': data,
        'user': editUser,
        'password': editPassword,
        'userRole': editUserRole
    };
    localStorage.setItem("storageName", JSON.stringify(user));
}

function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var user = JSON.parse(editValue);
    document.querySelector('#user_name').value = user['user'];
    document.querySelector('#user_password').value = user['password'];
    document.querySelector('#user_userRole').value = user['userRole'];
}