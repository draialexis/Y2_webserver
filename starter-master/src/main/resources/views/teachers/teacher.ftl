<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">


<#if teacher?has_content>
    <#assign uname = teacher.getUserName()>
    <h2>Enseignant num&eacute;ro : ${teacher.getId()}</h2>
    <p>${teacher.getFirstName()} ${teacher.getLastName()} (${uname})</p>
    <#if uname != admin1 && uname != admin2>
        <form action="/hidden/teachers/delete/${teacher.getId()}" method="POST">
            <button type="submit">Supprimer</button>
        </form>
    </#if>
</#if>

</body>

</html>
