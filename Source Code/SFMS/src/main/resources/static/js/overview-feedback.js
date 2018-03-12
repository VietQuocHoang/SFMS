/**
 * Created by MyPC on 27/02/2018.
 */
$(document).ready(function () {
    $('#tbl-targets').DataTable(
        {
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy",
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
                    "last": "Trước",
                    "next": "Sau",
                    "previous": "Cuối"
                },
                "aria": {
                    "sortAscending": ": kích hoạt để sắp xếp tăng dần",
                    "sortDescending": ": kích hoạt để sắp xếp giảm dần"
                }
            }
        }
    );
});

$("#feedback-title").focusout(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/title",
            method: "PUT",
            dataType: 'application/json',
            data: {"title": $("#feedback-title").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})

$("#feedback-description").focusout(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/description",
            method: "PUT",
            dataType: 'application/json',
            data: {"description": $("#feedback-description").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})

$("#startdate").change(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/start",
            method: "PUT",
            dataType: 'application/json',
            data: {"startdate": $("#startdate").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})


$("#enddate").change(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/end",
            method: "PUT",
            dataType: 'application/json',
            data: {"enddate": $("#enddate").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})

$("#enddate").change(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/end",
            method: "PUT",
            dataType: 'application/json',
            data: {"enddate": $("#enddate").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})


$("#typeId").change(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/type",
            method: "PUT",
            dataType: 'application/json',
            data: {"typeId": $("#typeId").val()},
            success: function (result) {

            },
            error: function (result) {

            }
        }
    );
})


$("#semesterId").change(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/semester",
            method: "PUT",
            dataType: 'application/json',
            data: {"semesterId": $("#semesterId").val()},
            success: function (result) {
                // $("#semesterId").setAttribute("min",)
                alert("fuq");
            },
            error: function (result) {
                alert("fuck");
            }
        }
    );
})
// $("#enddate").focusout(function () {
//     $.ajax(
//         {
//             url: "/sfms/api/modify-feedback/title",
//             method: "PUT",
//             dataType: 'application/json',
//             data: {"title": $("#enddate").val()},
//             success: function (result) {
//
//             },
//             error: function (result) {
//
//             }
//         }
//     );
// })

$(".conductor-field").hover(function () {
    $(this).children(".add-inf-item-link").show();
})
$(".conductor-field").mouseout(function () {
    $(this).children(".add-inf-item-link").hide();
})
$(".viewer-field").hover(function () {
    $(this).children(".add-inf-item-link").show();
})
$(".viewer-field").mouseout(function () {
    $(this).children(".add-inf-item-link").hide();
})
$(".add-inf-item-link").hover(function () {
    $(this).show();
})
// $(".add-inf-item-link").mouseout(function (){
//     $(this).hide();
// })

