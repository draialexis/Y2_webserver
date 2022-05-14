<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

<#if isAuthorized>
    <#include "sticker-update-form.ftl">
</#if>

<#if sticker?has_content>
    <h2>Gommette num&eacute;ro : ${sticker.getId()}</h2>
    <p>${sticker.getColor().name()} ; ${sticker.getDescription().name()}</p>
    <#if isAuthorized>
        <form action="/hidden/stickers/delete/${sticker.getId()}" method="POST">
            <button type="submit">Supprimer</button>
        </form>
    </#if>
</#if>

</body>

</html>
