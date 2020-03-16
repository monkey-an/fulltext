$("#save").on("click",function() {
    $.each($("td input"), function (i, val) {
        var temp = $(val).val();
        $(val).parent().html(temp);
    });
    $.each($("td textarea"), function (i, val) {
        var temp = $(val).val().replace(/\n/g,"<br/>");
        $(val).parent().html(temp);
    });
    $("#commit").removeAttr("disabled");
})

$(".newForm td").on("dblclick",function() {
    if($(this).html()=='') {
        var inputHtml = "<input type='text' style='width: 95%;'/>";
        var inputAera = "<textarea style='width: 95%;' rows='8'>";
        if($(this).height()<40) {
            $(this).html(inputHtml);
            $(this).find("input").focus();
        }else{
            $(this).html(inputAera);
            $(this).find("textarea").focus();
        }
    }
})

$("#agreey").on("click",function(){
    $(this).parent().html("同意");
})

$("#unagreey").on("click",function(){
    $(this).parent().html("<input type='text' class='form-control' placeholder='请输入指导意见' value='不同意，请'/>");
})

$("#commit").on("click",function(){
    var flowNo = $("#flow-no").attr("data");
    var flowName = $("#flow-name").attr("data");
    var taskId = $("#task-id").attr("data");

    var formData = new FormData();
    formData.append("flow-no",flowNo);
    formData.append("flow-name",flowName);
    formData.append("task-id",taskId);

    $.each($("input[type='file']") ,function (i, val) {
        var tempFile = $(val)[0].files[0];
        var name = $(val).attr("name");
        formData.append(name,tempFile);
    });

    $.each($("table") ,function (i, val) {
        if("need"==$(val).attr("form-type")) {
            var formId = $(val).prop("id");
            var tableHtml = $(val).prop("outerHTML");
            formData.append(formId, tableHtml);
        }
    });

    $.ajax({
        url:"/workflow/commitFlow",
        type:'post',
        data: formData,
        contentType: false,
        processData: false,
        success:function(result){
            if("SUCCESS"==result){
                alert("提交成功");
                window.close();
            }else{
                alert("提交失败，请检查表单并重试");
            }
        }
    })
})