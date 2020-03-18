$(document).ready(function() {
    var searchWords = $("#searchWordsInput").val();
    var searchKey = $("#searchKeyInput").val();
    var searchValue = $("#searchValueInput").val();

    $("#searchKey").html(searchKey);
    $("#searchValue").html(searchValue);
    $("#search_words").val(searchWords);

    initData();
});

function initData(){
    var $table;
    $(function() {
        $table = $('#table').bootstrapTable({
            url: '/realSearch',
            method: 'get',                     //请求方式（*）
            //post,contentType: "application/x-www-form-urlencoded",
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            pagination: true,                   //是否显示分页（*）
            sidePagination: "server",           //分页方式：client客户端分页(默认)，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: 10,                     	//每页大小
            pageList: [5, 10, 15],        		//可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索
            strictSearch: false,					//设为true，开启精确搜索
            showColumns: false,                  //是否显示所有的列（选择显示的列）
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                 //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false, 					//得到查询的参数
            queryParams : function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    pageSize: params.limit,                         //页面大小
                    pageNo: (params.offset / params.limit) + 1, //页码
                    searchKey : $("#searchKey").html(),
                    searchValue : $("#searchValue").html(),
                    searchWords : $("#search_words").val()
                    //sort: params.sort,      //排序列名
                    //sortOrder: params.order //排位命令（desc，asc）
                }
                return temp;
            },
            columns: [{
                checkbox: false,
                visible: false                  //是否显示复选框
            }, {
                field: 'id',
                title: '编号',
            },{
                field: 'serialName',
                title: '系列名称',
            }, {
                field: 'documentName',
                title: '文档名称',
            },{
                field: 'documentAuthor',
                title: '作者',
            },{
                field: 'documentYear',
                title: '出版年份',
            },{
                field: 'documentTotalPage',
                title: '页数',
            },{
                field: 'documentPrice',
                title: '定价',
            }],
            onLoadSuccess: function () {//加载绑定
            },
            onLoadError: function () {
                showTips("数据加载失败！");
            }
        });

        $("#query").click(function(){
            $("#table").bootstrapTable('refresh');
        });
    });
}