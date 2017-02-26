/**
 * Created by Asus on 24.02.2017.
 */

var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function addDish(restaurantId) {
    form.find(":input").val("");
    form.find("input[id='restaurantId']").val(restaurantId);
    // form.find("#id").val("");
    $('#editRow').modal();
}

function updateDishRow(restaurantId, id) {
    $.get(ajaxUrl + restaurantId + '/dish/' + id, function (data) {
        $.each(data, function (key, value){
        if (key == 'price') {
            form.find("input[name='" + key + "']").val(value / 100);
        }
        else {
            form.find("input[name='" + key + "']").val(value);
        }
    });
    form.find("input[id='restaurantId']").val(restaurantId);
    $('#editRow').modal();
    });
}

function deleteDishRow(restaurantId, id) {
    $.ajax({
        url: ajaxUrl + restaurantId + '/dish/' + id,
        type: 'DELETE',
        success: function () {
            updateTable(restaurantId);
            successNoty('Deleted');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function save(restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable(restaurantId);
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>" + jqXHR.responseJSON,
        type: 'error',
        layout: 'bottomRight'
    });
}

/*
var ajaxUrl = 'ajax/dishes/';
var datatableApi;

function updateTable(restaurantId) {
    $.get(ajaxUrl + restaurantId, updateTableByData);
}


$(function () {
    datatableApi = $('#datatable').DataTable({
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
                "data": "price"
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
                "desc"
            ]
        ]
    });
    makeEditable();
});
*/
