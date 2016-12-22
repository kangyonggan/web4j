<#assign title="错误" />

<@override name="app-content">
<div class="main-content">
    <div class="main-content-inner">
        <div class="page-content">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-8 col-sm-offset-2">
                    <div class="error-container">
                        <div class="well">
                            <h1 class="grey lighter smaller">
                            <span class="blue bigger-125">
                                <i class="ace-icon fa fa-sitemap"></i>
                                错误
                            </span>
                            </h1>

                            <hr/>

                            <@block name = "error" />

                            <hr/>
                            <div class="space"></div>

                            <div class="center">
                                <a href="javascript:history.back()" class="btn btn-grey">
                                    <i class="ace-icon fa fa-arrow-left"></i>
                                    返回
                                </a>
                                <a href="/dashboard" class="btn btn-primary">
                                    <i class="ace-icon fa fa-tachometer"></i>
                                    工作台
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@extends name="layout.ftl"/>