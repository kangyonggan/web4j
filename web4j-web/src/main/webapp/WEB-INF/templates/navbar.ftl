<#assign principal_avatar>
    <@app_user property="smallAvatar" />
</#assign>

<div id="navbar" class="navbar navbar-default">
    <div class="navbar-container" id="navbar-container">

    <@shiro.user>
        <#if !no_siderbar??>
            <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                <span class="sr-only">Toggle sidebar</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>
        </#if>
    </@shiro.user>

        <div class="navbar-header pull-left">
            <a href="${ctx}/" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                <@spring.message "app.name"/>
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
        <@shiro.user>
            <ul class="nav ace-nav">
                <#--<#include "message.ftl"/>-->

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo"
                             src="<#if principal_avatar?has_content>${ctx}/${principal_avatar}<#else>${ctx}/static/ace/dist/avatars/profile-pic.jpg</#if>"
                             alt="<@app_user property="fullname" />"/>
                        <span class="user-info">
									<small>欢迎,</small>
                            <@app_user property="fullname" />
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

                        <@shiro.hasPermission name="DASHBOARD">
                            <li>
                                <a href="${ctx}/dashboard">
                                    <i class="ace-icon fa fa-dashboard"></i>
                                    工作台
                                </a>
                            </li>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="ADMIN">
                            <li>
                                <a href="${ctx}/admin">
                                    <i class="ace-icon fa fa-desktop"></i>
                                    控制台
                                </a>
                            </li>
                        </@shiro.hasPermission>

                        <li class="divider"></li>

                        <li>
                            <a href="${ctx}/logout">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </@shiro.user>
        <@shiro.guest>
            <ul class="nav navbar-nav">
                <li>
                    <a href="${ctx}/login">登录</a>
                </li>
                <li>
                    <a href="${ctx}/register">注册</a>
                </li>
            </ul>
        </@shiro.guest>
        </div>
    </div>
</div>