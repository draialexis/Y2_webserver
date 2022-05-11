<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

<#if award?has_content>
    <h2>Attribution num&eacute;ro ${award.getId()}</h2>
    <div>
        le ${award.getAttributionDate()}
        <br>
        Gommette <a href="/stickers/${award.getSticker().getId()}">
            ${award.getSticker().getColor().name()}</a>
        attribu&eacute;e &agrave;
        <#if isAuthorized><a href="/hidden/students/${award.getStudent().getId()}"></#if>
            ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
            <#if isAuthorized></a></#if>
        <#if isAuthorized>
            par Pr.<a href="/hidden/teachers/${award.getTeacher().getId()}">${award.getTeacher().getLastName()}</a>
            <br>
            <form action="/hidden/awards/delete/${award.getId()}" method="POST">
                <button type="submit">Supprimer</button>
            </form>
        </#if>
    </div>
    <p>motif : "${award.getMotive()}"</p>
</#if>
</body>

</html>