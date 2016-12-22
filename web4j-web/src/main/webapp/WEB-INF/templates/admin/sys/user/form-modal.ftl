<#assign modal_title="${user.id???string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
<form class="form-horizontal" role="form" id="modal-form" method="post"
      action="${ctx}/admin/sys/user/${user.id???string('update', 'save')}">
    <#if user.id??>
        <input type="hidden" name="id" value="${user.id}"/>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>用户名<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "user.username" 'class="form-control" placeholder="5至20位以字母开头的小写字母和数字的组合"'/>
                <input type="hidden" id="old-username" value="${user.username!''}"/>
            </div>
        </div>
    </div>
    <#if !user.id??>
        <div class="row">
            <div class="row form-group">
                <div class="col-md-3 control-label">
                    <label>密码<span class="red">*</span></label>
                </div>
                <div class="col-md-7 controls">
                    <input type="text" name="password" class="form-control" placeholder="6至20位的字母数字组合"/>
                </div>
            </div>
        </div>
    </#if>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>姓名<span class="red">*</span></label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "user.fullname" 'class="form-control" placeholder="2至4个汉字"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>所属部门</label>
            </div>
            <div class="col-md-7 controls">
                <select name="deptCode" class="form-control">
                    <option value="">暂时不选</option>
                    <#list depts as dept>
                        <option value="${dept.code}"
                                <#if user.id?? && user.deptCode==dept.code>selected</#if>>${dept.name}</option>
                    </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>手机号</label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "user.mobile" 'class="form-control"'/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>电子邮箱</label>
            </div>
            <div class="col-md-7 controls">
                <@spring.formInput "user.email" 'class="form-control"'/>
            </div>
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

<script src="${ctx}/static/app/js/admin/sys/user/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>