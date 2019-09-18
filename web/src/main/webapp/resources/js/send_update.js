function updateRow(row_id){
    let updating_form = document.querySelector('form');
    jQuery.ajax({
        type: "post",
        url: "http://localhost:8080/web-1.0-SNAPSHOT/bankServlet",
        data: "bank_id=" + row_id + "bank_name=" + seraialize(updating_form),
        success: function(response){
            $('body').html($(response).find('body').html());
        }
    });
}