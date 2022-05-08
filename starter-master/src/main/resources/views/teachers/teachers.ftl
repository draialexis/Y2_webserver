<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<ul>
    <#list teachers as teacher>
        <li>
            <a href="/teachers/${teacher.getId()}">
                ${teacher.getId()} - ${teacher.getFirstName()} ${teacher.getLastName()}
            </a>
        </li>
    </#list>
</ul>

</body>

</html>
