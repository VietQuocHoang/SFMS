/**
 * Created by MyPC on 29/01/2018.
 */
var modifyconductorlink = "<a class='add-inf-item-link' href='/sfms/modify-feedback-conductors'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var modifyviewerlink = "<a class='add-inf-item-link'    href='/sfms/modify-feedback-viewers'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var btnAddClazz = "<input class='btn active btn-check-target' type='button' onclick='addClazzTarget(this)' value='Chọn'/>"
var btnAddCourse = "<input class='btn active btn-check-target' type='button' onclick='addCourseTarget(this)' value='Chọn'/>"
var btnAddMajor = "<input class='btn active btn-check-target' type='button' onclick='addMajorTarget(this)' value='Chọn'/>"
var btnAddDepartment = "<input class='btn active btn-check-target' type='button' onclick='addDepartmentTarget(this)' value='Chọn'/>"
var btnRemoveClazz = "<input class='btn active btn-check-target' type='button' onclick='removeClazzTarget(this)' value='Bỏ chọn'/>"
var btnRemoveCourse = "<input class='btn active btn-check-target' type='button' onclick='removeCourseTarget(this)' value='Bỏ chọn'/>"
var btnRemoveMajor = "<input class='btn active btn-check-target' type='button' onclick='removeMajorTarget(this)' value='Bỏ chọn'/>"
var btnRemoveDepartment = "<input class='btn active btn-check-target' type='button' onclick='removeDepartmentTarget(this)' value='Bỏ chọn'/>"
var showedTargetTab;
var showedTable;
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
            showedTable = $("#tbl-majors");
            break;
        case '2':
            showedTargetTab = $('#nav-course');
            showedTargetTab.addClass("show active");
            showedTable = $("#tbl-courses");
            break;
        case '3':
            showedTargetTab = $('#nav-clazz');
            showedTargetTab.addClass("show active");
            showedTable = $("#tbl-clazzes");
            break;
        case '4':
            showedTargetTab = $('#nav-department');
            showedTargetTab.addClass("show active");
            showedTable = $("#tbl-departments");
            break;
        default :
            showedTargetTab = $('#nav-major');
            showedTargetTab.addClass("show active");
            showedTable = $("#tbl-majors");
            break;
    }
});

function selected_to_button(){
    return btnAddClazz;
}
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
                    "render": selected_to_button
                },
                {"data": "name"},
                {//column for modify conductor
                    "data": null,
                    "defaultContent": modifyconductorlink
                },
                {//column for modifyviewer
                    "data": null,
                    "defaultContent": modifyviewerlink
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
                    "render": selected_to_button
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
                    "render":selected_to_button
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
                    "data": "id",
                    "render": selected_to_button
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
function removeTarget(target) {
    $.ajax({
        url: '/sfms/api/modify-feedback/remove/target',
        type: 'DELETE',
        dataType: 'json',
        contentType: 'application/json',
        dtaa: JSON.stringify(target),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                reloadTable();
            }
        },
        error: function () {
            alert("fuck")
        }
    })
}
function removeClazzTarget(el) {
    var data = $("#tbl-clazzes").DataTable().row($(el).parents('tr')).data();
    var clazz = {"id": data.id};
    removeTarget(clazz);
}
function removeCourseTarget(el) {
    var data = $("#tbl-courses").DataTable().row($(el).parents('tr')).data();
    var course = {"id": data.id};
    removeTarget(course);
}
function removeMajorTarget(el) {
    var data = $("#tbl-majors").DataTable().row($(el).parents('tr')).data();
    var major = {"id": data.id};
    removeTarget(major);
}
function removeDepartmentTarget(el) {
    var data = $("#tbl-departments").DataTable().row($(el).parents('tr')).data();
    var department = {"id": data.id};
    removeTarget(department);
}
// $(".btn-checked").click(function(){
//     selectedtarget.removeClass("btn-checked");
//     selectedtarget.addClass("btn-check-target");
//     selectedtarget.text("Chọn");
//     selectedtarget = null;
// });
// $(".btn-check-target").click(function () {
//     tmp = $(this).val();
//     if (selectedtarget != null) {
//         selectedtarget.removeClass("btn-checked");
//         selectedtarget.addClass("btn-check-target");
//         selectedtarget.val("Chọn");
//     }
//     if (tmp == "Chọn") {
//         selectedtarget = $(this);
//         selectedtarget.removeClass("btn-check-target");
//         selectedtarget.addClass("btn-checked");
//         selectedtarget.val("Đã chọn");
//     }
// });