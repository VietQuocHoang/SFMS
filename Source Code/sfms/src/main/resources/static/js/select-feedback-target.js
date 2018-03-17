/**
 * Created by MyPC on 29/01/2018.
 */
var modifyconductorlink = "<a class='add-inf-item-link' href='/sfms/modify-feedback-conductors'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var modifyviewerlink = "<a class='add-inf-item-link'    href='/sfms/modify-feedback-viewers'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var linkShow = "<a href='/sfms/modify-feedback/target'><i class='fa fa-plus' style='font-size: 24px'></i>    </a>";
var linkDelete = "<a href='#'><i class='fa fa-trash' style='font-size: 24px'></i>    </a>";
var selectedtarget;
var showedTargetTab;
var tmp;
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
                {
                    "data": "id",
                },
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
                    "defaultContent": linkShow +'  '+ linkDelete
                }
            ],
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
                {
                    "data": "id",
                },
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
                    "defaultContent": linkShow +'  '+ linkDelete
                }
            ],
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
                {
                    "data": "id",
                },
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
                    "defaultContent": linkShow +'  '+ linkDelete
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
                    "data": "id"
                },
                {"data": "className"},
                {"data": "courseByCourseId.name"},
                {"data": "courseByCourseId.code"},
                {"data": "semesterBySemesterId.title"},
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
                    "defaultContent": linkShow +'  '+ linkDelete
                }
            ],
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
$(".btn-check-target").click(function () {
    tmp = $(this).val();
    if (selectedtarget != null) {
        selectedtarget.removeClass("btn-checked");
        selectedtarget.addClass("btn-check-target");
        selectedtarget.val("Chọn");
    }
    if (tmp == "Chọn") {
        selectedtarget = $(this);
        selectedtarget.removeClass("btn-check-target");
        selectedtarget.addClass("btn-checked");
        selectedtarget.val("Đã chọn");
    }
});
// $(".btn-checked").click(function(){
//     selectedtarget.removeClass("btn-checked");
//     selectedtarget.addClass("btn-check-target");
//     selectedtarget.text("Chọn");
//     selectedtarget = null;
// });
