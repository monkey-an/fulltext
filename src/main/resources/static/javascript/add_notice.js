function pageDivCallBack() {
    var options = {
        lang : 'zh-CN',
        placeholder : '编辑通知正文',
        // toolbar : {'font':['strikethrough','superscript','subscript']},
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: false,
        dialogsFade : true,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'italic', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['table', ['table']],
            ['insert', ['link', 'hr']],
            ['view', ['fullscreen', 'codeview']],
            ['help', ['help']]
        ]
    };
    $('#notice_content').summernote(options);
};

$("#add_attachment").on("click",function(){
    var count = $(":file").length;
    var inputStr = "<input type='file' id='attachment-"+(count+1)+"' style='margin-left: 10px;'/>"
    $(this).parent().append(inputStr);
});

$("#notice-add-button").on("click",function(){
    var content = $("#notice_content").summernote("code");
    var text = $('<div>'+content+'</div>').text();

    var formData = new FormData();
    formData.append("notice-title-input",$("#notice-title-input").val());
    formData.append("if_inner_notice",$("#if_inner_notice").get(0).checked);
    formData.append("notice-author-input",$("#notice-author-input").val());
    formData.append("content",content);
    formData.append("text",text);

    $.each($("p :file") ,function (i, val) {
        var tempFile = $(val)[0].files[0];
        var id = $(val).prop("id");
        formData.append(id,tempFile);
    });

    $.ajax({
        url:"/admin/notice_add",
        type:'post',
        data: formData,
        contentType: false,
        processData: false,
        success:function(result){
            if("SUCCESS"==result){
                alert("提交成功");
                $("#admin-add-notice").click();
            }else{
                alert("提交失败，请检查表单并重试");
            }
        }
    })
});