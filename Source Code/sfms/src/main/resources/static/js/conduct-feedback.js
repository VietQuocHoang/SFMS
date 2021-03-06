var _ctx = $("meta[name='ctx']").attr("content");
var currentPageIndex = 0;
const feedbackPage = $(".feedback-page");
var currentPage = $(feedbackPage[currentPageIndex]);
var btnPagination = $(".btn-pagination");
var numOfPage = parseInt(feedbackPage.length);
var progressBar = $(".progress-bar");

function checkStarRating() {
    let selectedStar = $("input[class='input-star'][checked=checked]");
    for (let i = 0; i < selectedStar.length; i++) {
        let formRatingContainer = $(selectedStar[i]).parents(".form-rating");
        let listIcon = formRatingContainer.find(".star-rating");
        let listInput = formRatingContainer.find(".input-star");
        formRatingContainer.find(".star-rating.checked").removeClass("checked");
        let index = listInput.index($(selectedStar[i]));
        for (let j = 0; j <= index; j++) {
            $(listIcon[j]).addClass("checked");
            $(listInput[j]).attr("checked", false);
        }
        $(listInput[index]).attr("checked", true);
    }

}

$(document).ready(() => {
    currentPageIndex = 0;
    const feedbackPage = $(".feedback-page");
    currentPage = $(feedbackPage[currentPageIndex]);
    btnPagination = $(".btn-pagination");
    numOfPage = parseInt(feedbackPage.length);
    progressBar = $(".progress-bar");
    progress = ((currentPageIndex + 1) / numOfPage).toFixed(2) * 100;
    progressBar.css('width', progress + "%").text(progress + "%");
    checkStarRating();

    $(document).on('click', ".btn-pagination", (el) => {
        const value = $(el.target).val();
        currentPage.removeClass("page-active");
        if (value == "next") {
            if (currentPageIndex < numOfPage - 1) {
                currentPageIndex++;
            }
            if (currentPageIndex == numOfPage - 1) {
                $(el.target).attr("disabled", true);
                $(".btn-submit").attr("disabled", false);
            }
            $(btnPagination[0]).attr("disabled", false);
        } else {
            if (currentPageIndex > 0) {
                currentPageIndex--;
            }
            if (currentPageIndex == 0) {
                $(el.target).attr("disabled", true);
            }
            $(".btn-submit").attr("disabled", true);
            $(btnPagination[1]).attr("disabled", false);
        }
        var progress = ((currentPageIndex + 1) / numOfPage).toFixed(2) * 100;
        progressBar.css('width', progress + "%").text(progress + "%");
        currentPage = $(feedbackPage[currentPageIndex]);
        currentPage.addClass("page-active");
    });

    $(document).on('mouseover', ".star-rating", (el) => {
        let formRatingContainer = $(el.target).parents(".form-rating");
        let listIcon = formRatingContainer.find(".star-rating");
        let listInput = formRatingContainer.find(".input-star");
        formRatingContainer.find(".star-rating.checked").removeClass("checked");
        let index = listIcon.index($(el.target));
//console.log(index);
        for (let i = 0; i <= index; i++) {
            $(listIcon[i]).addClass("checked");
            $(listInput[i]).attr("checked", false);
        }
        $(listInput[index]).attr("checked", true);
    });

    $(document).on('click', ".other-option", (el) => {
        var otherOptionInput = $(el.target).parents(".form-check").find(".other-option-input-wrapper");
        if ($(el.target).is(":checked")) {
            otherOptionInput.addClass("checked");
        } else {
            otherOptionInput.removeClass("checked");
        }
    });

    $(document).on('click', "input[type='radio']", (el) => {
        var answerBlock = $(el.target).parents(".answer-block");
        var otherOptionInputWrapper = answerBlock.find(".other-option-input-wrapper");
        var checkedRadio = $(el.target);
        if (!checkedRadio.hasClass("other-option") && checkedRadio.is(":checked")) {
            otherOptionInputWrapper.removeClass("checked");
        }
    });

    $(document).on('click', "#btn-submit-form", (el) => {
        let listQuestionContainer = $("[class*='question-']");
        let data = [];
        let feedbackId = $(event.target).val();
        let allDone = true;
        for (let i = 0; i < listQuestionContainer.length; i++) {
            let questionContainer = $(listQuestionContainer[i]);
            let answerBlock = questionContainer.find(".answer-block");
            let classesNames = answerBlock.attr("class");
            let classSplitted = classesNames.split(" ");
            let questionType = classSplitted[1];
            let isRequired = $(questionContainer).hasClass("required");
            switch (questionType) {
                case 'CheckBox': {
                    if (isRequired && (answerBlock.find(".form-check-input:checked").length === 0)) {
                        allDone = false;
                        questionContainer.addClass("not-answered");
                    } else if (answerBlock.find(".form-check-input:checked").length === 0) {
                        //DO NOTHING
                    } else {
                        let option = answerBlock.find(".form-check-input:not(.other-option):checked");
                        for (let j = 0; j < option.length; j++) {
                            let optionId = parseInt($(option[j]).attr("value"));
                            let content = {"optionnByOptionnId": optionId};
                            data.push(content);
                            questionContainer.removeClass("not-answered");
                        }
                        if (answerBlock.find(".form-check-input.other-option").is(":checked")) {
                            let otherOption = answerBlock.find(".other-option-input");
                            let value = otherOption.val().trim();
                            if (value.length === 0) {
                                allDone = false;
                                questionContainer.addClass("not-answered");
                            } else {
                                let optionId = parseInt(otherOption.attr("name"));
                                let content = {"optionnByOptionnId": optionId, "answerContent": value};
                                data.push(content);
                                questionContainer.removeClass("not-answered");
                            }
                        }
                    }
                    break;
                }
                case 'Radio': {
                    if (isRequired && (answerBlock.find(".form-check-input:checked").length === 0)) {
                        allDone = false;
                        questionContainer.addClass("not-answered");
                    } else if (answerBlock.find(".form-check-input:checked").length === 0) {
                        //DO NOTHING
                    } else {
                        let option = answerBlock.find(".form-check-input:checked");
                        if (option.hasClass("other-option")) {
                            let otherOption = answerBlock.find(".other-option-input");
                            let value = otherOption.val().trim();
                            if (value.length === 0) {
                                allDone = false;
                                questionContainer.addClass("not-answered");
                            } else {
                                let optionId = parseInt(otherOption.attr("name"));
                                let content = {"optionnByOptionnId": optionId, "answerContent": value};
                                data.push(content);
                                questionContainer.removeClass("not-answered");
                            }
                        } else {
                            let optionId = parseInt(option.attr("value"));
                            let content = {"optionnByOptionnId": optionId};
                            data.push(content);
                            questionContainer.removeClass("not-answered");
                        }
                    }
                    break;
                }
                case 'TextArea': {
                    let textarea = questionContainer.find("textarea");
                    let value = textarea.val().trim();
                    //console.log(value);
                    if (isRequired && value.length === 0) {
                        allDone = false;
                        questionContainer.addClass("not-answered");
                    } else if (value.length === 0) {
                        // DO NOTHING
                    } else {
                        let optionId = parseInt(textarea.attr("name"));
                        let content = {"optionnByOptionnId": optionId, "answerContent": value};
                        data.push(content);
                        questionContainer.removeClass("not-answered");
                    }
                    break;
                }
                case 'Text': {
                    let textfield = questionContainer.find("input[type='text']");
                    let value = textfield.val().trim();
                    //console.log(value);
                    if (isRequired && value.length === 0) {
                        allDone = false;
                        questionContainer.addClass("not-answered");
                    } else if (value.length === 0) {
                        // DO NOTHING
                    } else {
                        let optionId = parseInt(textfield.attr("name"));
                        let content = {"optionnByOptionnId": optionId, "answerContent": value};
                        data.push(content);
                    }
                    break;
                }
                case 'Star': {
                    if (isRequired && (answerBlock.find(".input-star:checked").length === 0)) {
                        allDone = false;
                        questionContainer.addClass()
                    } else if (answerBlock.find(".input-star:checked").length === 0) {
                        // DO NOTHING
                    } else {
                        let option = answerBlock.find(".input-star:checked");
                        let optionId = parseInt(option.attr("value"));
                        let content = {"optionnByOptionnId": optionId};
                        data.push(content);
                        questionContainer.removeClass("not-answered");
                    }
                    break;
                }
            }
        }
//console.log(data);
        if (allDone) {
            $(".warning-text").removeClass("show");
            $.ajax({
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({"feedbackId": feedbackId, "answers": data}),
                url: _ctx + "/conduct-feedback/save",
                success: (data, status, xhr) => {
                    if (xhr.status === 200) {
                        $("#confirmModal").modal('hide');
                        notifySnackbar("Lưu câu trả lời thành công", "success");
                        setTimeout(() => {
                            window.location.href = _ctx + "/conduct-feedback/list";
                        }, 500);
                    }
                },
                error: (data, status, xhr) => {
                    $("#confirmModal").modal('hide');
                    if (xhr.status = 403) {
                        notifySnackbar("Vui lòng đăng nhập lại", "danger");
                        setTimeout(() => {
                            window.location.href = _ctx + "/logout";
                        }, 500);
                    } else {
                        notifySnackbar("Đã có lỗi xảy ra, vui lòng thử lại", "danger");
                        setTimeout(() => {
                            window.location.href = _ctx + "/conduct-feedback/list";
                        }, 500);
                    }
                },
            });
        } else {
            $(".warning-text").addClass("show");
            $("#confirmModal").modal('hide');
            $('html, body').animate({
                scrollTop: 0
            }, 1000);
        }
    });
});
