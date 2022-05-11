<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

<#if teacher?has_content>
    <h2>Enseignant num&eacute;ro : ${teacher.getId()}</h2>
    <p>${teacher.getFirstName()} ${teacher.getLastName()} (${teacher.getUserName()})</p>
</#if>

</body>

</html>
