<#assign title="部门管理">
<#assign ptitle="系统">

<@override name="actions">
<a href="${ctx}/admin/sys/dept/create" class="btn btn-sm btn-primary" data-backdrop="static" data-toggle="modal"
   data-target="#myModal">添加</a>
</@override>

<@override name="content">
<table id="dept-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>部门代码</th>
        <th>部门名称</th>
        <th>负责人</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if depts?size gt 0>
            <#list depts as dept>
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
<script src="${ctx}/static/app/js/admin/sys/dept/list.js"></script>
</@override>

<@extends name="../../layout.ftl"/>