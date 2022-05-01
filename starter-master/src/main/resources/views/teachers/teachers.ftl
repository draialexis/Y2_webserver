<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

<ul>
    <#list teachers as teacher>
        <li>${teacher.id_teacher} - ${teacher.firstName} ${teacher.lastName} (${teacher.userName})</li>
    </#list>
</ul>

</body>

</html>
