$(".list-widget").sortable({
    // appendTo: 'body',
    // helper: 'clone',
    connectWith: ".answers-panel",
    // remove: (event,ui)=>{
    //   ui.item.clone().appendTo(".list-widget");
    //   $(this).sortable('cancel');
    // },
    helper: (e, li)=>{
        copyHelper = li.clone().insertAfter(li);
        console.log(li.clone());
        return li.clone();
    },
    stop: ()=>{
        copyHelper && copyHelper.remove();
    }
}).disableSelection();
$(".questions-list").sortable().disableSelection();
function initAnswersPanel(answerPanel){
    $(answerPanel).sortable({
        // connectWith: ".list-widget",
        receive: (ev, ui)=>{
            copyHelper = null;
            let id = ui.item.attr('id');
            console.log(id);
            switch(id){
                case 'text-ele': {
                    ui.item.html("<div class='form-group'>" +
                        "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                        "<input type='text' class='form-control'>" +
                        "</div>");
                    break;
                }
                case 'radio-ele':{
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
                case 'checkbox-ele':{
                    ui.item.html("<div class='form-check'>" +
                        "<label class='form-check-label'>" +
                        "<input class='form-check-input' type='checkbox' checked> Đáp án 1" +
                        "</label>" +
                        "</div>");
                    break;
                }
                case 'textarea-ele':{
                    ui.item.html("<div class='form-group'>" +
                        "<label class='control-label'>Nhập câu trả lời của bạn vào đây</label>" +
                        "<textarea class='form-control'></textarea>" +
                        "</div>")
                }
            }
        },
    }).disableSelection();
}
$("body").droppable({
    drop: (event, ui)=>{
        console.log("deleted")
        $(ui.item).remove();
    }
});

var numOfQuestion = 0;

$(".btn-add-new").on('click', (event)=>{
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
        "</div>" +
        "<div class='question'>" +
        "<textarea class='form-control' placeholder='Nhập câu hỏi'></textarea>" +
        "</div>" +
        "<div class='answers-container'>" +
        "<p>Kéo loại câu hỏi của bạn vào khung dưới</p>" +
        "<div class='field-action'>" +
        "<a href='#' class='btn-edit'><i class='fa fa-fw fa-pencil'></i></a> " +
        "<a href='#' class='btn-remove'><i class='fa fa-fw fa-remove'></i></a> " +
        "</div>"+
        "<ul class='answers-panel'>" +
        "</ul>" +
        "</div>" +
        "</div>" +
        "</li>";
    let domNode = jQuery.parseHTML(html);
    console.log(domNode);
    $(".questions-list").append(domNode);
    let answerPanel = $(domNode).find(".answers-panel");
    console.log("answerPanel");
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