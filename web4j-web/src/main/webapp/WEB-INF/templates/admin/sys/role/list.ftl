<#assign title="角色管理">
<#assign ptitle="系统">

<#assign code = RequestParameters.code!'' />
<#assign name = RequestParameters.name!'' />

<@override name="actions">
<a href="${ctx}/admin/sys/role/create" class="btn btn-sm btn-primary" data-backdrop="static" data-toggle="modal"
   data-target="#myModal">添加</a>
</@override>

<@override name="content">
<form class="form-inline items-form" method="get" novalidate>
    <div class="form-group">
        <input type="text" class="form-control" name="code" value="${code}" placeholder="角色编码"/>
    </div>

    <div class="form-group">
        <input type="text" class="form-control" name="name" value="${name}" placeholder="角色名称"/>
    </div>

    <button class="btn btn-sm btn-purple">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="role-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>角色编码</th>
        <th>角色名称</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if page.list?size gt 0>
            <#list page.list as role>
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
    <@c.pagination url="${ctx}/admin/sys/role" param="code=${code}&name=${name}"/>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/sys/role/list.js"></script>
</@override>

<@extends name="../../layout.ftl"/>