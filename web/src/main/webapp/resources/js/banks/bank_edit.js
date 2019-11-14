function getBankValue(data) {
    var rows = document.querySelectorAll('#bank_' + data);
    var editRow = rows[0].cells[0].innerText;
    var bank = {
        'id': data,
        'name': editRow
    };
    localStorage.setItem("storageName", JSON.stringify(bank));
}

function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var bank = JSON.parse(editValue);
    document.querySelector('#bank_name').value = bank['name'];
}

