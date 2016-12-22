<#if dept.isDeleted == 1>
<a href="javascript:" data-role="dept-delete" title="恢复部门"
   data-url="${ctx}/admin/sys/dept/${dept.id}/undelete">
    <span class="label label-danger arrowed-in">已删除</span>
</a>
<#else>
<a href="javascript:" data-role="dept-delete" title="删除部门"
   data-url="${ctx}/admin/sys/dept/${dept.id}/delete">
    <span class="label label-success arrowed-in">未删除</span>
</a>
</#if>