<#assign title="菜单管理" />
<#assign ptitle="系统">

<@override name="style">
<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>
</@override>

<@override name="content">
<div class="col-sm-6">
    <div class="widget-box widget-color-green2">
        <div class="widget-header">
            <h4 class="widget-title lighter smaller">工作台菜单</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="dashboard_tree" class="ztree"></div>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-6">
    <div class="widget-box widget-color-blue2">
        <div class="widget-header">
            <h4 class="widget-title lighter smaller">控制台菜单</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="admin_tree" class="ztree"></div>
            </div>
        </div>
    </div>
</div>
<form id="menus-form-delete" method="post" action="${ctx}/admin/sys/menu/delete">
    <input type="hidden" name="id"/>
</form>
</@override>

<@override name="script">
<script>
    var dashboard_zNodes = [
        <#list all_dashboard_menus as menu>
            {id:${menu.id}, pId:${menu.pid}, code:"${menu.code}", name: "${menu.name}", open: true},
        </#list>];
    var admin_zNodes = [
        <#list all_admin_menus as menu>
            {id:${menu.id}, pId:${menu.pid}, code:"${menu.code}", name: "${menu.name}", open: true},
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
<script src="${ctx}/static/app/js/admin/sys/menu/index.js"></script>
</@override>

<@extends name="../../layout.ftl"/>
