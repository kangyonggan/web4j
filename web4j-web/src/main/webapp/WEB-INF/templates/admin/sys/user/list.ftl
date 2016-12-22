<#assign title="用户管理">
<#assign ptitle="系统">

<#assign deptCode = RequestParameters.deptCode!'' />
<#assign fullname = RequestParameters.fullname!'' />

<@override name="actions">
<a href="${ctx}/admin/sys/user/create" class="btn btn-sm btn-primary" data-backdrop="static" data-toggle="modal"
   data-target="#myModal">添加</a>
</@override>

<@override name="content">
<form class="form-inline items-form" method="get" novalidate>
    <div class="form-group">
        <select name="deptCode" class="form-control">
            <option value="">全部部门</option>
            <#list depts as dept>
                <option value="${dept.code}" <#if deptCode==dept.code>selected</#if>>${dept.name}</option>
            </#list>
        </select>
    </div>

    <div class="form-group">
        <input type="text" class="form-control" name="fullname" value="${fullname}" placeholder="姓名"/>
    </div>

    <button class="btn btn-sm btn-purple">
        搜索
        <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
    </button>
</form>

<div class="space-10"></div>

<table id="user-table" class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th>用户名</th>
        <th>姓名</th>
        <th>部门</th>
        <th>手机号</th>
        <th>电子邮箱</th>
        <th>是否锁定</th>
        <th>逻辑删除</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
        <#if page.list?size gt 0>
            <#list page.list as user>
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
    <@c.pagination url="${ctx}/admin/sys/user" param="deptCode=${deptCode}&fullname=${fullname}"/>
</@override>

<@override name="script">
<script src="${ctx}/static/app/js/admin/sys/user/list.js"></script>
</@override>

<@extends name="../../layout.ftl"/>