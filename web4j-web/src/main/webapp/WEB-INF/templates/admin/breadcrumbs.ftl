<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <a href="${ctx}/admin">控制台</a>
        </li>
    <#if ptitle??>
        <li class="active">${ptitle}</li>
    </#if>
    </ul>

    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" name="key"
                       id="nav-search-input" autocomplete="off">
                <i class="ace-icon fa fa-search nav-search-icon"></i>
            </span>
        </form>
    </div>
</div>