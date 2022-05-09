<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "award-create-form.ftl">

<h2>Attributions</h2>

<#if awards?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>date</th>
            <th>gommette</th>
            <th>&eacute;l&egrave;ve</th>
            <th>sa liste</th>
            <#if isAuthorized>
                <th>enseignant</th>
                <th>actions</th>
            </#if>
            <th>motif</th>
        </tr>
        <#list awards as award>
            <tr>
                <td><a href="/awards/id/${award.getId()}">${award.getId()}</a></td>
                <td>${award.getAttributionDate()}</td>
                <td>
                    <a href="/stickers/${award.getSticker().getId()}">
                        ${award.getSticker().getColor().name()} : ${award.getSticker().getDescription().name()}
                    </a>
                </td>
                <td>
                    <a href="/students/${award.getStudent().getId()}">
                        ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
                    </a>
                </td>
                <td>
                    <a href="/awards/student/${award.getStudent().getId()}">
                        <button>voir</button>
                    </a>
                </td>
                <#if isAuthorized>
                    <td><a href="/teachers/${award.getTeacher().getId()}">${award.getTeacher().getLastName()}</a></td>
                    <td>
                        <form action="/awards/delete/${award.getId()}" method="POST">
                            <button type="submit">SUPPRIMER</button>
                        </form>
                    </td>
                </#if>
                <td>"${award.getMotive()}"</td>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>