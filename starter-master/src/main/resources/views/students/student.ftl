<#ftl encoding="utf-8">

<html lang="fr">
<#include "../bits/head.html">
<body xmlns="http://www.w3.org/1999/html">
<#include "../bits/navbar.ftl">

<#include "student-update-form.ftl">

<h2>El&egrave;ve num&eacute;ro : ${student.getId()}</h2>
<p>${student.getFirstName()} ${student.getLastName()}</p>
<form action="/student/delete/${student.getId()}" method="POST">
    <button type="submit">SUPPRIMER</button>
</form>
</body>

</html>