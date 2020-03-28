$("p").on("click",function () {
    var funName = $(this).attr("fun");
    if(funName==undefined || funName==''){
        return;
    }
    $.ajax({
        //请求方式
        type : "POST",
        //请求地址
        url : "/workflow/getMainPage",
        //数据
        data : {
            funName:funName
        },
        //请求成功
        success : function(result) {
            $("#main-page-div").html(result);
            pageDivCallBack();
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            alert(e.status);
            alert(e.responseText);
        }
    });
});



$(document).ready(function () {
    if($("#work-mywork-input").length>0){
        $("#work-mywork-input").click();
    }
})