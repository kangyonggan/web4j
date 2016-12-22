$(function () {
    $("#ADMIN_DATA").addClass('active open');
    $("#ADMIN_DATA_CACHE").addClass('active');

    var $table = $('#cache-table');

    $table.on('click', 'a[data-role=clear-cache]', function () {
        var $trigger = $(this);
        var url = $trigger.data('url');
        var title = $trigger.attr("title");

        $.messager.confirm("提示", "确定" + title + "吗?", function () {
            $.get(url).success(function () {
                $trigger.parents("tr").remove();
                if ($table.find("tbody").find("tr").length == 0) {
                    $table.find("tbody").append("<tr> <td colspan='20'> <div class='empty'>暂无查询记录</div> </td> </tr>");
                }
                Notify.success("操作成功");
            }).error(function () {
                Notify.error("网络错误，请稍后重试");
            })
        });
    });

    $("#clear-module").click(function () {
        var href = $(this).attr("href");
        $.messager.confirm("提示", "确定清空吗?", function () {
            $.get(href).success(function () {
                Notify.success("操作成功");
                $table.find("tbody").empty().append("<tr> <td colspan='20'> <div class='empty'>暂无查询记录</div> </td> </tr>");
            }).error(function () {
                Notify.error("网络错误，请稍后重试");
            })
        });

        return false;
    });
});