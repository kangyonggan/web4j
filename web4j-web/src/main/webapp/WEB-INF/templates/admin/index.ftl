<#assign title="控制台">

<@override name="content">
<div class="alert alert-block alert-success">
    <button type="button" class="close" data-dismiss="alert">
        <i class="ace-icon fa fa-times"></i>
    </button>

    <i class="ace-icon fa fa-check green"></i>

    欢迎进入<strong class="green"><@spring.message "app.name"/></strong>控制台。请点击左边菜单开始工作。👈

</div>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/index.js"></script>
</@override>

<@extends name="layout.ftl"/>