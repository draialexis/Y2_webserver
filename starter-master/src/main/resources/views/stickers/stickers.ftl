<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#if isAuthorized>
    <#include "sticker-create-form.ftl">
</#if>

<h2>Gommettes</h2>

<#if stickers?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>couleur</th>
            <th>description</th>
            <#if isAuthorized>
                <th>action</th>
            </#if>
        </tr>
        <#list stickers as sticker>
            <tr>
                <td><a href="/stickers/${sticker.getId()}">${sticker.getId()}</a></td>
                <td>${sticker.getColor().name()}</td>
                <td>${sticker.getDescription().name()}</td>
                <#if isAuthorized>
                    <td>
                        <a href="/stickers/${sticker.getId()}">
                            <button type="button">Modifier</button>
                        </a>
                        <form method="POST" action="/hidden/stickers/delete/${sticker.id}">
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </#if>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>
