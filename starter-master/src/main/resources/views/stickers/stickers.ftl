<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "sticker-create-form.ftl">

<h2>Gommettes</h2>

<#if stickers?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>couleur</th>
            <th>description</th>
            <th>action</th>
        </tr>
        <#list stickers as sticker>
            <tr>
                <td><a href="/stickers/${sticker.getId()}">${sticker.getId()}</a></td>
                <td>${sticker.getColor().toString()}</td>
                <td>${sticker.getDescription().toString()}</td>
                <td>
                    <a href="/stickers/${sticker.getId()}">
                        <button type="button">Modifier</button>
                    </a>
                    <form method="POST" action="/stickers/delete/${sticker.id}">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>
