<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

<a href="/">&larr; Index</a>

<ul>
    <#list teachers as teacher>
        <li>${teacher.id_teacher} - ${teacher.firstName} ${teacher.lastName}</li>
    </#list>
</ul>

</body>

</html>
