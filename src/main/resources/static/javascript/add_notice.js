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

$("#notice-add-button").on("click",function(){
    alert($("#notice_content").summernote("code"));
})