<#assign title="缓存管理">
<#assign ptitle="数据">

<#assign module = RequestParameters.module!'user' />

<#if module=='user'>
    <#assign moduleName="用户"/>
<#elseif module=='menu'>
    <#assign moduleName="菜单"/>
<#elseif module=='role'>
    <#assign moduleName="角色"/>
<#elseif module=='dept'>
    <#assign moduleName="部门"/>
</#if>

<@override name="actions">
<a href="${ctx}/admin/data/cache/${module}/clearall" id="clear-module" class="btn btn-sm btn-danger">清空列表</a>
</@override>

<@override name="content">
<form class="form-inline items-form" method="get" novalidate>
    <div class="form-group">
        <select name="module" class="form-control">
            <option value="user" <#if module=='user'>selected</#if>>用户</option>
            <option value="menu" <#if module=='menu'>selected</#if>>菜单</option>
            <option value="role" <#if module=='role'>selected</#if>>角色</option>
            <option value="dept" <#if module=='dept'>selected</#if>>部门</option>
        </select>
    </div>

    <button class="btn btn-sm btn-purple">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="cache-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>缓存模块</th>
        <th>模块前缀</th>
        <th>键</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if keys?size gt 0>
            <#list keys as key>
                <#include "table-tr.ftl"/>
            </#list>
        <#else>
        <tr>
            <td colspan="20">
                <div class="empty">暂无查询记录</div>
            </td>
        </tr>
        </#if>
    </tbody>
</table>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/data/cache/list.js"></script>
</@override>

<@extends name="../../layout.ftl"/>