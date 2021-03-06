<#assign modal_title="${role.id???string('编辑角色', '添加新角色')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/admin/sys/role/${role.id???string('update', 'save')}">
    <#if role.id??>
        <input type="hidden" name="id" value="${role.id}"/>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>角色编码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "role.code" 'class="form-control" placeholder="格式参考:ROLE_ADMIN"'/>
                <input type="hidden" id="old-code" value="${role.code!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>角色名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "role.name" 'class="form-control" placeholder="角色名称:2至12个汉字"'/>
            </div>
        </div>
    </div>
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

    <script src="${ctx}/static/app/js/admin/sys/role/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>