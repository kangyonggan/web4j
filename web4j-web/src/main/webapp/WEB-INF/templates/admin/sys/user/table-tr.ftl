<#assign ctx="${(rca.contextPath)!''}">

<tr id="user-${user.id}">
    <td>${user.username}</td>
    <td>${user.fullname}</td>
    <td>
    <#if user.deptCode?? && user.deptCode!=''>
        <a data-toggle="modal" href="${ctx}/admin/sys/dept/${user.deptCode}"
           data-target="#myModal">${user.deptName}</a>
    </#if>
    </td>
    <td>${user.mobile}</td>
    <td>${user.email}</td>
    <td><#include "locked.ftl"></td>
    <td><#include "delete.ftl"></td>
    <td><@c.relative_date datetime=user.createdTime/></td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}/admin/sys/user/${user.username}"
               data-target="#myModal">查看</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑</a>
                </li>
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/password" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">修改密码</a>
                </li>
                <li>
                    <a href="${ctx}/admin/sys/user/${user.id}/roles" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">设置角色</a>
                </li>
            </ul>
        </div>
    </td>
</tr>