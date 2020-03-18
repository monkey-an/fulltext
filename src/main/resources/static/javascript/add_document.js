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

$("#document-add-button").on("click",function () {
    var inputs = $("#form_add_document").find("input");
    var formData = new FormData();
    $.each($(inputs) ,function (i, val) {
        var name = $(val).prop("id");
        var value = $(val).val();
        formData.append(name,value);
    });

    var documentType = $("#document_type").val();
    formData.append("documentType",documentType);

    $.ajax({
        url:"/admin/document_add",
        type:'post',
        data: formData,
        contentType: false,
        processData: false,
        success:function(result){
            if("SUCCESS"==result){
                alert("提交成功");
                pageDivCallBack();
                $.each($(inputs) ,function (i, val) {
                    $(val).val("");
                });
            }else{
                alert("添加失败，请检查文档信息并重试");
            }
        }
    })

});


$("#document-menu-add-button").on("click",function () {
    var documentId = $("#parent_menu").find("option:selected").attr("documentId");
    var menuId = $("#parent_menu").find("option:selected").attr("menuId");
    var inputs = $("#form_add_menu").find("input");

    var formData = new FormData();
    formData.append("documentId",documentId);
    formData.append("menuId",menuId);

    $.each($(inputs) ,function (i, val) {
        var name = $(val).prop("id");
        var value = $(val).val();
        formData.append(name,value);
    });

    $.ajax({
        url:"/admin/menu_add",
        type:'post',
        data: formData,
        contentType: false,
        processData: false,
        success:function(result){
            if("SUCCESS"==result){
                alert("提交成功");
                pageDivCallBack();
                $.each($(inputs) ,function (i, val) {
                    $(val).val("");
                });
            }else{
                alert("添加失败，请检查文档信息并重试");
            }
        }
    })

});