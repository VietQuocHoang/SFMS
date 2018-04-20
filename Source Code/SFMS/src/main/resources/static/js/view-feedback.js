$(document).ready(() => {
    let table = $("#tblFeedback").DataTable({
        "ajax": {
            "url": _ctx + "/api/feedbacks/list",
            "dataSrc": "",
        },
        "columns": [
            {"data": "feedbackName"},
            {"data": "semesterBySemesterId.title"},
            {
                "data": "startDate",
                "render": (data, type, row) => {
                    return new Date(row["startDate"]).toLocaleDateString() + " - " + new Date(row["endDate"]).toLocaleDateString();
                },
            },
            {"data": "typeByTypeId.description"},
            {
                "data": null,
                "render": (data, type, row) => {
                    if (null !== row["departmentByDepartmentById"]) {
                        return row["departmentByDepartmentId"].name;
                    } else if (null !== row["majorByMajorId"]) {
                        return row["majorByMajorId"].name;
                    } else if (null !== row["clazzByClazzId"]) {
                        return row["clazzByClazzId"].className;
                    } else if (null !== row["courseByCourseId"]) {
                        return row["courseByCourseId"].name;
                    }
                }
            },
            {
                "data": "isPublished",
                "render": (data, type, row) => {
                    return data ? "<p class='text-success'>Đã xuất bản</p>" : "<p class='text-danger'>Ngưng xuất bản</p>";
                }
            },
            {
                "data": "isPublished",
                "render": (data, type, row) => {
                    return data
                        ? "<button type='button' class='btn btn-danger' data-id='" + row["id"] + "' id='btn-depublish-modal' data-toggle='modal' data-target='#modalDepublish'>Ngưng</button>"
                        : "<button type='button' class='btn btn-primary' data-id='" + row["id"] + "' id='btn-publish-modal' data-toggle='modal' data-target='#modalPublish'>Xuất bản</button>";
                }
            }
        ],
        "lengthMenu": [[5, 20, 50, -1], [5, 20, 50, "Toàn bộ"]],
        "language": {
            "decimal": "",
            "emptyTable": "Không kết quả nào được tìm thấy",
            "info": "Hiển thị từ _START_ tới _END_ trong số _TOTAL_ kết quả",
            "infoEmpty": "Hiển thị 0 tới 0 trong số 0 kết quả",
            "infoFiltered": "(lọc từ _MAX_ kết quả)",
            "infoPostFix": "",
            "thousands": ",",
            "lengthMenu": "Hiển thị _MENU_ kết quả mỗi trang",
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
            },
        }
    });

    $(document).on('change', '.filter-scope', (event) => {
        let value = $(event.target).find(":selected").val();
        console.log(value);
        filterTable(value);
    });

    function filterTable(data) {
        table.columns(3).search(data).draw();
    }

    function switchPublishStateFeedback(feedbackId) {
        let snackBar = $("#snackbar");
        $.ajax({
            "url": _ctx + "/api/feedbacks/switch-publish",
            "type": "post",
            "data": {"id": feedbackId},
            success: (data, status, xhr) => {
                if (xhr.status === 200) {
                    notifySnackbar("Thay đổi trạng thái thành công", "success");
                }
            },
            error: (data, status, xhr) => {
                notifySnackbar("Đã có lỗi xảy ra, vui lòng thử lại", "danger");
            },
            complete: (data, status, xhr) => {
                $("#modalPublish").modal('hide');
                $("#modalDepublish").modal('hide');
                table.ajax.reload();
                filterTable($(".filter-scope").find(":selected").val());
            }
        });
    }

    $(document).on('click', '.btn-switch', (event) => {
        let feedbackId = $("#txtFeedbackId").val();
        switchPublishStateFeedback(feedbackId);
    });

    $(document).on('click', '#btn-publish-modal, #btn-depublish-modal', (event) => {
        let id = $(event.target).attr("data-id");
        $("#txtFeedbackId").val(id);
    });
});
