/**
 * Created by MyPC on 27/02/2018.
 */
// var btnActive = "<input class='btn active btn-check-target' type='button' onclick='activeCriteria(this)' value='Kích hoạt'/>"
// var btnDeactive = "<input class='btn active btn-checked' type='button' onclick='deactiveCriteria(this)' value='Đã kích hoạt'/>"
// var btnActive = "<input data-toggle='toggle' type='checkbox' onclick='activeCriteria(this)'/>"
// var btnDeactive = "<input checked data-toggle='toggle' type='checkbox' onclick='deactiveCriteria(this)'/>"
var btnDeactive = "<label class='tgl'>" +
    "<input onclick='deactiveCriteria(this)' type='checkbox' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnActive = "<label class='tgl'>" +
    "<input onclick='activeCriteria(this)' type='checkbox'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnDeactiveForm = "<label class='tgl' id='btnStatus'>" +
    "<input id='btnStt' type='checkbox' onclick='switchStatus()' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnActiveForm = "<label class='tgl' id='btnStatus'>" +
    "<input id='btnStt' type='checkbox' onclick='switchStatus()'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var linkShow = "<a href='#' onclick='initNewForm()' data-toggle='modal' data-target='#InfoModal'><i class='fa fa-plus' style='font-size: 20px'></i>    </a>";
var linkUpdate = "<a href='#' onclick='initUpdateForm(this)' data-toggle='modal' data-target='#InfoModal'><i class='fa fa-pencil' style='font-size: 20px'></i>    </a>";
var linkDelete = "<a href='#'><i class='fa fa-trash' style='font-size: 20px'></i>    </a>";
// var btnActiveForm = "<input class='btn active btn-check-target' id='btnStatus' type='button' name='btnStatus' value='Kích hoạt'/>"
// var btnDeactiveForm = "<input class='btn active btn-checked' id='btnStatus' type='button' name='btnStatus' value='Vô hiệu'/>"
// var btnActiveForm = "<input class='btn active btn-check-target' id='btnStatus' type='button' name='btnStatus' value='Kích hoạt'/>"
// var btnDeactiveForm = "<input class='btn active btn-checked' id='btnStatus' type='button' name='btnStatus' value='Vô hiệu'/>"
var showedTable, saveOpt, method, selectedId = "", status, btn, tmp;
$(document).ready(function () {
    status=1;
    loadCriteriaTable();
});
function switchStatus(){console.log(status);if(status==0){status=1;}else status=0;console.log(status);}
function initNewForm() {
    selectedId = "";
    saveOpt = 'create';
    method = 'POST';
    $("#critName").val("");
    $("#critType").val("");
    status=1;
    $("#btnStatus").replaceWith(btnDeactiveForm)
}
function initUpdateForm(el) {
    var data = $("#tbl-criteria").DataTable().row($(el).parents('tr')).data();
    selectedId = data.id;
    saveOpt = "update";
    method = 'PUT';
    $("#critName").val(data.criteria);
    // $("#critType").val(data.typeByTypeId.id);
    if (!data.status){ $("#btnStatus").replaceWith(btnActiveForm); status=0;} else {$("#btnStatus").replaceWith(btnDeactiveForm); status=1}
}
var status_to_button = function (data, type, full, meta) {
    // console.log(data);
    if (data == false)return btnActive;
    return btnDeactive;
}
var getTypeName = function (data, type, full, meta) {
    return data["description"];
}

function activeCriteria(el) {
    var data = $("#tbl-criteria").DataTable().row($(el).parents('tr')).data();
    var criteria = {"id": data.id};
    $.ajax({
        url: '/sfms/api/criteria/active',
        type: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(criteria),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                console.log(data);
                reloadTable();
            }
        },
        error: function () {
            alert("fuck")
        }
    })
}
function deactiveCriteria(el) {
    var data = $("#tbl-criteria").DataTable().row($(el).parents('tr')).data();
    var criteria = {"id": data.id};
    $.ajax({
        url: '/sfms/api/criteria/deactive',
        type: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(criteria),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                console.log(data);
                reloadTable();
            }
        },
        error: function () {
            alert("fuck")
        }
    })
}

function reloadTable() {
    setTimeout(function () {
        showedTable.ajax.reload(null, false);// reload without come back to the first page
    }, 200); //reload the table after 0.2s
}

$("#btnSave").click(function () {
    // var num=0;
    var stt;
    console.log(status);
    if(status==1)stt=true;else stt=false;
    var criteria = {
        "id": selectedId,
        "criteria": $("#critName").val(),
        "typeByTypeId": {"id": $("#critType").val()},
        // "typeByTypeId": num,
        "status": stt
    };
    console.log(criteria);
    $.ajax({
        url: '/sfms/api/criteria/' + saveOpt,
        type: method,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(criteria),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                console.log(data);
                reloadTable();
                initNewForm();
                $("#InfoModal").modal('toggle');
            }
        },
        error: function () {
            alert("somethin went wrong, go fuck yoself")
        }
    })
})
$(".btn-check-target").click(function () {
    btn = $(this);
    tmp = $(this).val();
    selectObjBtn();
});
function selectObjBtn() {
    if (tmp == "Vô hiệu") {
        btn.removeClass("btn-checked");
        btn.addClass("btn-check-target");
        btn.val("Kích hoạt");
        status = true;
        console.log(status);
        // alert("haha");
    }
    if (tmp == "Kích hoạt") {
        btn.removeClass("btn-check-target");
        btn.addClass("btn-checked");
        btn.val("Vô hiệu");
        status = false;
        console.log(status);
    }
}

function loadCriteriaTable() {
    $('#tbl-criteria').DataTable().destroy();
    showedTable = $('#tbl-criteria').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/criteria/list",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                // {"data": "id"},
                {"data": "criteria"},
                // {"data": "typeByTypeId", "render": getTypeName, "visible": false},
                {
                    "data": "status",
                    "render": status_to_button
                },
                {
                    "data": null,
                    "defaultContent": linkShow + '  ' + linkUpdate
                }
            ],
            initComplete: function () {
                // this.api().columns([0]).every(function () {
                //     var column = this;
                //     var select = $('<datalist id="listCrit"></datalist>').appendTo($("#filterName").empty());
                //     select.append('<option value=""></option>');
                //     var input = $('<input type="text" value="" list="listCrit" placeholder="Tiêu chí"/>')
                //         .appendTo($("#filterName"))
                //         .on("keyup keydown change", function () {
                //             var val = $.fn.dataTable.util.escapeRegex(
                //                 $(this).val()
                //             );
                //             // alert(val);
                //             column
                //                 .search(val ? '^' + val + '$' : '', true, false)
                //                 .search(val)
                //                 .draw();
                //         });
                //     select.on("change", function () {
                //         var val = $.fn.dataTable.util.escapeRegex(
                //             $(this).val()
                //         );
                //         alert(val);
                //         column
                //         // .search(val ? '^' + val + '$' : '', true, false)
                //             .search(val)
                //             .draw();
                //     });
                //     column.data().unique().sort().each(function (d, j) {
                //         select.append('<option value="' + d + '">' + d + '</option>')
                //     });
                // });
                // this.api().columns([1]).every(function () {
                //     var column = this;
                //     var input = $('<input type="text" value="" list="listType" placeholder="Đối tượng"/>')
                //             .appendTo($("#filterType").empty())
                //             .on('change keyup keydown', function () {
                //                 var val = $.fn.dataTable.util.escapeRegex(
                //                     $(this).val()
                //                 );
                //                 column
                //                 // .search(val ? '^' + val + '$' : '', true, false)
                //                     .search(val)
                //                     .draw();
                //             })
                //         ;
                //     var opts = new Array(column.data().length);
                //     var datalist = $('<datalist id="listType"></datalist>').appendTo($("#filterType"))
                //         .on("change", function () {
                //             var val = $.fn.dataTable.util.escapeRegex(
                //                 $(this).val()
                //             );
                //             // alert(val);
                //             column
                //             // .search(val ? '^' + val + '$' : '', true, false)
                //                 .search(val)
                //                 .draw();
                //         });
                //     datalist.append('<option value=""></option>');
                //     column.data().unique().sort().each(function (d, j) {
                //         if (!opts.includes(d["description"])) {
                //             opts.push(d["description"]);
                //             datalist.append('<option value="' + d["description"] + '">' + d["description"] + '</option>')
                //         }
                //     });
                // });
            },
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy. <a href='/sfms/modify-feedback/target'>Thêm đối tượng</a>",
                "info": "Hiển thị từ _START_ tới _END_ trong số _TOTAL_ kết quả",
                "infoEmpty": "Hiển thị 0 tới 0 trong số 0 kết quả",
                "infoFiltered": "(filtered from _MAX_ total entries)",
                "infoPostFix": "",
                "thousands": ",",
                "lengthMenu": "_MENU_ kết quả mỗi trang",
                "loadingRecords": "Đang tải...",
                "processing": "Đang xử lý...",
                "search": "Tìm:",
                "zeroRecords": "Không tìm thấy kết quả phù hợp",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Sau",
                    "previous": "Trước"
                },
                "aria": {
                    "sortAscending": ": kích hoạt để sắp xếp tăng dần",
                    "sortDescending": ": kích hoạt để sắp xếp giảm dần"
                }
            }
        }
    );
}

