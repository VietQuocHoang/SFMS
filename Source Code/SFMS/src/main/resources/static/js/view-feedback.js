$(document).ready(function () {

    /* selectedValue = "Phòng ban"; //selected.options[selected.selectedIndex].value;
     var i, tabcontent, tablinks;
     tabcontent = document.getElementsByClassName("tab-content");
     for (i = 0; i < tabcontent.length; i++) {
     tabcontent[i].style.display = "none";
     }
     document.getElementById(selectedValue).style.display = "block";

     $('#filter-scope').change(function () {

     var selected = document.getElementById("filter-scope");
     selectedValue = selected.options[selected.selectedIndex].value;
     //var selectedValue = parseInt(jQuery(this).val());
     //  alert(selectedValue);

     var i, tabcontent, tablinks;
     tabcontent = document.getElementsByClassName("tab-content");
     for (i = 0; i < tabcontent.length; i++) {
     tabcontent[i].style.display = "none";
     }
     /*tablinks = document.getElementsByClassName("tablinks");
     for (i = 0; i < tablinks.length; i++) {
     tablinks[i].className = tablinks[i].className.replace(" active", "");
     }*/
    /*document.getElementById(selectedValue).style.display = "block";
     //   evt.currentTarget.className += " active";
     });

     function openCity(evt, cityName) {
     var i, tabcontent, tablinks;
     tabcontent = document.getElementsByClassName("tabcontent");
     for (i = 0; i < tabcontent.length; i++) {
     tabcontent[i].style.display = "none";
     }
     tablinks = document.getElementsByClassName("tablinks");
     for (i = 0; i < tablinks.length; i++) {
     tablinks[i].className = tablinks[i].className.replace(" active", "");
     }
     document.getElementById(cityName).style.display = "block";
     evt.currentTarget.className += " active";
     }*/
    $('#filter-scope').addEventListener('change',function () {
        function searchViaAjax(selectedValue) {
            //var selected = document.getElementById("filter-scope");
            //selectedValue = selected.options(selected.selectedIndex).value;
            selectedValue = $('#filter-scope').find(":selected").text();
            $.ajax({
                type : "GET",
                url : "/view-list-feedback/list"+selectedValue,
                timeout : 100000,
                success : function(selectedValue) {
                    console.log("SUCCESS: ", selectedValue);
                    display(selectedValue);
                    alert(response);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done : function(e) {
                    console.log("DONE");
                }
            });
        }
    })
});