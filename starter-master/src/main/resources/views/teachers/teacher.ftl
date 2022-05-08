<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<p>
    Enseignant num&eacute;ro : ${teacher.getId()} <br>
    ${teacher.getFirstName()} ${teacher.getLastName()} (${teacher.getUserName()})
</p>

</body>

</html>
