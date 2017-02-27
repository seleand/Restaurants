/**
 * Created by Asus on 24.02.2017.
 */
var ajaxUrl = 'ajax/restaurants/';
var datatableApi;

/*
function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}
*/

// $(document).ready(function () {
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
                "data": "name"
            },
            {
                "defaultContent": "",
                "render": renderDishesBtn,
                "orderable": false
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
    // makeEditable();
});

function renderDishesBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="dishes?restaurantId=' + row.id + '" class="btn btn-xs btn-primary">Dishes</a>';
    }
}

