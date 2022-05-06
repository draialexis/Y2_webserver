<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/awards">&larr; Attributions</a>

<p>

    <a href="/awards/id/${award.getId()}">
        #${award.getId()} le ${award.getAttributionDate()}</a>
    <br>
    gommette <a href="/stickers/${award.getSticker().getId()}">
        ${award.getSticker().getColor().toString()}</a>
    attribu&eacute;e &agrave;
    <a href="/students/${award.getStudent().getId()}">
        ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
        (<a href="/awards/student/${award.getStudent().getId()}">liste compl&egrave;te</a>)</a>
    <#if full>
        par Pr.
        <a href="/teachers/${award.getTeacher().getId()}">
            ${award.getTeacher().getLastName()}</a>
    </#if>

</p>

<p>"${award.getMotive()}"</p>

</body>

</html>