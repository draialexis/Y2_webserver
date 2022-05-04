<#ftl encoding="utf-8">

<html lang="fr">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<ul>
    <#list pupils as pupil>
        <li><a href="/pupils/${pupil.id}">${pupil.id} - ${pupil.firstName} ${pupil.lastName}</a></li>
    </#list>
</ul>

</body>

</html>
