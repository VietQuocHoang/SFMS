$(document).ready(() => {
    $("#availableTemplate").DataTable({
        "language": {
            "lengthMenu": "_MENU_ bản ghi/trang",
            "emptyTable": "Không có dữ liệu nào trong bảng",
            "info": "_START_ - _END_ trên _TOTAL_ bản ghi",
            "search": "",
            "zeroRecords": "Không tìm thấy bản ghi phù hợp",
            "paginate": {
                "first": "Đầu",
                "last": "Cuối",
                "next": "Sau",
                "previous": "Trước"
            }
        },
        "lengthMenu": [[5, 10, 50, -1], [5, 10, 50, "Tất cả"]],
        "responsive": true
    });
});
$(window).resize(() => {
    if ($(window).outerWidth() < 992) {
        $(".preview-arrow").removeClass("fa-arrow-right")
            .addClass("fa-arrow-down");
    } else {
        $(".preview-arrow").removeClass("fa-arrow-down")
            .addClass("fa-arrow-right");
    }
});
$(".btn-preview").on('click', (el)=>{
    $(".loading-container").addClass("container-active");
    $(".template-container").addClass("container-fade");
    setTimeout(()=>{
        $(".loading-container").removeClass("container-active");
        $(".template-container").removeClass("container-fade");
    }, 3000)
});