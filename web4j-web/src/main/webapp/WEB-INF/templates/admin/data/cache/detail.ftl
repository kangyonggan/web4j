<#assign title="${key}">
<#assign ptitle="数据">

<@override name="content">
    <#if isList>
        <#list cache as obj>
        ${obj}
        <div class="space-10"></div>
        <div class="hr hr-18 dotted"></div>
        <div class="space-10"></div>
        </#list>
    <#else>
        ${cache}
    </#if>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/data/cache/detail.js"></script>
</@override>

<@extends name="../../layout.ftl"/>