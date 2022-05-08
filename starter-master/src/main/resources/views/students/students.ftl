<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<ul>
    <#list students as student>
        <li>
            <a href="/students/${student.getId()}">
                ${student.getId()} - ${student.getFirstName()} ${student.getLastName()}
            </a>
        </li>
    </#list>
</ul>

</body>

</html>
