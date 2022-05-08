<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "student-create-form.ftl">

<h2>&Eacute;l&egrave;ves</h2>

<#if students?has_content>
    <table>
        <tr>
            <th>ID</th>
            <th>nom</th>
            <th>pr&eacute;nom</th>
            <th>actions</th>
        </tr>
        <#list students as student>
            <tr>
                <td><a href="/students/${student.getId()}">${student.getId()}</a></td>
                <td>${student.getLastName()}</td>
                <td>${student.getFirstName()}</td>
                <td>
                    <a href="/students/${student.getId()}">
                        <button type="button">Modifier</button>
                    </a>
                    <form method="POST" action="/students/delete/${student.id}">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</#if>

</body>

</html>

