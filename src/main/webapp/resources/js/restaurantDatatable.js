/**
 * Created by Asus on 24.02.2017.
 */
var ajaxUrl = 'ajax/restaurants/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": false,
        "columns": [
            {
                "data": "name"
            },
            {
                "sDefaultContent": "",
                "bSortable": false
            },
            {
                "defaultContent": "Update",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    })
    ;
    makeEditable();
});
