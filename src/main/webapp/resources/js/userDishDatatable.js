/**
 * Created by Asus on 24.02.2017.
 */

var datatableApi;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "date"
            },
            {
                "data": "description"
            },
            {
                "data": "price",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return number_format(data/100,2,","," ");
                    }
                    return data;
                }
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

