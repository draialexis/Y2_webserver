<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<ul>
    <#list teachers as teacher>
        <li><a href="/teachers/id/${teacher.getId()}">${teacher.getFirstName()} ${teacher.getLastName()}</a></li>
    </#list>
</ul>

</body>

</html>
