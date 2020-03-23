$(document).ready(function() {
    var searchWords = $("#searchWordsInput").val();
    var searchKey = $("#searchKeyInput").val();
    var searchValue = $("#searchValueInput").val();

    if(searchKey.length>0) {
        $("#searchKey").html(searchKey);
    }
    if(searchValue.length>0) {
        $("#searchValue").html(searchValue);
    }
    $("#searchWords").val(searchWords);
});