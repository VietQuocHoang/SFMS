var _ctx = $("meta[name='ctx']").attr("content");
var dropdownItem = "<a class='dropdown-item' href='#'>" +
    "              <span class='text-success'>" +
    "                <strong class='notification-feedback-header'>" +
    "                  Thông báo mới: </strong>" +
    "              </span>" +
    "                        <span class='small float-right text-muted'></span>" +
    "                        <div class='dropdown-message small notification-feedback-description'></div>" +
    "                    </a>" +
    "                    <!--divider-->" +
    "                    <div class='dropdown-divider'></div>" +
    "                    <!--divider-->";

function getNotification() {
    $.ajax({
        type: "GET",
        url: _ctx + "/api/feedbacks/undone-by-authorized-user",
        contentType: 'application/json',
        dataType: 'json',
        success: (data, status, xhr) => {
            let notificationDropDown = $(".notification-dropdown");
            let dropDownItems = $(notificationDropDown).find(".dropdown-item");
            //remove old notifications
            for (let i = 0; i < dropDownItems.length; i++) {
                dropDownItems.remove();
            }
            if (xhr.status === 200) {
                let dataArr = Array.from(data);
                let length = dataArr.length;
                $(".notification-header").html("Có " + length + " feedback chưa hoàn thành");
                $(".notification-num").html(length + " thông báo");
                if (length > 0) {
                    for (let i = 0; i < length; i++) {
                        $(".notification-dropdown").append(
                            "<a class='dropdown-item' href='" + _ctx + "/conduct-feedback/" + data[i].feedbackByFeedbackId.id + "'>" +
                            "              <span class='text-success'>" +
                            "                <strong class='notification-feedback-header'>" + data[i].feedbackByFeedbackId.feedbackName + "</strong>" +
                            "              </span>" +
                            "                        <div class='dropdown-message small notification-feedback-description'>" + data[i].feedbackByFeedbackId.feedbackDes + "</div>" +
                            "                    </a>"
                            // "                    <!--divider-->" +
                            // "                    <div class='dropdown-divider'></div>" +
                            // "                    <!--divider-->"
                        );
                    }
                }
            } else if (xhr.status === 204) {
                $(".notification-header").html("Không có thông báo");
                $(".notification-num").html("0 thông báo")
            }
        },
        complete: (data, status, xhr) => {
            setTimeout(getNotification, 5000);
        }
    });
}

$(document).ready(function () {
    getNotification();
});