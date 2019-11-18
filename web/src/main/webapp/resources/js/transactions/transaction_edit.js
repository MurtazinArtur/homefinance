function getTransactionValue(data) {
    var rows = document.querySelectorAll('#transaction_' + data);
    var editDate = rows[0].cells[0].innerText;
    var editSource = rows[0].cells[1].innerText;
    var editCategory = rows[0].cells[2].innerText;
    var editAmount = rows[0].cells[3].innerText;
    var editCurrency = rows[0].cells[4].innerText;
    var editAccount = rows[0].cells[5].innerText;
    var editBank = rows[0].cells[6].innerText;
    var transaction = {
        'id' : data,
        'date' : editDate,
        'source' : editSource,
        'category' : editCategory,
        'amount' : editAmount,
        'currency' : editCurrency,
        'account' : editAccount,
        'bank' : editBank
    };
    localStorage.setItem("storageName", JSON.stringify(transaction));
}
function insertEditValue() {
    var editValue = localStorage.getItem("storageName");
    var transaction = JSON.parse(editValue);
    document.querySelector('#transaction_date').value = transaction['date'];
    document.querySelector('#transaction_source').value = transaction['source'];
    document.querySelector('#transaction_category').value = transaction['category'];
    document.querySelector('#transaction_amount').value = transaction['amount'];
    document.querySelector('#transaction_currency').value = transaction['currency'];
    document.querySelector('#transaction_account').value = transaction['account'];
    document.querySelector('#transaction_bank').value = transaction['bank'];
}