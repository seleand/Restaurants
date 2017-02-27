/**
 * Created by Asus on 24.02.2017.
 */

function addDish() {
    form.find(":input").val("");
    form.find("input[id='restaurantId']").val(rId);
    // form.find("#id").val("");
    $('#editRow').modal();
}

function updateDishRow(id) {
    $.get(ajaxUrl + '/dish/' + id, function (data) {
        $.each(data, function (key, value){
        if (key == 'price') {
            form.find("input[name='" + key + "']").val(value / 100);
        }
        else {
            form.find("input[name='" + key + "']").val(value);
        }
    });
    form.find("input[id='restaurantId']").val(rId);
    $('#editRow').modal();
    });
}

function deleteDishRow(id) {
    $.ajax({
        // url: ajaxUrl + restaurantId + '/dish/' + id,
        url: ajaxUrl + '/dish/' + id,
        type: 'DELETE',
        success: function () {
            // updateTable(restaurantId);
            updateTable();
            successNoty('Deleted');
        }
    });
}

function saveDish() {
    $.ajax({
        type: "POST",
        url: ajaxUrlPost,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

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
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDishEditBtn
            },
            {
                "defaultContent": "",
                "orderable": false,
                "render": renderDishDeleteBtn
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

function renderDishEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateDishRow(' + row.id + ');">Edit</a>';
    }
}

function renderDishDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteDishRow(' + row.id + ');">Delete</a>';
    }
}
