<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "student-update-form.ftl">

<#if student?has_content>
    <h2>El&egrave;ve num&eacute;ro : ${student.getId()}</h2>
    <p>${student.getFirstName()} ${student.getLastName()}</p>
    <form action="/hidden/student/delete/${student.getId()}" method="POST">
        <button type="submit">Supprimer</button>
    </form>
<#else>
    <p class="info-msg">ID mal lu ou absence de la ressource</p>
</#if>

</body>

</html>