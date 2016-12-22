<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">

    <#include "../sidebar.ftl"/>

<div class="main-content">
    <div class="main-content-inner">

        <#include "breadcrumbs.ftl"/>

        <div class="page-content">
            <div class="page-header">
                <h1>
                ${title!''}
                    <small class="pull-right">
                        <@block name="actions"/>
                    </small>
                </h1>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <@block name="content"/>
                </div>
            </div>
        </div>
    </div>
</div>
</@override>

<@override name="app-script">
<script src="${ctx}/static/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.validate.extends.js"></script>
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>