<#assign ctx="${(rca.contextPath)!''}">

<tr id="dept-${dept.id}">
    <td>${dept.code}</td>
    <td>${dept.name}</td>
    <td>
    <#if dept.deptHeaderUsername!=''>
        <a data-toggle="modal" href="${ctx}/admin/sys/user/${dept.deptHeaderUsername}"
           data-target="#myModal">${dept.deptHeaderFullname}</a>
    </#if>
    </td>
    <td><#include "delete.ftl"></td>
    <td><@c.relative_date datetime=dept.createdTime/></td>
    <td>
        <div class="btn-group">
            <a data-toggle="modal" class="btn btn-xs btn-inverse" href="${ctx}/admin/sys/dept/${dept.code}"
               data-target="#myModal">查看</a>

            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <li>
                    <a href="${ctx}/admin/sys/dept/${dept.id}/edit" data-toggle="modal" data-target="#myModal"
                       data-backdrop="static">编辑</a>
                </li>
            </ul>
        </div>
    </td>
</tr>