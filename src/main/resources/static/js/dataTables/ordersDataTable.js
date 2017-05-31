// $(document).ready(function () {
//     var lastIdx = null;
//     var table = $('#orderdetails').DataTable({
//         "ajax": "/order/details?name=beef",
//         "columns": [
//             {"data": "productName"},
//             {"data": "bigType"},
//             {"data": "smallType"},
//             {"data": "price"},
//             {"data": "num"},
//             {"data": "spec"},
//             {"data": "sum"},
//             {"data": "orderDateStr"},
//             {"data": "sender"},
//             {"data": "receiver"},
//             {"data": "freitght"},
//             {"data": "address"}
//         ]
//     });
// });

function repaint() {
    var name = $("#select_k1").val();
    $("#select_k1").show(this.value);
    var lastIdx = null;
    var table = $('#orderdetails').DataTable({
        "ajax": "/order/details?name=" + name,
        "destroy": true,
        "columns": [
            {"data": "productName"},
            {"data": "bigType"},
            {"data": "smallType"},
            {"data": "price"},
            {"data": "num"},
            {"data": "spec"},
            {"data": "sum"},
            {"data": "orderDateStr"},
            {"data": "sender"},
            {"data": "receiver"},
            {"data": "freitght"},
            {"data": "address"}
        ]
    });
}

