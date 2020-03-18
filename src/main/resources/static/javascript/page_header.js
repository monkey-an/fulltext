$(document).ready(function () {
    searchMap = new Map();
    var keySearchByAwardResult = "获奖成果";
    var keySearchByReport = "发展报告";
    var keySearchByYearbook = "年鉴";

    var valuesByAwardResult = [];
    valuesByAwardResult.push($("<li><a href=\"#\">全文</a></li>"));
    valuesByAwardResult.push($("<li><a href=\"#\">标题</a></li>"));
    valuesByAwardResult.push($("<li><a href=\"#\">关键字</a></li>"));
    valuesByAwardResult.push($("<li><a href=\"#\">第一作者</a></li>"));
    valuesByAwardResult.push($("<li><a href=\"#\">第二作者</a></li>"));

    var valuesByReport = [];
    valuesByReport.push($("<li><a href=\"#\">全文</a></li>"));
    valuesByReport.push($("<li><a href=\"#\">标题</a></li>"));

    var valuesByYearbook = [];
    valuesByYearbook.push($("<li><a href=\"#\">全文</a></li>"));
    valuesByYearbook.push($("<li><a href=\"#\">标题</a></li>"));

    searchMap.set(keySearchByAwardResult,valuesByAwardResult);
    searchMap.set(keySearchByReport,valuesByReport);
    searchMap.set(keySearchByYearbook,valuesByYearbook);

    $("#searchValues").children().remove();
    var temp = searchMap.get($("#searchKey").html().trim());
    $("#searchValues").append(temp);

});

function searchReload(){
    $("#searchValues").children().remove();
    var temp = searchMap.get($("#searchKey").html().trim());
    $("#searchValues").append(temp);
    $("#searchValue").html($(temp[0]).find("a").html());
}

$("#searchKeys li").on("click",function() {
    var text = $(this).find("a").html();
    $("#searchKey").html(text);
    searchReload();
});