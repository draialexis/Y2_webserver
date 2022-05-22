<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.ftl">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">
<#include "../bits/status.ftl">

<h2>Enseignants</h2>

<#if teachers?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>nom</th>
            <th>pr&eacute;nom</th>
            <th>nom d'utilisateur</th>
            <th>actions</th>
        </tr>
        <#list teachers as teacher>
            <#assign uname = teacher.getUserName()>

            <tr>
                <td><a href="/hidden/teachers/${teacher.getId()}">${teacher.getId()}</a></td>
                <td>${teacher.getLastName()}</td>
                <td>${teacher.getFirstName()}</td>
                <td>${uname}</td>
                <td>
                    <#if uname != admin1 && uname != admin2>
                        <form action="/hidden/teachers/delete/${teacher.getId()}" method="POST">
                            <button type="submit">Supprimer</button>
                        </form>
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>
