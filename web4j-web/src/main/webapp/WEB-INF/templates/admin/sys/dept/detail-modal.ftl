<#assign modal_title="部门详情" />

<@override name="modal-body">
<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
    <tbody>
    <tr>
        <th width="25%">ID</th>
        <td width="75%">${dept.id}</td>
    </tr>
    <tr>
        <th>部门代码</th>
        <td>${dept.code}</td>
    </tr>
    <tr>
        <th>部门名称</th>
        <td>${dept.name}</td>
    </tr>
    <tr>
        <th>负责人</th>
        <td>${dept.deptHeaderFullname}</td>
    </tr>
    <tr>
        <th>逻辑删除</th>
        <td>${(dept.isDeleted==1)?string('是', '否')}</td>
    </tr>
    <tr>
        <th>创建时间</th>
        <td><#if dept.createdTime??>${dept.createdTime?datetime}</#if></td>
    </tr>
    <tr>
        <th>更新时间</th>
        <td><#if dept.updatedTime??>${dept.updatedTime?datetime}</#if></td>
    </tr>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
<button class="btn btn-sm" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    <@spring.message "app.button.close"/>
</button>
</@override>

<@extends name="../../../modal-layout.ftl"/>