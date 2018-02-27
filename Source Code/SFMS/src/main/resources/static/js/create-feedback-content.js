$(".item-list").sortable({
    // appendTo: 'body',
    connectWith: ".answer-list",
    // remove: (event,ui)=>{
    //   ui.item.clone().appendTo(".list-widget");
    //   $(this).sortable('cancel');
    // },
    // axis: 'y',
    helper: (e, li) => {
        copyHelper = li.clone().insertAfter(li);
        return li.clone();
    },
    stop: () => {
        copyHelper && copyHelper.remove();
    },
    appendTo: '.question-list-container',
}).disableSelection();


$(".answer-list").sortable({
    receive: (ev, ui) => {
        console.log("received");
        copyHelper = null;
        if ($(ev.target).find('li').length > 1) {
            ui.item.remove();
        } else {
            let id = ui.item.attr('id');
            switch (id) {
                case 'text-ele': {
                    ui.item.html("<div class='form-group'>" +
                        "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                        "<input type='text' class='form-control'>" +
                        "</div>");
                    break;
                }
                case 'radio-ele': {
                    ui.item.html("<div class='form-check'>" +
                        "<label class='form-check-label'>" +
                        "<input class='form-check-input' type='radio' checked> Đáp án 1" +
                        "</label>" +
                        "</div>" +
                        "<div class='form-check'>" +
                        "<label class='form-check-label'>" +
                        "<input class='form-check-input' type='radio'> Đáp án 2" +
                        "</label>" +
                        "</div>" +
                        "<div class='form-check'>" +
                        "<label class='form-check-label'>" +
                        "<input class='form-check-input' type='radio'> Đáp án 3" +
                        "</label>" +
                        "</div>");
                    break;
                }
                case 'checkbox-ele': {
                    ui.item.html("<div class='form-check'>" +
                        "<label class='form-check-label'>" +
                        "<input class='form-check-input' type='checkbox' checked> Đáp án 1" +
                        "</label>" +
                        "</div>");
                    break;
                }
                case 'textarea-ele': {
                    ui.item.html("<div class='form-group'>" +
                        "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                        "<textarea class='form-control'></textarea>" +
                        "</div>")
                }
            }
        }
    },
    over: () => {
        removeIntent = false;
    },
    out: () => {
        removeIntent = true;
    },
    beforeStop: (event, ui) => {
        if (removeIntent == true) {
            ui.item.remove();
        }
    },
}).disableSelection();


$(".list-answer-edit").sortable({
    helper: 'clone',
    handle: ".sort-edit-answer-handle",
    appendTo: ".list-answer-edit",
    containment: ".edit-answer-wrapper"
}).disableSelection();

$(document).on("click", ".btn-remove-question", (event) => {
    let parentContainer = $(event.target).parents(".question-container");
    parentContainer.slideUp("normal", () => {
        parentContainer.remove()
    });
});

$(document).on("click", ".add-new-answer-wrapper", (event) => {
    let parentQuestionContainer = $(event.target).parents(".question-container");
    let listPreviewAnswer = parentQuestionContainer.find(".answer-wrapper");
    let listEditAnswer = parentQuestionContainer.find(".list-answer-edit");
    var editQuestion = "<li>" +
        "<div class='edit-answer-item'>" +
        "<div class='sort-edit-answer-handle'>" +
        "<i class='fa fa-bars'></i>" +
        "</div>" +
        "<div class='input-edit-answer-holder'>" +
        "<input type='text' class='answer-content-input' value='Đáp án " + (listEditAnswer.find("li").length + 1) + "'>" +
        " Trọng số: <input type='number' min='0' class='weight-input' value='1'>" +
        "</div>" +
        "<div class='remove-answer-holder'>" +
        "<i class='fa fa-close'></i>" +
        "</div>" +
        "</div>" +
        "</li >";
    let previewQuestion = "<div class='form-check'>" +
        "<label class='form-check-label'>" +
        "<input class='form-check-input' type='radio' disabled> Đáp án " + (listEditAnswer.find("li").length + 1) +
        "</label>" +
        "</div>";
    listPreviewAnswer.append(previewQuestion);
    listEditAnswer.append(editQuestion);
});

$(document).on("click", ".btn-expand-edit", (event) => {
    let parentContainer = $(event.target).closest(".question-container");
    let parentWrapper = parentContainer.find(".question-wrapper");
    let previewQuestion = parentWrapper.find(".preview-question-container");
    let editQuestionContainer = parentWrapper.find(".edit-question-container");
    $(event.target).toggleClass("fa-angle-down fa-angle-up");
    editQuestionContainer.slideToggle();
});

$(document).on("change", ".required-checkbox", (event) => {
    let checkbox = $(event.target);
    let parentContainer = checkbox.parents(".question-container");
    let requireQuestion = parentContainer.find(".required-question");
    if (checkbox.is(":checked")) {
        requireQuestion.show();
    } else {
        requireQuestion.hide();
    }
});

$(document).on("keyup", ".txtEditQuestion", (event) => {
    let txtEditQuestion = $(event.target);
    let parentContainer = txtEditQuestion.parents(".question-container");
    let questionContent = parentContainer.find(".question-content-paragraph");
    var value = txtEditQuestion.val();
    questionContent.html(value);
});

$(document).on("click", ".remove-answer-holder>i.fa-close", (event) => {
    let parentContainer = $(event.target).parents(".question-container");
    let listAnswer = parentContainer.find(".form-check");
    let parentListItem = $(event.target).parents("li");
    let listEditAnswerContainer = $(event.target).parents(".list-answer-edit");
    if (listEditAnswerContainer.find(".edit-answer-item").length > 1) {
        let index = listEditAnswerContainer.find("li").index(parentListItem);
        console.log(index);
        parentListItem.fadeOut(500, () => {
            parentListItem.remove()
        });
        let removeAnswer = $(listAnswer.get(index));
        removeAnswer.fadeOut(500, () => {
            removeAnswer.remove()
        });
    }
});


var numOfQuestion = 0;

function initAnswersPanel(answerPanel) {
    $(answerPanel).sortable({
        // connectWith: ".list-widget",
        receive: (ev, ui) => {
            console.log($(ev.target).find('li').length);
            copyHelper = null;
            if ($(ev.target).find('li').length > 1) {
                ui.item.remove();
            } else {
                let id = ui.item.attr('id');
                switch (id) {
                    case 'text-ele': {
                        ui.item.html("<div class='form-group'>" +
                            "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                            "<input type='text' class='form-control'>" +
                            "</div>");
                        break;
                    }
                    case 'radio-ele': {
                        ui.item.html("<div class='form-check'>" +
                            "<label class='form-check-label'>" +
                            "<input class='form-check-input' type='radio' checked> Đáp án 1" +
                            "</label>" +
                            "</div>" +
                            "<div class='form-check'>" +
                            "<label class='form-check-label'>" +
                            "<input class='form-check-input' type='radio'> Đáp án 2" +
                            "</label>" +
                            "</div>" +
                            "<div class='form-check'>" +
                            "<label class='form-check-label'>" +
                            "<input class='form-check-input' type='radio'> Đáp án 3" +
                            "</label>" +
                            "</div>");
                        break;
                    }
                    case 'checkbox-ele': {
                        ui.item.html("<div class='form-check'>" +
                            "<label class='form-check-label'>" +
                            "<input class='form-check-input' type='checkbox' checked> Đáp án 1" +
                            "</label>" +
                            "</div>");
                        break;
                    }
                    case 'textarea-ele': {
                        ui.item.html("<div class='form-group'>" +
                            "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                            "<textarea class='form-control'></textarea>" +
                            "</div>")
                    }
                }
            }
        },
        over: () => {
            removeIntent = false;
        },
        out: () => {
            removeIntent = true;
        },
        beforeStop: (event, ui) => {
            if (removeIntent == true) {
                ui.item.remove();
            }
        }
    }).disableSelection();
}

$(".btn-add-new").on('click', (event) => {
    var html = "<li>" +
        "<div class='container'>" +
        "<div class='toggle-bar'>" +
        "<select>" +
        "<option>Chuyên cần</option>" +
        "<option>Tác phong</option>" +
        "<option>Chuyên môn</option>" +
        "<option>Thái độ</option>" +
        "<option>Kiến thức</option>" +
        "</select>" +
        // "<button class='btn btn-remove-question'>Xóa</button> " +
        "</div>" +
        "<div class='question'>" +
        "<textarea class='form-control' placeholder='Nhập câu hỏi'></textarea>" +
        "</div>" +
        "<div class='answers-container'>" +
        "<p>Kéo loại câu hỏi của bạn vào khung dưới</p>" +
        "<div class='field-action'>" +
        "<button class='btn-edit-answer'><i class='fa fa-fw fa-pencil'></i></button>" +
        "<button class='btn-remove-answer' onclick='removeAnswer(event)'><i class='fa fa-fw fa-remove'></i></button>" +
        "</div>" +
        "<ul class='answers-panel'>" +
        "</ul>" +
        "</div>" +
        "</li>";
    let domNode = $(html);
    domNode.hide();
    // $(html).hide().appendTo($(".questions-list")).slideDown("fast");
    $(".questions-list").append(domNode);
    domNode.slideDown("fast");
    let answerPanel = $(domNode).find(".answers-panel");
    initAnswersPanel(answerPanel);
    numOfQuestion++;
});

// $("#sortable").disableSelection();
// function dragstart_handler(ev){
//     console.log("dragStart");
//     ev.dataTransfer.setData("text", ev.target.id);
//     ev.dropEffect="copy";
// }
// function dragover_handler(ev){
//     ev.preventDefault();
//     ev.dataTransfer.dropEffect = "copy";
// }
// function drop_handler(ev){
//     ev.preventDefault();
//     var data = ev.dataTransfer.getData("text");
//     var originalElem = document.getElementById(data);
//     var clonedElem = originalElem.cloneNode(true);
//     clonedElem.className += " ui-state-default";
//     console.log(clonedElem);
//     console.log(data);
//     $(ev.target)
//         .find("ul")
//         .append(clonedElem);
// }