<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/awards">&larr; Attributions</a>

<p>

    Attribution num&eacute;ro ${award.getId()}
    <br>
    le ${award.getAttributionDate()}
    <br>
    Gommette <a href="/stickers/${award.getSticker().getId()}">
        ${award.getSticker().getColor().toString()}</a>
    attribu&eacute;e &agrave;
    <a href="/students/${award.getStudent().getId()}">
        ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
    </a>(<a href="/awards/student/${award.getStudent().getId()}">voir sa liste compl&egrave;te</a>)
    <#if isAuthorized>
        par Pr.<a href="/teachers/${award.getTeacher().getId()}">${award.getTeacher().getLastName()}</a>
    </#if>

</p>

<p>motif : "${award.getMotive()}"</p>

</body>

</html>