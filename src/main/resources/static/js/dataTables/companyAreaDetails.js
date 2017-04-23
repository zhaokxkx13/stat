$(document).ready(function () {
    var lastIdx = null;
    var table = $('#companyAreaDetails').DataTable({
        "ajax": "/humanResource/kpi/companyAreaDetails",
        "columns": [
            {"data": "city"},
            {"data": "count"},
            {"data": "averageSalery"}
        ]
    });
});

