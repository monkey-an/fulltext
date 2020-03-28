$("#save").on("click",function() {
    var approvalResult = $('input:radio[name=drone]:checked').val();
    var approvalMsg = $('input[name=approvalMsg]').val();

    $.each($("td input"), function (i, val) {
        if($(val).prop("name")!='drone' && $(val).prop("name")!='approvalMsg') {
            var temp = $(val).val();
            $(val).parent().html(temp);
        }
        if($(val).prop("name")=='approvalMsg'){
            $(val).parent().html(approvalResult+" "+approvalMsg);
        }
    });

    var resultScore = 0;
    $.each($("td[type='need-accounting']"), function (i, val) {
        resultScore += parseInt($(val).html());
    });
    $("#result-score").html(resultScore);

    $.each($("td textarea"), function (i, val) {
        var temp = $(val).val().replace(/\n/g,"<br/>");
        $(val).parent().html(temp);
    });
    $("table button").remove();



    $("#commit").removeAttr("disabled");
})

$(".newForm td").on("click",function() {
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