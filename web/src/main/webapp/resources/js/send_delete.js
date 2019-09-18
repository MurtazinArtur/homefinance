function deleteRow(row_id){
    jQuery.ajax({
        type: "post",
        url: "http://localhost:8080/web-1.0-SNAPSHOT/bankServlet",
        data: "bank_id=" + row_id + "&vid=lids",
        success: function(response){
            $('body').html($(response).find('body').html());
        }
    });
}