function pageDivCallBack(){
    $.ajax({
        //请求方式
        type : "GET",
        //请求地址
        url : "/admin/getAllMenuNameMap",
        //请求成功
        success : function(result) {
            $("#parent_menu").children().remove();
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
        if($("#if_leaf").prop("checked")) {
            $("#document-menu-author-div").show();
            $("#document-menu-summary-div").show();
            $("#document-menu-keywords-div").show();
            $("#document-menu-completion-unit-div").show();
            $("#document-menu-members-div").show();
        }else{
            $("#document-menu-author-div").hide();
            $("#document-menu-summary-div").hide();
            $("#document-menu-keywords-div").hide();
            $("#document-menu-completion-unit-div").hide();
            $("#document-menu-members-div").hide();
        }
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
                alert("添加失败，请检查图书信息并重试");
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
                alert("添加失败，请检查图书信息并重试");
            }
        }
    })

});