$(document).ready(function () {

    
    /*   var number =  $('#option').val();
     pagination(number);
     
     $('#option').change(function () {
     var number = this.value;
     $("#nav").remove();
     pagination(number);
     $('#table-data tbody tr').css('opacity', '0.0').hide().slice(0, number).
     css('display', 'table-row').animate({opacity: 1}, 300);
     });
     
     function pagination(rowsShown) {
     
     $('.container-fluid').after('<div id="nav"></div>');
     var rowsShown = rowsShown;
     var rowsTotal = $('#table-data tbody tr').length;
     var numPages = rowsTotal / rowsShown;
     var div = '<div class="row justify-content-around" style="margin-top: 10px"><div id="div-button-option-number" class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 text-center"></div></div>';
     $('#nav').append(div);
     for (i = 0; i < numPages; i++) {
     var pageNum = i + 1;
     $('#div-button-option-number')
     .append('<button class="btn btn-pagination" value="' + i + '"><span class="fa fa-fw "></span>' + pageNum + '</button>&nbsp;');
     }
     $('#table-data tbody tr').hide();
     $('#table-data tbody tr').slice(0, rowsShown).show();
     $('#nav button:first').addClass('active');
     $('#nav button').bind('click', function () {
     
     $('#nav button').removeClass('active');
     $(this).addClass('active');
     var currPage = $(this).attr('value');
     var startItem = currPage * rowsShown;
     var endItem = startItem + rowsShown;
     $('#table-data tbody tr').css('opacity', '0.0').hide().slice(startItem, endItem).
     css('display', 'table-row').animate({opacity: 1}, 300);
     });
     }*/


});