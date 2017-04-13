$(document).ready(function () {
    var lastIdx = null;
    var table = $('#productdetails').DataTable({
        "ajax": "/product/details",
        "columns": [
            {"data": "year"},
            {"data": "month"},
            {"data": "sellSum"},
            {"data": "sameTime"},
            {"data": "sameTimeRateStr"},
            {"data": "preTime"},
            {"data": "increaseCircleRateStr"},
            {"data": "percentStr"},
            {"data": "sumToMonth"},
            {"data": "pos"}
        ]
    });
});
