$(document).ready(function () {
    var lastIdx = null;
    var table = $('#orderdetails').DataTable({
        "ajax": "/order/details",
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
});

