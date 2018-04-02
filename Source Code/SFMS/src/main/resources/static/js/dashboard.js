$(document).ready(() => {
    let table = $("#tblFeedback").DataTable({
        "lengthMenu": [[5, 20, 50, -1], [5, 20, 50, "Toàn bộ"]],
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
                "last": "Cuối",
                "next": "Sau",
                "previous": "Trước"
            },
            "aria": {
                "sortAscending": ": kích hoạt để sắp xếp tăng dần",
                "sortDescending": ": kích hoạt để sắp xếp giảm dần"
            },
        },
        "responsive": true,
    });

    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    let pieCharts = $(".pie-chart");

    function drawChart() {
        for (let i = 0; i < pieCharts.length; i++) {
            let title = $(pieCharts[i]).attr("data-chart-title");
            let name = $(pieCharts[i]).attr("data-chart-name").trim().replace('[', '').replace(']', '').split(",");
            let value = $(pieCharts[i]).attr("data-chart-value").trim().replace('[', '').replace(']', '').split(",");
            let id = $(pieCharts[i]).attr("id");
            let dataArr = [];
            for (let j = 0; j < name.length; j++) {
                let obj = [name[j].trim(), parseInt(value[j].trim())];
                dataArr.push(obj);
            }
            let data = new google.visualization.DataTable();
            data.addColumn('string', 'name');
            data.addColumn('number', 'value');
            data.addRows(dataArr);
            let options = {title: title, sliceVisibilityThreshold: 0};
            let chart = new google.visualization.PieChart(document.getElementById(id));
            chart.draw(data, options);
        }

    }
});
