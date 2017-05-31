// $(document).ready(function () {
//     var lastIdx = null;
//     var table = $('#productdetails').DataTable({
//         "ajax": "/product/details",
//         "columns": [
//             {"data": "year"},
//             {"data": "month"},
//             {"data": "sellSum"},
//             {"data": "sameTime"},
//             {"data": "sameTimeRateStr"},
//             {"data": "preTime"},
//             {"data": "increaseCircleRateStr"},
//             {"data": "percentStr"},
//             {"data": "sumToMonth"},
//             {"data": "pos"}
//         ]
//     });
// });


function repaint() {
    var name = $("#select_k2").val();
    $("#select_k2").show(this.value);
    var lastIdx = null;
    var table = $('#productdetails').DataTable({
        "ajax": "/product/details?name=" + name,
        "destroy": true,
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
}
