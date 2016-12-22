<#if user.isLocked == 1>
<a href="javascript:" data-role="user-lock" title="解锁用户"
   data-url="${ctx}/admin/sys/user/${user.id}/unlock">
    <span class="label label-danger arrowed-right">已锁定</span>
</a>
<#else>
<a href="javascript:" data-role="user-lock" title="锁定用户"
   data-url="${ctx}/admin/sys/user/${user.id}/lock">
    <span class="label label-success arrowed-right">未锁定</span>
</a>
</#if>