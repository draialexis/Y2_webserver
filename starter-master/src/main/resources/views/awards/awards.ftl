<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#if isAuthorized>
    <#include "award-create-form.ftl">
</#if>

<#if awards?has_content>
    <h2>
        Attributions
        <#if isByStudent>
            de ${awards[0].getStudent().getFirstName()} ${awards[0].getStudent().getLastName()}
        </#if>
    </h2>
    <table>
        <tr>
            <th>ID</th>
            <th>date</th>
            <th>gommette</th>
            <th>&eacute;l&egrave;ve</th>
            <#if !isByStudent>
                <th>sa liste</th>
            </#if>
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
                    <#if isAuthorized><a href="/hidden/students/${award.getStudent().getId()}"></#if>
                        ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
                        <#if isAuthorized></a></#if>
                </td>
                <#if !isByStudent>
                    <td>
                        <a href="/awards/student/${award.getStudent().getId()}">
                            <button>Voir</button>
                        </a>
                    </td>
                </#if>
                <#if isAuthorized>
                    <td>
                        <a href="/hidden/teachers/${award.getTeacher().getId()}">
                            ${award.getTeacher().getLastName()}
                        </a>
                    </td>
                    <td>
                        <form action="/hidden/awards/delete/${award.getId()}" method="POST">
                            <button type="submit">Supprimer</button>
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