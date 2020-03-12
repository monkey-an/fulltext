$("#giveup-login").on("click", function () {
    history.back();
});

$("#forget-pwd-tip-button").on("click",function(){
    $("#forget-pwd-tip-div").toggle(100);
});

$("#login-button").on("click",function () {
    var userName = $("#user-name-input").val();
    var password = $("#user-pwd-input").val();
    var rememberMe = $("#remember-me-checkbox").is(':checked');

    $.ajax({
        //请求方式
        type : "POST",
        //请求地址
        url : "/user/doLogin",
        //数据
        data : {
            userName:userName,
            password:password,
            rememberMe:rememberMe
        },
        //请求成功
        success : function(result) {
            if("NOT_NEED"==result){
                window.location="";
            }
            else if("LOGIN_SUCCESS"==result){
                window.location.replace(document.referrer);
            }else{
                $("#error-pwd-tip-div").show();
                $("#user-name-input").val("");
                $("#user-pwd-input").val("");
            }
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            alert(e.status);
            alert(e.responseText);
        }
    });
})