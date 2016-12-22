<#assign modal_title="${dept.id???string('编辑部门', '添加新部门')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/admin/sys/dept/${dept.id???string('update', 'save')}">
    <#if dept.id??>
        <input type="hidden" name="id" value="${dept.id}"/>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>部门代码<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "dept.code" 'class="form-control" placeholder="格式参考:be"'/>
                <input type="hidden" id="old-code" value="${dept.code!''}"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>部门名称<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "dept.name" 'class="form-control" placeholder="部门名称:2至12个汉字"'/>
            </div>
        </div>
    </div>
    <#if dept.id??>

        <div class="row">
            <div class="row form-group">
                <div class="col-md-3 control-label">
                    <label>负责人</label>
                </div>
                <div class="col-md-7 controls">
                    <select name="deptHeaderUsername" class="form-control">
                        <option value="">暂时不选</option>
                        <#list users as user>
                            <option value="${user.username}"
                                    <#if dept.deptHeaderUsername==user.username>selected</#if>
                            >${user.fullname}</option>
                        </#list>
                    </select>
                </div>
            </div>
        </div>
    </#if>
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

<script src="${ctx}/static/app/js/admin/sys/dept/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>