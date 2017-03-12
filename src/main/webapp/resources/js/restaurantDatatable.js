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
                "data": "votesQuantity"
            },
            {
                "defaultContent": "",
                "render": renderDishesBtn,
                "orderable": false
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderVoteBtn
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
                1,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.userVotedThisRestaurantToday) {
                $(row).css("font-weight", "bold").css("color", "green");
            }
        },
        "initComplete": makeEditable
    });
    // makeEditable();
});

function renderDishesBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="dishes?restaurantId=' + row.id + '" class="btn btn-xs btn-primary">'+i18n['restaurants.dishes']+'</a>';
    }
}

function renderVoteBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="dishes?restaurantId=' + row.id + '" class="btn btn-xs btn-primary">' + i18n[+row.userVotedThisRestaurantToday ? 'restaurants.voted' : 'restaurants.vote'] + '</a>';
    }
}
