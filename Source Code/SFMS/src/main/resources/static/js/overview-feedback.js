/**
 * Created by MyPC on 27/02/2018.
 */
var modifyconductorlink = "<a class='add-inf-item-link' href='modify-feedback-conductors'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var modifyviewerlink = "<a class='add-inf-item-link' href='modify-feedback-viewers'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var linkShow = "<a href='modify-feedback-target'><i class='fa fa-plus'></i></a>";
var linkDelete = "<a href='#'><i class='fa fa-trash'></i></a>";
var linkUpdate = "<a href='modify-feedback-target'><i class='fa fa-pencil'></i></a>";
var showedTargetTab;
$(document).ready(function () {
    loadDepartmentTable();
    loadMajorTable();
    loadCourseTable();
    loadClazzTable();
    switch ($('#typeId').val()) {
        case '1':
            showedTargetTab = $('#nav-major');
            showedTargetTab.addClass("show active");
            break;
        case '2':
            showedTargetTab = $('#nav-course');
            showedTargetTab.addClass("show active");
            break;
        case '3':
            showedTargetTab = $('#nav-clazz');
            showedTargetTab.addClass("show active");
            break;
        case '4':
            showedTargetTab = $('#nav-department');
            showedTargetTab.addClass("show active");
            break;
        default :
            showedTargetTab = $('#nav-major');
            showedTargetTab.addClass("show active");
            break;
    }
    
    // changeSemester(); 
    // changeStart(); changeEnd();
    // loadDepartmentTable();
    // loadMajorTable();
    // loadCourseTable();
    // loadClazzTable();
});
function loadDepartmentTable() {
    $('#tbl-departments').DataTable().destroy();
    $('#tbl-departments').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/targets/departments",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {"data": "name"},
                {//column for modify conductor
                    "data": null,
                    "defaultContent": modifyconductorlink
                },
                {//column for modifyviewer
                    "data": null,
                    "defaultContent": modifyviewerlink
                },
                {//column for view detail-update-delete
                    "data": null,
                    "defaultContent": linkShow + linkUpdate + linkDelete
                }
            ],
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy. <a href='/sfms/modify-feedback-target'>Thêm đối tượng</a>",
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
function loadMajorTable() {
    $('#tbl-majors').DataTable().destroy();
    $('#tbl-majors').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/targets/majors",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {"data": "name"},
                {"data": "code"},
                {//column for modify conductor
                    "data": null,
                    "defaultContent": modifyconductorlink
                },
                {//column for modifyviewer
                    "data": null,
                    "defaultContent": modifyviewerlink
                },
                {//column for view detail-update-delete
                    "data": null,
                    "defaultContent": linkShow + linkUpdate + linkDelete
                }
            ],
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy. <a href='/sfms/modify-feedback-target'>Thêm đối tượng</a>",
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
function loadCourseTable() {
    $('#tbl-courses').DataTable().destroy();
    $('#tbl-courses').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/targets/courses",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {"data": "name"},
                {"data": "code"},
                {//column for modify conductor
                    "data": null,
                    "defaultContent": modifyconductorlink
                },
                {//column for modifyviewer
                    "data": null,
                    "defaultContent": modifyviewerlink
                },
                {//column for view detail-update-delete
                    "data": null,
                    "defaultContent": linkShow + linkUpdate + linkDelete
                }
                // {
                //     "data": "majorCoursesById",
                //     "render": function(data, type, row, meta){
                //
                //     }
                // }
            ],
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy. <a href='/sfms/modify-feedback-target'>Thêm đối tượng</a>",
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
function loadClazzTable() {
    $('#tbl-clazzes').DataTable().destroy();
    $('#tbl-clazzes').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/targets/clazzes",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [ //define columns for the table
                // data for the cell from the returned list
                {
                    "data": "id",
                    "visible": false //hide the column processID
                },
                {"data": "courseByCourseId.name"},
                {"data": "courseByCourseId.code"},
                {"data": "semesterBySemesterId.title"},
                {"data": "className"},
                {"data": "userByLecturerId.fullname"},
                {//column for modify conductor
                    "data": null,
                    "defaultContent": modifyconductorlink
                },
                {//column for modifyviewer
                    "data": null,
                    "defaultContent": modifyviewerlink
                },
                {//column for view detail-update-delete
                    "data": null,
                    "defaultContent": linkShow + linkUpdate + linkDelete
                }
            ],
            "language": {
                "decimal": "",
                "emptyTable": "Không kết quả nào được tìm thấy. <a href='/sfms/modify-feedback-target'>Thêm đối tượng</a>",
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
$("#feedback-title").focusout(function () {
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/title",
            type: "PUT",
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
            type: "PUT",
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
    changeStart();
})

$("#enddate").change(function () {
    changeEnd();
})

$("#typeId").change(function () {
    let typeData = {
        "id": $("#typeId").val(),
    }
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/type",
            type: "PUT",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(typeData),
            success: function (data, status, xhr) {
                switch (data.typeByTypeId.description) {
                    case "Chuyên ngành":
                        loadMajorTable();
                        showedTargetTab.removeClass("show active");showedTargetTab=$('#nav-major'); showedTargetTab.addClass("show active");
                        break;
                    case "Môn học":
                        loadCourseTable();
                        showedTargetTab.removeClass("show active");showedTargetTab=$('#nav-course'); showedTargetTab.addClass("show active");
                        break;
                    case "Lớp":
                        loadClazzTable();
                        showedTargetTab.removeClass("show active");showedTargetTab=$('#nav-clazz'); showedTargetTab.addClass("show active");
                        break;
                    case "Phòng ban":
                        loadDepartmentTable()
                        showedTargetTab.removeClass("show active");showedTargetTab=$('#nav-department'); showedTargetTab.addClass("show active");
                        break;
                    default: break;
                }
            },
            error: function (result) {
                alert("fuck");
            }
        }
    );
})

$("#semesterId").change(function () {
    changeSemester();
})

function changeSemester() {
    let semesterData = {
        "id": $("#semesterId").val(),
        // "title": '',
        // "endDate": '',
        // "startDate": ''
    };
    console.log(semesterData);
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/semester",
            type: "PUT",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(semesterData),
            success: function (data, status, xhr) {
                let startd = new Date(parseInt(data.startDate));
                let endd = new Date(parseInt(data.endDate));
                $("#startdate").attr(
                    {
                        "min": $.datepicker.formatDate('yy-mm-dd', startd),
                        "max": $.datepicker.formatDate('yy-mm-dd', endd)
                    }
                );
                $("#startdate").val('');
                $("#enddate").attr(
                    {
                        "min": $.datepicker.formatDate('yy-mm-dd', startd),
                        "max": $.datepicker.formatDate('yy-mm-dd', endd)
                    }
                );
                $("#enddate").val('');
            },
            error: function (result) {
                alert("fuck");
            }
        }
    )
    ;
}

function changeStart() {
    let feedbackData = {
        "startDate": $("#startdate").val()
    }
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/start",
            type: "PUT",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(feedbackData),
            success: function (data, status, xhr) {
                $("#enddate").attr(
                    {
                        "min": $.datepicker.formatDate('yy-mm-dd', new Date(parseInt(data.startDate))),
                    }
                );
                $("#enddate").val($.datepicker.formatDate('yy-mm-dd', new Date(parseInt(data.endDate))))
            },
            error: function (xhr) {
                alert("ihi")
            }
        }
    );
}

function changeEnd() {
    let feedbackData = {
        "endDate": $("#startdate").val()
    }
    $.ajax(
        {
            url: "/sfms/api/modify-feedback/end",
            type: "PUT",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(feedbackData),
            success: function (data, status, xhr) {
                alert("hehe")
            },
            error: function (result) {
                alert("ihi")
            }
        }
    );
}


// $("#enddate").focusout(function () {
//     $.ajax(
//         {
//             url: "/sfms/api/modify-feedback/title",
//             type: "PUT",
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

// $(".conductor-field").hover(function () {
//     $(this).children(".add-inf-item-link").show();
// })
// $(".conductor-field").mouseout(function () {
//     $(this).children(".add-inf-item-link").hide();
// })
// $(".viewer-field").hover(function () {
//     $(this).children(".add-inf-item-link").show();
// })
// $(".viewer-field").mouseout(function () {
//     $(this).children(".add-inf-item-link").hide();
// })
// $(".add-inf-item-link").hover(function () {
//     $(this).show();
// })
// $(".add-inf-item-link").mouseout(function (){
//     $(this).hide();
// })

