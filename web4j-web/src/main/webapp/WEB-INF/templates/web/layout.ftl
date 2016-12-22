<#assign no_siderbar="1"/>

<@override name="app-style">
    <@block name="style"/>
</@override>

<@override name="app-content">
<div class="space-20"></div>

<div class="clear row">
    <@block name="content"/>
</div>

<div class="space-20"></div>
</@override>

<@override name="app-script">
    <@block name="script"/>
</@override>

<@extends name="../layout.ftl"/>
