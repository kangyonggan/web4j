<#assign ctx="${(rca.contextPath)!''}">

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title><@spring.message "app.name"/> - ${title!''}</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/jquery.gritter.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-fonts.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace.min.css" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/static/ace/dist/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${ctx}/static/ace/dist/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${ctx}/static/ace/dist/js/html5shiv.js"></script>
    <script src="${ctx}/static/ace/dist/js/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="${ctx}/static/app/css/app.css"/>
<@block name="app-style"/>
</head>
<body class="no-skin ${bodyClass!''}">

<#include "navbar.ftl"/>

<div class="main-container" id="main-container">

<@block name="app-content"/>

<#include "footer.ftl"/>

<#include "modal.ftl">

    <a href="javascript:" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>

<script>var ctx = '${ctx}';</script>
<script src="${ctx}/static/ace/dist/js/jquery.min.js"></script>
<script src="${ctx}/static/ace/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.bootstrap.min.js"></script>
<script src="${ctx}/static/ace/dist/js/jquery.gritter.min.js"></script>
<script src="${ctx}/static/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}/static/ace/dist/js/ace-extra.min.js"></script>
<script src="${ctx}/static/ace/dist/js/ace-elements.min.js"></script>
<script src="${ctx}/static/ace/dist/js/ace.min.js"></script>
<script src="${ctx}/static/app/js/app.js"></script>
<@block name="app-script"/>
</body>
</html>
