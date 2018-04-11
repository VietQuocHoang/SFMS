$(document).ready(() => {
    let _ctx = $("meta[name='ctx']").attr("content");
     $("#btnSubmit").on("click", (event) => {
         event.preventDefault();
         let data = $("#loginForm").serialize();
         $(".log-in-card").css("opacity", 0.5);
         $(".loading-gif").css("display", "block");
         $.ajax({
             type: "POST",
             url: _ctx + "/login",
             data: data,
             success: (data, status, xhr) => {
                 if (xhr.status === 200) {
                     $(".log-in-card").css("opacity", 1);
                     $(".loading-gif").css("display", "none");
                     window.location.href = _ctx + "/dashboard"
                 }
             },
             error: (data, status, xhr) => {
                 window.location.href = _ctx + "/login?error=true";
             }
         });
     });
});