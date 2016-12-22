<#assign bodyClass="login-layout light-login">

<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">
<div class="row">
    <div class="col-lg-4 col-md-3 col-sm-3 hidden-xs">
        <@block name="content-left"/>
    </div>

    <div class="col-lg-4 col-md-6 col-sm-6 col-xs-10 col-sm-offset-0 col-xs-offset-1">
        <@block name="content"/>
    </div>

    <div class="col-lg-4 col-md-3 col-sm-3 hidden-xs">
        <@block name="content-right"/>
    </div>
</div>
</@override>

<@override name="app-script">
<script src="${ctx}/static/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.validate.extends.js"></script>
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>
