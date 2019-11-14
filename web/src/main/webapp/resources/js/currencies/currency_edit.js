function getCurrencyValue(data) {
    var rows = document.querySelectorAll('#currency_' + data);
    var editName = rows[0].cells[0].innerText;
    var editCode = rows[0].cells[1].innerText;
    var editSymbol = rows[0].cells[2].innerText;
    var currency = {
        'id': data,
        'name': editName,
        'code': editCode,
        'symbol': editSymbol
    };
    localStorage.setItem("storageName", JSON.stringify(currency));
}

function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var currency = JSON.parse(editValue);
    document.querySelector('#currency_name').value = currency['name'];
    document.querySelector('#currency_code').value = currency['code'];
    document.querySelector('#currency_symbol').value = currency['symbol'];
}

