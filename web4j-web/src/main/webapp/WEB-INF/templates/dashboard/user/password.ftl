<#assign title="修改密码">
<#assign ptitle="我的">

<@override name="content">
<form action="${ctx}/dashboard/user/password" id="form" method="post" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">新密码<span class="red">*</span></label>
        <div class="col-xs-12 col-sm-5">
            <input type="text" name="password" class="form-control" placeholder="密码:6至20位的字母数字组合"/>
        </div>
    </div>

    <div class="clearfix form-actions">
        <div class="center">
            <input type="submit" class="btn btn-info width-10" data-toggle="form-submit" data-loading-text="正在提交..."
                   value="<@spring.message "app.button.save"/>"/>
        </div>
    </div>
</form>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/dashboard/user/password.js"></script>
</@override>

<@extends name="../layout.ftl"/>