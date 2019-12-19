function getAccountValue(data) {
    var rows = document.querySelectorAll('#account_' + data);
    var editAccountName = rows[0].cells[0].innerText;
    var editAmount = rows[0].cells[1].innerText;
    var editCurrencyModel = rows[0].cells[2].innerText;
    var editAccountType = rows[0].cells[3].innerText;
    var account = {
        'id': data,
        'name': editAccountName,
        'amount': editAmount,
        'currencyModel': editCurrencyModel,
        'accountType': editAccountType
    };
    localStorage.setItem("storageName", JSON.stringify(account));
}

function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var account = JSON.parse(editValue);
    document.querySelector('#account_name').value = account['name'];
    document.querySelector('#account_amount').value = account['amount'];
    document.querySelector('#account_currencyModel').value = account['currencyModel'];
    document.querySelector('#account_accountType').value = account['accountType'];
}