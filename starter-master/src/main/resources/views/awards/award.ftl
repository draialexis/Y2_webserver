<#ftl encoding="utf-8">
<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#if award?has_content>
    <h2>Attribution num&eacute;ro ${award.getId()}</h2>
    <div>
        le ${award.getAttributionDate()}
        <br>
        Gommette <a href="/stickers/${award.getSticker().getId()}">
            ${award.getSticker().getColor().name()}</a>
        attribu&eacute;e &agrave;
        <a href="/hidden/students/${award.getStudent().getId()}">
            ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
        </a>
        <#if isAuthorized>
            par Pr.<a href="/hidden/teachers/${award.getTeacher().getId()}">${award.getTeacher().getLastName()}</a>
            <br>
            <form action="/hidden/awards/delete/${award.getId()}" method="POST">
                <button type="submit">SUPPRIMER</button>
            </form>
        </#if>
    </div>
    <p>motif : "${award.getMotive()}"</p>
</#if>
</body>

</html>