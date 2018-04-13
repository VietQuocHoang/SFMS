/**
 * Created by MyPC on 29/01/2018.
 */
var modifyconductorlink = "<a class='add-inf-item-link' href='/sfms/modify-feedback-conductors'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
var modifyviewerlink = "<a class='add-inf-item-link'    href='/sfms/modify-feedback-viewers'><i class='fa fa-pencil'></i> Chỉnh sửa </a>";
// var btnAddClazz = "<input class='btn active btn-check-target' type='button' onclick='addClazzTarget(this)' value='Chọn'/>"
// var btnAddCourse = "<input class='btn active btn-check-target' type='button' onclick='addCourseTarget(this)' value='Chọn'/>"
// var btnAddMajor = "<input class='btn active btn-check-target' type='button' onclick='addMajorTarget(this)' value='Chọn'/>"
// var btnAddDepartment = "<input class='btn active btn-check-target' type='button' onclick='addDepartmentTarget(this)' value='Chọn'/>"
// var btnRemoveClazz = "<input class='btn active btn-checked' type='button' onclick='removeClazzTarget(this)' value='Bỏ chọn'/>"
// var btnRemoveCourse = "<input class='btn active btn-checked' type='button' onclick='removeCourseTarget(this)' value='Bỏ chọn'/>"
// var btnRemoveMajor = "<input class='btn active btn-checked' type='button' onclick='removeMajorTarget(this)' value='Bỏ chọn'/>"
// var btnRemoveDepartment = "<input class='btn active btn-checked' type='button' onclick='removeDepartmentTarget(this)' value='Bỏ chọn'/>"
var btnAddClazz = "<label class='tgl'>" +
    "<input onclick='addClazzTarget(this)' type='checkbox'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>";
var btnAddCourse = "<label class='tgl'>" +
    "<input onclick='addCourseTarget(this)' type='checkbox'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>";
var btnAddMajor = "<label class='tgl'>" +
    "<input onclick='addMajorTarget(this)' type='checkbox'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>";
var btnAddDepartment = "<label class='tgl'>" +
    "<input onclick='addDepartmentTarget(this)' type='checkbox'/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>";
var btnRemoveClazz = "<label class='tgl'>" +
    "<input onclick='removeClazzTarget(this)' type='checkbox' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnRemoveCourse = "<label class='tgl'>" +
    "<input onclick='removeCourseTarget(this)' type='checkbox' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnRemoveMajor = "<label class='tgl'>" +
    "<input onclick='removeMajorTarget(this)' type='checkbox' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"
var btnRemoveDepartment = "<label class='tgl'>" +
    "<input onclick='removeDepartmentTarget(this)' type='checkbox' checked/>" +
    "<span class='tgl_body'>" +
    "<span class='tgl_switch'></span>" +
    "<span class='tgl_track'>" +
    "<span class='tgl_bgd'></span>" +
    "<span class='tgl_bgd tgl_bgd-negative'></span></span></span></label>"

var showedTargetTab;
var showedTable;
var tmp;
var targets;
// var ;
$(document).ready(function () {
    switch ($('#typeId').val()) {
        case '1':
            showedTargetTab = $('#nav-major');
            showedTargetTab.addClass("show active");
            loadMajorTable();
            setInterval(function () {
                loadMajorTargets();
                showedTable.ajax.reload(null, false); // user paging is not reset on reload
            }, 3000);
            break;
        case '2':
            showedTargetTab = $('#nav-course');
            showedTargetTab.addClass("show active");
            loadCourseTable();
            setInterval(function () {
                loadCourseTargets();
                showedTable.ajax.reload(null, false); // user paging is not reset on reload
            }, 3000);
            break;
        case '3':
            showedTargetTab = $('#nav-clazz');
            showedTargetTab.addClass("show active");
            loadClazzTable();
            setInterval(function () {
                loadClazzTargets();
                showedTable.ajax.reload(null, false); // user paging is not reset on reload
            }, 3000);
            break;
        case '4':
            showedTargetTab = $('#nav-department');
            showedTargetTab.addClass("show active");
            loadDepartmentTable();
            setInterval(function () {
                loadDepartmentTargets();
                showedTable.ajax.reload(null, false); // user paging is not reset on reload
            }, 3000);
            break;
        default :
            showedTargetTab = $('#nav-major');
            showedTargetTab.addClass("show active");
            loadMajorTable();
            // showedTable = $("#tbl-majors");
            break;
    }
});

var selected_clazz_to_button = function (data, type, full, meta) {
    if (targets.length == 0)return btnAddClazz;
    for (var c in targets) {
        let target = targets[c];
        if (data == target["id"])return btnRemoveClazz;
    }
    return btnAddClazz;
}
var selected_course_to_button = function (data, type, full, meta) {
    if (targets.length == 0)return btnAddCourse;
    for (var c in targets) {
        let target = targets[c];
        if (data == target["id"])return btnRemoveCourse;
    }
    return btnAddCourse;
}
var selected_major_to_button = function (data, type, full, meta) {
    if (targets.length == 0)return btnAddMajor;
    for (var c in targets) {
        let target = targets[c];
        if (data == target["id"])return btnRemoveMajor;
    }
    return btnAddMajor;
}
var selected_department_to_button = function (data, type, full, meta) {
    if (targets.length == 0)return btnAddDepartment;
    for (var c in targets) {
        let target = targets[c];
        if (data == target["id"])return btnRemoveDepartment;
    }
    return btnAddDepartment;
}

var course_to_major = function (data, type, full, meta) {
    let major = data;
    if (major["code"] != null && major["name"] != null)return major["code"] + ' - ' + major["name"];
    if (major["name"] != null)return major["name"];
    return major["code"]
}

var course_to_nameandcode = function (data, type, full, meta) {
    if (data["code"] != null && data["name"] != null)return data["code"] + ' - ' + data["name"];
    if (data["name"] != null)return data["name"];
    return data["code"]
}

var lecturer_to_nameandcode = function (data, type, full, meta) {
    if (data["code"] != null && data["fullname"] != null)return data["code"] + ' - ' + data["fullname"];
    if (data["fullname"] != null)return data["fullname"];
    return data["code"]
}

function reloadTable() {
    // alert("hehe");
    switch ($('#typeId').val()) {
        case '1':loadMajorTargets();break;
        case '2':loadCourseTargets();break;
        case '3':loadClazzTargets();break;
        case '4':loadDepartmentTargets();break;
    }
    setTimeout(function () {
        showedTable.ajax.reload(null, false);// reload without come back to the first page
    }, 2000); //reload the table after 0.2s
}

// $("#filter-clazz-lecturer").change(function () {
//     loadClazzTable();
// });
// $("#filter-clazz-major").change(function () {
//     loadClazzTable();
// });
// $("#filter-clazz-semester").change(function () {
//     loadClazzTable();
// });
// $("#filter-clazz-course").change(function () {
//     loadClazzTable();
// });
// $("#filter-clazz-lecturer").change(function(){alert("hihi");loadClazzTable();});
// $("#filter-clazz-major").change(function(){alert("hihi");loadClazzTable();});
// $("#filter-clazz-semester").change(function(){alert("hihi");loadClazzTable();});
// $("#filter-clazz-course").change(function(){alert("hihi");loadClazzTable();});
function loadDepartmentTable() {
    $('#tbl-departments').DataTable().destroy();
    loadDepartmentTargets();
    showedTable=$('#tbl-departments').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/departments",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {
                    "data": "id",
                    "render": selected_department_to_button
                },
                {"data": "name"},
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
    loadMajorTargets();
    showedTable=$('#tbl-majors').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/majors",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {
                    "data": "id",
                    "render": selected_major_to_button
                },
                {"data": "name"},
                {"data": "code"},
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
    loadCourseTargets();
    showedTable=$('#tbl-courses').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/courses",
                "dataSrc": "",
                "type": "GET"
            },
            "columns": [
                {
                    "data": "id",
                    "render": selected_course_to_button
                },
                {"data": "name"},
                {"data": "code"},
                {"data": "majorByMajorId", "render": course_to_major},
            ],
            initComplete: function () {
                this.api().columns([3]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listMajor" placeholder="Chuyên ngành"/>')
                            .appendTo($("#filter-course-major").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listMajor"></datalist>').appendTo($("#filter-course-major"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        // console.log(d);
                        if (!recs.includes(d["name"])) {
                            recs.push(d["name"]);
                            let major = d;
                            if (major["code"] != null && major["name"] != null) datalist.append('<option value="' + major["code"] + ' - ' + major["name"] + '">' + major["code"] + ' - ' + major["name"] + '</option>');
                            else datalist.append('<option value="' + major["name"] + '">' + major["name"] + '</option>');
                        }
                    });
                });
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
function loadClazzTable() {
    $('#tbl-clazzes').DataTable().destroy();
    loadClazzTargets();
    showedTable = $('#tbl-clazzes').DataTable(
        {
            "ajax": {
                "url": "/sfms/api/modify-feedback/list/clazzes",
                "dataSrc": "",
                "type": "GET",
                // "dataType": "json",
                // "contentType": "application/json",
                // "data": JSON.stringify(filter)
            },
            "columns": [ //define columns for the table
                // data for the cell from the returned list
                {
                    "data": "id",
                    "render": selected_clazz_to_button
                },
                {"data": "className"},
                {"data": "courseByCourseId.majorByMajorId", "visible": false, "render": course_to_major},
                {"data": "courseByCourseId", "visible": false, "render": course_to_nameandcode},
                {"data": "courseByCourseId.name"},
                {"data": "courseByCourseId.code"},
                {"data": "semesterBySemesterId.title"},
                {"data": "userByLecturerId", "render": lecturer_to_nameandcode},
            ],
            initComplete: function () {
                this.api().columns([1]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listClazz" placeholder="Lớp" style="width: 97%"/>')
                            .appendTo($("#filter-clazz-name").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listClazz"></datalist>').appendTo($("#filter-clazz-name"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        if (!recs.includes(d)) {
                            recs.push(d);
                            datalist.append('<option value="' + d + '">' + d + '</option>');
                        }
                    });
                });
                this.api().columns([2]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listMajor" placeholder="Chuyên ngành" style="width: 97%"/>')
                            .appendTo($("#filter-clazz-major").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listMajor"></datalist>').appendTo($("#filter-clazz-name"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        // console.log(d);
                        if (!recs.includes(d["name"])) {
                            recs.push(d["name"]);
                            let major = d;
                            if (major["code"] != null && major["name"] != null) datalist.append('<option value="' + major["code"] + ' - ' + major["name"] + '">' + major["code"] + ' - ' + major["name"] + '</option>');
                            else datalist.append('<option value="' + major["name"] + '">' + major["name"] + '</option>');
                        }
                    });
                });
                this.api().columns([3]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listCourse" placeholder="Môn học" style="width: 97%"/>')
                            .appendTo($("#filter-clazz-course").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listCourse"></datalist>').appendTo($("#filter-clazz-course"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        // console.log(d);
                        if (!recs.includes(d["name"])) {
                            recs.push(d["name"]);
                            if (d["code"] != null && d["name"] != null)
                                datalist.append('<option value="' + d["code"] + ' - ' + d["name"] + '">' + d["code"] + ' - ' + d["name"] + '</option>');
                            else
                                datalist.append('<option value="' + d["name"] + '">' + d["name"] + '</option>');
                        }
                    });
                });
                this.api().columns([6]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listSemester" placeholder="Học kì" style="width: 97%"/>')
                            .appendTo($("#filter-clazz-semester").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listSemester"></datalist>').appendTo($("#filter-clazz-semester"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        // console.log(d);
                        if (!recs.includes(d)) {
                            recs.push(d);
                            datalist.append('<option value="' + d + '">' + d + '</option>');
                        }
                    });
                });
                this.api().columns([7]).every(function () {
                    var column = this;
                    var input = $('<input type="text" value="" list="listLecturer" placeholder="Giảng viên" style="width: 97%"/>')
                            .appendTo($("#filter-clazz-lecturer").empty())
                            .on('change keyup keydown', function () {
                                var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                );
                                column
                                // .search(val ? '^' + val + '$' : '', true, false)
                                    .search(val)
                                    .draw();
                            })
                        ;
                    var datalist = $('<datalist id="listLecturer"></datalist>').appendTo($("#filter-clazz-semester"))
                        .on("change", function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                $(this).val()
                            );
                            // alert(val);
                            column
                            // .search(val ? '^' + val + '$' : '', true, false)
                                .search(val)
                                .draw();
                        });
                    datalist.append('<option value=""></option>');
                    var recs = new Array(column.data().length);
                    column.data().unique().sort().each(function (d, j) {
                        // console.log(d);
                        if (!recs.includes(d["fullname"])) {
                            recs.push(d["fullname"]);
                            let data = d;
                            if (data["code"] != null && data["fullname"] != null) datalist.append('<option value="' + data["code"] + ' - ' + data["fullname"] + '">' + data["code"] + ' - ' + data["fullname"] + '</option>'); else
                                datalist.append('<option value="' + data["fullname"] + '">' + data["fullname"] + '</option>');
                        }
                    });
                });
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
function loadClazzTargets() {
    $.ajax({
            url: '/sfms/api/modify-feedback/list/targets/clazzes',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                targets = data;
                // console.log(targets);
            },
            error: function (xhr) {
                alert(xhr.message);
            }
        }
    )
}
function loadMajorTargets() {
    $.ajax({
            url: '/sfms/api/modify-feedback/list/targets/majors',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                targets = data;
                // console.log(targets);
            },
            error: function (xhr) {
                alert(xhr.message);
            }
        }
    )
}
function loadCourseTargets() {
    $.ajax({
            url: '/sfms/api/modify-feedback/list/targets/courses',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                targets = data;
                // console.log(targets);
            },
            error: function (xhr) {
                alert(xhr.message);
            }
        }
    )
}
function loadDepartmentTargets() {
    $.ajax({
            url: '/sfms/api/modify-feedback/list/targets/departments',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data, status, xhr) {
                targets = data;
                // console.log(targets);
            },
            error: function (xhr) {
                alert(xhr.message);
            }
        }
    )
}
function addTarget(target) {
    $.ajax({
        url: '/sfms/api/modify-feedback/add/target',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(target),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                console.log("targets now" + targets)
                reloadTable();
            }
        },
        error: function () {
            alert("fuck")
        }
    })
}
function addClazzTarget(el) {
    var data = $("#tbl-clazzes").DataTable().row($(el).parents('tr')).data();
    var clazz = {"id": data.id};
    addTarget(clazz);
}
function addCourseTarget(el) {
    var data = $("#tbl-courses").DataTable().row($(el).parents('tr')).data();
    var course = {"id": data.id};
    addTarget(course);
}
function addMajorTarget(el) {
    var data = $("#tbl-majors").DataTable().row($(el).parents('tr')).data();
    var major = {"id": data.id};
    addTarget(major);
}
function addDepartmentTarget(el) {
    var data = $("#tbl-departments").DataTable().row($(el).parents('tr')).data();
    var department = {"id": data.id};
    addTarget(department);
}
function removeTarget(target) {
    $.ajax({
        url: '/sfms/api/modify-feedback/remove/target',
        type: 'DELETE',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(target),
        success: function (data, status, xhr) {
            if (xhr.status === 200) {
                loadClazzTargets();
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