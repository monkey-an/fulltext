$(document).ready(function () {
    $.each($("[parentMenu]"), function (i, val) {
        $($(val).children()[0]).attr("myhide", "1");

        if ($(val).attr("parentMenu") != '0') {
            // alert($(val).attr("parentMenu"));
            $(val).hide();
        }

        if ($(val).attr("parentMenu") == '0') {
            $($(val).children()[0]).removeClass("glyphicon-minus");
            $($(val).children()[0]).addClass("glyphicon-plus");
        }

        if($($(val).children()[0]).hasClass("glyphicon-plus")){
            $($(val).children()[0]).attr("myhide", "0");
        }
    });

    $("[myhide]").on("click", function () {
        if ($(this).attr("myhide") == '0') {
            var menuId = $(this).parent().attr("selfMenu");

            $.each($("[parentMenu]"), function (i, val) {
                if ($(val).attr("parentMenu") == menuId) {
                    $(val).show();
                }
            });

            $(this).attr("myhide","1");

            $(this).removeClass("glyphicon-plus");
            $(this).addClass("glyphicon-minus");
            return;
        }
        if ($(this).attr("myhide") == '1') {
            var menuId = $(this).parent().attr("selfMenu");

            $.each($("[parentMenu]"), function (i, val) {
                if ($(val).attr("parentMenu") == menuId) {
                    $(val).hide();
                }
            });

            $(this).attr("myhide","0");

            $(this).addClass("glyphicon-plus");
            $(this).removeClass("glyphicon-minus");
            return;
        }

    });
});



