function pageDivCallBack(){
    $.ajax({
        //请求方式
        type : "GET",
        //请求地址
        url : "/admin/getAllMenuNameMap",
        //请求成功
        success : function(result) {
            for(var i=0;i<result.length;i++){
                var temp = result[i];
                $("#parent_menu").append("<option documentId='"+temp.documentId+"' menuId='"+temp.menuId+"'>"+temp.menuName+"</option>");
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            alert(e.status);
            alert(e.responseText);
        }
    });

    $("#if_leaf").change(function () {
        $("#document-menu-author-div").toggle();
        $("#document-menu-summary-div").toggle();
        $("#document-menu-keywords-div").toggle();
    });
}