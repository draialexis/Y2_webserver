<#ftl encoding="utf-8">
<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<#if isAuthorized>
    <fieldset>
        <legend>attribuer une gommette</legend>
        <form method="POST" action="/awards">
            <#-- todo improve: drop down menu with existing students and stickers-->
            <label for="student-id">&eacute;l&egrave;ve</label>
            <br>
            <input type=number id="student-id" name="student-id" required>
            <br>
            <label for="sticker-id">gommette</label>
            <br>
            <input type="number" id="sticker-id" name="sticker-id" required>
            <br>

            <label for="motive">motif</label>
            <br>
            <textarea name="motive" id="motive" cols="30" rows="10"></textarea>
            <button type="submit">Enregistrer</button>
        </form>
    </fieldset>
</#if>

<ul>
    <#list awards as award>
        <li>
            <a href="/awards/id/${award.getId()}">
                #${award.getId()} le ${award.getAttributionDate()}</a>
            <br>
            gommette <a href="/stickers/${award.getSticker().getId()}">
                ${award.getSticker().getColor().toString()}</a>
            attribu&eacute;e &agrave;
            <a href="/students/${award.getStudent().getId()}">
                ${award.getStudent().getFirstName()} ${award.getStudent().getLastName()}
                (<a href="/awards/student/${award.getStudent().getId()}">liste compl&egrave;te</a>)</a>
            <#if isAuthorized>
                par Pr.
                <a href="/teachers/${award.getTeacher().getId()}">
                    ${award.getTeacher().getLastName()}</a>
            </#if>
        </li>
        <p>"${award.getMotive()}"</p>
        <br>
    </#list>
</ul>

</body>

</html>