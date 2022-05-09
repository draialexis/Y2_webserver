<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<h2>Enseignants</h2>
<#--TODO add ?has_content checks everywhere -->
<#if teachers?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>nom</th>
            <th>pr&eacute;nom</th>
            <th>nom d'utilisateur</th>
        </tr>
        <#list teachers as teacher>
            <tr>
                <td><a href="/hidden/teachers/${teacher.getId()}">${teacher.getId()}</a></td>
                <td>${teacher.getLastName()}</td>
                <td>${teacher.getFirstName()}</td>
                <td>${teacher.getUserName()}</td>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>
