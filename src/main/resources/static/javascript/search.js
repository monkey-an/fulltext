$(document).ready(function() {
    var searchWords = $("#searchWordsInput").val();
    var searchKey = $("#searchKeyInput").val();
    var searchValue = $("#searchValueInput").val();

    $("#searchKey").html(searchKey);
    $("#searchValue").html(searchValue);
    $("#searchWords").val(searchWords);
});