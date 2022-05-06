<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

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
