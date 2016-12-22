<#assign title="个人资料">
<#assign ptitle="我的">

<@override name="content">

<form id="form" action="${ctx}/dashboard/user/profile" method="post" enctype="multipart/form-data"
      class="form-horizontal">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">用户名</label>
        <div class="col-xs-12 col-sm-5">
            <input type="text" name="username" value="${user.username!''}" class="form-control readonly" readonly/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">所属的组</label>
        <div class="col-xs-12 col-sm-5">
            <#if user.deptCode!=''>
                <a class="dept-tag" href="${ctx}/admin/sys/dept/${user.deptCode}" data-toggle="modal"
                   data-target="#myModal">
                    <span class="badge badge-purple">${user.deptName}</span>
                </a>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">姓名<span class="red">*</span></label>
        <div class="col-xs-12 col-sm-5">
            <@spring.formInput "user.fullname" 'class="form-control" placeholder="2至4个汉字"'/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">手机号</label>
        <div class="col-xs-12 col-sm-5">
            <@spring.formInput "user.mobile" 'class="form-control"'/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">电子邮箱</label>
        <div class="col-xs-12 col-sm-5">
            <@spring.formInput "user.email" 'class="form-control"'/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="title">当前头像</label>

        <div class="col-xs-12 col-sm-5">
            <span class="profile-picture">
                <img class="editable img-responsive"
                     src="<#if user.largeAvatar?has_content>${ctx}/${user.largeAvatar}<#else>${ctx}/static/ace/dist/avatars/profile-pic.jpg</#if>"></img>
            </span>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="title">新头像</label>

        <div class="col-xs-12 col-sm-5">
            <input type="file" name="avatar" id="avatar">
            <div class="help-block">
                请上传 png、gif、jpg 格式的图片文件，文件大小不能超过2MB。<br>
                建议上传一张 124*124 像素或等比例的图片。
            </div>
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
<script src="${ctx}/static/app/js/dashboard/user/profile.js"></script>
</@override>

<@extends name="../layout.ftl"/>