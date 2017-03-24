/**
 * Created by Asus on 08.03.2017.
 */
var ajaxUrl = 'ajax/restaurants/';
var restVoteUrl = 'ajax/votes/restaurant/';
var datatableApi;

/*
 function updateTable() {
 $.get(ajaxUrl, updateTableByData);
 }
 */

// $(document).ready(function () {
$(function () {
/*
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
*/
    datatableApi = $('#datatable').DataTable(extendsOpts({
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
    }));
    // makeEditable();
});

function renderDishesBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="dishes?restaurantId=' + row.id + '" class="btn btn-xs btn-primary">'+i18n['restaurants.dishes']+'</a>';
    }
}

function renderVoteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="changeVote(' + row.id + ', '+row.userVotedThisRestaurantToday+');">'+i18n[+row.userVotedThisRestaurantToday ? 'restaurants.voted' : 'restaurants.vote']+'</a>';
    }
}

function changeVote(id, userVotedThisRestaurantToday) {
    if (userVotedThisRestaurantToday) {
        $.ajax({
            type: "DELETE",
            url: restVoteUrl + id,
            success: function () {
                updateTable();
                successNoty('common.deleted');
            }
        });
    }
    else {
        $.ajax({
            type: "POST",
            url: restVoteUrl, // + id,
            data: {
                restaurantId: id
            },
            success: function () {
                updateTable();
                successNoty('common.saved');
            }
        });
    }
}
