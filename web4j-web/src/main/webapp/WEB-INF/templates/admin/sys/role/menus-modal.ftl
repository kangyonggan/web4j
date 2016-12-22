<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置角色菜单" />
<#assign action="admin/sys/role/${roleId}/menus" />

<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/admin/sys/role/${roleId}/menus">
    <input type="hidden" name="menus"/>
    <input type="hidden" name="type" value="${type}"/>
    <div class="control-group">
        <div>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</form>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@spring.message "app.button.cancel"/>
</button>

<button class="btn btn-sm btn-primary" id="user-btn"
        data-loading-text="正在保存..." data-toggle="form-submit" data-target="#modal-form">
    <i class="ace-icon fa fa-check"></i>
    <@spring.message "app.button.save"/>
</button>

<script>
    var zNodes = [
        <#list all_menus as menu>
            {
                id:${menu.id},
                pId:${menu.pid},
                name: "${menu.name}",
                code: "${menu.code}",
                open: true
            ${(role_menus?? && role_menus?seq_contains(menu.code))?string(", checked:true", "")}
            },
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/static/app/js/admin/sys/role/menus-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>